package ch.zhaw.checkout.checkout;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Arrays;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.aggregator.ArgumentsAccessor;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import ch.zhaw.checkout.checkout.VoucherSubclasses.ProductGroupVoucher;

public class ProductGroupVoucherTest {

    // Übung 7, Task e 1
    @ParameterizedTest
    @ValueSource(ints = { 1, 2, 5, 20, 49 })
    public void testProductVoucherCorrectCreation(int value) {
        var voucher = new ProductGroupVoucher("a", value);
        assertNotNull(voucher);
    }

    // Übung 7, Task e 2
    @ParameterizedTest
    @ValueSource(ints = { 0, -1, -Integer.MAX_VALUE })
    public void testProductVoucherFalseCreation(int value) {

        Exception exception = assertThrows(RuntimeException.class, () -> {
            var voucher = new ProductGroupVoucher("a", value);
        });

        assertTrue(exception.getMessage()
                .contains("Error: Product Group has to be defined and can't contain only spaces"));
    }

    // Übung 7, Task e 3
    @ParameterizedTest
    @ValueSource(strings = { "      ", "" })
    public void testProductVoucherFalseCreation(String value) {

        Exception exception = assertThrows(RuntimeException.class, () -> {
            var voucher = new ProductGroupVoucher(value, 42);
        });

        assertTrue(exception.getMessage()
                .contains("Error: Product Group has to be defined and can't contain only spaces"));
    }

    // Übung 7, Task f
    @ParameterizedTest
    // die letzten zwei selber erstellten Tests sind:
    // 1. Produktgruppe vom Voucher != Produktgruppe der 2 Produkte
    // 2. Betrag vom Voucher ist tiefer wie effektiver Warenkorb
    @CsvSource({ "p1,5,p1,7,p1,10,10", "p1,5,p1,3,p1,10,8", "p1,5,p2,7,p1,10,5", "p1,5,p1,5,p2,10,0",
            "p1,5,p1,5,p1,4,4" })
    public void testMultipleProducts(ArgumentsAccessor argumentsAccessor) {

        // für jeden Testfall einen Voucher mit CSV-Daten abfüllen
        var voucher = new ProductGroupVoucher(argumentsAccessor.getString(4), argumentsAccessor.getInteger(5));

        Product p1 = mock(Product.class);
        Product p2 = mock(Product.class);
        when(p1.getProductGroup()).thenReturn(argumentsAccessor.getString(0));
        when(p1.getPrice()).thenReturn(argumentsAccessor.getDouble(1));
        when(p2.getProductGroup()).thenReturn(argumentsAccessor.getString(2));
        when(p2.getPrice()).thenReturn(argumentsAccessor.getDouble(3));

        assertEquals(argumentsAccessor.getDouble(6), voucher.getDiscount(Arrays.asList(p1, p2)));
    }

}
