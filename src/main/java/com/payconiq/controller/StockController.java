package com.payconiq.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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

import com.payconiq.dto.StockDto;
import com.payconiq.model.Stock;
import com.payconiq.service.StockService;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@CrossOrigin(origins = "http://localhost:8080")
@RequestMapping("/api/stocks")
public class StockController {

	@Autowired
	private StockService stockService;

	// Get All Stock Details
	@GetMapping
	public ResponseEntity<List<StockDto>> getAll( @RequestParam(value = "pageNo", defaultValue ="0", required = false) int pageNo,
                                               @RequestParam(value = "pageSize", defaultValue ="10", required = false) int pageSize,
                                               @RequestParam(value = "sortBy", defaultValue = "id", required = false) String sortBy,
                                               @RequestParam(value = "sortDir", defaultValue ="asc", required = false) String sortDir ){
		
		log.info("Getting all Stock Details present in DB");
		try {
			List<StockDto> stocksList = stockService.getAll(pageNo, pageSize, sortBy, sortDir);
			return ResponseEntity.ok(stocksList);
		} catch (Exception ex) {
			log.debug("Error while retriving data from DB ", ex);
			return ResponseEntity.internalServerError().build();
		}
	}

	// Get Stock Details for particular id
	@GetMapping("/{id}")
	public ResponseEntity<StockDto> getStock(@PathVariable int id) {
		log.info("Getting Stock Details present in DB for id :{}", id);
		try {
			StockDto stock = stockService.getStock(id);
			return ResponseEntity.ok(stock);
		} catch (Exception ex) {
			log.debug("Error while retriving data from DB ", ex);
			return ResponseEntity.internalServerError().build();
		}
	}

	// Create new Stock 
	@PostMapping
	public ResponseEntity<StockDto> createStock(@RequestBody StockDto stockDto) {
		log.info("Creating Stock in DB with id {}", stockDto.getName());
		try {
			StockDto stock = stockService.createStock(stockDto);
			return ResponseEntity.status(HttpStatus.CREATED).body(stock);
		} catch (Exception ex) {
			log.debug("Error while retriving data from DB ", ex);
			return ResponseEntity.internalServerError().build();
		}
	}

	// Delete Stock Details for particular id
	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteStock(@PathVariable int id) {
		log.info("Delete Stock present in DB with id {}", id);
		try {
			stockService.deleteStock(id);
			return ResponseEntity.ok("Stock deleted");
		} catch (Exception ex) {
			log.debug("Error while retriving data from DB ", ex);
			return ResponseEntity.internalServerError().build();
		}
	}

	// Update Stock Details for particular id
	@PatchMapping("/{id}")
	public ResponseEntity<StockDto> updateStock(@RequestBody StockDto stockDto, @PathVariable int id) {
		log.info("Update Stock present in DB with id : {}" , id);
		try {
			StockDto stock = stockService.updateStock(stockDto,id);
			return ResponseEntity.ok(stock);
		} catch (Exception ex) {
			log.debug("Error while retriving data from DB ", ex);
			return ResponseEntity.internalServerError().build();
		}
	}

}
