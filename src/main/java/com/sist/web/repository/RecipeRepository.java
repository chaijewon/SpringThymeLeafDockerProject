package com.sist.web.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sist.web.entity.Recipe;
import java.util.*;
@Repository
public interface RecipeRepository extends JpaRepository<Recipe, Integer> {
    public Page<Recipe> findByTitleContains(String title,Pageable pg);
    /*
     *   SELECT * 
     *   FROM recipe
     *   WHERE title LIKE '%데이터%'
     *   OFFSET ? ROWS FETCH NEXT ? ROWS ONLY
     */
    public Page<Recipe> findByChefContains(String chef,Pageable pg);
    public long countByTitleContains(String title);
    /*
     *   SELECT COUNT(*) 
     *   FROM recipe
     *   WHERE title LIKE '%데이터%'
     */
    public long countByChefContains(String chef);
    /*
     *   findBy컬럼명연산자 
     *         ---- -----
     *   findByName(String name) 
     *   => WHERE name=? ========> equals 
     *   findByTitleStartsWith(String title) 
     *   => WHERE title LIKE 'title%'
     *   findByTitleEndsWith(String title) 
     *   => WHERE title LIKE '%title'
     *   findByTitleContains(String title)   
     *   => WHERE title LIKE '%title%'
     *   findByOrderByTitleDesc()
     *   
     *   findAll(Pageable , Sort) 
     *   count()
     *   save() / delete()
     */
}
