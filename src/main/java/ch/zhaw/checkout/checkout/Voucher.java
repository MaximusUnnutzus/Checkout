package ch.zhaw.checkout.checkout;

import java.util.List;

public interface Voucher {

    public double getDiscount(List<Product> products);

}
