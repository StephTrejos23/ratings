package io.javabrains.ratingsdataservice.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.javabrains.ratingsdataservice.models.Product;
import io.javabrains.ratingsdataservice.models.Supplier;
import io.javabrains.ratingsdataservice.repository.ProductRepository;
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

import java.time.LocalDate;

import static io.javabrains.ratingsdataservice.util.TestUtil.getObjectMapper;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;
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
public class ProductControllerIntegrationTest {
  private static final ObjectMapper OBJECT_MAPPER = getObjectMapper();
  private static final int PRODUCT_ID = 200;
  private static final String PRODUCT_NAME = "Cafe";

  private static final String BASE_URL_V1 = "/api/v1/";
  private static final String BASE_URL_V1_PRODUCT = BASE_URL_V1 + "product";

  private MockMvc mockMvc;

  @Autowired
  private WebApplicationContext context;

  @Autowired
  private ProductRepository productRepository;

  private Product product;

  @BeforeEach
  public void setUp() {
    mockMvc = MockMvcBuilders.webAppContextSetup(context).build();

    product = getProduct();
  }

  @Test
  void testWhenCreatingProductResponseOk() throws Exception {
    // given
    product.setId(0);

    // when -> then
    mockMvc
        .perform(post(BASE_URL_V1_PRODUCT).contentType(MediaType.APPLICATION_JSON_VALUE)
            .content(OBJECT_MAPPER.writeValueAsString(product)))
        .andDo(print()).andExpect(status().isCreated());

    assertTrue(productRepository.findByName(PRODUCT_NAME).isPresent());
  }
  @Test
  void testWhenCreatingProductAndIdIsSetThenResponseBadRequest() throws Exception {
    // given
    product.setId(1);

    // when -> then
    mockMvc
        .perform(post(BASE_URL_V1_PRODUCT).contentType(MediaType.APPLICATION_JSON_VALUE)
            .content(OBJECT_MAPPER.writeValueAsString(product)))
        .andDo(print()).andExpect(status().isBadRequest())
        .andExpect(jsonPath("$.message").value(containsString("Invalid client exception: Id must be 0.")));

    assertFalse(productRepository.findByName(PRODUCT_NAME).isPresent());
  }

  @Test
  void testWhenVerifyingNameNullThenResponseBadRequest() throws Exception {
    // given
    product.setId(0);
    product.setName(null);


    // when -> then
    mockMvc
        .perform(post(BASE_URL_V1_PRODUCT).contentType(MediaType.APPLICATION_JSON_VALUE)
            .content(OBJECT_MAPPER.writeValueAsString(product)))
        .andDo(print()).andExpect(status().isBadRequest())
        .andExpect(jsonPath("$.message").value(containsString("Invalid client exception: Name cannot be null or blank")));

    assertFalse(productRepository.findByName(null).isPresent());
  }

  @Test
  void testWhenVerifyingExpiredDateThenResponseBadRequest() throws Exception {
    product.setId(0);
    product.setExpirationDate(LocalDate.parse("2024-03-26"));

    // when -> then
    mockMvc
        .perform(post(BASE_URL_V1_PRODUCT).contentType(MediaType.APPLICATION_JSON_VALUE)
            .content(OBJECT_MAPPER.writeValueAsString(product)))
        .andDo(print()).andExpect(status().isBadRequest())
        .andExpect(jsonPath("$.message").value(containsString("Invalid client exception: The product is expired")));

    assertFalse(productRepository.findByName(PRODUCT_NAME).isPresent());
  }
  @Test
  void testWhenVerifyingPrice0ThenResponseBadRequest() throws Exception {
    product.setId(0);
    product.setExpirationDate(LocalDate.parse("2024-03-28"));
    product.setPrice(0);

    // when -> then
    mockMvc
        .perform(post(BASE_URL_V1_PRODUCT).contentType(MediaType.APPLICATION_JSON_VALUE)
            .content(OBJECT_MAPPER.writeValueAsString(product)))
        .andDo(print()).andExpect(status().isBadRequest())
        .andExpect(jsonPath("$.message").value(containsString("Invalid client exception: The price of the product cannot be 0!")));

    assertFalse(productRepository.findByName(PRODUCT_NAME).isPresent());
  }
  @Test
  void testWhenVerifyingSupplierMissingThenResponseBadRequest() throws Exception {
    product.setId(0);
    product.setExpirationDate(LocalDate.parse("2024-03-28"));
    product.setSupplier(null);
    // when -> then
    mockMvc
        .perform(post(BASE_URL_V1_PRODUCT).contentType(MediaType.APPLICATION_JSON_VALUE)
            .content(OBJECT_MAPPER.writeValueAsString(product)))
        .andDo(print()).andExpect(status().isBadRequest())
        .andExpect(jsonPath("$.message").value(containsString("Invalid client exception: Supplier missing")));

    assertFalse(productRepository.findByName(PRODUCT_NAME).isPresent());
  }

