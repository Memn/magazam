package com.memin.magazam.service.impl;

import com.memin.magazam.domain.Customer;
import com.memin.magazam.domain.Payment;
import com.memin.magazam.domain.Sale;
import com.memin.magazam.service.CustomerOperationService;
import com.memin.magazam.service.CustomerService;
import com.memin.magazam.service.PaymentService;
import com.memin.magazam.service.SaleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import java.math.BigDecimal;

@Service
@Transactional
public class CustomerOperationServiceImpl implements CustomerOperationService {

    private final Logger log = LoggerFactory.getLogger(CustomerOperationServiceImpl.class);
    @Inject
    private SaleService saleService;

    @Inject
    private CustomerService customerService;

    @Inject
    private PaymentService paymentService;

    @Override
    public Sale sale(Sale sale) {
        Sale     save     = saleService.save(sale);
        Customer customer = customerService.findOne(sale.getCustomer().getId());
        customer.setRemainingDebt(customer.getRemainingDebt().add(sale.getAmount()));
        return save;
    }

    @Override
    public Sale updateSale(Sale sale) {
        // before save payment we need old payment amount.
        BigDecimal oldSaleAmount = saleService.findOne(sale.getId()).getAmount();

        Sale save = saleService.save(sale);

        // new - old
        BigDecimal priceIncreased = sale.getAmount().subtract(oldSaleAmount);

        Customer customer = customerService.findOne(sale.getCustomer().getId());
        customer.setRemainingDebt(customer.getRemainingDebt().add(priceIncreased));
        return save;
    }

    @Override
    public void deleteSale(Long id) {
        Sale       sale    = saleService.findOne(id);
        BigDecimal oldSale = sale.getAmount();

        Customer customer = customerService.findOne(sale.getCustomer().getId());
        customer.setRemainingDebt(customer.getRemainingDebt().subtract(oldSale));

        saleService.delete(id);
    }

    @Override
    public Payment pay(Payment payment) {
        Payment  save     = paymentService.save(payment);
        Customer customer = customerService.findOne(payment.getCustomer().getId());
        customer.setRemainingDebt(customer.getRemainingDebt().subtract(payment.getAmount()));
        return save;
    }

    @Override
    public Payment updatePayment(Payment payment) {
        // before save payment we need old payment amount.
        BigDecimal oldPaymentAmount = paymentService.findOne(payment.getId()).getAmount();

        Payment save = paymentService.save(payment);

        // new - old
        BigDecimal newExtraPayment = payment.getAmount().subtract(oldPaymentAmount);

        Customer customer = customerService.findOne(payment.getCustomer().getId());
        customer.setRemainingDebt(customer.getRemainingDebt().subtract(newExtraPayment));
        return save;
    }

    @Override
    public void deletePayment(Long id) {
        Payment    payment    = paymentService.findOne(id);
        BigDecimal oldPayment = payment.getAmount();

        Customer customer = customerService.findOne(payment.getCustomer().getId());
        customer.setRemainingDebt(customer.getRemainingDebt().add(oldPayment));
        paymentService.delete(id);
    }

}
