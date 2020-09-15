package com.koreait.matzip.restaurant;



import javax.servlet.http.HttpServletRequest;

import com.koreait.matzip.CommonDAO;
import com.koreait.matzip.CommonUtils;
import com.koreait.matzip.Const;
import com.koreait.matzip.SecurityUtils;
import com.koreait.matzip.ViewRef;
import com.koreait.matzip.vo.RestaurantRecommendMenuVO;
import com.koreait.matzip.vo.RestaurantVO;
import com.koreait.matzip.vo.UserVO;


public class RestaurantController {
	
	private RestaurantService restService;
	
	public RestaurantController() {
		restService = new RestaurantService();
	}
	
	public String restMap(HttpServletRequest request) {
		request.setAttribute(Const.TITLE, "지도보기");
		request.setAttribute(Const.VIEW, "restaurant/restMap");
		return ViewRef.TEMP_MENU_TEMP;
	}
	public String restReg(HttpServletRequest request) {
		final int I_M = 1; //카테고리 코드
		request.setAttribute("categoryList", CommonDAO.selCodeList(I_M));
		
		request.setAttribute(Const.TITLE, "가게 등록하기");
		request.setAttribute(Const.VIEW, "restaurant/restReg");
		return ViewRef.TEMP_MENU_TEMP;
	}
	
	public String restRegProc(HttpServletRequest request) {
		
		String nm = request.getParameter("nm");
		String addr = request.getParameter("addr");
		double lat = CommonUtils.getDoubleParameter("lat", request);
		double lng = CommonUtils.getDoubleParameter("lng", request);
		int cd_category = CommonUtils.getIntParameter("cd_category", request);
		
		UserVO loginUser = SecurityUtils.getLoginUser(request);

		
		RestaurantVO param = new RestaurantVO();
		
		param.setNm(nm);
		param.setAddr(addr);
		param.setLat(lat);
		param.setLng(lng);
		param.setI_user(loginUser.getI_user());
		param.setCd_category(cd_category);

		
		int result = restService.restReg(param);
		
		return "redirect:/restaurant/restMap";
	}
	
	public String ajaxGetList(HttpServletRequest request) {
		return "ajax:" + restService.getRestList();
	}
	
	public String resDetail(HttpServletRequest request) {
		int i_rest = CommonUtils.getIntParameter("i_rest", request);
		
		RestaurantVO param = new RestaurantVO();
		
		param.setI_rest(i_rest);
		
		request.setAttribute("css", new String[] {"restaurant"});
		
		request.setAttribute("recommendMenuList", restService.getRecommendMenuList(i_rest));
		request.setAttribute("data", restService.getRest(param));
		request.setAttribute(Const.TITLE, "Detail");
		request.setAttribute(Const.VIEW, "restaurant/restDetail");
		return ViewRef.TEMP_MENU_TEMP;
	}
	public String addRecMenus(HttpServletRequest request) {
		
		int i_rest = restService.addRecMenus(request);
		
		return "redirect:/restaurant/restDetail?i_rest=" + i_rest;
	}
	
	 public String ajaxDelRecMenu(HttpServletRequest request) {
		 int i_rest = CommonUtils.getIntParameter("i_rest", request);
		 int seq = CommonUtils.getIntParameter("seq", request);
		 
		 RestaurantRecommendMenuVO param = new RestaurantRecommendMenuVO();
		 param.setI_rest(i_rest);
		 param.setSeq(seq);
		 
		 int result = restService.delRecMenu(param);
		 
		 return "ajax : " + result;
	 }
}
