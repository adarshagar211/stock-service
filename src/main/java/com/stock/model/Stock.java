package com.stock.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name="stocks_tbl")
public class Stock implements Serializable{

	private static final long serialVersionUID = -7285754929128206418L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id ;
	
	@Column(nullable = false , unique = true)
	private String name ;
	
	@Column(nullable=false)
	private BigDecimal currentPrice;
	
	@Column(nullable=false)
	@Temporal(TemporalType.DATE)
	private LocalDateTime lastUpdate;
	
}
