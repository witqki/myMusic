package com.example.myMusic.reply.dao;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.example.myMusic.reply.entities.Reply;



public interface ReplyDao extends PagingAndSortingRepository<Reply, Long>//分页和排序
,JpaSpecificationExecutor<Reply>//动态查询
{

}
