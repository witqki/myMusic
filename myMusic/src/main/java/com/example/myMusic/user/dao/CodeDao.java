package com.example.myMusic.user.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import com.example.myMusic.user.entities.Code;





public interface CodeDao extends PagingAndSortingRepository<Code, Long>//分页和排序
                            ,JpaSpecificationExecutor<Code>//动态查询
{

	@Query(value="from  Code s where s.phone like %:phone% and s.code like %:code% ")
	 List<Code> exitPhone(@Param("phone") String phone,@Param("code") String code);
	
	@Query(value="from  Code s where s.email like %:email% and s.code like %:code% ")
	 List<Code> exitEmail(@Param("email") String email,@Param("code") String code);
}
