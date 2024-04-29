package com.epam.tamentoring.unittests;

import com.epam.tamentoring.bo.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class MockedTests {
    @Mock
    private DiscountUtility discountUtility;

    @InjectMocks
    private OrderService orderService;

    public UserAccount userAccount;
    protected Product product1;
    protected Product product2;
    protected List<Product> productList = new ArrayList<>();

    double expectedDiscount = 3.0;

    @BeforeEach
    public void setUp() {
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

        userAccount = new UserAccount();
        userAccount.setName("John");
        userAccount.setSurname("Smith");
        userAccount.setDateOfBirth("1990/10/10");

        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void shouldVerifyDiscountByProvidedUserDataUsingMocks() {
        ShoppingCart shoppingCart = new ShoppingCart(productList);
        shoppingCart.addProductToCart(product1);
        shoppingCart.addProductToCart(product2);
        userAccount.setShoppingCart(shoppingCart);

        when(discountUtility.calculateDiscount(userAccount)).thenReturn(expectedDiscount);

        double actualDiscount = userAccount.getShoppingCart().getCartTotalPrice() - orderService.getOrderPrice(userAccount);

        assertEquals(expectedDiscount, actualDiscount);

        verify(discountUtility, times(1)).calculateDiscount(userAccount);
        verifyNoMoreInteractions(discountUtility);
    }
}
