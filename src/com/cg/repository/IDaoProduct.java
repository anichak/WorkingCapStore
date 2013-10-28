package com.cg.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.cg.domain.Category;
import com.cg.domain.Media;
import com.cg.domain.Merchant;
import com.cg.domain.Product;
import com.cg.domain.ProductDescription;
import com.cg.domain.ProductWish;
import com.cg.domain.Scheme;
@Repository
public interface IDaoProduct extends JpaRepository<Product,String>{

	@Transactional
	@Query("select p from Product p where p.merchant.merchantId=?1")
	public List<Product> getById(String id);
	@Transactional
	@Query("select p.productId from Product p where p.productName=?1 and p.merchant=?2")
	public String getByproductId(String name,Merchant mer);
	@Transactional
	@Query("select p from Product p where p.merchant=?1")
	public List<Product> getByproductId(Merchant mer);
	@Transactional
	@Query("select p from Product p ")
	List<Product> getAllProducts();
	@Transactional
	@Query("select  u from Product u  group by u.productBrand   ")
	 public List<Product> sortMeByproduct_brand(); 
	@Transactional
	@Query("select distinct u from Product u")
	List<Product> getInitialBrands();
	@Transactional
	@Query("select s from Scheme s where s.product=?1")
	public List<Scheme> getByentryId(Product p);
	@Transactional
	@Query("select m from Media m where m.product=?1  ")
	public Media  getByMediaId(Product product);
	@Transactional
	@Query("select p from Product p,Wishlist w where p.productId=w.product.productId and w.wishEnddate=?1")
	public List<Product> getByproductId(Date d);
	/*Praveen*/
	@Transactional
	@Query("select p from Product p where p.productId=(:x1)")
	Product findProduct(@Param("x1") String schemeName);
	
	@Transactional
	@Query("select p from Product p where p.productId=?1")
	Product findOne(String product);
	@Transactional
	 @Modifying
	 @Query("update Product p set p.productStock=p.productStock+1 where p.productId = ?1")
	 public void updateInventory(String productId);
	@Transactional
	@Query("select p.category from Product p where p.productId=?1")
	Category findCategoryId(String prodId);

	@Transactional
	List<Product> findByCategory(Category category);

	@Transactional
	Product findByProductId(String productId);

	@Transactional
	List<ProductDescription> findProductdescriptionByProductId(String prodId);
	
	
}
