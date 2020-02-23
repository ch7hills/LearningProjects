package com.report.samplereport.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.report.samplereport.jdbc.Models.JdbcDAO;
import com.report.samplereport.models.DataModel;
import com.report.samplereport.repository.DataModelRepository;

@Component
public class DataService {
	private static Logger log = LoggerFactory.getLogger(DataService.class);
	@Autowired
	DataModelRepository dataRepository;
	@Autowired
	public JdbcDAO dao;
	
	public List<DataModel> caliculateProcessingCharges() {
		List<Map<String, Object>> intradayTransactionResult = dao.intraDayTransactionIds();
		String intradayTransaction ="";
		String glue="";
		List<Long> intrayIds = new ArrayList<Long>();
		for(Map<String, Object> record: intradayTransactionResult){
			intrayIds.add(Long.parseLong(record.get("id").toString()));
			intradayTransaction = intradayTransaction +glue+record.get("id").toString();
			glue=",";
		}
		caliculateInraDayClientMoney(intrayIds);
		List<Map<String, Object>> normalTransactionResult = dao.normalTransactionIds(intradayTransaction);
		caliculateNormalClientMoney(normalTransactionResult);
		return dataRepository.findAll();
	}
	
	public void caliculateNormalClientMoney(List<Map<String, Object>> normalTransactionResult) {
		
		for(Map<String, Object> record: normalTransactionResult) {
			Optional<DataModel> Optionaldm = dataRepository.findById(Long.parseLong(record.get("id").toString()));
			if(Optionaldm != null) {
				DataModel dm = Optionaldm.get();
				if(dm.getPriorityFlag().equals("Y")){
					dm.setProcessingCharges(Double.parseDouble("500"));
					
				}else if(dm.getPriorityFlag().equals("N") && (dm.getTransactionType().equals("SELL") ||  dm.getTransactionType().equals("WITHDRAW"))) {
					dm.setProcessingCharges(Double.parseDouble("100"));
				}else if(dm.getPriorityFlag().equals("N") && (dm.getTransactionType().equals("BUY") ||  dm.getTransactionType().equals("DEPOSIT"))) {
					dm.setProcessingCharges(Double.parseDouble("50"));
				}
				dataRepository.save(dm);
			}
		}
	}
	
	public void  caliculateInraDayClientMoney(List<Long> intrayIds ) {
			
		for(Long id: intrayIds) {
			Optional<DataModel> Optionaldm = dataRepository.findById(id);
			if(Optionaldm != null) {
				DataModel dm = Optionaldm.get();
				dm.setProcessingCharges(Double.parseDouble("10"));
				dataRepository.save(dm);
			}
		}
	}
	
}
