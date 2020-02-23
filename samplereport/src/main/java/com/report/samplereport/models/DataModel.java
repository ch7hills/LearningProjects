package com.report.samplereport.models;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
public class DataModel implements Serializable {
	private static final long serialVersionUID = -339733988220756828L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String ExternalTransactionId;
	private String ClientId;
	private String SecurityId;
	private String TransactionType;
	private String TransactionDate;
	private Double MarketValue;
	private String PriorityFlag;
	private Double processingCharges;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getExternalTransactionId() {
		return ExternalTransactionId;
	}
	public void setExternalTransactionId(String externalTransactionId) {
		ExternalTransactionId = externalTransactionId;
	}
	public String getClientId() {
		return ClientId;
	}
	public void setClientId(String clientId) {
		ClientId = clientId;
	}
	public String getSecurityId() {
		return SecurityId;
	}
	public void setSecurityId(String securityId) {
		SecurityId = securityId;
	}
	public String getTransactionType() {
		return TransactionType;
	}
	public void setTransactionType(String transactionType) {
		TransactionType = transactionType;
	}
	public String getTransactionDate() {
		return TransactionDate;
	}
	public void setTransactionDate(String transactionDate) {
		TransactionDate = transactionDate;
	}
	public Double getMarketValue() {
		return MarketValue;
	}
	public void setMarketValue(Double d) {
		MarketValue = d;
	}
	public String getPriorityFlag() {
		return PriorityFlag;
	}
	public void setPriorityFlag(String priorityFlag) {
		PriorityFlag = priorityFlag;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public Double getProcessingCharges() {
		return processingCharges;
	}
	public void setProcessingCharges(Double processingCharges) {
		this.processingCharges = processingCharges;
	}
	
	public DataModel(Long id, String externalTransactionId, String clientId, String securityId, String transactionType,
			String transactionDate, Double marketValue, String priorityFlag, Double processingCharges) {
		super();
		this.id = id;
		ExternalTransactionId = externalTransactionId;
		ClientId = clientId;
		SecurityId = securityId;
		TransactionType = transactionType;
		TransactionDate = transactionDate;
		MarketValue = marketValue;
		PriorityFlag = priorityFlag;
		this.processingCharges = processingCharges;
	}
	public DataModel() {
		
	}

}
