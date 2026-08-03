package com.KeyStone.DeliveryService.Service;

import java.util.List;

import com.KeyStone.DeliveryService.Entity.Customer;

public interface CustomerServiceLogic {

    Customer createCustomer(Customer customer);
    Customer getCustomerById(Long id);
    Customer updateCustomer(Long id, Customer customer);

    List<Customer> getAllCustomers();
    void deleteCustomer(String email);
    Customer getCustomerByEmail(String email);
}
