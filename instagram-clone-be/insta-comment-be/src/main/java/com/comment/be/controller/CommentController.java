package com.comment.be.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.comment.be.feign.ImageProxy;

@RestController
@RequestMapping("/api/v1/comment")
public class CommentController {

	@Autowired
	private ImageProxy imageProxy;

	@GetMapping("/test")
	public String test() {
		return "Hello";
	}

	@GetMapping("/feign")
	public ResponseEntity<String> feign() {
		return imageProxy.test();
	}
}
