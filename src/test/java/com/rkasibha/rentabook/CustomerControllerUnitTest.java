package com.rkasibha.rentabook;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rkasibha.rentabook.controller.CustomerController;
import com.rkasibha.rentabook.controller.EntityDtoMapper;
import com.rkasibha.rentabook.dto.CustomerDto;
import com.rkasibha.rentabook.exception.DataNotFoundException;
import com.rkasibha.rentabook.model.Customer;
import com.rkasibha.rentabook.service.CustomerService;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.CustomEditorConfigurer;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@WebMvcTest(CustomerController.class)
public class CustomerControllerUnitTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CustomerService customerService;

    @MockBean
    private EntityDtoMapper entityDtoMapper;

    @Test
    public void testGetAllCustomers() throws Exception {
        CustomerDto mockCustomerDto = new CustomerDto();
        Customer mockCustomer = new Customer();
        Mockito.when(entityDtoMapper.convertCustomerToCustomerDto(mockCustomer)).thenReturn(mockCustomerDto);
        List<Customer> customers = new ArrayList<>();
        Mockito.when(customerService.getAllCustomers()).thenReturn(customers);

        mockMvc.perform(get("/customers").contentType(MediaType.APPLICATION_JSON))
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.jsonPath("$", hasSize(0)));

    }

    @Test
    public void testAddCustomer() throws Exception {
        CustomerDto mockCustomerDtoToAdd = new CustomerDto();
        mockCustomerDtoToAdd.setFirstName("Test");
        Customer mockCustomerToAdd = new Customer();
        Mockito.when(entityDtoMapper.convertCustomerDtoToCustomer(mockCustomerDtoToAdd)).thenReturn(mockCustomerToAdd);

        Mockito.when(customerService.addCustomer(mockCustomerToAdd)).thenReturn(mockCustomerToAdd);
        Mockito.when(entityDtoMapper.convertCustomerToCustomerDto(mockCustomerToAdd)).thenReturn(mockCustomerDtoToAdd);

        ObjectMapper mapper = new ObjectMapper();
        String mockCustomerDtoStr = mapper.writeValueAsString(mockCustomerDtoToAdd);
        mockMvc.perform(post("/customers").contentType(MediaType.APPLICATION_JSON).content(mockCustomerDtoStr))
        .andExpect(MockMvcResultMatchers.status().isCreated())
        .andExpect(MockMvcResultMatchers.jsonPath("$.firstName").value("Test"));
    }

    @Test
    public void testGetCustomerById() throws Exception {
        Customer mockReturnedCustomer = new Customer();
        CustomerDto mockCustomerDto = new CustomerDto();
        mockCustomerDto.setId(1);

        Mockito.when(customerService.getCustomerById(1)).thenReturn(mockReturnedCustomer);
        Mockito.when(entityDtoMapper.convertCustomerToCustomerDto(mockReturnedCustomer)).thenReturn(mockCustomerDto);

        mockMvc.perform(get("/customers/1").contentType(MediaType.APPLICATION_JSON))
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1));

        Mockito.when(customerService.getCustomerById(2)).thenThrow(DataNotFoundException.class);
        mockMvc.perform(get("/customers/2").contentType(MediaType.APPLICATION_JSON))
        .andExpect(MockMvcResultMatchers.status().isNotFound());
    }
}
