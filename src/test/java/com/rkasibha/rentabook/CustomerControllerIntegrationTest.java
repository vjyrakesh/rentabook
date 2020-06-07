package com.rkasibha.rentabook;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rkasibha.rentabook.dto.CustomerDto;
import com.rkasibha.rentabook.model.Customer;
import com.rkasibha.rentabook.service.CustomerService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource(locations = "classpath:application-integrationtest.properties")
public class CustomerControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private CustomerService customerService;

    @Test
    public void testGetAllCustomers() throws Exception {
        mockMvc.perform(get("/customers").contentType(MediaType.APPLICATION_JSON))
        .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void testAddCustomer() throws Exception {
        CustomerDto customerDto = new CustomerDto();
        customerDto.setFirstName("Test");
        customerDto.setLastName("TestLastName");
        customerDto.setContactNumber("+9198983343");
        customerDto.setEmailId("abc@abc.com");
        customerDto.setCity("Test");
        customerDto.setCountry("India");
        ObjectMapper mapper = new ObjectMapper();
        String customerDtoStr = mapper.writeValueAsString(customerDto);
        mockMvc.perform(post("/customers").contentType(MediaType.APPLICATION_JSON).content(customerDtoStr))
        .andExpect(MockMvcResultMatchers.status().isCreated())
        .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1))
        .andExpect(MockMvcResultMatchers.jsonPath("$.firstName").value("Test"));
    }

    @Test
    public void testGetCustomerById() throws Exception {
        Customer customer = new Customer();
        customer.setId(1);
        customer.setFirstName("Test");
        customer.setLastName("TestLastName");
        customer.setContactNumber("+9123232323");
        customer.setCity("Test");
        customer.setCountry("India");
        customer.setEmailId("abc@abc.com");
        customerService.addCustomer(customer);

        mockMvc.perform(get("/customers/1").contentType(MediaType.APPLICATION_JSON))
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1));
    }
}
