package com.example.myMusic.adminUser.dao;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;


import com.example.myMusic.adminUser.entities.AdminUser;

public interface AdminUserDao extends PagingAndSortingRepository<AdminUser, Long>//分页和排序
,JpaSpecificationExecutor<AdminUser>//动态查询
{
	@Query("from AdminUser s where s.name = ?1 AND s.password = ?2")
	AdminUser findByUsernameAndPassword(String username, String password);
}
