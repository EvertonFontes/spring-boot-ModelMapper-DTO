package com.efsoftware.demomodelMapper.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.efsoftware.demomodelMapper.model.Post;

@Repository
public interface PostRepository extends JpaRepository<Post, Long>{
  
}
