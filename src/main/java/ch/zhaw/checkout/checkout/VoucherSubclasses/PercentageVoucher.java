package ch.zhaw.checkout.checkout.VoucherSubclasses;

import java.util.List;

import ch.zhaw.checkout.checkout.Product;
import ch.zhaw.checkout.checkout.Voucher;

public class PercentageVoucher implements Voucher {

    private int discount = 0;

    public PercentageVoucher(int discount) {
        if (discount>50){
            throw new RuntimeException("Error: Discount calue must be less or equal then 50");
        }else if (discount<=0){
            throw new RuntimeException("Error: Discount value must be greatern zero");
        }

        this.discount = discount;
    }

    @Override
    public double getDiscount(List<Product> products) {
        var totalPrice = products.stream().mapToDouble(p -> p.getPrice()).sum();
        return totalPrice * ((double) discount / 100);
    }

}
