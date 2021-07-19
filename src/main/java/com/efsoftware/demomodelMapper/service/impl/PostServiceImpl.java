package com.efsoftware.demomodelMapper.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.efsoftware.demomodelMapper.exception.PostException;
import com.efsoftware.demomodelMapper.model.Post;
import com.efsoftware.demomodelMapper.repository.PostRepository;
import com.efsoftware.demomodelMapper.service.PostService;

@Service
public class PostServiceImpl implements PostService{
	
	private final PostRepository postRepository;
	
	public PostServiceImpl(PostRepository postRepository) {
		super();
		this.postRepository = postRepository;  
	}

	@Override
	public List<Post> getAllPosts() {
		return postRepository.findAll();
	}

	@Override
	public Post getPostById(Long id) {
		Optional<Post> postId = postRepository.findById(id);
		if(postId.isPresent()) {
			return postId.get();
		}else
		{
			throw new PostException("Post not Found");
		}   
	}

	@Override
	public Post createPost(Post post) {
		
		return postRepository.save(post);  
	}

	@Override
	public void deletePost(Long id) {
		Post postId = postRepository.findById(id)
				.orElseThrow(() -> new PostException("Post not found")); 
		
		postRepository.delete(postId); 	  
	}

	@Override
	public Post putPost(Long id, Post post) {
		Post postId = postRepository.findById(id)
				.orElseThrow(() -> new PostException("Post not found")); 
		
		postId.setTitle(post.getTitle());
		postId.setDescription(post.getDescription());
		postId.setContent(post.getContent()); 
		
		return postRepository.save(postId);    
	}

}
