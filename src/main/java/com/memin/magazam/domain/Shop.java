package com.memin.magazam.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A Shop.
 */
@Entity
@Table(name = "shop")
public class Shop implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    @Size(min = 2)
    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "address")
    private String address;

    @NotNull
    @Column(name = "phone_number", nullable = false)
    private String phoneNumber;

    @OneToMany(mappedBy = "shop")
    @JsonIgnore
    private Set<Customer> customers = new HashSet<>();

    @OneToMany(mappedBy = "shop")
    @JsonIgnore
    private Set<Sale> sales = new HashSet<>();

    @ManyToOne
    @NotNull
    private User user;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public Shop name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public Shop address(String address) {
        this.address = address;
        return this;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public Shop phoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
        return this;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Set<Customer> getCustomers() {
        return customers;
    }

    public Shop customers(Set<Customer> customers) {
        this.customers = customers;
        return this;
    }

    public Shop addCustomer(Customer customer) {
        customers.add(customer);
        customer.setShop(this);
        return this;
    }

    public Shop removeCustomer(Customer customer) {
        customers.remove(customer);
        customer.setShop(null);
        return this;
    }

    public void setCustomers(Set<Customer> customers) {
        this.customers = customers;
    }

    public Set<Sale> getSales() {
        return sales;
    }

    public Shop sales(Set<Sale> sales) {
        this.sales = sales;
        return this;
    }

    public Shop addSale(Sale sale) {
        sales.add(sale);
        sale.setShop(this);
        return this;
    }

    public Shop removeSale(Sale sale) {
        sales.remove(sale);
        sale.setShop(null);
        return this;
    }

    public void setSales(Set<Sale> sales) {
        this.sales = sales;
    }

    public User getUser() {
        return user;
    }

    public Shop user(User user) {
        this.user = user;
        return this;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Shop shop = (Shop) o;
        if (shop.id == null || id == null) {
            return false;
        }
        return Objects.equals(id, shop.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "Shop{" +
            "id=" + id +
            ", name='" + name + "'" +
            ", address='" + address + "'" +
            ", phoneNumber='" + phoneNumber + "'" +
            '}';
    }
}
