package com.example.myMusic.music.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import com.example.myMusic.music.entities.Music;



public interface MusicDao extends PagingAndSortingRepository<Music, Long>//分页和排序
,JpaSpecificationExecutor<Music>//动态查询{
{
	//暂时只查询歌名、歌手、专辑、主题
		@Query(value="from  Music s where s.name like %:Content% or s.singer like %:Content% or s.album like %:Content% or s.theme like %:Content% ")
		 List<Music> searchContent(@Param("Content") String searchContent);
		//检测歌曲地址是否存在
		@Query(value="from  Music s where s.path like %:path% ")
		 List<Music> exitMusicpath(@Param("path") String path);
}
