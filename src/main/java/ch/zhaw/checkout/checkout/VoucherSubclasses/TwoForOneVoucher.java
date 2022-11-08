package ch.zhaw.checkout.checkout.VoucherSubclasses;

import java.util.List;

import ch.zhaw.checkout.checkout.Product;
import ch.zhaw.checkout.checkout.Voucher;

public class TwoForOneVoucher implements Voucher {

    Product product;

    public TwoForOneVoucher(Product product) {
        this.product = product;
    }

    @Override
    public double getDiscount(List<Product> products) {
        var count = products.stream().filter(p -> this.product.getId().equals(p.getId())).toList().size();
        return Math.floor(count / 2) * this.product.getPrice();
    }

}
