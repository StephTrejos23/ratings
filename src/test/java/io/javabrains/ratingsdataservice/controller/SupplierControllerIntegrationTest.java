package io.javabrains.ratingsdataservice.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.javabrains.ratingsdataservice.models.Supplier;
import io.javabrains.ratingsdataservice.repository.SupplierRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlGroup;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static io.javabrains.ratingsdataservice.util.TestUtil.getObjectMapper;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * (enter description here)
 * <p>
 *
 * @author JChaves
 * @author Copyright (c) 2024 MountainView Software, Corp.
 */
@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureTestDatabase
@SqlGroup({
    @Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD, scripts = "classpath:sql/db/insert.sql"),
    @Sql(executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD, scripts = "classpath:sql/db/delete.sql")
})
public class SupplierControllerIntegrationTest {
  private static final ObjectMapper OBJECT_MAPPER = getObjectMapper();
  private static final int SUPPLIER_ID = 100;
  private static final String SUPPLIER_NAME = "Steph";

  private static final String BASE_URL_V1 = "/api/v1/";
  private static final String BASE_URL_V1_SUPPLIER = BASE_URL_V1 + "supplier";

  private MockMvc mockMvc;

  @Autowired
  private WebApplicationContext context;

  @Autowired
  private SupplierRepository supplierRepository;

  private Supplier supplier;

  @BeforeEach
  public void setUp() {
    mockMvc = MockMvcBuilders.webAppContextSetup(context).build();

    supplier = getSupplier();
  }

  @Test
  void testWhenCreatingSupplierResponseOk() throws Exception {
    // given
    supplier.setId(0);
    // when -> then
    mockMvc
        .perform(post(BASE_URL_V1_SUPPLIER).contentType(MediaType.APPLICATION_JSON_VALUE)
            .content(OBJECT_MAPPER.writeValueAsString(supplier)))
        .andDo(print()).andExpect(status().isCreated());

    assertTrue(supplierRepository.findByName(SUPPLIER_NAME).isPresent());
  }

  @Test
  void testWhenCreatingSupplierAndIdIsSetThenResponseBadRequest() throws Exception {
    // given
    supplier.setId(1);

    // when -> then
    mockMvc
        .perform(post(BASE_URL_V1_SUPPLIER).contentType(MediaType.APPLICATION_JSON_VALUE)
            .content(OBJECT_MAPPER.writeValueAsString(supplier)))
        .andDo(print()).andExpect(status().isBadRequest())
        .andExpect(jsonPath("$.message").value(containsString("Invalid client exception: Id must be 0.")));

    assertFalse(supplierRepository.findByName(SUPPLIER_NAME).isPresent());
  }

  @Test
  void testWhenVerifyingNameNullThenResponseBadRequest() throws Exception {
    // given
    supplier.setId(0);
    supplier.setName(null);


    // when -> then
    mockMvc
        .perform(post(BASE_URL_V1_SUPPLIER).contentType(MediaType.APPLICATION_JSON_VALUE)
            .content(OBJECT_MAPPER.writeValueAsString(supplier)))
        .andDo(print()).andExpect(status().isBadRequest())
        .andExpect(jsonPath("$.message").value(containsString("Invalid client exception: Name cannot be null or blank")));

    assertFalse(supplierRepository.findByName(null).isPresent());
  }

  @Test
  void testWhenVerifyingPhoneWithLettersThenResponseBadRequest() throws Exception {
    supplier.setId(0);
    supplier.setPhone("70205033s76");

    // when -> then
    mockMvc
        .perform(post(BASE_URL_V1_SUPPLIER).contentType(MediaType.APPLICATION_JSON_VALUE)
            .content(OBJECT_MAPPER.writeValueAsString(supplier)))
        .andDo(print()).andExpect(status().isBadRequest())
        .andExpect(jsonPath("$.message").value(containsString("Invalid client exception: Phone must contain 10 digits.")));

    assertFalse(supplierRepository.findByName(SUPPLIER_NAME).isPresent());
  }

