package com.example.order.common.util;

import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

import java.util.Random;

import static org.junit.Assert.*;


public class OddNumberGeneratorTest {

    @Test
    public void testGenerator() {
        String s = OddNumberGenerator.generateIndustryId();
        System.out.println(s);
    }

    @Test
    public void testGenerateBusinessId() {
        String s = OddNumberGenerator.generateBusinessId();
        System.out.println(s);
    }

    @Test
    public void testGenerateCustomerId() {
        String s = OddNumberGenerator.generateCustomerId();
        System.out.println(s);
    }

    @Test
    public void testGeneratPhoneNumber() {
        String s = OddNumberGenerator.generatePhoneNumber();
        System.out.println(s);
    }

    @Test
    public void testgenerateIdentifyNumber() {
        String s = OddNumberGenerator.generateIdentifyNumber();
        System.out.println(s);
    }
}