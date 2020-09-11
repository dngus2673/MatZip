package com.koreait.matzip.restaurant;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.koreait.matzip.CommonDAO;
import com.koreait.matzip.CommonUtils;
import com.koreait.matzip.Const;
import com.koreait.matzip.SecurityUtils;
import com.koreait.matzip.ViewRef;
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
		double lng = CommonUtils.getDoubleParameter("lng",request);
		int cd_category = CommonUtils.parseStringToInt("cd_category");
		
		UserVO loginUser = SecurityUtils.getLoginUser(request);

		
		RestaurantVO param = new RestaurantVO();
		
		param.setI_user(loginUser.getI_user());
		param.setNm(nm);
		param.setAddr(addr);
		param.setCd_category(cd_category);
		param.setLat(lat);
		param.setLng(lng);

		
		int result = restService.restReg(param);
		
		return "redirect:/restaurant/restMap";
	}
	
	public String ajaxGetList(HttpServletRequest request) {
		return "ajax:" + restService.getRestList();
	}
}
