package com.wiredbraincoffee.reward;


import com.wiredbraincoffee.product.Product;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RewardByDiscountServiceTest {

    @Test
    void setNeededPoints() {

        RewardByDiscountService reward = new RewardByDiscountService();

        reward.setNeededPoints(100);

        assertEquals(100, reward.getNeededPoints());

    }


    @Test
    void setPercentageForPoints() {
        RewardByDiscountService reward = new RewardByDiscountService();

        reward.setPercentage(0.1);

        assertEquals(0.1, reward.getPercentage());
    }

    @Test
    void zeroCustomerPointS() {
        RewardByDiscountService reward = new RewardByDiscountService();

        reward.setPercentage(0.1);
        reward.setNeededPoints(100);

        Product smallDecaf = new Product(1, "Small Decaf", 1.99);
        List<Product> order = Collections.singletonList(smallDecaf);

        RewardInformation info = reward.applyReward(order, 0);

        assertEquals(0, info.getDiscount());
        assertEquals(0, info.getPointsRedeemed());
    }
}
