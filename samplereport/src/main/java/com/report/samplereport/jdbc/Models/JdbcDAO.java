package com.report.samplereport.jdbc.Models;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
@Component
public class JdbcDAO {
	
	@Autowired
	JdbcTemplate jdbcTemplate;
	
	public List<Map<String, Object>> intraDayTransactionIds() {
 		String intradayTransactionSQL = "select m1.id from data_model m1, data_model m2 where m1.client_id=m2.client_id and m1.security_id=m2.security_id and m1.transaction_date=m2.transaction_date and (m1.transaction_type='BUY' and m2.transaction_type='SELL'  or m1.transaction_type='SELL' and m2.transaction_type='BUY' )";
		List<Map<String, Object>> intradayTransactionResult = jdbcTemplate.queryForList(intradayTransactionSQL);
		return intradayTransactionResult;
 	}
 	
 	public List<Map<String, Object>> normalTransactionIds(String intradayTransaction) {
 		String normalTransactionSQL = "select * from data_model where id not in("+intradayTransaction+")";
		List<Map<String, Object>> normalTransactionResult = jdbcTemplate.queryForList(normalTransactionSQL);
		return normalTransactionResult;
 	}
 	
 	public List<Map<String, Object>> getReportData() {
 		String normalTransactionSQL = "select client_id,transaction_type,transaction_date,priority_flag,processing_charges  from data_model group by client_id,transaction_type,transaction_date";
		List<Map<String, Object>> normalTransactionResult = jdbcTemplate.queryForList(normalTransactionSQL);
		return normalTransactionResult;
 	}
}
