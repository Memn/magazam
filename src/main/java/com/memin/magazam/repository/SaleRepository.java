package com.memin.magazam.repository;

import com.memin.magazam.domain.Sale;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;

import java.util.List;

/**
 * Spring Data JPA repository for the Sale entity.
 */
@SuppressWarnings("unused")
public interface SaleRepository extends JpaRepository<Sale,Long> {

    Page<Sale> findByShopUserLogin(String currentUserLogin, Pageable pageable);
}
