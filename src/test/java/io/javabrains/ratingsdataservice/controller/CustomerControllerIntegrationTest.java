package io.javabrains.ratingsdataservice.controller;

import static io.javabrains.ratingsdataservice.util.TestUtil.getObjectMapper;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.time.LocalDate;

import io.javabrains.ratingsdataservice.models.Customer;
import io.javabrains.ratingsdataservice.models.Language;
import io.javabrains.ratingsdataservice.repository.CustomerRepository;
import io.javabrains.ratingsdataservice.resources.CustomerController;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

/**
 * Tests for {@link CustomerController}.
 */
@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class CustomerControllerIntegrationTest {

    private static final ObjectMapper OBJECT_MAPPER = getObjectMapper();

    private static final String BASE_URL_V1 = "/api/v1/";
    private static final String BASE_URL_V1_CUSTOMER = BASE_URL_V1 + "customer";

    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext context;

    @Autowired
    private CustomerRepository customerRepository;

    private Customer customer;

    @BeforeEach
    public void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(context).build();

        customer = getCustomer();
    }

    @Test
    void testWhenSavingCustomerResponseOk() throws Exception {
        // given
//        customerRepository.save(getCustomer());

        // when -> then
        mockMvc
                .perform(post(BASE_URL_V1_CUSTOMER).contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(OBJECT_MAPPER.writeValueAsString(customer)))
                .andDo(print()).andExpect(status().isOk());

        // ok o 200 = todo ok
        // 400 = error metiendo un dato

        final var customers = customerRepository.findAll();
        System.out.println(customers);
//        final var customer = customerRepository.findById(CUSTOMER_ID).orElseThrow();
//
//        assertThat(customer.getId(), equalTo(1090L));
//        assertThat(customer.getFirstName(), equalTo("Joe"));
//        assertThat(customer.getLastName(), equalTo("Smith"));
//        assertThat(customer.getBirthDate(), equalTo(LocalDate.of(1990, 1, 1)));
//        assertThat(customer.getPhone(), equalTo("72076070"));
//        assertThat(customer.getEmail(), equalTo("steph@gmail.com"));
//        assertThat(customer.getLanguage(), equalTo("ENGLISH"));
    }

//    @Test
//    void testOkWhenCustomerIsUpdatedAndPhoneNumberDoesNotExist() throws Exception {
//        // given
//        customerRepository.save(getCustomer(false));
//        customerPhoneRepository.saveAll(getCustomerPhones(true));
//        customerRequest.setPhoneNumber("9991991991");
//
//        // when -> then
//        mockMvc
//                .perform(patch(BASE_URL_V1 + CUSTOMER_ID).contentType(MediaType.APPLICATION_JSON_VALUE)
//                        .content(OBJECT_MAPPER.writeValueAsString(customerRequest)))
//                .andDo(print()).andExpect(status().isOk());
//
//        final var customer = customerRepository.findById(CUSTOMER_ID).orElseThrow();
//
//        assertThat(customer.getId(), equalTo(1090L));
//        assertThat(customer.getFirstName(), equalTo("Joe"));
//        assertThat(customer.getMiddleName(), equalTo("J"));
//        assertThat(customer.getLastName(), equalTo("Smith"));
//        assertThat(customer.getBirthDt(), equalTo(LocalDate.of(1990, 1, 1)));
//        assertThat(customer.getSocialSecurityNumber(), equalTo("1234567890"));
//        assertThat(customer.getEmail1(), equalTo("test@snapfinance.com"));
//        assertThat(customer.getYearsAtCurrentResidence(), equalTo(1));
//        assertThat(customer.getMonthsAtCurrentResidence(), equalTo(6));
//        assertThat(customer.getStreetAddress(), equalTo("Some Address"));
//        assertThat(customer.getUnit(), equalTo("Unit"));
//        assertThat(customer.getCity(), equalTo("Miami"));
//        assertThat(customer.getState(), equalTo(State.FL));
//        assertThat(customer.getZipCode(), equalTo("4455"));
//
//        final var customerPhones = customerPhoneRepository.findAllByCustomer(customer);
//
//        assertTrue(customerPhones.stream()
//                .anyMatch(s -> s.getPhoneNumber().equals("9991991991") && s.isPrimary()));
//        assertTrue(customerPhones.stream()
//                .anyMatch(s -> s.getPhoneNumber().equals("8432171263") && !s.isPrimary()));
//        assertTrue(customerPhones.stream()
//                .anyMatch(s -> s.getPhoneNumber().equals("4436803398") && !s.isPrimary()));
//        assertTrue(customerPhones.stream()
//                .anyMatch(s -> s.getPhoneNumber().equals("3137179590") && !s.isPrimary()));
//    }

//    @Test
//    void testNotFoundWhenCustomerDoesNotExist() throws Exception {
//        // when -> then
//        mockMvc
//                .perform(patch(BASE_URL_V1 + 0).contentType(MediaType.APPLICATION_JSON_VALUE)
//                        .content(OBJECT_MAPPER.writeValueAsString(customer)))
//                .andDo(print()).andExpect(status().isNotFound())
//                .andExpect(jsonPath("$.message").value("Resource Not Found Exception: Customer not found"));
//    }

    private Customer getCustomer() {
        return new Customer(1, "Steph", "Trejos", LocalDate.of(2001, 10, 3), "72076070", "stephgmail.com", Language.ENGLISH);
    }
}
