package com.payconiq.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.fasterxml.jackson.annotation.JsonProperty;

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
@JsonAutoDetect(getterVisibility=Visibility.NONE)
public class Stock implements Serializable{

	private static final long serialVersionUID = -7285754929128206418L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonProperty("id")
	private Integer id ;
	
	@Column
	@JsonProperty("name")
	@NotEmpty
	private String name ;
	
	@Column
	@JsonProperty("currentPrice")
	@NotNull
	private Double currentPrice;
	
	@Column
	@JsonProperty("lastUpdate")
	private Date lastUpdate;
	
}
