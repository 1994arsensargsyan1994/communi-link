package com.arsensargsyan.communi.link.api.resource.community;

import static com.arsensargsyan.communi.link.api.resource.community.CommunityControllerIntegrationTestHelper.COMMUNITY_ID;
import static com.arsensargsyan.communi.link.api.resource.community.CommunityControllerIntegrationTestHelper.communityCreationRequest;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;

public class CommunityControllerIntegrationTest extends AbstractCommunityControllerIntegrationTest {

    @Test
    public void testCreate() throws Exception {
        mockMvc.perform(post("/community")
                        .content(objectMapper.writeValueAsString(communityCreationRequest()))
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$").isNotEmpty())
                .andExpect(jsonPath("$.communityId").isNotEmpty())
                .andExpect(jsonPath("$.successful").value(Boolean.TRUE));
    }

    @Test
    public void testLookupDetails() throws Exception {
        mockMvc.perform(get("/community/{id}/details", COMMUNITY_ID)
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$").isNotEmpty())
                .andExpect(jsonPath("$.successful").value(Boolean.TRUE))
                .andExpect(jsonPath("$.details").exists());
    }
}