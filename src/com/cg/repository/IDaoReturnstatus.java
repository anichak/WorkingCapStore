package com.cg.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.cg.domain.Returnstatus;
@Repository
public interface IDaoReturnstatus extends JpaRepository<Returnstatus,Long>{
	@Transactional
    @Query("select r from Returnstatus r where r.returnstatusId=?1")
	public Returnstatus findById(long id);
    
    @Transactional
	@Modifying
	@Query("update Returnstatus r set r.returnStatus ='Approved' where r.returnstatusId = ?1")
	public void setRStatusApprove(long returnstatusId);
    
    @Transactional
	@Modifying
	@Query("update Returnstatus r set r.returnStatus ='Rejected' where r.returnstatusId = ?1")
	public void setRStatusReject(long returnstatusId);
}
