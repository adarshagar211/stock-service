package com.stock.test;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.stock.controller.StockController;
import com.stock.dto.StockDto;
import com.stock.service.StockService;

@WebMvcTest(controllers = {StockController.class})
@AutoConfigureMockMvc
public class StockAppControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private StockService stockService;
	
	@Autowired
	private ObjectMapper objectMapper;

	@Test
	public void testgetAllStock() throws Exception {
		List<StockDto> stocks =List.of(StockDto.builder().id(1l).name("TESTSTOCK").currentPrice(BigDecimal.valueOf(12.44)).lastUpdate(LocalDateTime.now()).build());
		when(stockService.getAll(Mockito.anyInt(),Mockito.anyInt(),Mockito.anyString(),Mockito.anyString())).thenReturn(stocks);
		this.mockMvc.perform(get("/api/stocks"))
		            .andDo(print())
		            .andExpect(status().isOk())
				    .andExpect(jsonPath("$[0].name").value("TESTSTOCK"));
	}

	@Test
	public void testgetOneStock() throws Exception {
		StockDto stocks = StockDto.builder().id(1l).name("TESTSTOCK").currentPrice(BigDecimal.valueOf(12.44)).lastUpdate(LocalDateTime.now()).build();
		when(stockService.getStock(Mockito.anyInt())).thenReturn(stocks);
		this.mockMvc.perform(get("/api/stocks/1"))
		            .andDo(print())
		            .andExpect(status().isOk())
		            .andExpect(jsonPath("$.name").value("TESTSTOCK"));
	}

	@Test
	public void testcreateStock() throws Exception {
		StockDto stockDTO = StockDto.builder().id(1l).name("TESTSTOCK").currentPrice(BigDecimal.valueOf(12.44)).lastUpdate(LocalDateTime.now()).build();
		when(stockService.createStock(Mockito.any())).thenReturn(stockDTO);
		this.mockMvc.perform(post("/api/stocks").contentType(MediaType.APPLICATION_JSON_VALUE).accept(MediaType.APPLICATION_JSON_VALUE)
				     .content(objectMapper.writeValueAsString(stockDTO)))
		            .andDo(print())
		            .andExpect(status().isCreated())
		            .andExpect(jsonPath("$.name").value("TESTSTOCK"));
	}
	
	@Test
	public void testcreateInvalidStockName() throws Exception {
		StockDto stockDTO = StockDto.builder().id(1l).currentPrice(BigDecimal.valueOf(12.44)).lastUpdate(LocalDateTime.now()).build();
		when(stockService.createStock(Mockito.any())).thenReturn(stockDTO);
		this.mockMvc.perform(post("/api/stocks").contentType(MediaType.APPLICATION_JSON_VALUE).accept(MediaType.APPLICATION_JSON_VALUE)
				     .content(objectMapper.writeValueAsString(stockDTO)))
		            .andDo(print())
		            .andExpect(status().is4xxClientError())
		            .andExpect(jsonPath("$.name").value("Stock name cannot be empty or blank"));
	}
	
	@Test
	public void testcreateInvalidStockPrice() throws Exception {
		StockDto stockDTO = StockDto.builder().name("TESTSTOCK").build();
		when(stockService.createStock(Mockito.any())).thenReturn(stockDTO);
		this.mockMvc.perform(post("/api/stocks").contentType(MediaType.APPLICATION_JSON_VALUE).accept(MediaType.APPLICATION_JSON_VALUE)
				     .content(objectMapper.writeValueAsString(stockDTO)))
		            .andDo(print())
		            .andExpect(status().is4xxClientError())
		            .andExpect(jsonPath("$.currentPrice").value("Stock price cannot be null"));
	}
	
	@Test
	public void testupdateStock() throws Exception {
		StockDto stockDTO = StockDto.builder().id(1l).name("TESTSTOCK").currentPrice(BigDecimal.valueOf(12.44)).lastUpdate(LocalDateTime.now()).build();
		when(stockService.updateStock(Mockito.any(),Mockito.anyInt())).thenReturn(stockDTO);
		this.mockMvc.perform(patch("/api/stocks/1").contentType(MediaType.APPLICATION_JSON_VALUE).accept(MediaType.APPLICATION_JSON_VALUE)
				     .content(objectMapper.writeValueAsString(stockDTO)))
		            .andDo(print())
		            .andExpect(status().isOk())
		            .andExpect(jsonPath("$.name").value("TESTSTOCK"));
	}
	
	@Test
	public void testdeleteStock() throws Exception {
	    org.mockito.BDDMockito.willDoNothing().given(stockService).deleteStock((Mockito.anyInt()));	
		this.mockMvc.perform(delete("/api/stocks/1"))
		            .andDo(print())
		            .andExpect(status().isNoContent());
		verify(stockService, times(1)).deleteStock(1);
	}
}