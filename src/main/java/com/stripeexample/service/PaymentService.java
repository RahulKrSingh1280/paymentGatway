package com.stripeexample.service;

import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.PaymentIntent;
import com.stripe.param.PaymentIntentCreateParams;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class PaymentService {
    @Value("${stripe.api.key}")
    private String stripeApiKey;

    public String createPaymentIntent(double amount) {//takes the amount
        Stripe.apiKey = stripeApiKey;//stripeApiKey give for it to perform authantication

        PaymentIntentCreateParams createParams = new PaymentIntentCreateParams.Builder()
                .setAmount((long) (amount * 100))//stripe requires amount in cents
                .setCurrency("usd")//some calculation in USD whatever amount is there is multiple with 100
                //then it set the currency whether rupe,usd ect and then after that it save the payment
                .build();

        try {
            PaymentIntent paymentIntent = PaymentIntent.create(createParams);
            return paymentIntent.getId();
        } catch (StripeException e) {
            throw new RuntimeException("Error creating payment intent", e);
        }
    }
}
