package com.demo.drools.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class AppLogDao {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	
	@Transactional
	public int addLogRecord(String text){
		return jdbcTemplate.update("INSERT INTO APPLICATION_LOG (LOG_TEXT) VALUES (?)", text);					
	}
	

}
