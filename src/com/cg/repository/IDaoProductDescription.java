package com.cg.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.cg.domain.Product;
import com.cg.domain.ProductDescription;
@Repository
public interface IDaoProductDescription extends JpaRepository<ProductDescription,Long>{

    @Transactional 
	@Query("select s from ProductDescription s where s.product.productId= ?1 ")
	List<ProductDescription> getByProductId(String prod_id);

@Transactional	
	List<ProductDescription> findAttributeNameAndAtrributeValueByProduct(Product selectedProduct);
}
