package com.oot.usedcar.form;

import java.math.BigDecimal;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class EstimatePriceForm {

	private String brand;
	private String model;
	private String subModel;
	private int year;	

	@Min(value = 1, message = "The value must be positive")
	private int kilometer;
	private boolean isFlooding;
	private boolean isCrashing;
	private int usingType;
	private BigDecimal estimatePrice;

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getSubModel() {
		return subModel;
	}

	public void setSubModel(String subModel) {
		this.subModel = subModel;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public int getKilometer() {
		return kilometer;
	}

	public void setKilometer(int kilometer) {
		this.kilometer = kilometer;
	}

	public boolean isFlooding() {
		return isFlooding;
	}

	public void setFlooding(boolean isFlooding) {
		this.isFlooding = isFlooding;
	}

	public boolean isCrashing() {
		return isCrashing;
	}

	public void setCrashing(boolean isCrashing) {
		this.isCrashing = isCrashing;
	}

	public int getUsingType() {
		return usingType;
	}

	public void setUsingType(int usingType) {
		this.usingType = usingType;
	}

	public BigDecimal getEstimatePrice() {
		return estimatePrice;
	}

	public void setEstimatePrice(BigDecimal estimatePrice) {
		this.estimatePrice = estimatePrice;
	}

}
