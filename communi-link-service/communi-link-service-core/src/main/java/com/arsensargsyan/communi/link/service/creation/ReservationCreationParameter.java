package com.arsensargsyan.communi.link.service.creation;

import com.arsensargsyan.communi.link.common.DataRange;

public record ReservationCreationParameter(Long communityId, String username, String mail, String identifier, DataRange range) {
}