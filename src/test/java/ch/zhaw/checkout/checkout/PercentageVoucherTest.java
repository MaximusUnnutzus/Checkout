package ch.zhaw.checkout.checkout;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import ch.zhaw.checkout.checkout.VoucherSubclasses.PercentageVoucher;

// XXX Aufgabe d)
public class PercentageVoucherTest {

    @Test
    public void testVoucher_withoutProducts() {
        var voucher = new PercentageVoucher(7);
        assertEquals(0, voucher.getDiscount(new ArrayList<Product>()), 0.01);
    }

    @ParameterizedTest
    @ValueSource(ints = { 1, 2, 5, 20, 49, 50 })
    public void testVoucher_withoutProducts_multipleValues(int value) {
        var voucher = new PercentageVoucher(value);
        assertEquals(0, voucher.getDiscount(new ArrayList<Product>()), 0.01);
    }

    // Übung 7 c)
    @Test
    public void testVoucher_withProducts() {
        var voucher = new PercentageVoucher(42);

        // old (Übung 6)
        /*
         * var product1 = new Product("id1", "Dunkle Schokolade", "A", 77);
         * var product2 = new Product("id2", "Weisse Schokolade", "A", 42);
         */

        // new (Übung 7)
        var product1 = mock(Product.class);
        var product2 = mock(Product.class);
        when(product1.getPrice()).thenReturn(77.00);
        when(product2.getPrice()).thenReturn(42.00);
        assertEquals(49.98, voucher.getDiscount(Arrays.asList(product1, product2)), 0.01);
    }

    // Übung 7 a) Teil 1 - Test erfolgreich
    @Test
    public void testVoucherGreaterThenFiftyTrue() {
        Exception exception = assertThrows(RuntimeException.class, () -> {
            var voucher = new PercentageVoucher(51);
        });

        String expectedMessage = "Error: Discount calue must be less or equal then 50";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    // Übung 7 a) Teil 2 Test erfolgreich
    @Test
    public void testVoucherEqualZeroTrue() {

        Exception exception = assertThrows(RuntimeException.class, () -> {
            var voucher = new PercentageVoucher(0);
        });

        String expectedMessage = "Error: Discount value must be greatern zero";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

}
