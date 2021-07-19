package com.efsoftware.demomodelMapper.controller;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.efsoftware.demomodelMapper.dto.PostDTO;
import com.efsoftware.demomodelMapper.model.Post;
import com.efsoftware.demomodelMapper.service.PostService;

@RestController
@RequestMapping("/api/v1/posts")
public class PostController {

	private final PostService postService;
	
	@Autowired
	private ModelMapper modelMapper;

	public PostController(PostService postService) {
		super();
		this.postService = postService;
	}
	
	@GetMapping
	public List<PostDTO> getAll(){
		return postService.getAllPosts()
				.stream()
				.map(post -> modelMapper.map(post, PostDTO.class))
				.collect(Collectors.toList());    			
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<PostDTO> getPostById(@PathVariable Long id){
		
		Post post = postService.getPostById(id);
		
		PostDTO postDTO = modelMapper.map(post, PostDTO.class);
		
		return ResponseEntity.ok().body(postDTO);  
	}
	
	@PostMapping
	public ResponseEntity<PostDTO> savePost(@RequestBody PostDTO postDTO){
		// converted dto para class
		Post postRequest = modelMapper.map(postDTO, Post.class);
		
		Post post = postService.createPost(postRequest);
		
		// converted class para dto
		PostDTO postResponse = modelMapper.map(post, PostDTO.class);
		
		return new ResponseEntity<PostDTO>(postResponse, HttpStatus.CREATED);  
	}
	
	@PutMapping("/{id}")  
	public ResponseEntity<PostDTO> putPost(@PathVariable Long id, @RequestBody PostDTO postDTO){
		// converted dto para class
		Post postRequest = modelMapper.map(postDTO, Post.class);
		
		Post post = postService.putPost(id, postRequest);
		
		// converted class para dto
		PostDTO postResponse = modelMapper.map(post, PostDTO.class);
		
		return ResponseEntity.ok().body(postResponse);   
	}
	
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@DeleteMapping("/{id}")  
	public void removePost(@PathVariable Long id){
		postService.deletePost(id);    	
	}
	
	
	
	
	
	
	
	
}
