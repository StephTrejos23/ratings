package io.javabrains.ratingsdataservice;

import io.javabrains.ratingsdataservice.models.*;
import io.javabrains.ratingsdataservice.repository.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class Config {

    @Bean
    public RatingsRepository ratingsRepository() {
        return new RatingsRepository() {
            @Override
            public Rating getRating(String movieId) {
                return null;
            }

            @Override
            public void save(Rating rating) {

            }
        };
    }

//    @Bean
//    public CustomerRepository customerRepository() {
//        return new CustomerRepository() {
//            @Override
//            public Customer getCustomer(int id) {
//                return null;
//            }
//
//            @Override
//            public void save(Customer customer) {
//
//            }
//
//            @Override
//            public void delete(int id) {
//
//            }
//        };
//    }

//    @Bean
//    public InvoiceRepository invoiceRepository() {
//        return new InvoiceRepository() {
//            @Override
//            public Invoice getInvoice(int id) {
//                return null;
//            }
//
//            @Override
//            public void save(Invoice invoice) {
//
//            }
//
//            @Override
//            public void delete(int id) {
//
//            }
//        };
    //}

    public MessageRepository messageRepository() {
        return new MessageRepository() {
            @Override
            public int getNewId() {
                return 0;
            }

            @Override
            public void saveMessage(SmsMessage smsMessage) {

            }

            @Override
            public SmsMessage getMessageById(int id) {
                return null;
            }

            @Override
            public void deleteMessage(int id) {

            }

            @Override
            public void sendMessage(SmsMessage smsMessage) {

            }
        };
    }

    @Bean //objeto en memoria
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }

//    @Bean
//    public ProductRepository productRepository() {
//        return new ProductRepository() {
//            @Override
//            public Product getProduct(Integer id) {
//                return null;
//            }
//
//            @Override
//            public void save(Product product) {
//
//            }
//
//            @Override
//            public void delete(Integer id) {
//
//            }
//        };
    }

//    @Bean
//    public SupplierRepository supplierRepository() {
//        return new SupplierRepository() {
//            @Override
//            public Supplier getSupplier(int id) {
//                return null;
//            }
//
//            @Override
//            public void save(Supplier supplier) {
//
//            }
//
//            @Override
//            public void delete(int id) {
//
//            }
//        };
//    }

