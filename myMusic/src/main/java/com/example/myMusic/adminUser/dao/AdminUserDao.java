package com.example.myMusic.adminUser.dao;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;


import com.example.myMusic.adminUser.entities.AdminUser;

public interface AdminUserDao extends PagingAndSortingRepository<AdminUser, Long>//分页和排序
,JpaSpecificationExecutor<AdminUser>//动态查询
{

}
