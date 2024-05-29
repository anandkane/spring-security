package org.opus.explore.spring.springsecurityauthorizationmethodlevel.controller;

import org.opus.explore.spring.springsecurityauthorizationmethodlevel.model.*;
import org.opus.explore.spring.springsecurityauthorizationmethodlevel.service.*;
import org.springframework.security.access.prepost.*;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/v2/customers")
public class CustomerController {

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping
    @PreAuthorize("hasAnyAuthority('CUSTOMER_ADMIN', 'CUSTOMER_MANAGER')")
    public List<Customer> getAllCustomers() {
        return customerService.getAllCustomers();
    }

    @GetMapping("/{id}")
//    @PreAuthorize("hasAnyAuthority('CUSTOMER_ADMIN', 'CUSTOMER_MANAGER', 'CUSTOMER')")
    @PostAuthorize("hasAnyAuthority('CUSTOMER_ADMIN', 'CUSTOMER_MANAGER') or returnObject.username == authentication.name")
    public Customer getCustomerById(@PathVariable Long id) {
        Customer customer = customerService.getCustomerById(id);
        System.out.println("Looked up customer: " + customer.getUsername());
        return customer;
    }

    @PostMapping
    @PreAuthorize("hasAnyAuthority('CUSTOMER_ADMIN')")
    public Customer createCustomer(@RequestBody Customer customer) {
        return customerService.createCustomer(customer);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('CUSTOMER_ADMIN', 'CUSTOMER_MANAGER')")
    public Customer updateCustomer(@PathVariable Long id, @RequestBody Customer customer) {
        return customerService.updateCustomer(id, customer);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('CUSTOMER_ADMIN')")
    public void deleteCustomer(@PathVariable Long id) {
        customerService.deleteCustomer(id);
    }

    @GetMapping("/faq")
    @PreAuthorize("permitAll()")
    public String getFaq() {
        return "Frequently Asked Questions";
    }
}
