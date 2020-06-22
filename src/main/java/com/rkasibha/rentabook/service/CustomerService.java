package com.rkasibha.rentabook.service;

import com.rkasibha.rentabook.enumeration.MembershipStatus;
import com.rkasibha.rentabook.exception.DataNotFoundException;
import com.rkasibha.rentabook.model.Customer;
import com.rkasibha.rentabook.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

/**
 * Service class to handle all operations related to Customer objects.
 */
@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    /**
     * Add a new Customer object to the database.
     * Adds the current date as the membership activation date and sets the membership status to ACTIVE before adding
     * to the database.
     * @param customer Customer object to be added
     * @return Customer object added successfully
     */
    public Customer addCustomer(Customer customer) {
        customer.setMemberSince(LocalDate.now());
        customer.setStatus(MembershipStatus.ACTIVE);
        return customerRepository.save(customer);
    }

    /**
     * Fetch list of all Customer objects from the database.
     * @return List of Customer objects
     */
    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    /**
     * Fetch details of a customer given his id.
     * @param id id of the customer whose details have to be fetched
     * @return Customer object for the matching id
     * @throws DataNotFoundException thrown when the Customer object with the given id is not found
     */
    public Customer getCustomerById(Integer id) throws DataNotFoundException {
        Optional<Customer> optionalCustomer = customerRepository.findById(id);
        if(optionalCustomer.isPresent())
            return optionalCustomer.get();
        else
            throw new DataNotFoundException("Customer with id: " + id + " not found");
    }
}
