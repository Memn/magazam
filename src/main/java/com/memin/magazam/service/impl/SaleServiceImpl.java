package com.memin.magazam.service.impl;

import com.memin.magazam.service.SaleService;
import com.memin.magazam.domain.Sale;
import com.memin.magazam.repository.SaleRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.List;

/**
 * Service Implementation for managing Sale.
 */
@Service
@Transactional
public class SaleServiceImpl implements SaleService{

    private final Logger log = LoggerFactory.getLogger(SaleServiceImpl.class);
    
    @Inject
    private SaleRepository saleRepository;

    /**
     * Save a sale.
     *
     * @param sale the entity to save
     * @return the persisted entity
     */
    public Sale save(Sale sale) {
        log.debug("Request to save Sale : {}", sale);
        Sale result = saleRepository.save(sale);
        return result;
    }

    /**
     *  Get all the sales.
     *  
     *  @param pageable the pagination information
     *  @return the list of entities
     */
    @Transactional(readOnly = true) 
    public Page<Sale> findAll(Pageable pageable) {
        log.debug("Request to get all Sales");
        Page<Sale> result = saleRepository.findAll(pageable);
        return result;
    }

    /**
     *  Get one sale by id.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    @Transactional(readOnly = true) 
    public Sale findOne(Long id) {
        log.debug("Request to get Sale : {}", id);
        Sale sale = saleRepository.findOne(id);
        return sale;
    }

    /**
     *  Delete the  sale by id.
     *
     *  @param id the id of the entity
     */
    public void delete(Long id) {
        log.debug("Request to delete Sale : {}", id);
        saleRepository.delete(id);
    }
}
