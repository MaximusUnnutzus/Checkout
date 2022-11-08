package ch.zhaw.checkout.checkout.VoucherSubclasses;

import java.util.List;

import ch.zhaw.checkout.checkout.Product;

//Übung 7 Task d)
public class ProductGroupVoucher {
    private String productGroup;
    private int amount;

    public ProductGroupVoucher(String productGroup, int amount) {
        if (productGroup.isBlank() || productGroup.isEmpty() || amount <=0) {
            throw new RuntimeException("Error: Product Group has to be defined and can't contain only spaces");
        }

        this.productGroup = productGroup;
        this.amount = amount;
    }

    public double getDiscount(List<Product> products) {
        double warenkorb = products.stream().filter(x -> x.getProductGroup().contains(productGroup))
                .mapToDouble(x -> x.getPrice()).sum();

        if (amount < warenkorb) {
            return amount;

        } else {
            return warenkorb;

        }

    }
}
