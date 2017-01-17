package com.memin.magazam.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A Customer.
 */
@Entity
@Table(name = "customer")
public class Customer implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    @Size(min = 2)
    @Column(name = "name", nullable = false)
    private String name;

    @NotNull
    @Column(name = "phone_number", nullable = false)
    private String phoneNumber;

    @Column(name = "address")
    private String address;

    @Column(name = "remaining_debt", precision=10, scale=2)
    private BigDecimal remainingDebt;

    @Column(name = "creation_date")
    private ZonedDateTime creationDate;

    @OneToMany(mappedBy = "customer")
    @JsonIgnore
    private Set<Sale> sales = new HashSet<>();

    @OneToMany(mappedBy = "customer")
    @JsonIgnore
    private Set<Payment> payments = new HashSet<>();

    @ManyToOne
    @NotNull
    private Shop shop;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public Customer name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public Customer phoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
        return this;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public Customer address(String address) {
        this.address = address;
        return this;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public BigDecimal getRemainingDebt() {
        return remainingDebt;
    }

    public Customer remainingDebt(BigDecimal remainingDebt) {
        this.remainingDebt = remainingDebt;
        return this;
    }

    public void setRemainingDebt(BigDecimal remainingDebt) {
        this.remainingDebt = remainingDebt;
    }

    public ZonedDateTime getCreationDate() {
        return creationDate;
    }

    public Customer creationDate(ZonedDateTime creationDate) {
        this.creationDate = creationDate;
        return this;
    }

    public void setCreationDate(ZonedDateTime creationDate) {
        this.creationDate = creationDate;
    }

    public Set<Sale> getSales() {
        return sales;
    }

    public Customer sales(Set<Sale> sales) {
        this.sales = sales;
        return this;
    }

    public Customer addSale(Sale sale) {
        sales.add(sale);
        sale.setCustomer(this);
        return this;
    }

    public Customer removeSale(Sale sale) {
        sales.remove(sale);
        sale.setCustomer(null);
        return this;
    }

    public void setSales(Set<Sale> sales) {
        this.sales = sales;
    }

    public Set<Payment> getPayments() {
        return payments;
    }

    public Customer payments(Set<Payment> payments) {
        this.payments = payments;
        return this;
    }

    public Customer addPayment(Payment payment) {
        payments.add(payment);
        payment.setCustomer(this);
        return this;
    }

    public Customer removePayment(Payment payment) {
        payments.remove(payment);
        payment.setCustomer(null);
        return this;
    }

    public void setPayments(Set<Payment> payments) {
        this.payments = payments;
    }

    public Shop getShop() {
        return shop;
    }

    public Customer shop(Shop shop) {
        this.shop = shop;
        return this;
    }

    public void setShop(Shop shop) {
        this.shop = shop;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Customer customer = (Customer) o;
        if (customer.id == null || id == null) {
            return false;
        }
        return Objects.equals(id, customer.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "Customer{" +
            "id=" + id +
            ", name='" + name + "'" +
            ", phoneNumber='" + phoneNumber + "'" +
            ", address='" + address + "'" +
            ", remainingDebt='" + remainingDebt + "'" +
            ", creationDate='" + creationDate + "'" +
            '}';
    }
}
