package com.oot.usedcar.service.car;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.oot.usedcar.domain.UsedCar;
import com.oot.usedcar.repository.UsedCarRepository;

@Service
public class UsedCarServiceImplement implements UsedCarService {

	@Autowired
	private UsedCarRepository usedCarRepository;

	@Override
	public void save(UsedCar used_car) {
		usedCarRepository.save(used_car);
	}

	@Override
	public UsedCar findByBrandAndModelAndSubmodelAndYearAndKilometer(String brand, String model, String submodel, int year, int kilometer) {
		return usedCarRepository.findByBrandAndModelAndSubmodelAndYearAndKilometer(brand, model, submodel, year, kilometer);
	}

}
