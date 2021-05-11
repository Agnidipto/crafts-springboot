package com.crafts.utils;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.crafts.dto.ResponseFile;
import com.crafts.dto.ResponsePost;
import com.crafts.entity.File;
import com.crafts.entity.Post;

public class PostUtil {

	public static ResponsePost convertToResponsePost(Post post) {

		String postUriString = ServletUriComponentsBuilder
				.fromCurrentContextPath()
				.path("/post/")
				.path(post.getPostId()).toUriString();

		Set<File> files = post.getPictures();
		Set<ResponseFile> responsefile=new HashSet<ResponseFile>();
		
		for(File file:files) {
			if(file!=null) {
				
				responsefile.add(FileUtil.convertToResponseFile(file));

			}
		}
		return new ResponsePost(post.getPostId(), post.getName(), post.getDescription(), postUriString, responsefile);
	}
}
