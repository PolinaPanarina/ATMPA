package com.epam.tamentoring.unittests;

import com.epam.tamentoring.bo.Product;
import com.epam.tamentoring.bo.ShoppingCart;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class ProductTests {

    protected static Product product1;
    protected static Product product2;

    @BeforeAll
    public static void setUp() {
        product1 = new Product();
        product2 = new Product();

        product1.setId(1);
        product1.setName("A");
        product1.setPrice(15.00);
        product1.setQuantity(2);

        product2.setId(2);
        product2.setName("B");
        product2.setPrice(10.50);
        product2.setQuantity(2);
    }

    @Test
    public void shouldAddProductToCart() {
        List<Product> productList = new ArrayList<>();
        ShoppingCart shoppingCart = new ShoppingCart(productList);
        shoppingCart.addProductToCart(product1);

        Assertions.assertEquals(1, shoppingCart.getProducts().size());
        Assertions.assertEquals(product1, shoppingCart.getProductById(1));
    }

    @Test
    public void shouldRemoveProductFromTheCart() {
        List<Product> productList = new ArrayList<>();
        ShoppingCart shoppingCart = new ShoppingCart(productList);
        shoppingCart.addProductToCart(product1);
        shoppingCart.addProductToCart(product2);

        shoppingCart.removeProductFromCart(product1);
        Assertions.assertEquals(1, shoppingCart.getProducts().size());
        Assertions.assertEquals(product2, shoppingCart.getProductById(2));

        shoppingCart.removeProductFromCart(product2);
        Assertions.assertEquals(0, shoppingCart.getProducts().size());
    }

    @Test
    public void shouldGetTotalPrice() {
        List<Product> productList = new ArrayList<>();
        ShoppingCart shoppingCart = new ShoppingCart(productList);
        shoppingCart.addProductToCart(product1);
        shoppingCart.addProductToCart(product2);
        double expectedPrice = product1.getPrice() * product1.getQuantity() + product2.getPrice() * product2.getQuantity();

        Assertions.assertEquals(expectedPrice, shoppingCart.getCartTotalPrice());
    }
}
