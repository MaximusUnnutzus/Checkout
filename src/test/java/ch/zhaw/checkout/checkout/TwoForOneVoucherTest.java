package ch.zhaw.checkout.checkout;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.aggregator.ArgumentsAccessor;
import org.junit.jupiter.params.provider.CsvSource;

import ch.zhaw.checkout.checkout.VoucherSubclasses.TwoForOneVoucher;

public class TwoForOneVoucherTest {

    //Übung 6 - old
    @Test
    public void testOtherProduct() {
        var product1 = new Product("id1", "name", null, 77);
        var product2 = new Product("id2", "name", null, 77);


        
        var voucher = new TwoForOneVoucher(product1);
        var products = new ArrayList<Product>();
        products.add(product1);
        products.add(product2);

        assertEquals(0, voucher.getDiscount(products), 0.01);
    }



    @ParameterizedTest
    @CsvSource({ "0,0", "1,0", "2,1", "3,1", "4,2" })
    public void testMultipleProducts(ArgumentsAccessor argumentsAccessor) {
        var product = new Product("id", "name", null, 77);
        var voucher = new TwoForOneVoucher(product);
        var products = new ArrayList<Product>();
        for (var i = 0; i < argumentsAccessor.getInteger(0); i++) {
            products.add(product);
        }
        var expectedPrice = argumentsAccessor.getInteger(1) * 77;
        assertEquals(expectedPrice, voucher.getDiscount(products), 0.01);
    }

}
