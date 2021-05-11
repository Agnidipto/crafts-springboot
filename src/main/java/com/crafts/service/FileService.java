package com.crafts.service;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.crafts.entity.File;
import com.crafts.repo.FileRepo;

@Service
public class FileService {

	@Autowired
	FileRepo frepo;
	
	public File store(MultipartFile multipartFile) throws IOException {
		String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
	    File file = new File(fileName, multipartFile.getContentType(), multipartFile.getBytes());
	    
	    return frepo.save(file);
	}
	
	public File getFileById(String id) {
		return frepo.findById(id).get();
	}
	  
	public List<File> getAllFiles() {
		return frepo.findAll();
	}
}
