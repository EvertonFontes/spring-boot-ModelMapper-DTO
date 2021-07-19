package com.efsoftware.demomodelMapper.service;

import java.util.List;

import com.efsoftware.demomodelMapper.model.Post;

public interface PostService {

	List<Post> getAllPosts();
	Post getPostById(Long id);
	Post createPost(Post post);
	void deletePost(Long id);
	Post putPost(Long id, Post post);    
}
