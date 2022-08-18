package com.payconiq.dto;

import java.io.Serializable;
import java.util.Date;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StockDto implements Serializable{

	private static final long serialVersionUID = -12354929128206418L;

	@JsonProperty("id")
	private Integer id ;
	
	@JsonProperty("name")
	@NotBlank
	private String name ;
	
	@JsonProperty("currentPrice") 
	@NotNull
	private Double currentPrice;
	
	@JsonProperty("lastUpdate")
	private Date lastUpdate;
}
