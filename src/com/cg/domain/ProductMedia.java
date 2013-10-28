package com.cg.domain;

public class ProductMedia {
	
	@Override
	public String toString() {
		return "ProductMedia [p=" + p + ", m=" + m + "]";
	}
	private Product p;
	public ProductMedia(Product p, Media m) {
		super();
		this.p = p;
		this.m = m;
	}
	public ProductMedia() {
		super();
		// TODO Auto-generated constructor stub
	}
	private Media m;
	
	public Product getP() {
		return p;
	}
	public void setP(Product p) {
		this.p = p;
	}
	public Media getM() {
		return m;
	}
	public void setM(Media m) {
		this.m = m;
	}
	
	
	
}
