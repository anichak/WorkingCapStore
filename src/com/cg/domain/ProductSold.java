package com.cg.domain;

import java.io.Serializable;

public class ProductSold implements Serializable{
		
	private String product_id;
	private String product_name;
	private String user_id;
	private int qty;
	private double price;
	private double actual_price;
	private String scheme_id;
	public String getProduct_id() {
		return product_id;
	}
	public void setProduct_id(String product_id) {
		this.product_id = product_id;
	}
	public String getProduct_name() {
		return product_name;
	}
	public void setProduct_name(String product_name) {
		this.product_name = product_name;
	}
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public int getQty() {
		return qty;
	}
	public void setQty(int qty) {
		this.qty = qty;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public double getActual_price() {
		return actual_price;
	}
	public void setActual_price(double actual_price) {
		this.actual_price = actual_price;
	}
	public String getScheme_id() {
		return scheme_id;
	}
	public void setScheme_id(String scheme_id) {
		this.scheme_id = scheme_id;
	}
	
	
	@Override
	public String toString() {
		return "ProductSold [product_id=" + product_id + ", product_name="
				+ product_name + ", user_id=" + user_id + ", qty=" + qty
				+ ", price=" + price + ", actual_price=" + actual_price
				+ ", scheme_id=" + scheme_id + "]";
	}
	
}
