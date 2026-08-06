package com.sist.web.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import java.util.*;
import com.sist.web.entity.*;
import com.sist.web.repository.*;

import lombok.RequiredArgsConstructor;
@Service
@RequiredArgsConstructor
public class RecipeServiceImpl implements RecipeService{
    private final RecipeRepository rDao;

	@Override
	public List<Recipe> findByTitleContains(String title) {
		// TODO Auto-generated method stub
		return rDao.findByTitleContains(title);
	}

	@Override
	public List<Recipe> findByChefContains(String chef) {
		// TODO Auto-generated method stub
		return rDao.findByChefContains(chef);
	}

	@Override
	public List<Recipe> recipeListData(int page) {
		// TODO Auto-generated method stub
		// Pageable => 페이지 요청 정보 
		// 페이지 번호 / 페이지 크기 , 정렬 조건 
		Pageable pg=PageRequest.of(page-1,12,
				Sort.by(Sort.Direction.ASC,"no"));
		/*
		 *   실제 SQL문장 
		 *   SELECT *
		 *   FROM recipe 
		 *   ORDER BY no ASC 
		 *   OFFSET ? ROWS FETCH NEXT ? ROWS ONLY
		 *         --- 0번 시작 
		 *   JPA => 중심이 객체 단위로 사용 
		 *               -------- @Entity
		 *          객체 ===== Column (메소드) = ORM 
		 *          => LinQ (c#)
		 */
		Page<Recipe> pList=rDao.findAll(pg);
		List<Recipe> list=new ArrayList<Recipe>();
		// Page => List변환 
		if(pList!=null && pList.hasContent()) // 값이 존재
		{
			list=pList.getContent();
		}
		return list;
	}

	@Override
	public int[] getPageData(int page) {
		// TODO Auto-generated method stub
		
		int totalpage=(int)(Math.ceil(rDao.count()/12.0));
		int startPage=((page-1)/10*10)+1;
		int endPage=((page-1)/10*10)+10;
		if(endPage>totalpage)
			endPage=totalpage;
		int[] pages= {page,totalpage,startPage,endPage};
		
		return pages;
	}
    
}
