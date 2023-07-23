package com.arsensargsyan.communi.link.api.facade.creation;

import static com.arsensargsyan.communi.link.api.facade.CommunityServiceFacadeTestHelper.communityCreationRequest;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

import java.util.List;

import com.arsensargsyan.communi.link.api.facade.AbstractCommunityServiceFacadeTest;
import com.arsensargsyan.communi.link.api.facade.validation.CommunityCreationRequestValidationFailure;
import com.arsensargsyan.communi.link.api.facade.validation.RequestValidator;
import com.arsensargsyan.communi.link.api.facade.validation.ValidationResult;
import com.arsensargsyan.communi.link.api.model.request.CommunityCreationRequest;
import com.arsensargsyan.communi.link.service.CommunityService;
import com.arsensargsyan.communi.link.service.creation.CommunityCreationFailure;
import com.arsensargsyan.communi.link.service.creation.CommunityCreationParameter;
import com.arsensargsyan.communi.link.service.creation.CommunityCreationResult;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

public class DefaultCommunityCreationHandlerTest extends AbstractCommunityServiceFacadeTest {

    @Mock
    private RequestValidator<CommunityCreationRequest> requestValidator;

    @Mock
    private CommunityService communityService;

    @InjectMocks
    private DefaultCommunityCreationHandler communityCreationHandler;

    @Override
    @AfterEach
    protected void verifyNoMoreMockInteractions() {
        verifyNoMoreInteractions(requestValidator, communityService);
    }

    @Test
    public void testCreateWhenValidationFailed() {
        final var request = communityCreationRequest();

        when(requestValidator.validate(request)).thenReturn(
                ValidationResult.failedWith(CommunityCreationRequestValidationFailure.TYPE_INVALID_VALUE)
        );
        final var response = communityCreationHandler.create(request);

        Assertions.assertThat(response).isNotNull();
        Assertions.assertThat(response.isFailed()).isTrue();

        verify(requestValidator).validate(request);
    }

    @Test
    public void testCreateFailed() {
        final var request = communityCreationRequest();

        when(requestValidator.validate(request)).thenReturn(ValidationResult.success());
        when(communityService.create(any(CommunityCreationParameter.class)))
                .thenReturn(new CommunityCreationResult(List.of(CommunityCreationFailure.COMMUNITY_ALREADY_EXISTS)));

        final var response = communityCreationHandler.create(request);

        Assertions.assertThat(response).isNotNull();
        Assertions.assertThat(response.isFailed()).isTrue();

        verify(requestValidator).validate(request);
        verify(communityService).create(any(CommunityCreationParameter.class));
    }

    @Test
    public void testCreate() {
        final Long communityId = randomId();

        final var request = communityCreationRequest();

        when(requestValidator.validate(request)).thenReturn(ValidationResult.success());
        when(communityService.create(any(CommunityCreationParameter.class))).thenReturn(new CommunityCreationResult(communityId));

        final var response = communityCreationHandler.create(request);

        Assertions.assertThat(response).isNotNull();
        Assertions.assertThat(response.isSuccessful()).isTrue();
        Assertions.assertThat(response.getCommunityId()).isEqualTo(communityId);

        verify(requestValidator).validate(request);
        verify(communityService).create(any(CommunityCreationParameter.class));
    }
}