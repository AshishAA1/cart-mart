package com.mart.entities;

import java.util.Arrays;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.Version;

@Entity(name = "crates")
public class Crate {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(nullable = false)
	private Long crateId;

	@Column(name="crateName")
	private String crateName;

	@Column(name="crateDescription")
	private String crateDescription;

	@Column(name="cratePrice")
	private Integer cratePrice;

	@Column(name="crateColor")
	private String crateColor;

	@Column(name="sellerInfo")
	private String sellerInfo;

	@Column(name="address")
	private String address;

	@Column(name="state")
	private String state;

	@Column(name="phoneNo")
	private String phoneNo;

	@Column(name="crateMeasurment")
	private String crateMeasurment;

	@Column(name="crateImgPath")
	private String crateImgPath;

	@Lob
	@Column(name="crate_img", columnDefinition = "LONGBLOB")
	private byte[] crateImg;
	
	@Version
	@Column(name="version")
    private Integer version;

	public Crate() {

	}

	public Crate(Long crateId, String crateName, String crateDescription, Integer cratePrice, String crateColor,
			String sellerInfo, String address, String state, String phoneNo, String crateMeasurment,
			String crateImgPath, byte[] crateImgbyteArray) {
		super();
		this.crateId = crateId;
		this.crateName = crateName;
		this.crateDescription = crateDescription;
		this.cratePrice = cratePrice;
		this.crateColor = crateColor;
		this.sellerInfo = sellerInfo;
		this.address = address;
		this.state = state;
		this.phoneNo = phoneNo;
		this.crateMeasurment = crateMeasurment;
		this.crateImgPath = crateImgPath;
		this.crateImg = crateImg;
	}
	
	public Crate(Long crateId, String crateName, String crateDescription, Integer cratePrice, String crateColor,
			String sellerInfo, String address, String state, String phoneNo, String crateMeasurment,
			String crateImgPath) {
		
		this.crateId = crateId;
		this.crateName = crateName;
		this.crateDescription = crateDescription;
		this.cratePrice = cratePrice;
		this.crateColor = crateColor;
		this.sellerInfo = sellerInfo;
		this.address = address;
		this.state = state;
		this.phoneNo = phoneNo;
		this.crateMeasurment = crateMeasurment;
		this.crateImgPath = crateImgPath;
		
	}

	public Long getCrateId() {
		return crateId;
	}

	public void setCrateId(Long crateId) {
		this.crateId = crateId;
	}

	public String getCrateName() {
		return crateName;
	}

	public void setCrateName(String crateName) {
		this.crateName = crateName;
	}

	public String getCrateDescription() {
		return crateDescription;
	}

	public void setCrateDescription(String crateDescription) {
		this.crateDescription = crateDescription;
	}

	public Integer getCratePrice() {
		return cratePrice;
	}

	public void setCratePrice(Integer cratePrice) {
		this.cratePrice = cratePrice;
	}

	public String getCrateColor() {
		return crateColor;
	}

	public void setCrateColor(String crateColor) {
		this.crateColor = crateColor;
	}

	public String getSellerInfo() {
		return sellerInfo;
	}

	public void setSellerInfo(String sellerInfo) {
		this.sellerInfo = sellerInfo;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getPhoneNo() {
		return phoneNo;
	}

	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}

	public String getCrateMeasurment() {
		return crateMeasurment;
	}

	public void setCrateMeasurment(String crateMeasurment) {
		this.crateMeasurment = crateMeasurment;
	}

	public String getCrateImgPath() {
		return crateImgPath;
	}

	public void setCrateImgPath(String crateImgPath) {
		this.crateImgPath = crateImgPath;
	}

	public byte[] getCrateImgbyteArray() {
		return crateImg;
	}

	public void setCrateImgbyteArray(byte[] crateImgbyteArray) {
		this.crateImg = crateImgbyteArray;
	}
	
	

	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	@Override
	public String toString() {
		return "Crate [crateId=" + crateId + ", crateName=" + crateName + ", crateDescription=" + crateDescription
				+ ", cratePrice=" + cratePrice + ", crateColor=" + crateColor + ", sellerInfo=" + sellerInfo
				+ ", address=" + address + ", state=" + state + ", phoneNo=" + phoneNo + ", crateMeasurment="
				+ crateMeasurment + ", crateImgPath=" + crateImgPath + ", crateImgbyteArray="
				+ Arrays.toString(crateImg) + "]";
	}

}
