package com.crafts.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.crafts.dto.ResponseFile;
import com.crafts.entity.File;
import com.crafts.service.FileService;

@Controller
@CrossOrigin(origins="*")
public class FileController {
	
	@Autowired
	FileService fileservice;
	
	@PostMapping("/file")
	public ResponseEntity<String> storeFile(@RequestParam("file") MultipartFile file){
		String message="";
		try {
			fileservice.store(file);
			message="Uploaded file successfully :"+file.getOriginalFilename();
			return new ResponseEntity<String>(message, HttpStatus.OK);
		} catch (IOException e) {
			message="Uploading file failed :"+e.getMessage();
			return new ResponseEntity<String>(message, HttpStatus.BAD_REQUEST);
		}
	}
	
	@GetMapping("/file")
	public ResponseEntity<List<ResponseFile>> getAllFiles() {
				
		List<ResponseFile> files = fileservice.getAllFiles().stream().map(dbFile -> {
		      String fileDownloadUri = ServletUriComponentsBuilder
		          .fromCurrentContextPath()
		          .path("/file/")
		          .path(dbFile.getFileId())
		          .toUriString();

		      return new ResponseFile(
		          dbFile.getName(),
		          fileDownloadUri,
		          dbFile.getType(),
		          dbFile.getFile().length);
		    }).collect(Collectors.toList());
		
		return new ResponseEntity<List<ResponseFile>>(files, HttpStatus.OK);
		
	}
	
	@GetMapping("/file/{id}")
	public ResponseEntity<File> getFileById(@PathVariable String id){
		
		File file=fileservice.getFileById(id);
		
		return ResponseEntity.ok()
				//uncomment below code to direct download
		        //.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getName() + "\"")
		        .body(file);
	}

}
