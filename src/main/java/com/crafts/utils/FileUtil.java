package com.crafts.utils;

import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.crafts.dto.ResponseFile;
import com.crafts.entity.File;

public class FileUtil {

	public static ResponseFile convertToResponseFile(File file) {
		String fileDownloadUri = ServletUriComponentsBuilder
				.fromCurrentContextPath()
				.path("/file/")
				.path(file.getFileId()).toUriString();

		return new ResponseFile(file.getName(), fileDownloadUri, file.getType(),
				file.getFile().length);
		
		
	}
}
