package com.cg.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.cg.domain.Returneditem;
@Repository
public interface IDaoReturneditem extends JpaRepository<Returneditem,Long>{
	 @Transactional
	 @Modifying
	 @Query("update Returneditem rti set rti.returnStatus ='Approved' where rti.returnstatusId = ?1")
	 public void setReturnedApprove(long returnstatusId);
	 
	 @Transactional
	 @Modifying
	 @Query("update Returneditem rti set rti.returnStatus ='Rejected' where rti.returnstatusId = ?1")
	 public void setReturnedReject(long returnstatusId);
}
