package com.memin.magazam.service;

import com.memin.magazam.domain.Payment;
import com.memin.magazam.domain.Sale;

public interface CustomerOperationService {
    Sale sale(Sale sale);

    Sale updateSale(Sale sale);

    void deleteSale(Long id);

    Payment pay(Payment payment);

    Payment updatePayment(Payment payment);

    void deletePayment(Long id);

}
