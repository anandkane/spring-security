package org.opus.explore.spring.springsecurityauthorization.service;

import org.opus.explore.spring.springsecurityauthorization.model.*;
import org.springframework.security.access.prepost.*;
import org.springframework.stereotype.*;

import java.util.*;

@Service
public class CustomerService {

    private long idCounter = 0;

    private Map<Long, Customer> customers = new HashMap<>();

    public CustomerService() {
        customers.put(++idCounter,
                      new Customer(idCounter, generateUsername("Aarav Kumar"), "Aarav Kumar", "aarav.kumar@gmail.com"));
        customers.put(++idCounter, new Customer(idCounter, generateUsername("Aditi Sharma"), "Aditi Sharma",
                                                "aditi.sharma@gmail.com"));
        customers.put(++idCounter,
                      new Customer(idCounter, generateUsername("Vijay Singh"), "Vijay Singh", "vijay.singh@gmail.com"));
        customers.put(++idCounter,
                      new Customer(idCounter, generateUsername("Priya Patel"), "Priya Patel", "priya.patel@gmail.com"));
        customers.put(++idCounter, new Customer(idCounter, generateUsername("Raj Malhotra"), "Raj Malhotra",
                                                "raj.malhotra@gmail.com"));
        customers.put(++idCounter,
                      new Customer(idCounter, generateUsername("Ananya Roy"), "Ananya Roy", "ananya.roy@gmail.com"));
        customers.put(++idCounter,
                      new Customer(idCounter, generateUsername("Ravi Verma"), "Ravi Verma", "ravi.verma@gmail.com"));
        customers.put(++idCounter,
                      new Customer(idCounter, generateUsername("Sneha Gupta"), "Sneha Gupta", "sneha.gupta@gmail.com"));
        customers.put(++idCounter, new Customer(idCounter, generateUsername("Karan Kapoor"), "Karan Kapoor",
                                                "karan.kapoor@gmail.com"));
        customers.put(++idCounter,
                      new Customer(idCounter, generateUsername("Riya Mehra"), "Riya Mehra", "riya.mehra@gmail.com"));
    }

    private String generateUsername(String name) {
        return name.toLowerCase().replace(" ", ".");
    }

    public List<Customer> getAllCustomers() {
        return new ArrayList<>(customers.values());
    }

    public Customer getCustomerById(Long id) {
        if (!customers.containsKey(id)) {
            throw new RuntimeException("Customer not found");
        }
        return customers.get(id);
    }

    public Customer createCustomer(Customer customer) {
        customer.setId(++idCounter);
        customers.put(customer.getId(), customer);
        return customer;
    }

    public Customer updateCustomer(Long id, Customer customer) {
        if (!customers.containsKey(id)) {
            throw new RuntimeException("Customer not found");
        }
        customer.setId(id);
        customers.put(id, customer);
        return customer;
    }

    public void deleteCustomer(Long id) {
        if (!customers.containsKey(id)) {
            throw new RuntimeException("Customer not found");
        }
        customers.remove(id);
    }

    public Customer getCustomerByUsername(String username) {
        for (Customer customer : customers.values()) {
            if (customer.getUsername().equals(username)) {
                return customer;
            }
        }
        throw new RuntimeException("Customer not found");
    }

    public Customer getCustomerByUsernameNoException(String username) {
        try {
            Customer customerByUsername = getCustomerByUsername(username);
            return customerByUsername;
        } catch (Exception e) {
            return null;
        }
    }
}