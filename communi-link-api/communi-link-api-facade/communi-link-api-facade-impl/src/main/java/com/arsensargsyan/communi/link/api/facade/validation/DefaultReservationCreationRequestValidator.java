package com.arsensargsyan.communi.link.api.facade.validation;

import static com.arsensargsyan.communi.link.common.DataRange.MAX_DATE;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.arsensargsyan.communi.link.api.model.request.ReservationCreationRequest;
import com.arsensargsyan.communi.link.common.DataRange;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
class DefaultReservationCreationRequestValidator implements RequestValidator<ReservationCreationRequest> {

    @Value("${reservation.duration.days:7}")
    private int days;

    @Override
    public ValidationResult validate(final ReservationCreationRequest request) {
        final List<ReservationCreationRequestValidationFailure> failures = new ArrayList<>();
        final LocalDateTime start = request.start();
        final LocalDateTime end = request.end();

        if (start != null && start.isAfter(MAX_DATE)) {
            failures.add(ReservationCreationRequestValidationFailure.RESERVATION_START_INVALID_VALUE);
        }
        if (end != null && end.isAfter(MAX_DATE)) {
            failures.add(ReservationCreationRequestValidationFailure.RESERVATION_END_INVALID_VALUE);
        }
        if (start == null && end == null) {
            return ValidationResult.failedWith(ReservationCreationRequestValidationFailure.RESERVATION_PERIOD_INVALID_REQUEST);
        }
        if (start != null && end != null && start.isAfter(end)) {
            failures.add(ReservationCreationRequestValidationFailure.RESERVATION_PERIOD_INVALID_VALUE);
        }
        if (DataRange.duration(start, end, days)) {
            failures.add(ReservationCreationRequestValidationFailure.RESERVATION_PERIOD_INVALID_REQUEST);
        }
        return ValidationResult.of(failures);
    }
}