  @Test
  void testWhenVerifyingSupplierDoesNotExistThenResponseBadRequest() throws Exception {
    product.setId(0);
    product.setPrice(24);
    product.setExpirationDate(LocalDate.parse("2024-03-28"));
    product.getSupplier().setId(50);
    // when -> then
    mockMvc
        .perform(post(BASE_URL_V1_PRODUCT).contentType(MediaType.APPLICATION_JSON_VALUE)
            .content(OBJECT_MAPPER.writeValueAsString(product)))
        .andDo(print()).andExpect(status().isNotFound())
        .andExpect(jsonPath("$.message").value(containsString("Resource not found exception: Supplier does not exist")));

    assertFalse(productRepository.findByName(PRODUCT_NAME).isPresent());
  }

  @Test
  void testWhenUpdatingProductThenResponseOk() throws Exception {
    // when -> then
    mockMvc
        .perform(put(BASE_URL_V1_PRODUCT + "/" + product.getId())
            .contentType(MediaType.APPLICATION_JSON_VALUE).content(OBJECT_MAPPER.writeValueAsString(product)))
        .andDo(print()).andExpect(status().isOk());

    final var result = productRepository.findById(product.getId()).orElseThrow();

    assertThat(result.getId(), equalTo(200));
    assertThat(result.getName(), equalTo(product.getName()));
    assertThat(result.getExpirationDate(), equalTo(product.getExpirationDate()));
    assertThat(result.getPrice(), equalTo(product.getPrice()));
    assertThat(result.getSupplier().getId(), equalTo(product.getSupplier().getId()));
  }

  @Test
  void testWhenUpdatingProductDoesNotExistThenResponseNotFound() throws Exception {
    // when -> then
    mockMvc
        .perform(put(BASE_URL_V1_PRODUCT + "/15" ).contentType(MediaType.APPLICATION_JSON_VALUE)
            .content(OBJECT_MAPPER.writeValueAsString(product)))
        .andDo(print()).andExpect(status().isNotFound())
        .andExpect(jsonPath("$.message").value("Resource not found exception: The product does not exist"));
  }
  @Test
  void testWhenUpdatingNameNullThenResponseBadRequest() throws Exception {
    // given
    product.setName(null);

    // when -> then
    mockMvc
        .perform(put(BASE_URL_V1_PRODUCT + "/" + product.getId()).contentType(MediaType.APPLICATION_JSON_VALUE)
            .content(OBJECT_MAPPER.writeValueAsString(product)))
        .andDo(print()).andExpect(status().isBadRequest())
        .andExpect(jsonPath("$.message").value(containsString("Invalid client exception: Name cannot be null or blank")));
  }

  @Test
  void testWhenUpdatingExpiredDateThenResponseBadRequest() throws Exception {
    product.setExpirationDate(LocalDate.now().minusDays(1));

    // when -> then
    mockMvc
        .perform(put(BASE_URL_V1_PRODUCT + "/" + product.getId()).contentType(MediaType.APPLICATION_JSON_VALUE)
            .content(OBJECT_MAPPER.writeValueAsString(product)))
        .andDo(print()).andExpect(status().isBadRequest())
        .andExpect(jsonPath("$.message").value(containsString("Invalid client exception: The product is expired")));

    assertFalse(productRepository.findByName(PRODUCT_NAME).isPresent());
  }
  @Test
  void testWhenUpdatingPrice0ThenResponseBadRequest() throws Exception {
    product.setExpirationDate(LocalDate.now().plusDays(1));
    product.setPrice(0);

    // when -> then
    mockMvc
        .perform(put(BASE_URL_V1_PRODUCT + "/" + product.getId()).contentType(MediaType.APPLICATION_JSON_VALUE)
            .content(OBJECT_MAPPER.writeValueAsString(product)))
        .andDo(print()).andExpect(status().isBadRequest())
        .andExpect(jsonPath("$.message").value(containsString("Invalid client exception: The price of the product cannot be 0!")));

    assertFalse(productRepository.findByName(PRODUCT_NAME).isPresent());
  }
  @Test
  void testWhenUpdatingSupplierMissingThenResponseBadRequest() throws Exception {
    product.setExpirationDate(LocalDate.now().plusDays(1));
    product.setSupplier(null);
    // when -> then
    mockMvc
        .perform(put(BASE_URL_V1_PRODUCT + "/" + product.getId()).contentType(MediaType.APPLICATION_JSON_VALUE)
            .content(OBJECT_MAPPER.writeValueAsString(product)))
        .andDo(print()).andExpect(status().isBadRequest())
        .andExpect(jsonPath("$.message").value(containsString("Invalid client exception: Supplier missing")));

    assertFalse(productRepository.findByName(PRODUCT_NAME).isPresent());
  }

