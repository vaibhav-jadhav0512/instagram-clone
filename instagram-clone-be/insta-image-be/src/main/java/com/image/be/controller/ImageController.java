package com.image.be.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/image")
public class ImageController {

	@GetMapping("/test")
	public String test() {
		return "Image success";
	}
}
