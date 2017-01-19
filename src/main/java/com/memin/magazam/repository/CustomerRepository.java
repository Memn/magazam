package com.memin.magazam.repository;

import com.memin.magazam.domain.Customer;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;

import java.util.List;

/**
 * Spring Data JPA repository for the Customer entity.
 */
@SuppressWarnings("unused")
public interface CustomerRepository extends JpaRepository<Customer,Long> {

    Page<Customer> findByShopUserLogin(String currentUserLogin, Pageable pageable);
}
