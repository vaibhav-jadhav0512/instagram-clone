package com.image.be.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.image.be.model.ImageEntity;

import lombok.extern.slf4j.Slf4j;

@Repository
@Slf4j
public class ImageRepositoryImpl implements ImageRepository {

	@Autowired
	private NamedParameterJdbcTemplate template;

	@Override
	public void saveImage(ImageEntity imageEntity) {
		String sql = "INSERT INTO insta.`images` (image_name, image_data) VALUES (:imageName, :imageData)";

		MapSqlParameterSource parameters = new MapSqlParameterSource();
		parameters.addValue("imageName", imageEntity.getImageName());
		parameters.addValue("imageData", imageEntity.getImageData());

		template.update(sql, parameters);

	}

	@Override
	public byte[] getImageDataById(Long imageId) {
		String sql = "SELECT image_data FROM insta.`images` WHERE id = :id";
		MapSqlParameterSource parameters = new MapSqlParameterSource().addValue("id", imageId);
		log.info("Getting image");
		return template.queryForObject(sql, parameters, byte[].class);
	}

}
