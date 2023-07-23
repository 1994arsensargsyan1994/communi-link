package com.arsensargsyan.communi.link.api.facade.validation;

import static com.arsensargsyan.communi.link.api.facade.CommunityServiceFacadeTestHelper.reservationCreationRequest;

import java.time.LocalDateTime;

import com.arsensargsyan.communi.link.api.facade.AbstractCommunityServiceFacadeTest;
import com.arsensargsyan.communi.link.common.DataRange;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.test.util.ReflectionTestUtils;

public class DefaultReservationCreationRequestValidatorTest extends AbstractCommunityServiceFacadeTest {

    private int days;

    @InjectMocks
    private DefaultReservationCreationRequestValidator requestValidator;

    @Override
    @AfterEach
    protected void verifyNoMoreMockInteractions() {
    }

    @BeforeEach
    public void setUp() {
        ReflectionTestUtils.setField(requestValidator, "days", 7);
    }

    @Test
    public void testValidateWithInvalidValidStartValue() {
        final var request = reservationCreationRequest();
        request.start(DataRange.MAX_DATE.plusDays(1));

        final ValidationResult validationResult = requestValidator.validate(request);

        Assertions.assertThat(validationResult).isNotNull();
        Assertions.assertThat(validationResult.hasFailures()).isTrue();
    }

    @Test
    public void testValidateWithInvalidValidEndValue() {
        final var request = reservationCreationRequest();
        request.end(DataRange.MAX_DATE.plusDays(1));

        final ValidationResult validationResult = requestValidator.validate(request);

        Assertions.assertThat(validationResult).isNotNull();
        Assertions.assertThat(validationResult.hasFailures()).isTrue();
    }

    @Test
    public void testValidateWithInvalidRangeValue() {
        final var request = reservationCreationRequest();
        request.start(null);
        request.end(null);

        final ValidationResult validationResult = requestValidator.validate(request);

        Assertions.assertThat(validationResult).isNotNull();
        Assertions.assertThat(validationResult.hasFailures()).isTrue();
    }

    @Test
    public void testValidateWithInvalidStartIsAfterEndValue() {
        final var request = reservationCreationRequest();
        request.start(LocalDateTime.now());
        request.end(request.start().minusDays(1));

        final ValidationResult validationResult = requestValidator.validate(request);

        Assertions.assertThat(validationResult).isNotNull();
        Assertions.assertThat(validationResult.hasFailures()).isTrue();
    }

    @Test
    public void testValidateWithInvalidRangeWithDaysValue() {
        final var request = reservationCreationRequest();
        request.start(LocalDateTime.now());
        request.end(request.start().plusDays(8));

        final ValidationResult validationResult = requestValidator.validate(request);

        Assertions.assertThat(validationResult).isNotNull();
        Assertions.assertThat(validationResult.hasFailures()).isTrue();
    }

    @Test
    public void testValidate() {
        final var request = reservationCreationRequest();
        request.start(LocalDateTime.now());
        request.end(request.start().plusDays(6));

        final ValidationResult validationResult = requestValidator.validate(request);

        Assertions.assertThat(validationResult.failures()).isEmpty();
        Assertions.assertThat(validationResult.hasFailures()).isFalse();
    }
}