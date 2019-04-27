package com.example.myMusic.common.util;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Random;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.apache.http.HttpResponse;
import org.jaudiotagger.audio.AudioFile;
import org.jaudiotagger.audio.AudioHeader;
import org.jaudiotagger.audio.exceptions.CannotReadException;
import org.jaudiotagger.audio.exceptions.InvalidAudioFrameException;
import org.jaudiotagger.audio.exceptions.ReadOnlyFileException;
import org.jaudiotagger.audio.flac.FlacFileReader;
import org.jaudiotagger.audio.mp3.MP3AudioHeader;
import org.jaudiotagger.audio.mp3.MP3File;
import org.jaudiotagger.tag.FieldKey;
import org.jaudiotagger.tag.TagException;
import org.jaudiotagger.tag.flac.FlacTag;

import com.example.myMusic.common.dto.UserregisteredDTO;
import com.example.myMusic.common.dto.discuss.DiscussDTO;
import com.example.myMusic.common.dto.mix_discuss_reply.IdDTO;
import com.example.myMusic.common.dto.reply.ReplyDTO;
import com.example.myMusic.common.web.ExtAjaxResponse;
import com.example.myMusic.discuss.entities.Discuss;
import com.example.myMusic.music.entities.Music;
import com.example.myMusic.reply.entities.Reply;
import com.example.myMusic.user.entities.User;
import com.sun.mail.util.MailSSLSocketFactory;

public class BeanUtil {
	//private String url="http://39.96.172.186:8080/";//外网
	private static String rootUrl="G:/apache-tomcat-9.0.19-windows-x64/apache-tomcat-9.0.19/webapps/ROOT/";
	private static String url="http://localhost:8080/";//本地
	private static long limittime=60*1000;//一分钟的毫秒
	private static int pagesize=10;
	private static int codenumber=6;
	public static long getLimittime() {
		return limittime;
	}
	public static String getUrl() {
		return url;
	}
	public static String getRootUrl() {
		return rootUrl;
	}
    public static User UserregisteredDTOtoUser(UserregisteredDTO userregisteredDTO,User user) {
    	user.setName(userregisteredDTO.getName());
    	user.setPassword(userregisteredDTO.getPassword());
    	user.setSex(userregisteredDTO.getSex());
    	user.setPhone(userregisteredDTO.getPhone());
    	user.setEmail(userregisteredDTO.getEmail());
    	return user;
    }
    public static void discussTodiscussDTO(Discuss discuss,DiscussDTO discussdto) {
    	discussdto.setId(discuss.getId());
    	discussdto.setContent(discuss.getContent());
    	discussdto.setLikernumber(discuss.getLikernumber());
    }
    public static void replyToreplyDTO(Reply reply,ReplyDTO replydto) {
    	replydto.setId(reply.getId());
    	replydto.setContent(reply.getContent());
    	replydto.setLikernumber(reply.getLikernumber());
    }
    public static List fenye(List list,int page){
    	if(page<=0)
    		page=1;
        int totalcount=list.size();//总数据
        int pagecount=0;//页面总数
        int m=totalcount%pagesize;
        //获取页面总数
        if  (m>0){
            pagecount=totalcount/pagesize+1;
        }else{
            pagecount=totalcount/pagesize;
        }
        if(page>pagecount)
        	page=pagecount;
        //如果只有一页
        if (m==0){
            List subList= list.subList(0,totalcount);
            return subList;
        }
        else{
        	 if (page==pagecount){//如果取最后一页
                 List subList= list.subList(pagesize*(page-1),totalcount);
                 return subList;
             }else{
                 List subList= list.subList((page-1)*pagesize,pagesize*(page));
                 return subList;
             }
        }
       
    }
    public static int  pageTotal(List list) {
    	 int totalcount=list.size();//总数据
    	 int pagecount=0;//页面总数
         int m=totalcount%pagesize;
         //获取页面总数
         if  (m>0){
             pagecount=totalcount/pagesize+1;
         }else{
             pagecount=totalcount/pagesize;
         }
		return pagecount;
    	
    }
    public static int nowpage(List list,int page) {
    	if(page<=0)
    		page=1;
        int totalcount=list.size();//总数据
        int pagecount=0;//页面总数
        int m=totalcount%pagesize;
        //获取页面总数
        if  (m>0){
            pagecount=totalcount/pagesize+1;
        }else{
            pagecount=totalcount/pagesize;
        }
        if(page>pagecount)
        	page=pagecount;
        return page;
    }
    
