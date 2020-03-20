package com.example.product.controller;

import com.example.product.config.AppConfig;
import com.example.product.exception.handler.ProductGlobalExceptionHandler;
import com.example.product.model.ProductRequest;
import com.example.product.model.ProductResponse;
import com.example.product.service.ProductService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

@RunWith(SpringRunner.class)
public class ProductCatalogControllerTest {
    @Mock
    ProductService mockProductService;

    @InjectMocks
    ProductCatalogController mockProductCatalogController;

    private ProductGlobalExceptionHandler exceptionalHandler = new ProductGlobalExceptionHandler();
    private MockMvc mockMvc;
    private ObjectMapper objectMapper;
    private String basePath;
    private ProductResponse productResponse;
    private ProductRequest productRequest;

    @Before
    public void setUp() throws Exception {
        initMocks(this);
        objectMapper = new AppConfig().objectMapper();
        ReflectionTestUtils.setField(exceptionalHandler, "objectMapper", objectMapper);
        basePath = "/v1/products";
        productResponse = getProductResponse();
        productRequest = getProductRequest();
        this.mockMvc = MockMvcBuilders.standaloneSetup(mockProductCatalogController).setControllerAdvice(exceptionalHandler).build();
    }

    @After
    public void tearDown() {
        basePath = null;
        productResponse = null;
        productRequest = null;
        mockMvc = null;
    }

    /**
     * Scenario: Test to save datasource configurations.
     * <p/>
     * Expected: Since the request is valid, configuration will be saved successfully with Http status 200 OK
     */
    @Test
    public void testSaveProduct() throws Exception {
        when(mockProductService.createOrUpdateProduct(any(ProductRequest.class))).thenReturn(productResponse);
        String requestPayload = objectMapper.writeValueAsString(productRequest);
        RequestBuilder requestBuilder = MockMvcRequestBuilders.post(basePath + "/product")
                .content(requestPayload).contentType(MediaType.APPLICATION_JSON);
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        MockHttpServletResponse response = result.getResponse();
        assertEquals("Status code must be same as expected", HttpStatus.NOT_FOUND.value(), response.getStatus());
    }

    private ProductResponse getProductResponse() {
        ProductResponse productRes = new ProductResponse();
        productRes.setId("1");
        productRes.setDescription("description");
        productRes.setBrand("Brand");
        productRes.setName("Product");
        productRes.setCategory("category");
        return productRes;
    }

    private ProductRequest getProductRequest() {
        ProductRequest productReq = new ProductRequest();
        productReq.setBrand("Brand");
        productReq.setName("Product");
        productReq.setCategory("category");
        productReq.setDescription("description");
        return productReq;
    }


}
