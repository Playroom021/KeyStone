package com.KeyStone.DeliveryService.Service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.KeyStone.DeliveryService.Entity.Customer;
import com.KeyStone.DeliveryService.Repository.CustomerRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor

public class CustomerServicLoginImpl implements CustomerServiceLogic {

    @Autowired
    private CustomerRepository customerRepo;



    @Override
    public Customer createCustomer(Customer customer) {

        if (customerRepo.existsByEmail(customer.getEmail())) {
            throw new RuntimeException("Customer with email " + customer.getEmail() + " already exists.");
        }
        
        customer.setActive(true);
        customer.setCreatedAt(LocalDateTime.now());
        
        return customerRepo.save(customer);
    }

    @Override
    public Customer updateCustomer(Long id ,Customer customer){

        Customer existingCustomer= customerRepo.findById(id).orElseThrow(() -> new RuntimeException("Customer not found with id: " + id));
        existingCustomer.setCompanyName(customer.getCompanyName());
        existingCustomer.setContactPerson(customer.getContactPerson());
        existingCustomer.setEmail(customer.getEmail());
        existingCustomer.setPhone(customer.getPhone());
        existingCustomer.setAddress(customer.getAddress());
        existingCustomer.setActive(customer.isActive());

        return customerRepo.save(existingCustomer);

    }

    @Override
    public Customer getCustomer(Long id) {
        return customerRepo.findById(id)
            .orElseThrow(() -> new RuntimeException("Customer not found with id: " + id));

    }

    @Override
    public List<Customer>getAllCustomers() {
        return customerRepo.findAll();
    }

    @Override
    public void deleteCustomer(String email) {
        Customer customer = customerRepo.findByEmail(email);
        if (customer == null) {
            throw new RuntimeException("Customer not found with email: " + email);
        }
        customerRepo.delete(customer);  
    }

}
