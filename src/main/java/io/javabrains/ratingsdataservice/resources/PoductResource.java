package io.javabrains.ratingsdataservice.resources;

import io.javabrains.ratingsdataservice.repository.ProductRepository;
import io.javabrains.ratingsdataservice.services.ProductService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/productdata")
public class PoductResource {

    private final ProductService productService;


    public PoductResource(ProductService productService) {
        this.productService = productService;
    }


}