   public static List<IdDTO> sortlist(List<IdDTO> iddto) {
	 
	   Collections.sort(iddto, new Comparator<IdDTO>() {//降序排序，按里面的Date排
           @Override
           public int compare(IdDTO o1, IdDTO o2) {
               int flag = o1.getDate().compareTo(o2.getDate());
               if(flag == -1){//
                   flag = 1;
               }else if(flag == 1){
                   flag = -1;
               }//把这段删除就成升序排序
               return flag;
           }
       });
	   return iddto;
   }
   //将地址的\换成/
   public static String leftTorigth(String left) {
	   if(left==null||left.isEmpty()) {
		   return null;
	   }
	   else {
		   File file=new File(left);
		   if(file.exists()) {
			   String str=file.getAbsolutePath().replace(file.getName(), "");
			   String tmp=str.replace("\\", "/");
			 return   tmp+file.getName();
		   }else {
			   return "null";
		   }
	   }
   }
   //读取flac音频信息  //需要确认传入的地址一定有用才行
   public static Music readflac(String flac) {
	   Music music=new Music();
	   try {
		   music.setCreateTime(new Date());
		   music.setUpdateTime(new Date());
		   File file=new File(flac);
		   String str= leftTorigth(file.getAbsolutePath());
		   if(str!=null) {
		   music.setPath(str);
		   }
		   FlacFileReader read=new FlacFileReader();
		   AudioFile audioFile=read.read(file);
		   AudioHeader audioHeader=audioFile.getAudioHeader();//时长在这里
		   FlacTag flacTag=(FlacTag) audioFile.getTag();

		   music.setTime(audioHeader.getTrackLength());//时长
		   if(flacTag.hasField("TITLE")) {
			   music.setName(flacTag.getFirst(FieldKey.TITLE));
		   }else {
			   music.setName(file.getName().substring(0, file.getName().lastIndexOf(".")));
		   }
		 //  System.out.println(flacTag.getFirst(FieldKey.TITLE));//歌名
		   if(flacTag.hasField("ARTIST")) {
			   music.setSinger(flacTag.getFirst(FieldKey.ARTIST));
		   }else {
			   music.setSinger("未知");
		   }
	//	    System.out.println( flacTag.getFirst(FieldKey.ARTIST));//歌手
		   if(flacTag.hasField("ALBUM")) {
			   music.setAlbum(flacTag.getFirst(FieldKey.ALBUM));
		   }else {
			   music.setAlbum("未知");
		   }
		//    System.out.println(flacTag.getFirst(FieldKey.ALBUM));//专辑
		   if(flacTag.hasField("DATE")) {
			   music.setDate(flacTag.getFirst("DATE"));
		   }else {
			   music.setDate("未知");
		   }
		   // System.out.println(flacTag.getFirst("DATE"));//发行日期
		   if(flacTag.hasField("GENRE")) {
			   music.setGenre(flacTag.getFirst(FieldKey.GENRE));
		   }else {
			   music.setGenre("未知");
		   }
		   if(flacTag.hasField("LANGUAGE")) {
			   music.setLanguage(flacTag.getFirst(FieldKey.LANGUAGE));
		   }else {
			   music.setLanguage("未知");
		   }
		   if(flacTag.hasField("REGION")) {
			   music.setRegion(flacTag.getFirst("REGION"));
		   }else {
			   music.setRegion("未知");
		   }
		   if(flacTag.hasField("THEME")) {
			   music.setTheme(flacTag.getFirst("THEME"));
		   }else {
			   music.setTheme("未知");
		   }
		//    System.out.println(flacTag.getFirst(FieldKey.GENRE));//类型
//		    System.out.println("乐队"+flacTag.getFirst("BAND"));//乐队
//		    System.out.println( flacTag.getFirst("ALBUMARTIST"));//专辑艺术家
//		    System.out.println(flacTag.getFirst(FieldKey.COMPOSER));//作曲家
	   }catch(Exception e) {
		   e.printStackTrace();
	   }
	   music.setCreateTime(new Date());
	   music.setUpdateTime(new Date());
       return music;
   }
   
