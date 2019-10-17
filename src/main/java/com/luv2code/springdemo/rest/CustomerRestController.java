package com.luv2code.springdemo.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.luv2code.springdemo.entity.Customer;
import com.luv2code.springdemo.service.CustomerService;

@RestController
@RequestMapping("/api")
public class CustomerRestController {

	// autowire
	@Autowired
	private CustomerService customerService;

	// GET mapping - list of all customers
	@GetMapping("/customers")
	public List<Customer> getCustomers() {
		return customerService.getCustomers();
	}

	// GET mapping - one customer with its id
	@GetMapping("/customers/{customerId}")
	public Customer getCustomer(@PathVariable int customerId) {
		Customer c = customerService.getCustomer(customerId);
		if (c == null) {
			throw new CustomerNotFoundException("Customer id not found - " + customerId);
		}
		return c;
	}
	
	// POST Mapping - add new customer
	@PostMapping("/customers")
	public Customer addCustomer(@RequestBody Customer c) {
		c.setId(0);
		customerService.saveCustomer(c);
		return c;
	}
	
	// PUT Mapping - update existing customer
	@PutMapping("/customers")
	public Customer updateCustomer(@RequestBody Customer c) {
		customerService.saveCustomer(c);
		return c;
	}
	
	// DELETE Mapping - delete existing customer
	@DeleteMapping("/customers/{customerId}")
	public String deleteCustomer(@PathVariable int customerId) {
		Customer c = customerService.getCustomer(customerId);
		if (c == null) {
			throw new CustomerNotFoundException("Customer id not found - " + customerId);
		} 
		customerService.deleteCustomer(customerId);
		
		return "Deleted customer id - " + customerId;
	}
}
