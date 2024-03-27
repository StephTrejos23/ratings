package io.javabrains.ratingsdataservice.controller;

import static io.javabrains.ratingsdataservice.util.TestUtil.getObjectMapper;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.time.LocalDate;
import java.util.List;

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
@AutoConfigureTestDatabase
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

    customerRepository.deleteAll();

    customer = getCustomer();
  }

  @Test
  void testWhenSavingCustomerResponseOk() throws Exception {
    // given
    customer.setId(0);

    // when -> then
    mockMvc
        .perform(post(BASE_URL_V1_CUSTOMER).contentType(MediaType.APPLICATION_JSON_VALUE)
            .content(OBJECT_MAPPER.writeValueAsString(customer)))
        .andDo(print()).andExpect(status().isCreated());

    final List<Customer> customers = customerRepository.findAll();
    assertEquals(1, customers.size());
  }

  @Test
  void testWhenSavingCustomerAndIdIsSetThenResponseBadRequest() throws Exception {
    // given
    customer.setId(1);

    // when -> then
    mockMvc
        .perform(post(BASE_URL_V1_CUSTOMER).contentType(MediaType.APPLICATION_JSON_VALUE)
            .content(OBJECT_MAPPER.writeValueAsString(customer)))
        .andDo(print()).andExpect(status().isBadRequest())
        .andExpect(jsonPath("$.message").value(containsString("Invalid client exception: Id must be 0.")));

    final List<Customer> customers = customerRepository.findAll();
    assertEquals(0, customers.size());
  }

  @Test
  void testWhenVerifyingEmailThenResponseBadRequest() throws Exception {
    // given
    customer.setId(0);
    customer.setEmail("stephgmail.com");

    // when -> then
    mockMvc
        .perform(post(BASE_URL_V1_CUSTOMER).contentType(MediaType.APPLICATION_JSON_VALUE)
            .content(OBJECT_MAPPER.writeValueAsString(customer)))
        .andDo(print()).andExpect(status().isBadRequest())
        .andExpect(jsonPath("$.message").value(containsString("Invalid client exception: Email must contain an @.")));

    final List<Customer> customers = customerRepository.findAll();
    assertEquals(0, customers.size());
  }

  @Test
  void testWhenVerifyingPhoneWithLettersThenResponseBadRequest() throws Exception {
    customer.setId(0);
    customer.setPhone("70205033s76");

    // when -> then
    mockMvc
        .perform(post(BASE_URL_V1_CUSTOMER).contentType(MediaType.APPLICATION_JSON_VALUE)
            .content(OBJECT_MAPPER.writeValueAsString(customer)))
        .andDo(print()).andExpect(status().isBadRequest())
        .andExpect(jsonPath("$.message").value(containsString("Invalid client exception: Phone must contain 10 digits.")));

    final List<Customer> customers = customerRepository.findAll();
    assertEquals(0, customers.size());
  }

  @Test
  void testWhenUpdatingCustomerThenResponseOk() throws Exception {
    // given
    customerRepository.save(customer);
    customer.setFirstName("Jose");

    // when -> then
    mockMvc
        .perform(put(BASE_URL_V1_CUSTOMER + "/" + customer.getId())
            .contentType(MediaType.APPLICATION_JSON_VALUE).content(OBJECT_MAPPER.writeValueAsString(customer)))
        .andDo(print()).andExpect(status().isOk());

    final var result = customerRepository.findById(customer.getId()).orElseThrow();

    assertThat(result.getId(), equalTo(1));
    assertThat(result.getFirstName(), equalTo("Jose"));
    assertThat(result.getLastName(), equalTo("Trejos"));
    assertThat(result.getBirthDate(), equalTo(LocalDate.of(2001, 10, 3)));
    assertThat(result.getPhone(), equalTo("7207622070"));
    assertThat(result.getEmail(), equalTo("steph@gmail.com"));
    assertThat(result.getLanguage(), equalTo(Language.ENGLISH));
  }

  @Test
  void testWhenUpdatingCustomerDoesNotExistThenResponseNotFound() throws Exception {
    // when -> then
    mockMvc
        .perform(put( BASE_URL_V1_CUSTOMER + "/" + 0).contentType(MediaType.APPLICATION_JSON_VALUE)
            .content(OBJECT_MAPPER.writeValueAsString(customer)))
        .andDo(print()).andExpect(status().isNotFound())
        .andExpect(jsonPath("$.message").value("Resource not found exception: The customer does not exist"));
  }
  @Test
  void testWhenDeletingCustomerResponseOk() throws Exception {
    // given
    customerRepository.save(customer);
    int customerId = customerRepository.findAll().stream().map(Customer::getId).findFirst().orElse(1);

    // when -> then
    mockMvc
        .perform(delete(BASE_URL_V1_CUSTOMER+ "/" + customerId))
        .andDo(print()).andExpect(status().isOk());

    final List<Customer> customers = customerRepository.findAll();
    assertEquals(0, customers.size());
  }

  @Test
  void testWhenDeletingCustomerThenResponseBadRequest() throws Exception {
    // when -> then
    mockMvc
        .perform(delete( BASE_URL_V1_CUSTOMER + "/" + 12))
        .andDo(print()).andExpect(status().isNotFound())
        .andExpect(jsonPath("$.message").value("Resource not found exception: The customer does not exist"));
  }

  @Test
  void testWhenGettingCustomersResponseOk() throws Exception {
    // given
    customerRepository.save(customer);
    int customerId = customerRepository.findAll().stream().map(Customer::getId).findFirst().orElse(1);
    // when -> then
    mockMvc
        .perform(get(BASE_URL_V1 + "/" + "customers"))
        .andDo(print()).andExpect(status().isOk())
        .andExpect(jsonPath("$.[0].id",equalTo(customerId)))
        .andExpect(jsonPath("$.[0].firstName",equalTo("Steph")))
        .andExpect(jsonPath("$.[0].lastName",equalTo("Trejos")))
        .andExpect(jsonPath("$.[0].birthDate",equalTo("2001-10-03")))
        .andExpect(jsonPath("$.[0].phone",equalTo("7207622070")))
        .andExpect(jsonPath("$.[0].email",equalTo("steph@gmail.com")))
        .andExpect(jsonPath("$.[0].language",equalTo("ENGLISH")));
  }

  @Test
  void testWhenGettingCustomerThenResponseBadRequest() throws Exception {
    // when -> then
    mockMvc
        .perform(get(BASE_URL_V1_CUSTOMER + "/" + 1))
        .andDo(print()).andExpect(status().isNotFound())
        .andExpect(jsonPath("$.message").value(containsString("Resource not found exception: The customer does not exist")));
  }

  @Test
  void testWhenGettingCustomerThenResponseIsOk() throws Exception {
    //given
    customerRepository.save(customer);
    int customerId = customerRepository.findAll().stream().map(Customer::getId).findFirst().orElse(1);
    // when -> then
    mockMvc
        .perform(get(BASE_URL_V1_CUSTOMER + "/" + customerId))
        .andDo(print()).andExpect(status().isOk())
        .andExpect(jsonPath("$.id",equalTo(customerId)))
        .andExpect(jsonPath("$.firstName",equalTo("Steph")))
        .andExpect(jsonPath("$.lastName",equalTo("Trejos")))
        .andExpect(jsonPath("$.birthDate",equalTo("2001-10-03")))
        .andExpect(jsonPath("$.phone",equalTo("7207622070")))
        .andExpect(jsonPath("$.email",equalTo("steph@gmail.com")))
        .andExpect(jsonPath("$.language",equalTo("ENGLISH")));
  }

  private Customer getCustomer() {
    return new Customer(1, "Steph", "Trejos", LocalDate.of(2001, 10, 3), "7207622070", "steph@gmail.com", Language.ENGLISH);
  }
}
