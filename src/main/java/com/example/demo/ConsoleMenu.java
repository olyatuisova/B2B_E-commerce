package com.example.demo;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Scanner;

@Component
public class ConsoleMenu implements CommandLineRunner {

    private final ProductService productService;

    public ConsoleMenu(ProductService productService) {
        this.productService = productService;
    }

    @Override
    public void run(String... args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        System.out.println("  Console interface successfully started!  ");
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");

        while (running) {
            System.out.println("\n~MAIN MENU~");
            System.out.println("1 - Show all products");
            System.out.println("2 - Add a new product");
            System.out.println("0 - Exit menu");
            System.out.print("Your choice: ");

            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    List<Product> products = productService.getAll();
                    if (products.isEmpty()) {
                        System.out.println("-> The database is currently empty.");
                    } else {
                        System.out.println("\n-> Product list:");
                        for (Product p : products) {
                            System.out.println("ID: " + p.getId() + " | Name: " + p.getName());
                        }
                    }
                    break;
                case "2":
                    try {
                        System.out.print("Enter product name: ");
                        String name = scanner.nextLine();

                        PhysicalProduct newProduct = new PhysicalProduct();

                        newProduct.setName(name);


                        productService.save(newProduct);
                        System.out.println("Product '" + name + "' successfully saved to the database");

                    } catch (Exception e) {
                        System.out.println("Input error!");
                    }
                    break;
                case "0":
                    running = false;
                    System.out.println("-> Exiting the console menu.");
                    break;
                default:
                    System.out.println("-> Unknown command. Please enter 1, 2, or 0.");
            }
        }
    }
}