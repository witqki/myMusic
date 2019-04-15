package com.example.myMusic.discuss.dao;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.example.myMusic.discuss.entities.Discuss;



public interface DiscussDao extends PagingAndSortingRepository<Discuss, Long>//分页和排序
,JpaSpecificationExecutor<Discuss>//动态查询
{

}
