public abstract class Product implements Discountable {

    private int id;
    private String name;
    private double basePrice;
    private String category;
    protected int stock;


    public static class ProductStats {
        private static int totalProducts = 0;
        private static double totalCatalogValue = 0;

        public static void register(double price) {
            totalProducts++;
            totalCatalogValue += price;
        }

        public static void printStats() {
            System.out.println("Product Statistics");
            System.out.println("Total products:    " + totalProducts);
            System.out.println("Total catalog value: " + totalCatalogValue + " uah");
        }
    }

    public class ProductReview {
        private String reviewer;
        private int rating;
        private String comment;

        public ProductReview(String reviewer, int rating, String comment) {
            this.reviewer = reviewer;
            this.rating = rating;
            this.comment = comment;
        }

        public void print() {
            System.out.println("Review for [" + name + "]:");
            System.out.println("  " + reviewer + " → " + rating + "/5 - " + comment);
        }
    }

    public Product(int id, String name, double basePrice, String category) {
        this.id = id;
        this.name = name;
        this.basePrice = basePrice;
        this.category = category;
        ProductStats.register(basePrice); // реєструємо товар
    }

    public double getBasePrice() { return basePrice; }
    public String getName() { return name; }
    public int getId() { return id; }

    public abstract double calculateFinalPrice(Customer customer);
    public abstract String getProductType();

    public void displayInfo() {
        System.out.println("Product: " + name + " — " + basePrice + " uah");
    }

    public void displayInfo(boolean showStock) {
        displayInfo();
        if (showStock) {
            System.out.println("In stock: " + stock + " pcs");
        }
    }

    public void displayInfo(Customer customer) {
        displayInfo();
        System.out.println("Your price: " + calculateFinalPrice(customer) + " uah");
    }

    @Override
    public double applyDiscount(double price) {
        return price;
    }
}