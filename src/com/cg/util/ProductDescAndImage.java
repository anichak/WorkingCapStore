package com.cg.util;

import java.util.List;

import com.cg.domain.ProductDescription;

public class ProductDescAndImage {
	List<ProductDescription> proddescription;
	String image;
	public List<ProductDescription> getProddescription() {
		return proddescription;
	}
	public void setProddescription(List<ProductDescription> proddescription) {
		this.proddescription = proddescription;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
}
