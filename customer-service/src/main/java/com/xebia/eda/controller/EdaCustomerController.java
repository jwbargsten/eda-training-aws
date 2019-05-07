package com.xebia.eda.controller;

import com.xebia.common.domain.Customer;
import com.xebia.common.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/customer-api/v2")
//TODO: add hooks for async logic
public class EdaCustomerController {

    private final CustomerService customerService;

    @Autowired
    public EdaCustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping("/customers/{id}")
    @ResponseBody
    public Optional<Customer> getCustomer(Long id) {
        return customerService.getCustomer(id);
    }

    @GetMapping("/customers")
    @ResponseBody
    public List<Customer> getCustomers() {
        return customerService.getCustomers();
    }

    @PostMapping("/customers")
    @ResponseBody
    public Customer saveCustomer(@Valid @RequestBody Customer customer) {
        return customerService.saveCustomer(customer);
    }

    @PutMapping("/customers/{id")
    @ResponseBody
    public Customer updateCustomer(@Valid @RequestBody Customer customer, Long id) {
        return customerService.updateCustomer(customer, id);
    }


}