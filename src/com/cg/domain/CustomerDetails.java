package com.cg.domain;

import java.util.Date;

public class CustomerDetails {


	private long orderId;
	private String productId;
	private String productName;
	private double productCost;
	private String shippingStatus;
	private Date deliveryDate;
	private String userId;
	private long transactionId;
	private long productQuantity;
	private String returnStatus;
	private long returnstatusId;
	public long getOrderId() {
		return orderId;
	}
	public void setOrderId(long orderId) {
		this.orderId = orderId;
	}
	public String getProductId() {
		return productId;
	}
	public void setProductId(String productId) {
		this.productId = productId;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public double getProductCost() {
		return productCost;
	}
	public void setProductCost(double productCost) {
		this.productCost = productCost;
	}
	public String getShippingStatus() {
		return shippingStatus;
	}
	public void setShippingStatus(String shippingStatus) {
		this.shippingStatus = shippingStatus;
	}
	public Date getDeliveryDate() {
		return deliveryDate;
	}
	public void setDeliveryDate(Date deliveryDate) {
		this.deliveryDate = deliveryDate;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public long getTransactionId() {
		return transactionId;
	}
	public void setTransactionId(long transactionId) {
		this.transactionId = transactionId;
	}
	public long getProductQuantity() {
		return productQuantity;
	}
	public void setProductQuantity(long productQuantity) {
		this.productQuantity = productQuantity;
	}
	public String getReturnStatus() {
		return returnStatus;
	}
	public void setReturnStatus(String returnStatus) {
		this.returnStatus = returnStatus;
	}
	public long getReturnstatusId() {
		return returnstatusId;
	}
	public void setReturnstatusId(long returnstatusId) {
		this.returnstatusId = returnstatusId;
	}
	
	
	public CustomerDetails(long orderId, String productId, String productName,
			double productCost, String shippingStatus, Date deliveryDate,
			String userId, long transactionId, long productQuantity,
			String returnStatus, long returnstatusId) {
		super();
		this.orderId = orderId;
		this.productId = productId;
		this.productName = productName;
		this.productCost = productCost;
		this.shippingStatus = shippingStatus;
		this.deliveryDate = deliveryDate;
		this.userId = userId;
		this.transactionId = transactionId;
		this.productQuantity = productQuantity;
		this.returnStatus = returnStatus;
		this.returnstatusId = returnstatusId;
	}
	public CustomerDetails() {
		super();
		// TODO Auto-generated constructor stub
	}

	
}
