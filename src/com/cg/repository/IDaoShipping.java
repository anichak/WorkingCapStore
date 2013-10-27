package com.cg.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.cg.domain.Shipping;
@Repository
public interface IDaoShipping extends JpaRepository<Shipping,Integer>{
	@Transactional
	@Query("select s from Shipping s where s.order.orderId= ?1")
	public List<Shipping> findByOrder_id(long order_id);
}
