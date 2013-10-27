package com.cg.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.List;

import com.cg.domain.ProductSold;

public class CartPersistence extends Thread{
	
	private String userId;
	private List<ProductSold> itemsList;
	public CartPersistence(String userId, List<ProductSold> items) {
		super();
		this.userId = userId;
		this.itemsList = items;
	}


	@Override
	public void run() {
		try
		{
			for(ProductSold item: itemsList)
			{
				FileOutputStream fileOut=new FileOutputStream(new File("C:\\Users\\tpatnala\\Cap Store\\tmp\\" + userId + ".ser"));
				ObjectOutputStream out=new ObjectOutputStream(fileOut);
				out.writeObject(item);
				out.close();
				fileOut.close();
			}
		}
		catch(IOException i){
			i.printStackTrace();	
		}
	}

	
}
