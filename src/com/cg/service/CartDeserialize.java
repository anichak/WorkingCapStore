package com.cg.service;

import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.List;
import com.cg.domain.ProductSold;

public class CartDeserialize extends Thread {

	private String userId;
	private List<ProductSold> itemsList;
	public CartDeserialize(String userId) {
		super();
		this.userId = userId;
	}


	@Override
	public void run() {
		ProductSold item = null;
		try
		{
			FileInputStream itemsFile = new FileInputStream("C:\\Users\\tpatnala\\Cap Store\\tmp\\" + userId + ".ser");
			ObjectInputStream ois = new ObjectInputStream(itemsFile);
			item = (ProductSold) ois.readObject();
			while(item != null)
			{
				itemsList.add(item);
				try{
					item = (ProductSold) ois.readObject();
				}
				catch(Exception e){}			
			}
		}
		catch(Exception i){
			i.printStackTrace();	
		}
	}

	public List<ProductSold> getItemsList(){
		return itemsList;
	}
}
