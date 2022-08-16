package com.payconiq.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.payconiq.model.Stock;
import com.payconiq.service.StockService;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@RequestMapping("/api/stocks")
public class StockController {

	@Autowired
	private StockService stockService;

	// Get All Stock Details for display on UI
	@GetMapping
	public ResponseEntity<List<Stock>> getAll( @RequestParam(required = false) Integer page ) {
		log.info("Getting all Stock Details present in DB");
		try {
			List<Stock> stocksList = stockService.getAll(page).toList();
			return ResponseEntity.ok(stocksList);
		} catch (Exception ex) {
			log.debug("Error while retriving data from DB ", ex);
			return ResponseEntity.internalServerError().build();
		}
	}

	// Get Stock Details for particular id
	@GetMapping("/{id}")
	public ResponseEntity<Stock> getStock(@PathVariable int id) {
		log.info("Getting Stock Details present in DB for id :{}", id);
		try {
			Stock stock = stockService.getStock(id);
			return ResponseEntity.ok(stock);
		} catch (Exception ex) {
			log.debug("Error while retriving data from DB ", ex);
			return ResponseEntity.internalServerError().build();
		}
	}

	// Create new Stock 
	@PostMapping
	public ResponseEntity<Stock> createStock(@RequestBody Stock stock) {
		log.info("Creating Stock Details present in DB");
		try {
			stock = stockService.createStock(stock);
			return ResponseEntity.status(HttpStatus.CREATED).body(stock);
		} catch (Exception ex) {
			log.debug("Error while retriving data from DB ", ex);
			return ResponseEntity.internalServerError().build();
		}
	}

	// Delete Stock Details for particular id
	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteStock(@PathVariable int id) {
		log.info("Delete Stock Details present in DB");
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
	public ResponseEntity<Stock> updateStock(@RequestBody Stock stock, @PathVariable int id) {
		log.info("Update Stock Details present in DB");
		try {
			stockService.updateStock(stock,id);
			return ResponseEntity.ok(stock);
		} catch (Exception ex) {
			log.debug("Error while retriving data from DB ", ex);
			return ResponseEntity.internalServerError().build();
		}
	}

}
