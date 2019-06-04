package com.wiredbraincoffee.reward;

import com.wiredbraincoffee.product.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.Duration;
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

        assertAll("Reward info errors",
                () -> assertNotNull(info),
                () -> assertEquals(2.99, info.getDiscount()),
                        () ->  assertEquals(100, info.getPointsRedeemed()));
    }

    @Test
    @DisplayName("Exception is thrown when invalid product ID")
    void exceptionThrownWhenInvalidProductID() {
        long productId = 0;
        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            reward.setGiftProductId(productId);
        });
        assertTrue(exception.getMessage().contains(String.valueOf(productId)));
    }

    @Test
    @DisplayName("Should not exceed timeout")
    void timeoutNotExceeded() {
        int numberOfProducts = 2;
        reward.setGiftProductId(numberOfProducts - 1);

        RewardInformation info = assertTimeoutPreemptively(
                Duration.ofMillis(4),
                () ->
                        reward.applyReward(
                                buildSampleOrder(numberOfProducts),
                                200
                        )
        );

        assertEquals(2.99, info.getDiscount());
    }


    private List<Product> buildSampleOrder(int numberOfProducts) {
        List<Product> list = IntStream.range(1, numberOfProducts)
                .mapToObj(i -> new Product(i, "Product " + 1, 2.99))
                .collect(toList());
        return list;
    }

}
