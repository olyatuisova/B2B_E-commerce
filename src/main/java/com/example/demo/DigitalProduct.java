package com.example.demo;

import jakarta.persistence.Entity;

@Entity
public class DigitalProduct extends Product {

  private String downloadLink;
  private String licenseType;


  public DigitalProduct() {
  }


  public DigitalProduct(String name, double price, String category,
                        String downloadLink, String licenseType) {
    super(name, price, category);
    this.downloadLink = downloadLink;
    this.licenseType = licenseType;
    this.stock = Integer.MAX_VALUE;
  }

  @Override
  public double calculateFinalPrice(Customer customer) {
    return switch (licenseType) {
      case "team" -> getBasePrice() * 0.9;
      case "enterprise" -> getBasePrice() * 0.7;
      default -> getBasePrice();
    };
  }

  @Override
  public String getProductType() {
    return "Digital product";
  }


  public String getDownloadLink() { return downloadLink; }
  public void setDownloadLink(String downloadLink) { this.downloadLink = downloadLink; }
  public String getLicenseType() { return licenseType; }
  public void setLicenseType(String licenseType) { this.licenseType = licenseType; }
  @Override
  public String getDiscountType() {
    return "Digital License Discount";
  }
}