  @Test
  void testWhenUpdatingSupplierDoesNotExistThenResponseBadRequest() throws Exception {
    product.setPrice(24);
    product.setExpirationDate(LocalDate.now().plusDays(1));
    product.getSupplier().setId(50);
    // when -> then
    mockMvc
        .perform(put(BASE_URL_V1_PRODUCT + "/" + product.getId()).contentType(MediaType.APPLICATION_JSON_VALUE)
            .content(OBJECT_MAPPER.writeValueAsString(product)))
        .andDo(print()).andExpect(status().isNotFound())
        .andExpect(jsonPath("$.message").value(containsString("Resource not found exception: Supplier does not exist")));

    assertFalse(productRepository.findByName(PRODUCT_NAME).isPresent());
  }

  @Test
  void testWhenDeletingProductResponseOk() throws Exception {
    // when -> then
    mockMvc
        .perform(delete(BASE_URL_V1_PRODUCT + "/" + product.getId()))
        .andDo(print()).andExpect(status().isOk());

    assertFalse(productRepository.findById(product.getId()).isPresent());
  }

  @Test
  void testWhenDeletingProductThenResponseBadRequest() throws Exception {
    // when -> then
    mockMvc
        .perform(delete(BASE_URL_V1_PRODUCT + "/" + 12))
        .andDo(print()).andExpect(status().isNotFound())
        .andExpect(jsonPath("$.message").value("Resource not found exception: The product does not exist"));
  }

  @Test
  void testWhenGettingProductResponseOk() throws Exception {
    // when -> then
    mockMvc
        .perform(get(BASE_URL_V1 + "/products"))
        .andDo(print()).andExpect(status().isOk())
        .andExpect(jsonPath("$.[0].id", equalTo(200)))
        .andExpect(jsonPath("$.[0].name", equalTo("Helado")))
        .andExpect(jsonPath("$.[0].expirationDate", equalTo("2020-02-02")))
        .andExpect(jsonPath("$.[0].price", equalTo(24.0)))
        .andExpect(jsonPath("$.[0].supplier.id", equalTo(100)))
        .andExpect(jsonPath("$.[1].id", equalTo(201)))
        .andExpect(jsonPath("$.[1].name", equalTo("Yogurt")))
        .andExpect(jsonPath("$.[1].expirationDate", equalTo("2020-02-02")))
        .andExpect(jsonPath("$.[1].price", equalTo(20.0)))
        .andExpect(jsonPath("$.[1].supplier.id", equalTo(101)));
  }

  @Test
  void testWhenGettingProductThenResponseBadRequest() throws Exception {
    // when -> then
    mockMvc
        .perform(get(BASE_URL_V1_PRODUCT + "/" + 12))
        .andDo(print()).andExpect(status().isNotFound())
        .andExpect(jsonPath("$.message").value(containsString("Resource not found exception: The product does not exist")));
  }

  @Test
  void testWhenGettingProductThenResponseIsOk() throws Exception {
    // when -> then
    mockMvc
        .perform(get(BASE_URL_V1_PRODUCT + "/" + PRODUCT_ID))
        .andDo(print()).andExpect(status().isOk())
        .andExpect(jsonPath("$.id", equalTo(200)))
        .andExpect(jsonPath("$.name", equalTo("Helado")))
        .andExpect(jsonPath("$.expirationDate", equalTo("2020-02-02")))
        .andExpect(jsonPath("$.price", equalTo(24.0)))
        .andExpect(jsonPath("$.supplier.id", equalTo(100)));

  }
  private Supplier getSupplier() {
    return new Supplier(100, "", "", "");
  }

  private Product getProduct() {
    return new Product(PRODUCT_ID, PRODUCT_NAME, LocalDate.now().plusDays(1), 24f, getSupplier());
  }

}
