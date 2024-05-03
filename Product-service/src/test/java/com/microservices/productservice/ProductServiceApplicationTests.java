package com.microservices.productservice;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.microservices.productservice.ProductRepository.ProductRepository;
import com.microservices.productservice.dto.ProductRequest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.testcontainers.containers.MongoDBContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.shaded.com.fasterxml.jackson.core.JsonProcessingException;

import java.math.BigDecimal;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@Testcontainers
@AutoConfigureMockMvc
class ProductServiceApplicationTests {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private MockMvc mockMvc;
    @Container
    static MongoDBContainer mongoDBContainer = new MongoDBContainer("mongo:4.4.2");

    @DynamicPropertySource
    static void setProperties(DynamicPropertyRegistry dymDynamicPropertyRegistry) {
        dymDynamicPropertyRegistry.add("spring.data.mongodb.uri", mongoDBContainer::getReplicaSetUrl);
    }


    @Test
    void shouldCreateProduct() throws Exception {
        ProductRequest requestSent = getProductRequest();
        String requestSentString = objectMapper.writeValueAsString(requestSent);

        mockMvc.perform(MockMvcRequestBuilders.post("/api/product")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestSentString))
                .andExpect(status().isCreated());


        Assertions.assertEquals(1, productRepository.findAll().size());
    }

    public ProductRequest getProductRequest() {
        ProductRequest request = ProductRequest.builder()
                .name("testProduct")
                .description("testing the product service")
                .price(BigDecimal.valueOf(1900))
                .build();

        return request;
    }


}
