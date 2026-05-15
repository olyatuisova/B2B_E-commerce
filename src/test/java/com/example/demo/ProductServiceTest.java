package com.example.demo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.util.Arrays;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;


class ProductServiceTest {

    @Mock //для бд, щоб тест не записуввав реальні дані
    private ProductRepository productRepository;

    @InjectMocks
    private ProductService productService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSaveProduct_PositiveScenary() {
        // Arrange (Підготовка)
        PhysicalProduct validProduct = new PhysicalProduct();
        validProduct.setName("server block");
        validProduct.setBasePrice(150000);


        when(productRepository.save(any(Product.class))).thenReturn(validProduct);
        Product savedProduct = productService.save(validProduct);
        assertNotNull(savedProduct); //перевірка чи товар не порожній
        assertEquals("server block", savedProduct.getName()); // перевірка на збіг імя

        // перевірка, чи метод був в
        verify(productRepository, times(1)).save(validProduct);
    }

    // 2 позитивний сценарій
    @Test
    void testGetAllProducts_PositiveScenary() {
        PhysicalProduct p1 = new PhysicalProduct(); p1.setName("Macbook");
        PhysicalProduct p2 = new PhysicalProduct(); p2.setName("Scanner");

        when(productRepository.findAll()).thenReturn(Arrays.asList(p1, p2));
        List<Product> products = productService.getAll();

        assertEquals(2, products.size()); // має повернутись 2 товари(рівно2)
        assertEquals("Macbook", products.get(0).getName());
        verify(productRepository, times(1)).findAll();
    }

    @Test
    void testSaveProduct_NegativeScenary_NegativePrice() {
        PhysicalProduct invalidProduct = new PhysicalProduct();
        invalidProduct.setName("defective thing");
        invalidProduct.setBasePrice(-500);

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            productService.save(invalidProduct);
        });

        assertEquals("price cant be negative :(", exception.getMessage());


        verify(productRepository, never()).save(any(Product.class));
    }
}