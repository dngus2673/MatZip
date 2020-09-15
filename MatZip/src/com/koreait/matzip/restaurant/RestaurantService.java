package com.koreait.matzip.restaurant;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import com.google.gson.Gson;
import com.koreait.matzip.CommonUtils;
import com.koreait.matzip.FileUtils;
import com.koreait.matzip.vo.RestaurantDomain;
import com.koreait.matzip.vo.RestaurantRecommendMenuVO;
import com.koreait.matzip.vo.RestaurantVO;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

public class RestaurantService {
	
	private RestaurantDAO restdao;
	
	public RestaurantService() {
		restdao = new RestaurantDAO();
	}
	
	public int restReg(RestaurantVO param) {
		return restdao.inRestaurant(param);
	}
	public String getRestList() {
		List<RestaurantDomain> list = restdao.selRestList();
		Gson gson = new Gson();
		return gson.toJson(list);
	}
	public RestaurantDomain getRest(RestaurantVO param) {
		return restdao.selRestaurant(param);
	}
	
	public int addRecMenus(HttpServletRequest request) {
		
		String savePath = request.getServletContext().getRealPath("/res/img/restaurant"); // 상대 주소 지정
		String tempPath = savePath + "/temp"; // 위치의 절대주소값을 줌	, \는 윈도우에서만 가능	
		FileUtils.makeFolder(tempPath);
		
		int maxFileSize = 10_485_760; //1024 * 1024 * 10 (10mb) //최대 파일 사이즈 크기
		MultipartRequest multi = null;
		int i_rest = 0;
		String[] menu_nmArr = null;
		String[] menu_priceArr = null;
		List<RestaurantRecommendMenuVO> list = null;
		try {
			multi = new MultipartRequest(request, tempPath, maxFileSize, "UTF-8", new DefaultFileRenamePolicy());
			//객체화 후 request해야 String으로 받아짐
			i_rest = CommonUtils.getIntParameter("i_rest", multi); 
			
			System.out.println("i_rest : " + i_rest);
			menu_nmArr = multi.getParameterValues("menu_nm");
			menu_priceArr = multi.getParameterValues("menu_price");
						 
			if(menu_nmArr == null || menu_priceArr == null) {
				return i_rest;
			}
			
			list = new ArrayList();
			for(int i=0; i<menu_nmArr.length; i++) {
				RestaurantRecommendMenuVO vo = new RestaurantRecommendMenuVO();
				vo.setI_rest(i_rest);
				vo.setMenu_nm(menu_nmArr[i]);
				vo.setMenu_price(CommonUtils.parseStringToInt(menu_priceArr[i]));
				list.add(vo);
			}	
			
			String targetPath = savePath + "/" + i_rest;
			FileUtils.makeFolder(targetPath);
			
			String originFileNm = "";
			
			Enumeration files = multi.getFileNames();
			while(files.hasMoreElements()) {		
				String key = (String)files.nextElement(); //다음에 이동할 곳을 가르키면서  key값을 줌
				System.out.println("key : " + key);
				originFileNm = multi.getFilesystemName(key);
				System.out.println("fileNm : " + originFileNm);
				
				if(originFileNm != null) { // file 선택을 안했으면 null이 넘어옴
					String ext = FileUtils.getExt(originFileNm);
					String saveFileNm = UUID.randomUUID() + ext;
					
					System.out.println("saveFileNm : " + saveFileNm);				
					File oldFile = new File(tempPath + "/" + originFileNm);
				    File newFile = new File(targetPath + "/" + saveFileNm);
				    oldFile.renameTo(newFile);	
				    
				    int idx = CommonUtils.parseStringToInt(key.substring(key.lastIndexOf("_") + 1));				    
				    RestaurantRecommendMenuVO vo = list.get(idx);
				    vo.setMenu_pic(saveFileNm);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		if(list != null) {
			for(RestaurantRecommendMenuVO vo : list) {
				restdao.insRecommendMenu(vo);
			}	
		}
		
		return i_rest;
	}
	public List<RestaurantRecommendMenuVO> getRecommendMenuList(int i_rest){
		return restdao.selRecommendMenuList(i_rest);
	}
	
	public int delRecMenu(RestaurantRecommendMenuVO param) {
		return restdao.delRecommendMenu(param);
	}
}
