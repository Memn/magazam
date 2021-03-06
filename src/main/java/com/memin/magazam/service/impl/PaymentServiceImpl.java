package com.memin.magazam.service.impl;

import com.memin.magazam.domain.Payment;
import com.memin.magazam.repository.PaymentRepository;
import com.memin.magazam.repository.search.PaymentSearchRepository;
import com.memin.magazam.security.AuthoritiesConstants;
import com.memin.magazam.security.SecurityUtils;
import com.memin.magazam.service.PaymentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;

import static org.elasticsearch.index.query.QueryBuilders.queryStringQuery;

/**
 * Service Implementation for managing Payment.
 */
@Service
@Transactional
public class PaymentServiceImpl implements PaymentService{

    private final Logger log = LoggerFactory.getLogger(PaymentServiceImpl.class);

    @Inject
    private PaymentRepository paymentRepository;

    @Inject
    private PaymentSearchRepository paymentSearchRepository;

    /**
     * Save a payment.
     *
     * @param payment the entity to save
     * @return the persisted entity
     */
    public Payment save(Payment payment) {
        log.debug("Request to save Payment : {}", payment);
        Payment result = paymentRepository.save(payment);
//        paymentSearchRepository.save(result);
        return result;
    }

    /**
     *  Get all the payments.
     *
     *  @param pageable the pagination information
     *  @return the list of entities
     */
    @Transactional(readOnly = true)
    public Page<Payment> findAll(Pageable pageable) {
        log.debug("Request to get all Payments");
        Page<Payment> result;
        if (SecurityUtils.isCurrentUserInRole(AuthoritiesConstants.ADMIN)) {
            result = paymentRepository.findAll(pageable);
        } else {
            result = paymentRepository.findByCustomerShopUserLogin(SecurityUtils.getCurrentUserLogin(),pageable);
        }
        return result;
    }

    /**
     *  Get one payment by id.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    @Transactional(readOnly = true)
    public Payment findOne(Long id) {
        log.debug("Request to get Payment : {}", id);
        Payment payment = paymentRepository.findOne(id);
        return payment;
    }

    /**
     *  Delete the  payment by id.
     *
     *  @param id the id of the entity
     */
    public void delete(Long id) {
        log.debug("Request to delete Payment : {}", id);
        paymentRepository.delete(id);
//        paymentSearchRepository.delete(id);
    }

    /**
     * Search for the payment corresponding to the query.
     *
     *  @param query the query of the search
     *  @return the list of entities
     */
    @Transactional(readOnly = true)
    public Page<Payment> search(String query, Pageable pageable) {
        log.debug("Request to search for a page of Payments for query {}", query);
        Page<Payment> result = paymentSearchRepository.search(queryStringQuery(query), pageable);
        return result;
    }
}
