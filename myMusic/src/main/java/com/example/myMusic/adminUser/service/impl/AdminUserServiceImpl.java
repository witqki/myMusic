package com.example.myMusic.adminUser.service.impl;

import java.io.File;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.myMusic.adminUser.service.AdminUserService;
import com.example.myMusic.common.util.BeanUtil;
import com.example.myMusic.common.web.ExtAjaxResponse;
import com.example.myMusic.music.dao.MusicDao;
import com.example.myMusic.music.entities.Music;
@Service
@Transactional
public class AdminUserServiceImpl implements AdminUserService{
	@Autowired
    private MusicDao musicDao=null;
	
	
	@Override
	public ExtAjaxResponse importsong(String path) {
		// TODO Auto-generated method stub
		ExtAjaxResponse extAjaxResponse=new ExtAjaxResponse();
		if(path==null||path.length()==0||path.trim().equals("")) {//传入为空
			extAjaxResponse.setMsg("传入的音乐地址为空！");
			extAjaxResponse.setSuccess(false);
			return extAjaxResponse;
		}else {
			File file=new File(path);
			if(file.exists()) {//如果地址存在
				if(file.isFile()) {//如果是文件
					//假如是.flac文件
					if(BeanUtil.isNeedFile(file.getAbsolutePath(), 1)) {
						
						 Music music=BeanUtil.readMp3(file.getAbsolutePath());
						 List<Music> list=musicDao.exitMusicpath(music.getPath());
						 if(list.size()!=0&&list!=null){
							 extAjaxResponse.setMsg("文件已存在！");
							 extAjaxResponse.setSuccess(false);
						 }else {
							 musicDao.save(music);
							 extAjaxResponse.setMsg("success！");
							 extAjaxResponse.setSuccess(true);
						 }
						
					}
					else if(BeanUtil.isNeedFile(file.getAbsolutePath(), 2)) {
						Music music=BeanUtil.readflac(file.getAbsolutePath());
						 List<Music> list=musicDao.exitMusicpath(music.getPath());
						 if(list.size()!=0&&list!=null){
							 extAjaxResponse.setMsg("文件已存在！");
							 extAjaxResponse.setSuccess(false);
						 }else {
							 musicDao.save(music);
							 extAjaxResponse.setMsg("success！");
							 extAjaxResponse.setSuccess(true);
						 }
					}
					else {
						extAjaxResponse.setMsg("选择的文件不是所要求的音频文件类型！");
						extAjaxResponse.setSuccess(false);
					}
					
				}if(file.isDirectory()) {//如果是文件夹
					List<String> allfile=BeanUtil.readAllfile(file.getAbsolutePath());
					if(allfile!=null&&allfile.size()!=0) {
						 List<String> filter=BeanUtil.filter(allfile);
						 if(filter!=null&&filter.size()!=0) {
							 //进行信息导入
							 for(String str:filter) {
								 if(BeanUtil.isNeedFile(str, 1)) {//是MP3文件
									 Music music=BeanUtil.readMp3(str);
									 List<Music> list=musicDao.exitMusicpath(music.getPath());
									 if(list!=null&&list.size()!=0) {
										 //存在则不做任何事
									 }else {									
										 musicDao.save(music);
									 }
								 }
								 else if(BeanUtil.isNeedFile(str, 2)) {//是flac文件
									 Music music=BeanUtil.readflac(str);
									 List<Music> list=musicDao.exitMusicpath(music.getPath());
									 if(list!=null&&list.size()!=0) {
										 //存在则不做任何事
									 }else {
										 
										 musicDao.save(music);
									 }
								 }else {
									    extAjaxResponse.setMsg("信息导入有误！");
										extAjaxResponse.setSuccess(false);
										return extAjaxResponse;
								 }
								 extAjaxResponse.setMsg("信息导入完成！");
									extAjaxResponse.setSuccess(true);
							 }
						 }else {
							    extAjaxResponse.setMsg("文件夹无对应格式的音频文件！");
								extAjaxResponse.setSuccess(false);
						 }
					}else {
						extAjaxResponse.setMsg("文件夹为空！");
						extAjaxResponse.setSuccess(false);
					}
					
				}
				return extAjaxResponse;
			}
			else {
				extAjaxResponse.setMsg("传入的音乐地址不存在！"+path);
				extAjaxResponse.setSuccess(false);
				return extAjaxResponse;
			}
		}
	
	}
}
