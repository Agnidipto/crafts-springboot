package com.crafts.controller;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.crafts.dto.ResponsePost;
import com.crafts.entity.Comments;
import com.crafts.entity.Likes;
import com.crafts.entity.Post;
import com.crafts.exception.PostNotFoundException;
import com.crafts.exception.UserNotFoundException;
import com.crafts.service.PostService;

@Controller
@CrossOrigin(origins="*")
public class PostController {
	
	@Autowired 
	PostService pservice;

	@GetMapping(value="/posts", produces = MediaType.APPLICATION_JSON_VALUE) //tested
	public @ResponseBody List<Post> getAllPosts() {
		return pservice.getAllPosts();
	}
		
	@PostMapping("/posts") //tested
	public @ResponseBody Post savePost(@RequestParam(value="file1", required=false) MultipartFile file1 ,@RequestParam(value="file2", required=false) MultipartFile file2, @RequestParam(value="file3", required=false) MultipartFile file3, @RequestParam("description") String description, @RequestParam("name") String name) throws IOException, ParseException {
		
		Post post=new Post();
		post.setDescription(description);
		post.setName(name);
		
		return pservice.savePost(post, file1, file2, file3);
	}
	
	@GetMapping("/post/{id}") //tested
	public @ResponseBody Post getPostById(@PathVariable String id) throws PostNotFoundException {
		
		return pservice.getPostById(id);
	}
		
	@DeleteMapping("/post/{id}") //tested
	public ResponseEntity<String> deletePost(@PathVariable String id) throws PostNotFoundException {
		
		String message=pservice.deletePost(id);
		return ResponseEntity.ok().body(message);
	}
	
	@PostMapping("/user/{id}/newpost") //tested
	public ResponseEntity<Post> createNewPost(@PathVariable String id, @RequestParam(value="file1", required=false) MultipartFile file1 ,@RequestParam(value="file2", required=false) MultipartFile file2, @RequestParam(value="file3", required=false) MultipartFile file3, @RequestParam("description") String description, @RequestParam("name") String name) throws UserNotFoundException, IOException, ParseException {
		
		Post post=new Post();
		post.setDescription(description);
		post.setName(name);
		
		return ResponseEntity.ok().body(pservice.createNewPost(id, post, file1, file2, file3));
	}
	
	//likes and comments
	
	@PostMapping("/post/{postId}/comment/{userId}") //tested
	public ResponseEntity<Comments> createComment(@PathVariable String postId, @PathVariable String userId, @RequestBody String content) throws PostNotFoundException, UserNotFoundException {
		return ResponseEntity.ok().body(pservice.comment(userId, postId, content));
	}
	
	@PostMapping("/post/{postId}/like/{userId}") //tested
	public ResponseEntity<Likes> createLike(@PathVariable String postId, @PathVariable String userId) throws PostNotFoundException, UserNotFoundException {
		return ResponseEntity.ok().body(pservice.liked(userId, postId));
	}
	
	@GetMapping("/post/{postId}/likes") //tested
	public ResponseEntity<Set<Likes>> getLikes(@PathVariable String postId) throws PostNotFoundException {
		return ResponseEntity.ok().body(pservice.getLikes(postId));
	}
	
	@GetMapping("/post/{postId}/comments") //tested
	public ResponseEntity<Set<Comments>> getComments(@PathVariable String postId) throws PostNotFoundException {
		return ResponseEntity.ok().body(pservice.getComments(postId));
	}
	
	@DeleteMapping("/comment/{id}")
	public ResponseEntity<String> deleteComment(@PathVariable String id) throws Exception {
		return ResponseEntity.ok().body(pservice.deleteComment(id));
	}
	
	@DeleteMapping("/like/{id}")
	public ResponseEntity<String> deleteLike(@PathVariable String id) throws Exception {
		return ResponseEntity.ok().body(pservice.deleteLike(id));
	}
	
	//Response Posts
	
	@GetMapping("/posts/url") //tested
	public @ResponseBody List<ResponsePost> getAllPostsUrl() {
		return pservice.getPostUrl();
	}
	
	@GetMapping("/post/url/{id}") //tested
	public @ResponseBody ResponsePost getPostByIdUrl(@PathVariable String id) throws PostNotFoundException {
		
		return pservice.getPostByIdUrl(id);
	}
}
