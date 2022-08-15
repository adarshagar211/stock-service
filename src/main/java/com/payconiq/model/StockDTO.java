package com.payconiq.model;

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
public class StockDTO implements Serializable{

	private static final long serialVersionUID = -7285754929128206418L;
	
	@JsonProperty("id")
	private Integer id ;
	
	@JsonProperty("stockName")
	private String stockName ;
	
	@JsonProperty("currentPrice")
	private String currentPrice;
	
}
