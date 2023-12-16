package com.image.be.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ImageEntity {
	private Long id;
	private String imageName;
	private byte[] imageData;
}
