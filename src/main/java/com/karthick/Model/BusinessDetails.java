package com.karthick.Model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BusinessDetails {

	@JsonProperty("businessName")
	private String businessName;
	@JsonProperty("businessEmail")
	private String businessEmail;
	@JsonProperty("businessMobile")
	private String businessMobile;
	@JsonProperty("businessAddress")
	private String businessAddress;
	@JsonProperty("logo")
	private String logo;
	@JsonProperty("banner")
	private String banner;
	public String getBusinessName() {
		return businessName;
	}
	public void setBusinessName(String businessName) {
		this.businessName = businessName;
	}
	public String getBusinessEmail() {
		return businessEmail;
	}
	public void setBusinessEmail(String businessEmail) {
		this.businessEmail = businessEmail;
	}
	public String getBusinessMobile() {
		return businessMobile;
	}
	public void setBusinessMobile(String businessMobile) {
		this.businessMobile = businessMobile;
	}
	public String getBusinessAddress() {
		return businessAddress;
	}
	public void setBusinessAddress(String businessAddress) {
		this.businessAddress = businessAddress;
	}
	public String getLogo() {
		return logo;
	}
	public void setLogo(String logo) {
		this.logo = logo;
	}
	public String getBanner() {
		return banner;
	}
	public void setBanner(String banner) {
		this.banner = banner;
	}
	
}
