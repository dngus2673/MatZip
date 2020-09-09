package com.koreait.matzip.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
//인터페이스는 부모역할만 하는 것
//인터페이스 강제성 있음
//인터페이스 사이에는 상속 가능
//추상 클래스는 객체 생성 안됨
//static, abstract 인터페이스에서 생략되어도 자동으로 사용
public interface JdbcUpdateInterface {
	void update(PreparedStatement ps) throws SQLException;
}
