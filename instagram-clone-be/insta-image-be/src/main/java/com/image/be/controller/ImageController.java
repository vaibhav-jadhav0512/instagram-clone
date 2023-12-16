package com.image.be.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.image.be.service.ImageService;

@RestController
@RequestMapping("/api/v1/image")
public class ImageController {

	@Autowired
	private ImageService imageService;

	@PostMapping("/upload")
	public ResponseEntity<String> handleFileUpload(@RequestParam("file") MultipartFile file) {
			imageService.saveImage(file);
			return new ResponseEntity<>("Image uploaded successfully", HttpStatus.OK);
	}

	@GetMapping("/get/{id}")
	public ResponseEntity<byte[]> getImageById(@PathVariable("id") Long id) {
		try {
			byte[] imageData = imageService.getImageDataById(id);
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.IMAGE_JPEG);

			return new ResponseEntity<>(imageData, headers, HttpStatus.OK);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
		}
	}
}
