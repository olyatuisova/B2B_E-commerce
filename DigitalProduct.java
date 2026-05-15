public class DigitalProduct extends Product {

  private String downloadLink;
  private String licenseType;

  public DigitalProduct(int id, String name, double price,
                        String category, String licenseType) {
    super(id, name, price, category);
    this.licenseType = licenseType;
    this.stock = Integer.MAX_VALUE;
  }

  @Override
  public double calculateFinalPrice(Customer customer) {
    return switch (licenseType) {
      case "team"       -> getBasePrice() * 0.9;
      case "enterprise" -> getBasePrice() * 0.7;
      default           -> getBasePrice();
    };
  }

  @Override
  public String getProductType() { return "Digital product"; }

  @Override
  public double applyDiscount(double price) { return price * 0.95; }

  @Override
  public String getDiscountType() { return "License discount"; }
}