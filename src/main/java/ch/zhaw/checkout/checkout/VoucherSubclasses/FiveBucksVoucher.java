package ch.zhaw.checkout.checkout.VoucherSubclasses;

import java.util.List;

import ch.zhaw.checkout.checkout.Product;
import ch.zhaw.checkout.checkout.Voucher;

public class FiveBucksVoucher implements Voucher {

    @Override
    public double getDiscount(List<Product> products) {
        var sum = products.stream().mapToDouble(p -> p.getPrice()).sum();
        if (sum >= 10) {
            return 5;
        }
        return 0;
    }

}
