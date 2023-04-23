package com.example.task2628;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BlogRepository extends JpaRepository<Blog, Long> {
    @Query("SELECT b FROM Blog b WHERE CONCAT(LOWER(b.name), ' ', LOWER(b.date), ' ', LOWER(b.blogText)) LIKE %?1%")
    List<Blog> search_all(String keyword);
    @Query("SELECT b FROM Blog b WHERE CONCAT(LOWER(b.name), ' ', LOWER(b.date)) LIKE %?1%")
    List<Blog> search_name_date(String keyword);

    @Query("SELECT b FROM Blog b WHERE CONCAT(LOWER(b.name), ' ', LOWER(b.blogText)) LIKE %?1%")
    List<Blog> search_name_text(String keyword);
    @Query("SELECT b FROM Blog b WHERE CONCAT(b.date, ' ', b.blogText) LIKE %?1%")
    List<Blog> search_date_text(String keyword);
    @Query("SELECT b FROM Blog b WHERE CONCAT(b.name, ' ') LIKE %?1%")
    List<Blog> search_name(String keyword);
    @Query("SELECT b FROM Blog b WHERE CONCAT(b.date, ' ') LIKE %?1%")
    List<Blog> search_date(String keyword);
    @Query("SELECT b FROM Blog b WHERE CONCAT(b.blogText, ' ') LIKE %?1%")
    List<Blog> search_text(String keyword);
}
