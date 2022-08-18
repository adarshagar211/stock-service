package com.payconiq.test;

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

import java.util.Date;
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
import com.payconiq.controller.StockController;
import com.payconiq.dto.StockDto;
import com.payconiq.service.StockService;

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
		List<StockDto> stocks =List.of(StockDto.builder().id(1).name("TESTSTOCK").currentPrice(12.44).lastUpdate(new Date()).build());
		when(stockService.getAll(Mockito.anyInt(),Mockito.anyInt(),Mockito.anyString(),Mockito.anyString())).thenReturn(stocks);
		this.mockMvc.perform(get("/api/stocks"))
		            .andDo(print())
		            .andExpect(status().isOk())
				    .andExpect(jsonPath("$[0].name").value("TESTSTOCK"));
	}

	@Test
	public void testgetOneStock() throws Exception {
		StockDto stocks = StockDto.builder().id(1).name("TESTSTOCK").currentPrice(12.44).lastUpdate(new Date()).build();
		when(stockService.getStock(Mockito.anyInt())).thenReturn(stocks);
		this.mockMvc.perform(get("/api/stocks/1"))
		            .andDo(print())
		            .andExpect(status().isOk())
		            .andExpect(jsonPath("$.name").value("TESTSTOCK"));
	}

	@Test
	public void testcreateStock() throws Exception {
		StockDto stockDTO = StockDto.builder().name("TESTSTOCK").currentPrice(12.44).build();
		when(stockService.createStock(Mockito.any())).thenReturn(stockDTO);
		this.mockMvc.perform(post("/api/stocks").contentType(MediaType.APPLICATION_JSON_VALUE).accept(MediaType.APPLICATION_JSON_VALUE)
				     .content(objectMapper.writeValueAsString(stockDTO)))
		            .andDo(print())
		            .andExpect(status().isCreated())
		            .andExpect(jsonPath("$.name").value("TESTSTOCK"));
	}
	
	@Test
	public void testupdateStock() throws Exception {
		StockDto stockDTO = StockDto.builder().name("TESTSTOCK").currentPrice(12.44).build();
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
		            .andExpect(status().isOk());
		verify(stockService, times(1)).deleteStock(1);
	}
}