package com.wiredbraincoffee.reward;


import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RewardByDiscountServiceTest {

    @Test
    void setNeededPoints() {

        RewardByDiscountService reward = new RewardByDiscountService();

        reward.setNeededPoints(100);

        assertEquals(100, reward.getNeededPoints());

    }
}
