package com.example.myMusic.common.util;

public class SingerUtil {
      public static String getLocation(Integer in) {
    	  if(in.equals(new Integer(1))) {
    		  return "内地";
    	  }
    	  else if(in.equals(new Integer(2))) {
    		  return "港台";
    	  }
    	  else  if(in.equals(new Integer(3))) {
    		  return "欧美";
    	  }
    	  else  if(in.equals(new Integer(4))) {
    		  return "日本";
    	  }
    	  else if(in.equals(new Integer(5))) {
    		  return "韩国";
    	  }
    	  else {
    		  return "其他";
    	  }
      }
      public static String getType(Integer in) {
    	  if(in.equals(new Integer(1))) {
    		  return "男";
    	  }
    	  else if(in.equals(new Integer(2))) {
    		  return "女";
    	  }
    	  else{
    		  return "组合";
    	  }
      }
      
      public static String getGenre(Integer in) {
    	  if(in.equals(new Integer(1))) {
    		  return "流行";
    	  }
    	  else if(in.equals(new Integer(2))) {
    		  return "嘻哈";
    	  }
    	  else  if(in.equals(new Integer(3))) {
    		  return "摇滚";
    	  }
    	  else  if(in.equals(new Integer(4))) {
    		  return "电子";
    	  }
    	  else if(in.equals(new Integer(5))) {
    		  return "爵士";
    	  }
    	  else  if(in.equals(new Integer(6))) {
    		  return "民歌";
    	  }
    	  else if(in.equals(new Integer(7))) {
    		  return "轻音乐";
    	  }
    	  else if(in.equals(new Integer(8))) {
    		  return "民谣";
    	  }
    	  else if(in.equals(new Integer(9))) {
    		  return "古典";
    	  }
    	  
    	  else if(in.equals(new Integer(10))) {
    		  return "乡村";
    	  }
    	  else {
    		  return "蓝调";
    	  }
      }
}

