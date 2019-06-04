package com.wiredbraincoffee.reward;


import com.wiredbraincoffee.product.Product;
import org.junit.jupiter.api.*;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class RewardByDiscountServiceTest {

    private RewardByDiscountService reward = null;


    @BeforeEach
    void setUp() {

        reward = new RewardByDiscountService();
        reward.setPercentage(0.1);
        reward.setNeededPoints(100);

        System.out.println("BeforeEach");

    }

    @Test
    void setNeededPoints() {
        System.out.println("Test setNeededPoints");

        assertEquals(100, reward.getNeededPoints());

    }


    @Test
    void setPercentageForPoints() {

        assertEquals(0.1, reward.getPercentage());

    }

    @Test
    void zeroCustomerPointS() {
        
        Product smallDecaf = new Product(1, "Small Decaf", 1.99);
        List<Product> order = Collections.singletonList(smallDecaf);

        RewardInformation info = reward.applyReward(order, 0);

        assertEquals(0, info.getDiscount());
        assertEquals(0, info.getPointsRedeemed());
    }
}
