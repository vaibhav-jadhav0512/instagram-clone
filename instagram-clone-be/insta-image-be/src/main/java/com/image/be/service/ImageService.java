package com.image.be.service;

import org.springframework.web.multipart.MultipartFile;

public interface ImageService {

	void saveImage(MultipartFile file);

	byte[] getImageDataById(Long id);

}
