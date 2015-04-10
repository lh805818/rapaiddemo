package com.company.project.model;

import java.io.Serializable;
import java.util.Date;

public class TradePack implements Serializable{

	private String appId;
	private String sign;
	private String myFlowNo;
	private String tradeNo;
	private Date tradeTime;
	public String getAppId() {
		return appId;
	}
	public void setAppId(String appId) {
		this.appId = appId;
	}
	public String getSign() {
		return sign;
	}
	public void setSign(String sign) {
		this.sign = sign;
	}
	public String getMyFlowNo() {
		return myFlowNo;
	}
	public void setMyFlowNo(String myFlowNo) {
		this.myFlowNo = myFlowNo;
	}
	public String getTradeNo() {
		return tradeNo;
	}
	public void setTradeNo(String tradeNo) {
		this.tradeNo = tradeNo;
	}
	
	/**
	 * @return the tradeTime
	 */
	public Date getTradeTime() {
		return tradeTime;
	}
	/**
	 * @param tradeTime the tradeTime to set
	 */
	public void setTradeTime(Date tradeTime) {
		this.tradeTime = tradeTime;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return super.toString();
	}
	
	
}
