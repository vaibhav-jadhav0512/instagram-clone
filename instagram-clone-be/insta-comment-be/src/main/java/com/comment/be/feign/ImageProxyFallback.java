package com.comment.be.feign;

import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class ImageProxyFallback implements FallbackFactory<ImageProxy> {

	@Override
	public ImageProxy create(Throwable cause) {
		return new ImageProxy() {

			@Override
			public ResponseEntity<String> test() {
				log.error("Image service is down");
				return new ResponseEntity<>("Image Service is down", HttpStatus.SERVICE_UNAVAILABLE);
			}
		};
	}

}
