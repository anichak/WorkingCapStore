package com.cg.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.cg.domain.CustomerDetails;
import com.cg.domain.Transaction;
@Repository
public interface IDaoTransaction extends JpaRepository<Transaction,Long>{
	@Transactional
	@Query("select t from Transaction t")
	List<Transaction> getAllTransactions();
	@Transactional
	@Query("select new com.cg.domain.CustomerDetails(t.order.orderId,t.product.productId,t.productName,t.productCost,s.shippingStatus,s.deliveryDate,s.user.userId,t.transactionId,t.productQuantity,r.returnStatus,r.returnstatusId) from Transaction t,Shipping s,Returnstatus r, Product p where t.product.productId=p.productId and t.order.orderId=s.order.orderId and t.order.orderId=r.order.orderId and p.productId=r.product.productId and t.order.user.userId=?1 ")
	public List<CustomerDetails> getCustomerDetails(String userId);
	@Transactional
	@Query("select t from Transaction t where t.order.orderId= ?1")
	public List<Transaction> findByOrder_id(long order_id);
}
