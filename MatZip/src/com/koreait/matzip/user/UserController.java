package com.koreait.matzip.user;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.koreait.matzip.Const;
import com.koreait.matzip.SecurityUtils;
import com.koreait.matzip.ViewRef;
import com.koreait.matzip.vo.UserVO;
	// 넣고 담고 맵핑의 역할
public class UserController {
	private UserService service;
	
	public UserController() {
		service = new UserService();
	}
	// -> /user/login
	public String login(HttpServletRequest request) {
		
		String error = request.getParameter("error");
		
		if(error != null){
			switch(error) {
			case "2":
				request.setAttribute("msg", "아이디를 확인해 주세요");
				break;
			case "3":
				request.setAttribute("msg", "비밀번호를 확인해 주세요.");
				break;
			}
		}
		
		request.setAttribute(Const.TITLE, "login"); // 타이틀 명
		request.setAttribute(Const.VIEW, "user/login");
		return ViewRef.TEMP_DEFAULT; // 템플릿을 열 때
	}
	// 로그인 처리
	public String loginProc(HttpServletRequest request) {
		
		String user_id = request.getParameter("user_id");
		String user_pw = request.getParameter("user_pw");
		// 객체의 주소값이 들어감.
		UserVO param = new UserVO();
		
		param.setUser_id(user_id);
		param.setUser_pw(user_pw);
		
		int result = service.login(param);
		
		if(result == 1) { //로그인 성공
			// 회원 정보 주소 값을 세션에 전달 
			HttpSession hs = request.getSession();
			hs.setAttribute(Const.LOGIN_USER, param);
			
			return "redirect:/restaurant/restMap";
		}else {
			return "redirect:/user/login?user_id=" + user_id + "&error=" + result;
		}
	}
	
	public String join(HttpServletRequest request) {
		request.setAttribute(Const.TITLE, "join");
		request.setAttribute(Const.VIEW, "user/join");
		return ViewRef.TEMP_DEFAULT;
	}
	
	public String joinProc(HttpServletRequest request) {
		
		String user_id = request.getParameter("user_id");
		String user_pw = request.getParameter("user_pw");
		String nm = request.getParameter("nm");
		
		UserVO param = new UserVO();
		
		param.setUser_id(user_id);
		param.setUser_pw(user_pw);
		param.setNm(nm);
		
		int result = service.join(param);
		
		return "redirect:/user/login";
	}
	
	public String ajaxIdChk(HttpServletRequest request) {
		String user_id = request.getParameter("user_id");

		UserVO param = new UserVO();
		param.setUser_id(user_id);
		param.setUser_pw("");
		int result = service.login(param);
		
		return String.format("ajax:{\"result\": %s}", result);
	}
	
	public String logout(HttpServletRequest request) {
		HttpSession hs = request.getSession();
		hs.invalidate();
		return "redirect:/user/login";
	}
}
