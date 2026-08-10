package com.sist.web.controller;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
// => Router기능 (화면 이동) 
// => return "main/main" , return "redirect:"  
// => cookie 저장후에 이동 , _ok
import java.util.*;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.sist.web.service.*;
import com.sist.web.vo.*;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
@Controller
@RequiredArgsConstructor
public class DataBoardController {
   private final DataBoardService dService;
   
   @GetMapping("/databoard/list")
   public String databoard_list(
		   @RequestParam(value="page",required = false) String page,
		   Model model)
   {
	   if(page==null)
		   page="1";
	   int curpage=Integer.parseInt(page);
	   int start=(curpage*10)-10;
	   List<DataBoardVO> list=
			   dService.databoardListData(start);
	   int totalpage=dService.databoardTotalPage();
	   
	   model.addAttribute("list", list);
	   model.addAttribute("curpage", curpage);
	   model.addAttribute("totalpage", totalpage);
	   model.addAttribute("main_html", "databoard/list");
	   return "main/main";
   }
   @GetMapping("/databoard/insert")
   public String databoard_insert(Model model)
   {
	   model.addAttribute("main_html", "databoard/insert");
	   return "main/main";
   }
   // => 경로 
   @PostMapping("/databoard/insert_ok")
   public String databoard_insert_ok(@ModelAttribute("vo")
                DataBoardVO vo,HttpServletRequest request)
   throws Exception
   {
	   String uploadDir=request.getServletContext()
			                   .getRealPath("/upload");
	   System.out.println(uploadDir);
	   // C:\\Users\\sist\\AppData\\Local\\Temp\\tomcat-docbase.8080.6744220911720755851\\upload
	   // /home/sist/ 
	   File dir=new File(uploadDir);
	   if(!dir.exists())
	   {
		   dir.mkdirs();
		   /*
		    *   new File("upload") => mkdir
		    *   new File("/upload/image") ==> mkdirs
		    */
	   }
	   
	   List<MultipartFile> files=vo.getFiles();
	   String filename=""; // a.jpg,b.jpg...
	   String filesize="";
	   boolean bCheck=false;// 파일 구분 //
	   for(MultipartFile file:files)
	   {
		   if(file.isEmpty())
		   {
			   bCheck=false;
		   }
		   else
		   {
			   String oname=file.getOriginalFilename();
			   File f=new File(uploadDir,oname);
			   if(f.exists())
			   {
				   // aaa.jpg   ==> name="aaa" ext=".jpg"
				   /*
				    *  substring(start) => 포함 
				    *  substring(start,end) end-1
				    */
				   String name=oname.substring(0,oname.lastIndexOf("."));
				   String ext=oname.substring(oname.lastIndexOf("."));
				   int count=1;
				   while(f.exists())
				   {
					   String newName=name+"("+count+")"+ext;
					   f=new File(uploadDir,newName);
					   count++;
				   }
				   
			   }
			   
			   // Upload 
			   // Paths.get => 운영체제 => \ / 
			   Path path=Paths.get(uploadDir,f.getName());
			   Files.copy(file.getInputStream(), path);
			   filename+=f.getName()+",";
			   filesize+=f.length()+",";
			   bCheck=true;
		   }
	   }
	   
	   //DB처리 
	   if(bCheck==true)
	   {
		   filename=filename.substring(0,filename.lastIndexOf(","));
		   filesize=filesize.substring(0,filesize.lastIndexOf(","));
		   vo.setFilename(filename);
		   vo.setFilesize(filesize);
		   vo.setFilecount(files.size());
	   }
	   else
	   {
		   vo.setFilename("");
		   vo.setFilesize("");
		   vo.setFilecount(0);
	   }
	   dService.databoardInsert(vo);
			   // => application.getReal()
	   return "redirect:/databoard/list";
   }
}







