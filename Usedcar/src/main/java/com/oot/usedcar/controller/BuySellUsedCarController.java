package com.oot.usedcar.controller;

import java.math.BigDecimal;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.oot.usedcar.domain.Car;
import com.oot.usedcar.domain.CarReservation;
import com.oot.usedcar.domain.UsedCar;
import com.oot.usedcar.form.UsedCarSearchForm;
import com.oot.usedcar.form.EstimatePriceForm;
import com.oot.usedcar.form.ReserveForm;
import com.oot.usedcar.service.InitialDataService;
import com.oot.usedcar.service.car.CarService;
import com.oot.usedcar.service.car.UsedCarService;
import com.oot.usedcar.service.estimate.EstimatePriceService;
import com.oot.usedcar.service.reserve.ReserveService;

@Controller
public class BuySellUsedCarController {

	@Autowired
	EstimatePriceService estimatePriceService;

	@Autowired
	CarService carService;
	
	@Autowired
	UsedCarService usedCarService;
	
	@Autowired
	ReserveService reserveService;

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
	    initialDataService.initailUsedCar();
	    
	    return "initial"; 
	  } 
	
	@RequestMapping(value = { "/dashboard" }, method = RequestMethod.GET)
	public String dashboard(Model model) {
		System.out.println("dashboard");
		return "dashboard/index";
	}
	
	@RequestMapping(value = { "/estimatePrice" }, method = RequestMethod.GET)
	public String estimatePrice(Model model, String t) {
		model.addAttribute("esitmatePriceForm", new EstimatePriceForm());
		System.out.println("get estimatePrice");
		return "estimate";
	}

	@RequestMapping(value = "/estimatePrice", method = RequestMethod.POST)
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
		model.addAttribute("carSearch", new UsedCarSearchForm());
		System.out.println("get car search");
		return "usedcarsearchform";
	}

	@RequestMapping(value = { "/carSearch" }, method = RequestMethod.POST)
	public String search(@Valid UsedCarSearchForm carSearch, BindingResult result) {
		System.out.println("search");
		
		String car_brand = carSearch.getBrand();
		String car_model = carSearch.getModel();
		String car_submodel = carSearch.getSubModel();
		int car_year = carSearch.getYear();
		int car_kilometer = carSearch.getKilometer();
		 
		UsedCar used_car = usedCarService.findByBrandAndModelAndSubmodelAndYearAndKilometer
				(car_brand, car_model, car_submodel, car_year, car_kilometer);
		if(used_car != null) {
			System.out.println("Used car is null.");
		} else {
			System.out.println(used_car.getId().toString());
			System.out.println(used_car.getColor());
			System.out.println(used_car.getCarId());
			System.out.println(used_car.getPrice().toString());
			System.out.println(used_car.getYear());
			System.out.println(used_car.getStatus());
			System.out.println(used_car.getReceivingDate());
		}
		
		if (result.hasErrors())
			System.out.println("hasError");
		return "index";
	}
 
	@RequestMapping(value = { "/sell" }, method = RequestMethod.GET)
	public String sell(Model model, String t, String t2) {
		System.out.println("sell");
		return "index";
	}

	@RequestMapping(value = { "/reserve/{carId}" }, method = RequestMethod.GET)
	public String reserve(Model model, @PathVariable("carId") String carId) {
		 
		System.out.println("reserve car id = " + carId);
		
		//car id from search form
		//set car detail to form
		Car car = new Car();
		car.setId(Long.parseLong("999"));
		car.setBrand("Toyota");
		
		ReserveForm reserveForm = new ReserveForm();
		
		reserveForm.setName("Testname");
		reserveForm.setAddress("address");
		reserveForm.setPhoneNumber("000000");
		reserveForm.setReserveCar(car);
		
		model.addAttribute("reserveForm", reserveForm);
		
		return "reserveForm";

	}

	@RequestMapping(value = { "/saveReserve" }, method = RequestMethod.POST)
	public String saveReserve(Model model, @Valid ReserveForm reserveForm) {
		
		CarReservation carReserve = new CarReservation();
		carReserve.setId(Long.parseLong("1"));
		carReserve.setName("Test");
		carReserve.setAddress("testtttttttt");
		carReserve.setPhoneNumber("000000");
		
		reserveService.save(carReserve);
		System.out.println("saveReserve");
		return "index";
	}
}
