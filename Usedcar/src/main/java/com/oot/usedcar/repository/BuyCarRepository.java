package com.oot.usedcar.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.oot.usedcar.domain.BuyCar;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public interface BuyCarRepository extends JpaRepository<BuyCar, Long> {
	BuyCar findById(Long id);
	BuyCar findByCustomerId(String customerId);
	BuyCar findByName(String name);
	BuyCar findByLastname(String lastname);
	BuyCar findByDate(Date date);
	BuyCar findByPrice(BigDecimal price);
	
}



