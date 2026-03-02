public class Main {
    public static void main(String[] args) {

        System.out.println("B2B E-Commerce System\n");

        Customer customer1 = new Customer(
                1, "Bohdan Khudoba", "bohdan@company.ua",
                "pass123", "TOV Technologies", "12345678"
        );
        customer1.setCreditLimit(200_000);

        Customer customer2 = new Customer(
                2, "Bohdan Khudoba", "Bohdan_khudoba@bigcorporation.ua",
                "pass456", "BigCorp LLC", "87654321"
        );
        customer2.setCreditLimit(600_000);

        Admin admin = new Admin(
                3, "Maria Shevchenko", "maria@shop.ua",
                "admin123", "Sales"
        );
        admin.addPermission("MANAGE_PRODUCTS");
        admin.addPermission("APPROVE_ORDERS");

        PhysicalProduct laptop = new PhysicalProduct(
                1, "Laptop Dell", 35000, "Electronics", 2.5, 1
        );

        PhysicalProduct printer = new PhysicalProduct(
                2, "Printer HP", 8000, "Electronics", 5.0, 1
        );

        DigitalProduct softwareTeam = new DigitalProduct(
                3, "Microsoft Office", 5000, "Software", "team"
        );

        DigitalProduct softwareEnterprise = new DigitalProduct(
                4, "Adobe Creative Suite", 12000, "Software", "enterprise"
        );


        System.out.println("--- Admin Actions ---");
        admin.showDashboard();
        System.out.println();
        admin.addProduct(laptop);
        admin.addProduct(printer);
        admin.addProduct(softwareTeam);
        admin.addProduct(softwareEnterprise);

        System.out.println("\n--- Product Catalog ---");

        laptop.displayInfo();
        laptop.displayInfo(true);
        laptop.displayInfo(customer1);
        laptop.displayInfo(customer2);

        System.out.println();
        softwareEnterprise.displayInfo();
        softwareEnterprise.displayInfo(customer1);

        System.out.println("\n Shipping Info");
        System.out.println(laptop.getName() + ":");
        System.out.println("Shipping cost: " + laptop.calculateShippingCost() + " uah");
        System.out.println("Shipping price (with VAT): " + laptop.calculateShippingPrice() + " uah");
        laptop.updateStatus("Preparing");
        laptop.updateStatus("Shipped");

        System.out.println("\nShopping Cart (customer 1)");
        customer1.addToCart(laptop, 3);
        customer1.addToCart(softwareTeam, 5);
        customer1.addToCart(printer, 2);

        System.out.println("\nOrder Processing");
        Order order1 = new Order();
        order1.setStatus("confirmed");
        System.out.println("Order created for: " + customer1.getCompanyName());

        // Адмін підтверджує
        System.out.println("Admin " + admin.getName() + " approved the order.");

        System.out.println("\nDiscounts");
        double originalPrice = 10000;
        System.out.println("Original price: " + originalPrice + " uah");
        System.out.println("Physical discount (" + laptop.getDiscountType() + "): "
                + laptop.applyDiscount(originalPrice) + " uah");
        System.out.println("Digital discount (" + softwareTeam.getDiscountType() + "): "
                + softwareTeam.applyDiscount(originalPrice) + " uah");

        System.out.println("\nUser Dashboards");
        User[] users = {customer1, customer2, admin};
        for (User user : users) {
            System.out.println("Role: " + user.getRole());
            user.showDashboard();
            System.out.println();
        }

        System.out.println("All Products");
        Product[] products = {laptop, printer, softwareTeam, softwareEnterprise};
        for (Product p : products) {
            System.out.println(p.getProductType() + "-"
                    + p.getName() + "Base: " + p.getBasePrice() + " uah"
                    + "VIP price: " + p.calculateFinalPrice(customer2) + " uah");
        }
    }
}
