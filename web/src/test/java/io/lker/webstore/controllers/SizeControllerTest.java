package io.lker.webstore.controllers;

import com.google.gson.reflect.TypeToken;
import io.lker.webstore.common.model.product.ProductSize;
import io.lker.webstore.usermanagement.services.springjpa.SizeJPAService;
import io.lker.webstore.usermanagement.util.TestUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
class SizeControllerTest {

    @Mock
    SizeJPAService sizeJPAService;

    @InjectMocks
    SizeController sizeController;

    MockMvc mockMvc;

    Set<ProductSize> sizes;


    @BeforeEach
    void setUp() {
        sizes = new HashSet<>();
        sizes.add(ProductSize.builder().id(1L).name("S")
                .description("Small Size")
                .build());
        sizes.add(ProductSize.builder().id(2L).name("M")
                .description("Medium Size")
                .build());
        sizes.add(ProductSize.builder().id(3L).name("L")
                .description("Large Size")
                .build());

        mockMvc = MockMvcBuilders.standaloneSetup(sizeController)
                .build();

    }

    @Test
    void save() throws Exception {
        ProductSize productSize = ProductSize.builder().id(10L).build();
        String json = TestUtils.objectToJson(productSize);
        mockMvc.perform(post("/api/sizes")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(status().isOk()
        );
    }

    @Test
    void findAll() throws Exception {
        when(sizeJPAService.findAll()).thenReturn(sizes);
        MvcResult result = mockMvc.perform(get("/api/sizes")).andReturn();
        List<ProductSize> returnedSizes = TestUtils.jsonToList(result.getResponse().getContentAsString(),
                new TypeToken<ArrayList<ProductSize>>(){});
        assertNotNull(returnedSizes);
        assertEquals(3, returnedSizes.size());

    }
}