  @Test
  void testWhenUpdatingSupplierThenResponseOk() throws Exception {
    // when -> then
    mockMvc
        .perform(put(BASE_URL_V1_SUPPLIER + "/" + supplier.getId())
            .contentType(MediaType.APPLICATION_JSON_VALUE).content(OBJECT_MAPPER.writeValueAsString(supplier)))
        .andDo(print()).andExpect(status().isOk());

    final var result = supplierRepository.findById(supplier.getId()).orElseThrow();

    assertThat(result.getId(), equalTo(100));
    assertThat(result.getName(), equalTo("Steph"));
    assertThat(result.getAddress(), equalTo("Costa Rica"));
    assertThat(result.getPhone(), equalTo("7207622070"));
  }

  @Test
  void testWhenUpdatingSupplierDoesNotExistThenResponseNotFound() throws Exception {
    // when -> then
    mockMvc
        .perform(put(BASE_URL_V1_SUPPLIER + "/" + 12).contentType(MediaType.APPLICATION_JSON_VALUE)
            .content(OBJECT_MAPPER.writeValueAsString(supplier)))
        .andDo(print()).andExpect(status().isNotFound())
        .andExpect(jsonPath("$.message").value("Resource not found exception: The supplier does not exist"));
  }

  @Test
  void testWhenDeletingSupplierResponseOk() throws Exception {
    // when -> then
    mockMvc
        .perform(delete(BASE_URL_V1_SUPPLIER + "/" + supplier.getId()))
        .andDo(print()).andExpect(status().isOk());

    assertFalse(supplierRepository.findById(supplier.getId()).isPresent());
  }

  @Test
  void testWhenDeletingSupplierThenResponseBadRequest() throws Exception {
    // when -> then
    mockMvc
        .perform(delete(BASE_URL_V1_SUPPLIER + "/" + 12))
        .andDo(print()).andExpect(status().isNotFound())
        .andExpect(jsonPath("$.message").value("Resource not found exception: The supplier does not exist"));
  }

  @Test
  void testWhenGettingSupplierResponseOk() throws Exception {
    // when -> then
    mockMvc
        .perform(get(BASE_URL_V1 + "/suppliers"))
        .andDo(print()).andExpect(status().isOk())
        .andExpect(jsonPath("$.[0].id", equalTo(100)))
        .andExpect(jsonPath("$.[0].name", equalTo("Dos Pinos")))
        .andExpect(jsonPath("$.[0].address", equalTo("Coyol")))
        .andExpect(jsonPath("$.[0].phone", equalTo("5522668855")))
        .andExpect(jsonPath("$.[1].id", equalTo(101)))
        .andExpect(jsonPath("$.[1].name", equalTo("Charo")))
        .andExpect(jsonPath("$.[1].address", equalTo("Poas")))
        .andExpect(jsonPath("$.[1].phone", equalTo("2222668855")))
        .andExpect(jsonPath("$.[2].id", equalTo(102)))
        .andExpect(jsonPath("$.[2].name", equalTo("Sensacion")))
        .andExpect(jsonPath("$.[2].address", equalTo("Grecia")))
        .andExpect(jsonPath("$.[2].phone", equalTo("5522677855")));
  }

  @Test
  void testWhenGettingSupplierThenResponseBadRequest() throws Exception {
    // when -> then
    mockMvc
        .perform(get(BASE_URL_V1_SUPPLIER + "/" + 12))
        .andDo(print()).andExpect(status().isNotFound())
        .andExpect(jsonPath("$.message").value(containsString("Resource not found exception: The supplier does not exist")));
  }

  @Test
  void testWhenGettingSupplierThenResponseIsOk() throws Exception {
    // when -> then
    mockMvc
        .perform(get(BASE_URL_V1_SUPPLIER + "/" + SUPPLIER_ID))
        .andDo(print()).andExpect(status().isOk())
        .andExpect(jsonPath("$.id", equalTo(SUPPLIER_ID)))
        .andExpect(jsonPath("$.name", equalTo("Dos Pinos")))
        .andExpect(jsonPath("$.address", equalTo("Coyol")))
        .andExpect(jsonPath("$.phone", equalTo("5522668855")));

  }

  private Supplier getSupplier() {
    return new Supplier(SUPPLIER_ID, SUPPLIER_NAME, "Costa Rica", "7207622070");
  }
}
