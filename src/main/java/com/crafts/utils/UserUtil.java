package com.crafts.utils;

import java.util.List;
import java.util.stream.Collectors;

import com.crafts.dto.ResponseFile;
import com.crafts.dto.ResponsePost;
import com.crafts.dto.ResponseUser;
import com.crafts.entity.User;

public class UserUtil {

	public static ResponseUser convertToResponseUser(User user) {
		
		ResponseFile profileImage=null;
		if(user.getProfileImage()!=null)
			profileImage=FileUtil.convertToResponseFile(user.getProfileImage());
		
		List<ResponsePost> posts= user.getPosts().stream().map(post -> {
			
			return PostUtil.convertToResponsePost(post);
			
		}).collect(Collectors.toList());
		
		return new ResponseUser(user.getUserId(), user.getName(), user.getEmail(), posts, profileImage);
	}
}
