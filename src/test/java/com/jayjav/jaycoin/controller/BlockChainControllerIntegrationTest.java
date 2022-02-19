package com.jayjav.jaycoin.controller;

import com.jayjav.jaycoin.JaycoinApplication;
import com.jayjav.jaycoin.util.ApplicationConstants;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * @author OMONIYI ILESANMI
 */
@AutoConfigureMockMvc
@SpringBootTest(classes = {JaycoinApplication.class})
public class BlockChainControllerIntegrationTest {


    @Autowired
    private MockMvc restAuditMockMvc;

    @BeforeEach
    public void initTest() {
    }

    @Test
    @DisplayName("Get Block Chain")
    public void getChain() throws Exception {

        restAuditMockMvc.perform(get("/api/v1/getChain"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$.[*].index").value(hasItem(1)))
                .andExpect(jsonPath("$.[*].proof").value(hasItem(1)))
                .andExpect(jsonPath("$.[*].previousHash").value(hasItem("0")));
    }

    @Test
    @DisplayName("Mine Block")
    public void mineBlock() throws Exception {

        restAuditMockMvc.perform(get("/api/v1/mineBlock"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$.index").value(2))
                .andExpect(jsonPath("$.proof").value(38561))
                .andExpect(jsonPath("$.message").value(ApplicationConstants.CONGRATULATIONS_MESSAGE));
    }

    @Test
    @DisplayName("validate Block Chain")
    public void validateChain() throws Exception {

        restAuditMockMvc.perform(get("/api/v1/validateChain"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$.size").value(1))
                .andExpect(jsonPath("$.message").value(ApplicationConstants.VALID_BLOCK_MESSAGE));
    }
}
