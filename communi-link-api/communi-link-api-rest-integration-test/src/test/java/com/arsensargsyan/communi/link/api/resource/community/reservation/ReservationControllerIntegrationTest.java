package com.arsensargsyan.communi.link.api.resource.community.reservation;

import static com.arsensargsyan.communi.link.api.resource.community.CommunityControllerIntegrationTestHelper.COMMUNITY_ID;
import static com.arsensargsyan.communi.link.api.resource.community.CommunityControllerIntegrationTestHelper.RESERVATION_ID;
import static com.arsensargsyan.communi.link.api.resource.community.CommunityControllerIntegrationTestHelper.reservationCreationRequest;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.arsensargsyan.communi.link.api.resource.community.AbstractCommunityControllerIntegrationTest;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;

public class ReservationControllerIntegrationTest extends AbstractCommunityControllerIntegrationTest {

    @Test
    public void testReserve() throws Exception {
        mockMvc.perform(post("/community/{communityId}/reserve", COMMUNITY_ID)
                        .content(objectMapper.writeValueAsString(reservationCreationRequest()))
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$").isNotEmpty())
                .andExpect(jsonPath("$.reservationId").isNotEmpty())
                .andExpect(jsonPath("$.successful").value(Boolean.TRUE));
    }

    @Test
    public void testCancel() throws Exception {
        mockMvc.perform(delete("/community/{communityId}/cancel/{reservationId}", COMMUNITY_ID, RESERVATION_ID))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isNotEmpty())
                .andExpect(jsonPath("$.successful").value(Boolean.TRUE));
    }

}