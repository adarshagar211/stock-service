package com.stock.test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import com.stock.dto.StockDto;
import com.stock.model.Stock;
import com.stock.repository.StockRepository;
import com.stock.service.impl.StockServiceImpl;

@ExtendWith(MockitoExtension.class)
public class StockAppServiceTest {

	@Mock
	private StockRepository stockRepository;

	@InjectMocks
	private StockServiceImpl stockService;

	@Test
	public void testgetAllStock() throws Exception {
		Page<Stock> stocks = new PageImpl<>(
				List.of(Stock.builder().id(1l).name("TESTSTOCK").currentPrice(BigDecimal.valueOf(12.44)).lastUpdate(LocalDateTime.now()).build()));
		when(stockRepository.findAll(Mockito.any(Pageable.class))).thenReturn(stocks);
		StockDto stocksResponse = stockService.getAll(0,10,"id","asc").get(0);
		assertThat(stocksResponse).isNotNull();	
		assertThat(stocksResponse.getCurrentPrice()).isEqualTo(BigDecimal.valueOf(12.44));
	}

	@Test
	public void testgetOneStock() throws Exception {
		Stock stock =Stock.builder().id(1l).name("TESTSTOCK").currentPrice(BigDecimal.valueOf(12.44)).lastUpdate(LocalDateTime.now()).build();
		when(stockRepository.findById((Mockito.any()))).thenReturn(Optional.of(stock));
		StockDto stocksResponse = stockService.getStock(1);
		assertThat(stocksResponse).isNotNull();	
		assertThat(stocksResponse.getCurrentPrice()).isEqualTo(BigDecimal.valueOf(12.44));
	}
	
	@Test
	public void testsaveStock() throws Exception {
		StockDto stockDto =StockDto.builder().id(1l).name("TESTSTOCK").currentPrice(BigDecimal.valueOf(12.44)).lastUpdate(LocalDateTime.now()).build();
		Stock stock =Stock.builder().id(1l).name("TESTSTOCK").currentPrice(BigDecimal.valueOf(12.44)).lastUpdate(LocalDateTime.now()).build();
		when(stockRepository.save((Mockito.any()))).thenReturn(stock);
		StockDto stocksResponse = stockService.createStock(stockDto);
		assertThat(stocksResponse).isNotNull();	
		assertThat(stocksResponse.getCurrentPrice()).isEqualTo(BigDecimal.valueOf(12.44));
	}
	
	@Test
	public void testdeleteStock() throws Exception {
	    org.mockito.BDDMockito.willDoNothing().given(stockRepository).deleteById((Mockito.anyInt()));
		stockService.deleteStock(1);
		verify(stockRepository, times(1)).deleteById(1);
	}
	
	@Test
	public void testupdateStock() throws Exception {
		StockDto stockDto =StockDto.builder().id(1l).name("TESTSTOCK").currentPrice(BigDecimal.valueOf(12.44)).lastUpdate(LocalDateTime.now()).build();
		Stock stock =Stock.builder().id(1l).name("TESTSTOCK").currentPrice(BigDecimal.valueOf(12.44)).lastUpdate(LocalDateTime.now()).build();
		when(stockRepository.findById((Mockito.any()))).thenReturn(Optional.of(stock));
		when(stockRepository.save((Mockito.any()))).thenReturn(stock);
		StockDto stocksResponse = stockService.updateStock(stockDto, 1);
		assertThat(stocksResponse).isNotNull();	
		assertThat(stocksResponse.getCurrentPrice()).isEqualTo(BigDecimal.valueOf(12.44));
	}
}