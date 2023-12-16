package com.image.be.service;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.image.be.model.ImageEntity;
import com.image.be.repository.ImageRepository;

@Service
public class ImageServiceImpl implements ImageService {

	@Autowired
	private ImageRepository imageRepository;

	@Override
	public void saveImage(MultipartFile file) {
		ImageEntity imageEntity = new ImageEntity();
		imageEntity.setImageName(file.getOriginalFilename());
		try {
			imageEntity.setImageData(file.getBytes());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		imageRepository.saveImage(imageEntity);
	}

	@Override
	public byte[] getImageDataById(Long id) {
		return imageRepository.getImageDataById(id);
	}

}
