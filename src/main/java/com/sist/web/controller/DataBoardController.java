package com.sist.web.controller;
// => Router기능 (화면 이동) 
// => return "main/main" , return "redirect:"  
// => cookie 저장후에 이동 , _ok
import java.util.*;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.sist.web.service.*;
import com.sist.web.vo.*;

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
	   
	   model.addAttribute("main_html", "databoard/list");
	   return "main/main";
   }
}

