package com.crafts.service;

import java.io.IOException;

import java.text.ParseException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import com.crafts.dto.ResponsePost;
import com.crafts.entity.Comments;
import com.crafts.entity.File;
import com.crafts.entity.Likes;
import com.crafts.entity.Post;
import com.crafts.entity.User;
import com.crafts.exception.PostNotFoundException;
import com.crafts.exception.UserNotFoundException;
import com.crafts.repo.*;
import com.crafts.utils.*;

@Service
public class PostService {

	@Autowired
	PostRepo prepo;
	
	@Autowired 
	UserService uservice;
	
	@Autowired
	LikeRepo lrepo;

	
	@Autowired
	CommentRepo crepo;
	
	public List<Post> getAllPosts() {
		return prepo.findAll();
	}
	
	public Post savePost(Post post, MultipartFile file1, MultipartFile file2, MultipartFile file3) throws IOException, ParseException {
		
		List<MultipartFile> filelist=Arrays.asList(file1, file2, file3);
		
		Set<File> files=new HashSet<>();
		for(MultipartFile multipartFile : filelist) {
			
			if(multipartFile==null)
				continue;

			String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
		    
		    File f=new File(fileName, multipartFile.getContentType(), multipartFile.getBytes());
			
			files.add(f);
		}
		
		post.setDatetime(dateutil.getCurrentTime());
	    post.setPictures(files);
	    return prepo.save(post);
	}
	
	
	public Post getPostById(String id) throws PostNotFoundException {
		
		Optional<Post> postop=prepo.findById(id);
		if(!postop.isPresent())
			throw new PostNotFoundException("Post Not Found for given id");
		
		return postop.get();
	}
	
	
	
	public String deletePost(String id) throws PostNotFoundException {
		
		Post post=getPostById(id);
		User user=post.getUser();
		user.getPosts().remove(post);
		post.getComments().stream().forEach(comment -> {comment.setPost(null);});
		post.getLikes().stream().forEach(like -> {like.setPost(null);});
		prepo.delete(post);
		return "Deleted!";
	}
	
	public Post createNewPost(String id, Post post, MultipartFile file1, MultipartFile file2, MultipartFile file3 ) throws UserNotFoundException, IOException, ParseException {
		
		User user=uservice.getUserById(id);
		List<MultipartFile> filelist=Arrays.asList(file1, file2, file3);
		
		Set<File> files=new HashSet<>();
		for(MultipartFile multipartFile : filelist) {
			
			if(multipartFile==null)
				continue;

			String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
		    
		    File f=new File(fileName, multipartFile.getContentType(), multipartFile.getBytes());
			
			files.add(f);
		}
		
	    post.setPictures(files);
	    post.setUser(user);
	    post.setDatetime(dateutil.getCurrentTime());
	    return prepo.save(post);
	}
	
	
	// likes and comments
	
	public Comments comment (String userId, String postId, String content) throws PostNotFoundException, UserNotFoundException {
		Post post=getPostById(postId);
		User user=uservice.getUserById(userId);
		User userCommentedOn=post.getUser();
		
		Comments comment=new Comments(content, post, user, userCommentedOn);
		return crepo.save(comment);
	}
	
	public Likes liked(String userId, String postId) throws PostNotFoundException, UserNotFoundException {
		Post post=getPostById(postId);
		User user=uservice.getUserById(userId);
		User userCommentedOn=post.getUser();
		
		Likes like= new Likes(post, user, userCommentedOn);
		return lrepo.save(like);
	}
	
	public Set<Likes> getLikes(String postId) throws PostNotFoundException {
		Post post=getPostById(postId);
		return post.getLikes();
	}
	
	public Set<Comments> getComments(String postId) throws PostNotFoundException {
		Post post=getPostById(postId);
		return post.getComments();
	}
	
	public Comments getCommentById(String id) throws Exception {
		Optional<Comments> coop=crepo.findById(id);
		if(!coop.isPresent())
			throw new Exception("Comment Not Found");
		else return coop.get();
	}
	
	public Likes getLikeById(String id) throws Exception {
		Optional<Likes> lop=lrepo.findById(id);
		if(!lop.isPresent())
			throw new Exception("Like Not Found");
		else return lop.get();
	}
	
	public String deleteComment(String id) throws Exception {
		Comments comment=getCommentById(id);
		
		User user1=comment.getCommentedBy();
		user1.getMadeComments().remove(comment);
		User user2=comment.getCommentedOn();
		user2.getReceivedComments().remove(comment);
		
		Post post=comment.getPost();
		post.getComments().remove(comment);
		
		crepo.delete(comment);
		return "Deleted!";
	}
	
	public String deleteLike(String id) throws Exception {
		Likes like=getLikeById(id);
		
		User user1=like.getLikedBy();
		user1.getLiked().remove(like);
		User user2=like.getLikedOn();
		user2.getReceivedLikes().remove(like);
		
		Post post=like.getPost();
		post.getLikes().remove(like);
		
		lrepo.delete(like);
		return "Deleted!";
	}
	
	//post urls
	
	public ResponsePost getPostByIdUrl(String id) throws PostNotFoundException {
		Optional<Post> postop=prepo.findById(id);
		if(!postop.isPresent())
			throw new PostNotFoundException("Post Not Found for given id");
		
		Post post=postop.get();
		return PostUtil.convertToResponsePost(post);
	}
	
	public List<ResponsePost> getPostUrl() {
		List<Post> posts= prepo.findAll();
		
		return posts.stream().map(post -> PostUtil.convertToResponsePost(post)).collect(Collectors.toList());
		
	}
}

