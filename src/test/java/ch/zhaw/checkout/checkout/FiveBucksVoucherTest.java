package ch.zhaw.checkout.checkout;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import ch.zhaw.checkout.checkout.VoucherSubclasses.FiveBucksVoucher;

// XXX Aufgabe c)
public class FiveBucksVoucherTest {

    @Test
    public void testEmpty() {
        var voucher = new FiveBucksVoucher();
        assertEquals(0, voucher.getDiscount(new ArrayList<Product>()), 0.01);
    }

    @Test
    public void testTwo() {
        var products = new ArrayList<Product>();
        var voucher = new FiveBucksVoucher();
        products.add(new Product("id", "test", "test", 2));
        assertEquals(0, voucher.getDiscount(new ArrayList<Product>()), 0.01);
    }

    @Test
    public void testTen() {
        var voucher = new FiveBucksVoucher();
        var products = new ArrayList<Product>();
        products.add(new Product("id", "test", "test", 10));
        assertEquals(5, voucher.getDiscount(products), 0.01);
    }

}
