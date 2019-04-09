package com.example.myMusic.music.dao;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.example.myMusic.music.entities.Music;



public interface MusicDao extends PagingAndSortingRepository<Music, Long>//分页和排序
,JpaSpecificationExecutor<Music>//动态查询{
{

}
