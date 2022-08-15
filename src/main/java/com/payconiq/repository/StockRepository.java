package com.payconiq.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.payconiq.model.Stock;

@Repository
public interface StockRepository extends JpaRepository<Stock, Integer> {

}
