package com.sist.web.vo;
/*
 *  NO        NOT NULL NUMBER         
	NAME      NOT NULL VARCHAR2(51)   
	SUBJECT   NOT NULL VARCHAR2(2000) 
	CONTENT   NOT NULL CLOB           
	PWD       NOT NULL VARCHAR2(10)   
	REGDATE            DATE           
	HIT                NUMBER         
	FILENAME           VARCHAR2(1000) 
	FILESIZE           VARCHAR2(500)  
	FILECOUNT          NUMBER      
	
	1. Spring-Boot => ThymeLeaf 
	2. JPA + MyBatis 
	         => 동적 쿼리
	   => JOIN / JPQL / QueryDSL 
	3. Spring Security + JWT 
	4. 알림 => WebSocket + Stormp + 카프카 
	5. JavaMail 
	6. Front => Pinia (Vue) 
	7. Spring AI
	=======> CI/CD (AWS) : 무중단 (Blue / Green) 
	                       Eginx => JenKins
	-----------------------------
	SpringAI + React + tanStack-Query + TypeScript + NodeJS
	-------------------------------------------------------
	  | NextJS
 */
import java.util.*;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

@Data
public class DataBoardVO {
   private int no,hit,filecount;
   private String name,subject,content,pwd,
           filename,filesize,dbday;
   private Date regdate;
   private List<MultipartFile> files;
}







