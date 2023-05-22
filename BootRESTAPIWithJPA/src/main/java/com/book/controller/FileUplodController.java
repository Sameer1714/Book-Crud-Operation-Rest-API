package com.book.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.book.helper.FileUploadHelper;

@RestController
public class FileUplodController {

	@Autowired
	FileUploadHelper fileUploadHelper;

	@PostMapping("/uploadfile")
	public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file) {

		// System.out.println(file.getName());

		try {
			if (file.isEmpty()) {
				return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Request must COntaine File");
			}

			if (!file.getContentType().equals("image/jpeg")) {
				return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Only Image/jpeg Supported");
			}

			boolean flag = fileUploadHelper.uploadfile(file);
			if (flag) {
				// return ResponseEntity.ok("File Uploades Sucessfully");
				return ResponseEntity.ok(ServletUriComponentsBuilder.fromCurrentContextPath().path("/image/")
						.path(file.getOriginalFilename()).toUriString());
			}

			System.out.println(file.getOriginalFilename());
			System.out.println(file.getSize());

		} catch (Exception e) {
			// TODO: handle exception
		}

		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Server problem try Agian");
	}
}
