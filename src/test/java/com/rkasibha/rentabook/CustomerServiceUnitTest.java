package com.rkasibha.rentabook;

import com.rkasibha.rentabook.exception.DataNotFoundException;
import com.rkasibha.rentabook.model.Customer;
import com.rkasibha.rentabook.repository.CustomerRepository;
import com.rkasibha.rentabook.service.CustomerService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(SpringExtension.class)
public class CustomerServiceUnitTest {

    @Mock
    private CustomerRepository customerRepository;

    @InjectMocks
    private CustomerService customerService;

    @Test
    public void testGetAllCustomers() {
        List<Customer> allCustomers = new ArrayList<>();
        Mockito.when(customerRepository.findAll()).thenReturn(allCustomers);

        assertThat(customerService.getAllCustomers().size()).isEqualTo(0);
    }

    @Test
    public void testAddCustomer() {
        Customer toAddCustomer = new Customer();
        toAddCustomer.setFirstName("Test");

        Customer addedCustomer = new Customer();
        addedCustomer.setId(1);
        addedCustomer.setFirstName("Test");

        Mockito.when(customerRepository.save(toAddCustomer)).thenReturn(addedCustomer);
        assertThat(customerService.addCustomer(toAddCustomer).getId()).isEqualTo(1);
    }

    @Test
    public void testGetCustomerById() throws DataNotFoundException {
        Customer foundCustomer = new Customer();
        foundCustomer.setId(1);

        Mockito.when(customerRepository.findById(1)).thenReturn(java.util.Optional.of(foundCustomer));
        assertThat(customerService.getCustomerById(1).getId()).isEqualTo(1);

        Mockito.when(customerRepository.findById(2)).thenThrow(NoSuchElementException.class);
        DataNotFoundException dataNotFoundException = assertThrows(DataNotFoundException.class, ()->{
            customerService.getCustomerById(2);
        });
        assertThat(dataNotFoundException.getMessage()).isEqualTo("Customer with id: 2 not found");
    }
 }
