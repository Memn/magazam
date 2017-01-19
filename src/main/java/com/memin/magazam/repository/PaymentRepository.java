package com.memin.magazam.repository;

import com.memin.magazam.domain.Payment;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;

import java.util.List;

/**
 * Spring Data JPA repository for the Payment entity.
 */
@SuppressWarnings("unused")
public interface PaymentRepository extends JpaRepository<Payment,Long> {


    Page<Payment> findByCustomerShopUserLogin(String currentUserLogin, Pageable pageable);
}
