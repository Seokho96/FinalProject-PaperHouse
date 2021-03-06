package com.bit.paperhouse.util;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import javax.servlet.ServletContext;

import java.util.Base64.Decoder;

import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.http.MediaType;
import org.springframework.web.multipart.MultipartFile;

import com.bit.paperhouse.dto.WriterDto;


public class UtilEx {
	

	//남은 일 수 (date) 계산 
	//기한 만료 시 카운트 30 ...! lastcount = -1
	public static int Onelastcount(String lastdate) {
		
		int lastcount = 0;
		
		
		Calendar cal1 = Calendar.getInstance();
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;
        try {
        	cal1.setTime(df.parse(lastdate));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Calendar cal2 = Calendar.getInstance();
        cal2.setTime(new Date());
        
        System.out.println("마지막 날짜: " + df.format(cal1.getTime()));
        System.out.println("현재 날짜   : " + df.format(cal2.getTime()));
        
        cal1.add(Calendar.DATE, - cal2.get(Calendar.DATE));
        System.out.println("남은 일 수  : " + cal1.get(Calendar.DATE));
		
        lastcount = cal1.get(Calendar.DATE);
        
        if(lastcount == 0) {
        	lastcount = -1;
        }
        
		return lastcount;
	}
	
	public static HashMap<String, Integer> getSubscribes(List<WriterDto> getNamesAndDates) {
		
		//리턴 값 선언 : 형식 (aname : adate , bname : bdate);
		HashMap<String, Integer> map = new HashMap<String, Integer>();
		
		for(int i = 0;  i <getNamesAndDates.size(); i++) {
			
			//남은 구독일 수 계산 함수
			int lastcount = UtilEx.Onelastcount(getNamesAndDates.get(i).getEndDate());
			
			map.put(getNamesAndDates.get(i).getWriterName(), lastcount);
			
		}
		
		System.out.println("사용자의 구독 리스트 : " + map.toString());
		return map;
		
	}
	
	public static List<Integer> getFollowed(String follow) {
		String followed[] = follow.split("-");
		List<Integer> list = new ArrayList<Integer>();
		for (int i = 0; i < followed.length; i++) {
			int f = Integer.parseInt(followed[i]);
			list.add(f);
		}
		return list;
	}
	
	public static String saveFile(MultipartFile file,String path) {
		
		UUID uuid = UUID.randomUUID();
	    String saveName = uuid + "-" + file.getOriginalFilename();
	    
	   
	    System.out.println("업로드패쓰 : " + path);
	    
	    //UPLOAD_PATH
	    File saveFile = new File(path + saveName);
	    
	    
	    try {
			file.transferTo(saveFile);
		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	    
	    return saveName;
	}
	
	public static String unFollow(String followList, String unfollow) {
		System.out.println("unFollow method Start");
		
		String followed[] = followList.split("-");
		String setFollowDB = "";
		
		List<String> list = new ArrayList<String>();
		for (int i = 0; i < followed.length; i++) {
			String f = followed[i];
			list.add(f + "-");
		}
		
		System.out.println("디비에서 뽑은 리스트: " + list.toString());
		
		for (int i = 0; i < list.size(); i++) {
			list.remove(unfollow + "-");
		}
		
		System.out.println("시퀀스 삭제한 리스트: " + list.toString());
		
		for (int i = 0; i < list.size(); i++) {
			setFollowDB += list.get(i);
		}
		
		System.out.println("디비 입력 값 : " + setFollowDB);
		
		System.out.println("unFollow method End");
		
		return setFollowDB;
	}
	
	public static String fileToString(MultipartFile novel) throws IOException {
		
		 	String fileString = new String();
		    BufferedInputStream inputStream =  null;
		    ByteArrayOutputStream byteOutStream = null;

		    try {
		        inputStream = new BufferedInputStream(novel.getInputStream());
		        byteOutStream = new ByteArrayOutputStream();

				int len = 0;
				byte[] buf = new byte[1024];

		        while ((len = inputStream.read(buf)) != -1) {
		             byteOutStream.write(buf, 0, len);

		        }

		        byte[] fileArray = byteOutStream.toByteArray();
		        fileString = new String(Base64.encodeBase64(fileArray),"UTF-8");

		    } catch (IOException e) {
		        e.printStackTrace();
		    } finally {
		    	inputStream.close();
		        byteOutStream.close();
		    }
		return fileString;
	}
	
	public static String decode(String cont) {

		Decoder decoder = java.util.Base64.getDecoder(); 
		byte[] decodedBytes = decoder.decode(cont);
		String novel = new String(decodedBytes);
		
		return novel;
	}
	

	public static MediaType getMediaTypeForFileName(ServletContext servletContext, String filename) {
		
		String mimeType = servletContext.getMimeType(filename);
		
		try {
			MediaType mediaType = MediaType.parseMediaType(mimeType);
			return mediaType;	//미디어 타입으로 넘겨준다
		}catch (Exception e) {
			return MediaType.APPLICATION_OCTET_STREAM;
		}
	}
	
}
