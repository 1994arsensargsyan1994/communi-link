package com.arsensargsyan.communi.link.api.facade.validation;

import static com.arsensargsyan.communi.link.api.facade.CommunityServiceFacadeTestHelper.communityCreationRequest;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

import java.util.Set;

import com.arsensargsyan.communi.link.api.facade.AbstractCommunityServiceFacadeTest;
import com.arsensargsyan.communi.link.api.facade.settings.AppSettingsProvider;
import com.arsensargsyan.communi.link.api.model.settings.AppSettingsCommunityType;
import com.arsensargsyan.communi.link.api.model.settings.AppSettingsDto;
import com.arsensargsyan.communi.link.common.CommunityType;
import com.arsensargsyan.communi.link.common.FailureDto;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

public class DefaultCommunityCreationRequestValidatorTest extends AbstractCommunityServiceFacadeTest {

    @Mock
    private AppSettingsProvider settingsProvider;

    @InjectMocks
    private DefaultCommunityCreationRequestValidator requestValidator;

    @Override
    @AfterEach
    protected void verifyNoMoreMockInteractions() {
        verifyNoMoreInteractions(settingsProvider);
    }


    @Test
    public void testValidateForInvalidType() {
        final var request = communityCreationRequest();

        when(settingsProvider.provide())
                .thenReturn(new AppSettingsDto(
                                randomUUID(), Set.of(new AppSettingsCommunityType(CommunityType.SPA, Boolean.FALSE))
                        )
                );

        final ValidationResult validationResult = requestValidator.validate(request);

        Assertions.assertThat(validationResult.hasFailures()).isTrue();
        Assertions.assertThat(validationResult.failures()).containsExactly(
                new FailureDto(
                        CommunityCreationRequestValidationFailure.TYPE_INVALID_VALUE.code(),
                        CommunityCreationRequestValidationFailure.TYPE_INVALID_VALUE.reason()
                )
        );
        verify(settingsProvider).provide();
    }

    @Test
    public void testValidate() {
        final var request = communityCreationRequest();

        when(settingsProvider.provide())
                .thenReturn(new AppSettingsDto(
                                randomUUID(), Set.of(new AppSettingsCommunityType(CommunityType.GYM, Boolean.FALSE))
                        )
                );

        final ValidationResult validationResult = requestValidator.validate(request);

        Assertions.assertThat(validationResult.hasFailures()).isFalse();
        Assertions.assertThat(validationResult.failures()).isEmpty();

        verify(settingsProvider).provide();
    }
}