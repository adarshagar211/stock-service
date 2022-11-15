package com.stock.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
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
	private Long id ;
	
	@JsonProperty("name")
	@NotBlank(message = "Stock name cannot be empty or blank")
	private String name ;
	
	@JsonProperty("currentPrice") 
	@NotNull(message = "Stock price cannot be null")
	private BigDecimal currentPrice;
	
	@JsonProperty("lastUpdate")
	private LocalDateTime lastUpdate;
}
