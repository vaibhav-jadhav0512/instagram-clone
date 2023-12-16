package com.image.be.repository;

import com.image.be.model.ImageEntity;

public interface ImageRepository {

	void saveImage(ImageEntity img);

	byte[] getImageDataById(Long id);

}
