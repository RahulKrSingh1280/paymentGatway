package com.stripeexample.controller;

import com.stripeexample.entity.Product;
import com.stripeexample.repository.ProductRepository;
import com.stripeexample.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityNotFoundException;

@RestController
@RequestMapping("/payments")
public class PaymentController {

    @Value("${stripe.api.key}")
    private String stripeApiKey;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private PaymentService paymentService;


    //http://localhost:8080/payments/initiate?productId=1
    @PostMapping("/initiate")
    public ResponseEntity<String> initiatePayment(@RequestParam Long productId){//it will fetch that the productid
        Product product = productRepository.findById(productId)//firstly found the product product found good not found throw exception
                .orElseThrow(()-> new EntityNotFoundException("Product not found with id"+productId));
        //once the product found for that product get the price
        String paymentIntentId = paymentService.createPaymentIntent(((Product) product).getPrice());//get the price
        //from there   paymentService.createPaymentIntent call that paymentService
        return ResponseEntity.ok(paymentIntentId);
    }
}
