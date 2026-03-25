public class Main {
    public static void main(String[] args) {

        Customer customer = new Customer(
                1, "Ivan", "ivan@company.ua", "pass123", "TOV Tech", "12345678"
        );
        customer.setCreditLimit(200_000);

        Admin admin = new Admin(
                2, "Maria", "maria@shop.ua", "admin123", "Sales"
        );


        PhysicalProduct laptop = new PhysicalProduct(
                1, "Laptop Dell", 35000, "Electronics", 2.5, 1
        );
        PhysicalProduct printer = new PhysicalProduct(
                2, "Printer HP", 8000, "Electronics", 5.0, 1
        );
        DigitalProduct software = new DigitalProduct(
                3, "MS Office", 5000, "Software", "enterprise"
        );

        User[] users = {customer, admin};
        for (User u : users) {
            u.showDashboard();
        }


        Shippable shippable = laptop;
        System.out.println("Shipping cost: " + shippable.calculateShippingCost() + " uah");
        shippable.updateStatus("Preparing");


        Discountable discountable = software;
        System.out.println("Discount: " + discountable.getDiscountType()
                + " → " + discountable.applyDiscount(5000) + " uah");

        Product.ProductStats.printStats();
        User.UserStats.printStats();


        Product.ProductReview review1 = laptop.new ProductReview("Ivan", 5, "Great laptop");
        Product.ProductReview review2 = software.new ProductReview("Maria", 4, "Works well");
        review1.print();
        review2.print();

        Discountable flashSale = new Discountable() {
            @Override
            public double applyDiscount(double price) { return price * 0.50; }
            @Override
            public String getDiscountType() { return "Flash Sale -50%"; }
        };
        System.out.println("Flash sale: " + flashSale.getDiscountType()
                + " → " + flashSale.applyDiscount(laptop.getBasePrice()) + " uah");


        Shippable express = new Shippable() {
            @Override
            public double calculateShippingCost() { return 500; }
            @Override
            public double calculateShippingPrice() { return 600; }
            @Override
            public String getTrackingNumber() { return "EXPRESS-001"; }
            @Override
            public void updateStatus(String status) {
                System.out.println("Express delivery: " + status);
            }
        };
        System.out.println("Express shipping: " + express.calculateShippingCost() + " uah");
        express.updateStatus("Out for delivery");


        Product promo = new Product(99, "Promo Bundle", 1000, "Promo") {
            @Override
            public double calculateFinalPrice(Customer c) { return getBasePrice() * 0.60; }
            @Override
            public String getProductType() { return "Promo product"; }
            @Override
            public String getDiscountType() { return "Promo -40%"; }
        };
        System.out.println(promo.getName() + " price: "
                + promo.calculateFinalPrice(customer) + " uah");


        customer.addToCart(laptop, 2);
        customer.addToCart(printer, 1);
        customer.addToCart(software, 5);
        customer.addToCart(laptop, 1);
        customer.printCart();

        Order order = new Order(customer);
        order.fillFromCart(customer.getCart());
        order.setPaymentMethod("Invoice NET30");
        order.confirm();
        order.printOrder();

        order.setStatus("SHIPPED");
        order.setStatus("DELIVERED");
        MyLinkedList<Integer> intList = new MyLinkedList<>();
        intList.add(5);
        intList.add(2);
        intList.add(9);
        intList.sort();
        intList.print();

        MyLinkedList<String> strList = new MyLinkedList<>();
        strList.add("banana");
        strList.add("apple");
        strList.add("cherry");
        strList.sort();
        strList.print();

        MyLinkedList<MyData> customList = new MyLinkedList<>();
        customList.add(new MyData(10));
        customList.add(new MyData(3));
        customList.add(new MyData(7));
        customList.sort();
        customList.print();
    }

}