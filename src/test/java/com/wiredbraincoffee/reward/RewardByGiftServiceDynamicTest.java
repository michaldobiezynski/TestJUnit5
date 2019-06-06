package com.wiredbraincoffee.reward;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.TestFactory;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.DynamicTest.dynamicTest;

public class RewardByGiftServiceDynamicTest {

    @BeforeEach
    void setUp() {
        System.out.println("BeforeEach");
    }

    @TestFactory
    Collection<DynamicTest> dynamicTestCollection() {
        return Arrays.asList(
                dynamicTest(
                        "1st dynamic test",() -> assertEquals(1,1)),
                dynamicTest(
                        "2nd dynamic test",() -> assertEquals(1,1))
                );
    }
}
