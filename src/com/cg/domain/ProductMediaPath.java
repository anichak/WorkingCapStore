package com.cg.domain;

public class ProductMediaPath {
	
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	public ProductMediaPath(Product product, Media media) {
		super();
		this.product = product;
		this.media = media;
	}
	public Media getMedia() {
		return media;
	}
	public void setMedia(Media media) {
		this.media = media;
	}
	private Product product;
	private Media media;
}
