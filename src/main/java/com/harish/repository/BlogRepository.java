package com.harish.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.harish.model.Blog;

import java.util.List;

@Repository
public interface BlogRepository extends JpaRepository<Blog,Integer> {

}
