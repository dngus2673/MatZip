package com.koreait.matzip.restaurant;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.koreait.matzip.db.JdbcSelectInterface;
import com.koreait.matzip.db.JdbcTemplate;
import com.koreait.matzip.db.JdbcUpdateInterface;
import com.koreait.matzip.vo.RestaurantDomain;
import com.koreait.matzip.vo.RestaurantVO;

public class RestaurantDAO {
	
	public int inRestaurant (RestaurantVO param) {

		String sql = " INSERT INTO t_restaurant "
				+ " (i_user, nm, addr, lat, lng, cd_category) "
				+ " VALUES"
				+ " (?, ?, ?, ?, ?, ?) ";
		
		return JdbcTemplate.executeUpdate(sql, new JdbcUpdateInterface() {

			@Override
			public void update(PreparedStatement ps) throws SQLException {
				ps.setInt(1, param.getI_user());
				ps.setNString(2, param.getNm());
				ps.setNString(3, param.getAddr());
				ps.setDouble(4, param.getLat());
				ps.setDouble(5, param.getLng());
				ps.setInt(6, param.getCd_category());
			}
			
		});
	}
	public List<RestaurantDomain> selRestList(){
		List<RestaurantDomain> list = new ArrayList();
		
		String sql = " SELECT i_rest, nm, lat, lng FROM t_restaurant ";
		
		JdbcTemplate.executeQuery(sql, new JdbcSelectInterface() {

			@Override
			public void prepared(PreparedStatement ps) throws SQLException {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void executeQuery(ResultSet rs) throws SQLException {
				while(rs.next()) {
					RestaurantDomain dvo = new RestaurantDomain();
					dvo.setI_rest(rs.getInt("i_rest"));
					dvo.setNm(rs.getNString("nm"));
					dvo.setLat(rs.getDouble("lat"));
					dvo.setLng(rs.getDouble("lng"));
					list.add(dvo);
				}
			}
			
		});
		return list;
	}
}
