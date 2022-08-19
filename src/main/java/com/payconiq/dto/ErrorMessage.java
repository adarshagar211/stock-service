package com.payconiq.dto;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ErrorMessage implements Serializable {

	private static final long serialVersionUID = -389784023640370836L;
	
	@JsonProperty("errorCode")
	private int errorCode;
	
	@JsonProperty("errorMessage")
	private String errorMessage;
}
