package com.cg.domain;

import java.util.List;

import com.cg.domain.Order;
import com.cg.domain.Shipping;
import com.cg.domain.Transaction;

public class JoinOrdersTransaction {
	private Order orders;
	private List<Transaction> transactions;
   private List<Shipping> shipping;
	public Order getOrders() {
			return orders;
		}

   public List<Transaction> getTransactions() {
	return transactions;
}

public List<Shipping> getShipping() {
	return shipping;
}

public void setOrders(Order orders) {
	this.orders = orders;
}






  public void setShipping(List<Shipping> shipping) {
	this.shipping = shipping;
}

public void setTransactions(List<Transaction> transactions) {
	this.transactions = transactions;
}
@Override
public String toString() {
	return "JoinOrdersTransaction [orders=" + orders + ", transactions="
			+ transactions + ", shipping=" + shipping + "]";
}

}
