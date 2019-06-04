package com.wiredbraincoffee.reward;

import com.wiredbraincoffee.product.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.toList;
import static org.junit.jupiter.api.Assertions.*;

public class RewardByGiftServiceTest {

    private RewardByGiftService reward = null;

    @BeforeEach
    void setUp() {
        reward = new RewardByGiftService();
        reward.setGiftProductId(4);
        reward.setNeededPoints(100);
    }

    @Test
    @DisplayName("Correct product ID is set")
    void correctProdcutID() {
        assertEquals(4,reward.getGiftProductId(),
                "Error, the product ID is incorrect"
                );
    }

    @Test
    @DisplayName("Reward applied with enough points")
    void rewardApplied() {

        RewardInformation info = reward.applyReward(buildSampleOrder(10), 200);

        assertAll("Reward infor errors",
                () -> assertNotNull(info),
                () -> assertEquals(2, info.getDiscount()),
                        () ->  assertEquals(10, info.getPointsRedeemed()));
    }

    private List<Product> buildSampleOrder(int numberOfProducts) {
        List<Product> list = IntStream.range(1, numberOfProducts)
                .mapToObj(i -> new Product(i, "Product " + 1, 2.99))
                .collect(toList());
        return list;
    }

}
