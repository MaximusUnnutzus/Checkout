package ch.zhaw.checkout.checkout.RestController;
import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import ch.zhaw.checkout.checkout.Product;

@RestController
public class ProductController {
private List<Product> products = new ArrayList<Product>();

    @EventListener(ApplicationReadyEvent.class)
    public void init() {
        this.products.add(new Product("id1", "White Heaven", "Schokolade", 3.5));
        this.products.add(new Product("id2", "Dark Dream", "Schokolade", 3.5));
        this.products.add(new Product("id3", "Nuts", "Schokolade", 3));
        this.products.add(new Product("id4", "Happy Day", "Lollipop", 0.8));
        this.products.add(new Product("id5", "Sweet Magic", "Lollipop", 1));
        System.out.println("Init Data");
    }

    @GetMapping("/")
    public String Status(){
        return "up and running";
    }

    @GetMapping("/count")
    public int Count(){
        return products.size();
    }
}