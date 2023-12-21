package com.actions.be.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "INSTA-API-GATEWAY", contextId = "ImageProxy", fallbackFactory = ImageProxyFallback.class)
public interface ImageProxy {

	@GetMapping("/api/v1/image/test")
	public ResponseEntity<String> test();

}
