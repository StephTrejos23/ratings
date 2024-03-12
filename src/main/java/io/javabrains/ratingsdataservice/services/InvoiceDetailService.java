package io.javabrains.ratingsdataservice.services;

import io.javabrains.ratingsdataservice.models.InvoiceDetail;
import io.javabrains.ratingsdataservice.repository.ProductRepository;
import org.springframework.stereotype.Service;

@Service
public class InvoiceDetailService {

    private final ProductService productService;
    public InvoiceDetailService(ProductService productService) {
        this.productService = productService;
    }

    public void validateInvoiceDetails(InvoiceDetail invoiceDetail){

      if(invoiceDetail.getPrice()<=0){
          throw new IllegalArgumentException("The price cannot be 0! ");
      }

      if(invoiceDetail.getAmount()<=0){
          throw new IllegalArgumentException("The amount cannot be 0! ");
      }

      if(productService.existFor(invoiceDetail.getProduct().getId())){
          throw new IllegalArgumentException("The product does not exist");
      }


    }
}
