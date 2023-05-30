package com.beetech.mvcspringboot.service.interfaces;


import com.paypal.api.payments.Payment;
import com.paypal.base.rest.PayPalRESTException;

/**
 * The interface Paypal service.
 */
public interface PaypalService {
    /**
     * Create payment payment.
     * @return the payment
     * @throws PayPalRESTException the PayPal rest exception
     */
    Payment createPayment(Double total, String currency, String method, String intent,
                          String description, String cancelUrl, String successUrl)
            throws PayPalRESTException;

    /**
     * Execute payment payment.
     *
     * @param paymentId the payment id
     * @param payerId   the payer id
     * @return the payment
     * @throws PayPalRESTException the pay pal rest exception
     */
    Payment executePayment(String paymentId, String payerId) throws PayPalRESTException;

    /**
     * Gets payment details.
     *
     * @param paymentId the payment id
     * @return the payment details
     * @throws PayPalRESTException the pay pal rest exception
     */
    Payment getPaymentDetails(String paymentId) throws PayPalRESTException;

    /**
     * Cancel payment.
     *
     * @param paymentId the payment id
     * @throws PayPalRESTException the pay pal rest exception
     */
    void cancelPayment(String paymentId) throws PayPalRESTException;

}