   //读取MP3 //需要确认传入的地址一定有用才行
   public static Music readMp3(String mp3path) {
	  String chastset="gbk";//ISO-8859-1
	   Music music=new Music();
	   try {
		   /*  简单mp3信息读取*/
		   File file=new File(mp3path);
		   String str= leftTorigth(file.getAbsolutePath());
		   if(str!=null) {
		     music.setPath(str);
		   }
		   MP3File MP3file = new MP3File(mp3path);
			MP3AudioHeader header= (MP3AudioHeader)MP3file.getAudioHeader(); //获得头部信息
			music.setTime(new Integer(header.getTrackLength()));
		
			byte[] buf=new byte[128];
			 RandomAccessFile raf = new RandomAccessFile(mp3path, "r");
			 raf.seek(raf.length()-128);
			
			 raf.read(buf);
			 raf.close();
			 if(buf.length!=128) {
				 music.setName(file.getName().substring(0, file.getName().lastIndexOf(".")));
				  music.setAlbum("未知");
				  music.setSinger("未知");
				  music.setDate("未知");
				// System.out.println("数据长度不合法！");
			 }
			 else if(!"TAG".equalsIgnoreCase(new String(buf,0,3))){//标签头是否存在
				// System.out.println("MP3标签信息数据格式不正确！");
				 music.setName(file.getName().substring(0, file.getName().lastIndexOf(".")));
				
				  music.setAlbum("未知");
				  music.setSinger("未知");
				  music.setDate("未知");
			    }	
			 else 
			 {
				 String SongName = new String(buf,3,30,chastset).trim();//歌曲名称
				 if(isNull(SongName)) {
					 SongName=file.getName().substring(0, file.getName().lastIndexOf("."));
				 }
				// System.out.println(SongName);
				 String Artist = new String(buf,33,30,chastset).trim();//歌手名字
				 if(isNull(Artist)) {
					 Artist="未知";
				 }
				 //System.out.println(Artist);
				  String Album = new String(buf,63,30,chastset).trim();//专辑名称
				  if(isNull(Album)) {
					  Album="未知";
					 }
				 // System.out.println(Album);
				  String Year = new String(buf,93,4,chastset).trim();//出品年份
				  if(isNull(Year)) {
					  Year="未知";
					 }
				 // System.out.println(Year);
				  music.setName(SongName);
				  music.setAlbum(Album);
				  music.setSinger(Artist);
				  music.setDate(Year);
				
			 }
	   }catch(Exception e) {
		   e.printStackTrace();
	   }
	   music.setGenre("未知");
	   music.setLanguage("未知");
	   music.setRegion("未知");
	   music.setTheme("未知");
	   music.setCreateTime(new Date());
	   music.setUpdateTime(new Date());
	   return music;
   }
   public static boolean isNull(String str) {
	   boolean b=false;
	   if(str==null) {
		   b=true;
	   }
	   if(str.trim().equals("")) {
		   b=true;
	   }
	   if(str.trim().length()==0) {
		   b=true;
	   }
	   return b;
   }
   //获取文件夹下的所有文件路径
   public static List<String> readAllfile(String path) {
	   List<String> list=new ArrayList<String>();
	   File file = new File(path);
       if (file.exists()) {
           File[] files = file.listFiles();
           if (null == files || files.length == 0) {
               System.out.println("文件夹"+file.getName()+"是空的!");
               return null;
           } 
           else 
           {
               for (File file2 : files) {
                   if (file2.isDirectory()) {
                      // System.out.println("文件夹:" + file2.getAbsolutePath());
                	   List<String> secondlist= BeanUtil.readAllfile(file2.getAbsolutePath());
                	   if(secondlist!=null&&secondlist.size()!=0) {
                		   list.addAll(secondlist);
                	   }
                   } else {
                      // System.out.println("文件:" + file2.getAbsolutePath());
                	   list.add(file2.getAbsolutePath());
                   }
               }
           }
       }
       else {
    	 return null;
       }
	   
      return list;
	   
   }
   //过滤文件，得到所要文件
   public static List<String> filter(List<String> oldlist){
	   List<String> newlist=new ArrayList<String>();
	   if(oldlist!=null&&oldlist.size()!=0) {
		   for(String str:oldlist) {
			   if(BeanUtil.isNeedFile(str,0)) 
			   { //符合类型的文件
				   newlist.add(str);
			   }
		   }
		   return newlist;
	   }
	   else {
	      return null;
	   }
   }
   //判断文件类型,0-全部类型，1-mp3类型， 2-flac类型
   public static boolean isNeedFile(String path,int i) {
	   if(i!=0&&i!=1&&i!=2) {
		   return false;
	   }
	   String flac=".flac";
	   String mp3=".mp3";
	   File file=new File(path);
	  if(file.exists()) {
		  if(file.isFile()) {
	         String type=path.substring(path.lastIndexOf("."),path.length());
	         if(i==0||i==1) {
		       if(type.equals(mp3)) {
		    	   return true;
		       }
	         }
	         if(i==0||i==2) {
		       if(type.equals(flac)) {
		    	   return true;
		       }
	         }		       
    	    return false;       
		  }else{
			  return false;
		  }
	  }else {
		  return false;
	  }
	   
	
	   
   }
   
