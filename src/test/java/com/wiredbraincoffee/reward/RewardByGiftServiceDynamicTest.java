package com.wiredbraincoffee.reward;

import com.wiredbraincoffee.product.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.TestFactory;

import java.util.*;
import java.util.stream.LongStream;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.DynamicTest.dynamicTest;

public class RewardByGiftServiceDynamicTest {

    private RewardByGiftService reward;

    @BeforeEach
    void setUp() {
        reward = new RewardByGiftService();
        reward.setNeededPoints(100);
        System.out.println("BeforeEach");
    }

//    @TestFactory
//    Iterator<DynamicTest> dynamicTestCollection() {
//        return Arrays.asList(
//                dynamicTest(
//                        "1st dynamic test",() -> assertEquals(1,1)),
//                dynamicTest(
//                        "2nd dynamic test",() -> assertEquals(1,1))
//                ).iterator();
//    }

        @TestFactory
        Stream<DynamicTest> giftProductNotInOrderRewardNotApplied() {
        return getStreamOfRandomNumbers()
                .limit(5)
                .mapToObj(randomId ->
                        dynamicTest(
                                "Testing product ID " + randomId,
                                () -> {
                                    reward.setGiftProductId(randomId);
                                    RewardInformation info = reward.applyReward(
                                            getSampleOrder(), 200);

                                    assertEquals(0, info.getDiscount());
                                    assertEquals(0, info.getPointsRedeemed());
                                }
                        )
                );
    }

    private LongStream getStreamOfRandomNumbers() {
        Random r = new Random();
        return r.longs(1000,2000);

    }

    private List<Product> getSampleOrder() {

        Product smallDecaf = new Product(1, "Small Decaf", 1.99);
        Product bigDecaf = new Product(2, "bigDecaf", 2.49);
        Product bigLatte = new Product(1, "bigLatte", 2.99);
        Product bigTea = new Product(1, "bigTea", 2.99);
        Product Espresso = new Product(1, "Espresso", 2.99);

        return Arrays.asList(smallDecaf,bigDecaf,bigLatte,bigTea,Espresso);

    }
}
