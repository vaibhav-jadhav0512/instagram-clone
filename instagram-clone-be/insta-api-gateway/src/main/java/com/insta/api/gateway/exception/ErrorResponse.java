package com.insta.api.gateway.exception;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ErrorResponse {

	private String code;
	private Date timestamp;
	private String correlationId;
	private String message;
	private String detail;
}