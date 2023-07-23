package com.arsensargsyan.communi.link.service;

import com.arsensargsyan.communi.link.service.creation.ReservationCreationParameter;
import com.arsensargsyan.communi.link.service.creation.ReservationCreationResult;

public interface ReservationService {

    ReservationCreationResult create(ReservationCreationParameter parameter);
}