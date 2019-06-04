package com.wiredbraincoffee.reward;

import com.wiredbraincoffee.product.Product;
import org.junit.jupiter.api.BeforeEach;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.toList;

public class RewardByGiftServiceTest {

    private RewardByGiftService reward = null;

    @BeforeEach
    void setUp() {
        reward = new RewardByGiftService();
        reward.setGiftProductId(4);
        reward.setNeededPoints(100);
    }

    private List<Product> buildSampleOrder(int numberOfProducts) {
        List<Product> list = IntStream.range(1, numberOfProducts)
                .mapToObj(i -> new Product(i, "Product " + 1, 2.99))
                .collect(toList());
        return list;
    }

}
