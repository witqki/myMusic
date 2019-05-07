package com.example.myMusic.songList.dao;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;


import com.example.myMusic.songList.entities.SongList;

public interface SongListDao extends PagingAndSortingRepository<SongList, Long>//分页和排序
,JpaSpecificationExecutor<SongList>{

}
