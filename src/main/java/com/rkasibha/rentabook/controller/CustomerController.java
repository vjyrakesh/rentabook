package com.rkasibha.rentabook.controller;

import com.rkasibha.rentabook.dto.CustomerDto;
import com.rkasibha.rentabook.dto.ErrorDto;
import com.rkasibha.rentabook.exception.DataNotFoundException;
import com.rkasibha.rentabook.model.Customer;
import com.rkasibha.rentabook.service.CustomerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Controller class for Customer resources.
 */
@Slf4j
@RestController
@RequestMapping(value = "/customers")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @Autowired
    private EntityDtoMapper entityDtoMapper;

    /**
     * Fetch the list of all Customers.
     * Returns 200 OK if successful.
     * @return JSON array of all Customer objects
     */
    @GetMapping(value = "")
    public ResponseEntity<List<CustomerDto>> getAllCustomers() {
        List<Customer> customers = customerService.getAllCustomers();
        List<CustomerDto> customerDtos = new ArrayList<>();
        for(Customer customer : customers) {
            customerDtos.add(entityDtoMapper.convertCustomerToCustomerDto(customer));
        }
        log.info("Number of Customer objects returned: " + customerDtos.size());
        return new ResponseEntity<>(customerDtos, HttpStatus.OK);
    }

    /**
     * Add a new Customer.
     * Returns 201 Created if successful.
     * @param customerDto Customer details to be added
     * @return JSON object representing the Customer object added
     */
    @PostMapping(value = "")
    public ResponseEntity<CustomerDto> addCustomer(@RequestBody CustomerDto customerDto) {
        Customer addedCustomer = customerService.addCustomer(entityDtoMapper.convertCustomerDtoToCustomer(customerDto));
        log.info("Added Customer object with id: " + addedCustomer.getId() + " successfully");
        return new ResponseEntity<>(entityDtoMapper.convertCustomerToCustomerDto(addedCustomer), HttpStatus.CREATED);
    }

    /**
     * Fetch details of a customer by his id.
     * @param id id of the customer whose details have to be fetched
     * @return JSON object representing the customer
     */
    @GetMapping(value = "/{id}")
    public ResponseEntity<?> getCustomerById(@PathVariable Integer id) {
        try {
            Customer foundCustomer = customerService.getCustomerById(id);
            log.info("Found Customer with id: " + id);
            return new ResponseEntity<>(entityDtoMapper.convertCustomerToCustomerDto(foundCustomer), HttpStatus.OK);
        } catch (DataNotFoundException ex) {
            ErrorDto errorDto = new ErrorDto();
            errorDto.setError(ex.getMessage());
            log.error("Could not find Customer with id: " + id);
            return new ResponseEntity<>(errorDto, HttpStatus.NOT_FOUND);
        }
    }
}
