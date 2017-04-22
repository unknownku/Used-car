package com.oot.usedcar.controller;

import java.math.BigDecimal;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.oot.usedcar.domain.Car;
import com.oot.usedcar.domain.UsedCar;
import com.oot.usedcar.form.CarSearchForm;
import com.oot.usedcar.form.EstimatePriceForm;
import com.oot.usedcar.service.InitialDataService;
import com.oot.usedcar.service.car.CarService;
import com.oot.usedcar.service.estimate.EstimatePriceService;

@Controller
public class BuySellUsedCarController {

	@Autowired
	EstimatePriceService estimatePriceService;

	@Autowired
	CarService carService;

	@Autowired
	InitialDataService initialDataService;

	@RequestMapping(value = { "/" }, method = RequestMethod.GET)
	public String index(Model model) {
		System.out.println("index");
		return "index";
	}
	
	@RequestMapping(value = { "/init" }, method = RequestMethod.GET)
	public String initial(Model model) {
		System.out.println("initial");
		initialDataService.initailUser();
		initialDataService.initailCar();
		initialDataService.initailBuyCar();
		return "initial";
	}

	@RequestMapping(value = { "/dashboard" }, method = RequestMethod.GET)
	public String dashboard(Model model) {
		System.out.println("dashboard");
		return "dashboard/index";
	}
	
	@RequestMapping(value = { "/estimatePrice" }, method = RequestMethod.POST)
	public String estimatePrice(Model model, String t) {
		model.addAttribute("esitmatePriceForm", new EstimatePriceForm());
		System.out.println("get estimatePrice");
		return "index";
	}

	@RequestMapping(value = "/estimatePrice", method = RequestMethod.GET)
	public String estimatePrice(@Valid EstimatePriceForm estimatePriceForm) {
		System.out.println("post estimatePrice");
		// Start mockup data
		estimatePriceForm.setBrand("TOYOTA");
		estimatePriceForm.setModel("Altis");
		estimatePriceForm.setYear(2015);
		estimatePriceForm.setKilometer(100000);
		estimatePriceForm.setFlooding(true);
		estimatePriceForm.setCrashing(true);
		estimatePriceForm.setUsingType(0);
		// End mockup data

		String brand = estimatePriceForm.getBrand();
		String model = estimatePriceForm.getModel();
		int year = estimatePriceForm.getYear();
		int kilometer = estimatePriceForm.getKilometer();
		boolean isFlooding = estimatePriceForm.isFlooding();
		boolean isCrashing = estimatePriceForm.isCrashing();
		int usingType = estimatePriceForm.getUsingType();

		Car car = carService.findByBrandAndModelAndYear(brand, model, year);
		BigDecimal middlePrice = car.getMiddlePrice();
		BigDecimal depreciationPrice = estimatePriceService.calculateDepreciationPrice(year, kilometer, isFlooding,
				isCrashing, usingType);
		BigDecimal estimatePrice = estimatePriceService.calculateEstimatePrice(middlePrice, depreciationPrice);
		System.out.println(estimatePrice.toString());
		return "index";
	}

	@RequestMapping(value = { "/buy" }, method = RequestMethod.GET)
	public String buy(Model model, String t) {
		System.out.println("buy");
		return "index";
	}

	@RequestMapping(value = { "/carSearch" }, method = RequestMethod.GET)
	public String carSearch(Model model, String t) {
		model.addAttribute("carSearch", new CarSearchForm());
		System.out.println("get estimatePrice");
		return "carsearchform";
	}

	@RequestMapping(value = { "/carSearch" }, method = RequestMethod.POST)
	public String search(@Valid CarSearchForm carSearchForm) {
		System.out.println("search");
		
		String car_brand = carSearchForm.getBrand();
		String car_model = carSearchForm.getModel();
		String car_submodel = carSearchForm.getSubModel();
		int car_year = carSearchForm.getYear();
		int car_kilometer = carSearchForm.getKilometer();
		
		UsedCar used_car = carService.findUsedCars(car_brand, car_model, car_submodel, car_year, car_kilometer);
		System.out.println(used_car.getId().toString());
		System.out.println(used_car.getColor());
		System.out.println(used_car.getCarId());
		System.out.println(used_car.getPrice().toString());
		System.out.println(used_car.getYear());
		System.out.println(used_car.getStatus());
		System.out.println(used_car.getReceivingDate());
		return "index";
	}

	@RequestMapping(value = { "/reserve" }, method = RequestMethod.GET)
	public String reserve(Model model, String t, String t2) {
		System.out.println("reserve");
		return "index";
	}

	@RequestMapping(value = { "/sell" }, method = RequestMethod.GET)
	public String sell(Model model, String t, String t2) {
		System.out.println("sell");
		return "index";
	}

}
