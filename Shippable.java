public interface Shippable{
  double calculateShippingCost();

  double calculateShippingPrice();
  String getTrackingNumber();
  void updateStatus(String status);
}