   public static ExtAjaxResponse checkpsw(String psw,String conpsw) {
	   ExtAjaxResponse extAjaxResponse=new ExtAjaxResponse();
	   if(psw.trim()==null||psw.trim().equals(""))
		{
			extAjaxResponse.setMsg("密码为空！");
			extAjaxResponse.setSuccess(false);
		}else if(conpsw.trim()==null||conpsw.trim().equals("")) {
			extAjaxResponse.setMsg("确认密码为空！");
			extAjaxResponse.setSuccess(false);
		}else if(psw.trim().equals(conpsw.trim())) {
			extAjaxResponse.setMsg("密码与确认密码不相同！");
			extAjaxResponse.setSuccess(false);
		}else if(!Rules.passwordrule(psw.trim())) {
			extAjaxResponse.setMsg("密码要包含数字、字母、下划线，长度在 5 到 10 个字符，并且要同时含有数字和字母！");
			extAjaxResponse.setSuccess(false);
		}
		else {
			extAjaxResponse.setMsg("success");
			extAjaxResponse.setSuccess(true);
		}
	   return extAjaxResponse;
   }
   
   public static ExtAjaxResponse checkphone(String phone) {
	   ExtAjaxResponse extAjaxResponse=new ExtAjaxResponse();
	   if(isNull(phone)) {
		   extAjaxResponse.setMsg("手机号为空！");
			extAjaxResponse.setSuccess(false);
	   }else {
			boolean b=Rules.phonerule(phone.trim());
			if(!b) {
				extAjaxResponse.setMsg("电话号码不正确！");
				extAjaxResponse.setSuccess(false);
			}
			else {
				extAjaxResponse.setMsg("success！");
				extAjaxResponse.setSuccess(true);
			}
	   }
	   
	   return extAjaxResponse;
	   
   }
   public static ExtAjaxResponse checkemail(String email) {
	   ExtAjaxResponse extAjaxResponse=new ExtAjaxResponse();
	   if(isNull(email)) {
		   extAjaxResponse.setMsg("邮箱为空！");
			extAjaxResponse.setSuccess(false);
	   }else {
			boolean b=Rules.phonerule(email.trim());
			if(!b) {
				extAjaxResponse.setMsg("邮箱不正确！");
				extAjaxResponse.setSuccess(false);
			}
			else {
				extAjaxResponse.setMsg("success！");
				extAjaxResponse.setSuccess(true);
			}
	   }
	   
	   return extAjaxResponse;
	   
   }
   //生成验证码
   public static String getCode() {
	   Random rand=new Random();
	   StringBuffer sbuf=new StringBuffer();
	   for(int i=0;i<codenumber;i++) {
	      int j=(int)(Math.random()*10);
	      sbuf.append(j);
	   }
	   return sbuf.toString();
   }
   
   
   //向邮箱发送信息
   public static ExtAjaxResponse sendEmail(String email,String content) {
	   ExtAjaxResponse extAjaxResponse=new ExtAjaxResponse();
	   if(isNull(email)||isNull(content)) {
		   extAjaxResponse.setMsg("传入信息有为空！");
		   extAjaxResponse.setSuccess(false);
	   }else {
		   Properties prop = new Properties();
			// 开启debug调试，以便在控制台查看
			prop.setProperty("mail.debug", "true"); 
			// 设置邮件服务器主机名
			prop.setProperty("mail.host", "smtp.qq.com");
			// 发送服务器需要身份验证
			prop.setProperty("mail.smtp.auth", "true");
			// 发送邮件协议名称
			prop.setProperty("mail.transport.protocol", "smtp");
			try {
			// 开启SSL加密，否则会失败
			MailSSLSocketFactory sf = new MailSSLSocketFactory();
			sf.setTrustAllHosts(true);
			prop.put("mail.smtp.ssl.enable", "true");
			prop.put("mail.smtp.ssl.socketFactory", sf);
			// 创建session
			Session session = Session.getInstance(prop);
			// 通过session得到transport对象
			Transport ts = session.getTransport();
			// 连接邮件服务器：邮箱类型，帐号，授权码代替密码（更安全）
			ts.connect("smtp.qq.com","656704118", "raaavbnflgvabajg");//后面的字符是授权码，用qq密码反正我是失败了（用自己的，别用我的，这个号是我瞎编的，为了。。。。）
			// 创建邮件
			
			Message message = createSimpleMail(session, email, content);
			// 发送邮件
			ts.sendMessage(message, message.getAllRecipients());
			ts.close();
			}catch(Exception e) {
				e.printStackTrace();
				extAjaxResponse.setMsg("发送产生未知错误！");
				extAjaxResponse.setSuccess(false);
			}
	   }
	  return extAjaxResponse;
	   
   }
   /**
	* @Method: createSimpleMail
	* @Description: 创建一封只包含文本的邮件
	*/
	public static MimeMessage createSimpleMail(Session session,String email,String content)
	throws Exception {
	// 创建邮件对象
	MimeMessage message = new MimeMessage(session);
	// 指明邮件的发件人
	message.setFrom(new InternetAddress("656704118@qq.com"));
	// 指明邮件的收件人，现在发件人和收件人是一样的，那就是自己给自己发
	message.setRecipient(Message.RecipientType.TO, new InternetAddress(email));
	// 邮件的标题
	message.setSubject("mymusic验证码");
	// 邮件的文本内容
	message.setContent(content, "text/html;charset=UTF-8");
	// 返回创建好的邮件对象
	return message;
}
   public static ExtAjaxResponse sendPhone(String phone,String code) {
	   ExtAjaxResponse extAjaxResponse=new ExtAjaxResponse();
	   if(isNull(phone)||isNull(code)) {
		   extAjaxResponse.setMsg("传入数据含有空数据！");
		   extAjaxResponse.setSuccess(false);
	   }else {
		   String host = "http://yzx.market.alicloudapi.com";
		    String path = "/yzx/sendSms";
		    String method = "POST";
		    String appcode = "ee11354aacc745b6b039dcb130ba713f";
		    Map<String, String> headers = new HashMap<String, String>();
		    //最后在header中的格式(中间是英文空格)为Authorization:APPCODE 83359fd73fe94948385f570e3c139105
		    headers.put("Authorization", "APPCODE " + appcode);
		    Map<String, String> querys = new HashMap<String, String>();
		    querys.put("mobile", phone);
		    querys.put("param", "code:"+code);
		    querys.put("tpl_id", "TP1710262");
		    Map<String, String> bodys = new HashMap<String, String>();
		    try {
		    	/**
		    	* 重要提示如下:
		    	* HttpUtils请从
		    	* https://github.com/aliyun/api-gateway-demo-sign-java/blob/master/src/main/java/com/aliyun/api/gateway/demo/util/HttpUtils.java
		    	* 下载
		    	*
		    	* 相应的依赖请参照
		    	* https://github.com/aliyun/api-gateway-demo-sign-java/blob/master/pom.xml
		    	*/
		    	HttpResponse response = HttpUtils.doPost(host, path, method, headers, querys, bodys);
		    	System.out.println(response.toString());
		    	 extAjaxResponse.setMsg("success！");
				   extAjaxResponse.setSuccess(true);
		    	//获取response的body
		    	//System.out.println(EntityUtils.toString(response.getEntity()));
		    } catch (Exception e) {
		    	e.printStackTrace();
		    	 extAjaxResponse.setMsg("发送验证码发生错误！");
				   extAjaxResponse.setSuccess(false);
		    }
	   }
	   return extAjaxResponse;
   }
}
