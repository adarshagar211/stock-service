package com.stock.controller;

import java.net.URI;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.stock.dto.StockDto;
import com.stock.service.StockService;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@CrossOrigin(origins = "http://localhost:8080")
@RequestMapping("/api/stocks")
public class StockController {

	public static final String STOCKS_URL = "/api/stocks/";
	
	@Autowired
	private StockService stockService;

	// Get All Stock Details
	@GetMapping
	public ResponseEntity<List<StockDto>> getAll( @RequestParam(value = "pageNo", defaultValue ="0", required = false) int pageNo,
                                                  @RequestParam(value = "pageSize", defaultValue ="10", required = false) int pageSize,
                                                  @RequestParam(value = "sortBy", defaultValue = "id", required = false) String sortBy,
                                                  @RequestParam(value = "sortDir", defaultValue ="asc", required = false) String sortDir){		
		log.info("Getting all Stock Details present in DB");
		List<StockDto> stocksList = stockService.getAll(pageNo, pageSize, sortBy, sortDir);
		return ResponseEntity.ok(stocksList);
	}

	// Get Stock Details for particular id
	@GetMapping("/{id}")
	public ResponseEntity<StockDto> getStock(@PathVariable int id) {
		log.info("Getting Stock Details present in DB for id :{}", id);
		StockDto stock = stockService.getStock(id);
		return ResponseEntity.ok(stock);
	}

	// Create new Stock 
	@PostMapping
	public ResponseEntity<StockDto> createStock(@Valid @RequestBody StockDto stockDto) {
		log.info("Creating Stock in DB with id {}", stockDto.getName());
		StockDto stock = stockService.createStock(stockDto);
		return ResponseEntity.created(URI.create(STOCKS_URL +stock.getId())).body(stock);
	}

	// Delete Stock Details for particular id
	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteStock(@PathVariable int id) {
		log.info("Delete Stock present in DB with id {}", id);
		stockService.deleteStock(id);
		return ResponseEntity.noContent().build();
	}

	// Update Stock Details for particular id
	@PatchMapping("/{id}")
	public ResponseEntity<StockDto> updateStock(@RequestBody StockDto stockDto, @PathVariable int id) {
		log.info("Update Stock present in DB with id : {}" , id);
		StockDto stock = stockService.updateStock(stockDto,id);
		return ResponseEntity.ok(stock);
	}

}
	