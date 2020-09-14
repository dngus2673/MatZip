package com.koreait.matzip.restaurant;

import java.util.List;

import com.google.gson.Gson;
import com.koreait.matzip.vo.RestaurantDomain;
import com.koreait.matzip.vo.RestaurantVO;

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
}
