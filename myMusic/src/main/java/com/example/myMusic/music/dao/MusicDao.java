package com.example.myMusic.music.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import com.example.myMusic.common.dto.music.SearchMusicDTO;
import com.example.myMusic.music.entities.Music;




public interface MusicDao extends PagingAndSortingRepository<Music, Long>//分页和排序
,JpaSpecificationExecutor<Music>//动态查询{
{
	//暂时只查询歌名、歌手、专辑、主题
//		@Query(value="from  Music s where s.name like %:Content% or s.singer like %:Content% or s.album like %:Content% or s.theme like %:Content% ")
//		 List<Music> searchContent(@Param("Content") String searchContent);
//		//检测歌曲地址是否存在
		@Query(value="from  Music s where s.trueid like %:path% ")
		 List<Music> exitMusic(@Param("path") Long path);
//		//找出播放量最大的歌曲
//		@Query(value="select * from Music m ORDER BY m.amount_play deSC limit 20", nativeQuery = true)
//		 List<Music> hotMusic();
//		//找出发行日期最早的歌曲
//		@Query(value="select * from Music m ORDER BY m.date deSC limit 20", nativeQuery = true)
//		 List<Music> newMusic();
//		
//		//歌手是否存在
//		@Query(value = "select new com.example.myMusic.common.dto.music.SearchMusicDTO(b.id,b.name, b.singer, b.time,b.picturepath,b.amount_play) "
//				+ "from Singer a, Music b where a.location = :location and"
//				+ " a.name = b.singer"
//				)
//		 List<SearchMusicDTO> findMusic(@Param("location") String location);
	
		
}
