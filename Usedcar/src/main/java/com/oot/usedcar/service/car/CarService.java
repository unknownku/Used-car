package com.oot.usedcar.service.car;

import com.oot.usedcar.domain.Car;

public interface CarService {
	
	public void save(Car car); 
	public Car findByBrand(String brand);
	public Car findByModel(String model);
	public Car findBySubModel(String subModel);
	public Car findByYear(int year);
	public Car findByBrandAndModelAndYear(String brand, String model, int year);

}
