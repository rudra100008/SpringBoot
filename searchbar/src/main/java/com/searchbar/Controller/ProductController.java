package com.searchbar.Controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.reactive.function.client.WebClient;
import com.searchbar.Entity.Product;
import reactor.core.publisher.Mono;


@Controller
public class ProductController {
    @Autowired
    private WebClient.Builder webClientBuilder;
    private static final String API_URL="http://localhost:8080/product";

    @GetMapping("/productDisplay")
    public String getProduct(Model model)
    {
        Mono<Product[]> productMono=webClientBuilder.build().get()
                                                    .uri(API_URL)
                                                    .retrieve()
                                                    .bodyToMono(Product[].class);
        Product[] productArray=productMono.block();
        List<Product> productList=Arrays.asList(productArray);
        model.addAttribute("products", productList);
        return "ProductDisplay";

    }
    @GetMapping("/addProduct")
    public String productHandler(Model model)
    {
        model.addAttribute("product", new Product());
        return "productForm";
    }    
}
