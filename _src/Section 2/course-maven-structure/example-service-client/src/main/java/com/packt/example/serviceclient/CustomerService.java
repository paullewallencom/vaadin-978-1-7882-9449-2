package com.packt.example.serviceclient;

public interface CustomerService {

	void storeCustomer(CustomerDTO customer);

	CustomerDTO getCustomerById(long id);
}
