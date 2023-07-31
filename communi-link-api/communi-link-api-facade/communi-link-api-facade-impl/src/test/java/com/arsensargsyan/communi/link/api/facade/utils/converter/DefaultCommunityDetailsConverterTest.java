package com.arsensargsyan.communi.link.api.facade.utils.converter;

import static com.arsensargsyan.communi.link.api.facade.CommunityServiceFacadeTestHelper.communityDetails;

import com.arsensargsyan.communi.link.api.facade.AbstractCommunityServiceFacadeTest;
import com.arsensargsyan.communi.link.api.model.response.details.CommunityDetailsDto;
import com.arsensargsyan.communi.link.service.lookup.CommunityDetails;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;

public class DefaultCommunityDetailsConverterTest extends AbstractCommunityServiceFacadeTest {

    @InjectMocks
    private DefaultCommunityDetailsConverter detailsConverter;

    @Override
    @AfterEach
    protected void verifyNoMoreMockInteractions() {
    }

    @Test
    @SuppressWarnings("all")
    public void testConvertWithInvalidParameter() {
        Assertions.assertThatThrownBy(() -> detailsConverter.convert(null))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    public void testConvert() {
        final var source = communityDetails();
        assertEquals(detailsConverter.convert(source), source);
    }

    private static void assertEquals(final CommunityDetailsDto listDetails, final CommunityDetails source) {
        Assertions.assertThat(listDetails).isNotNull();
        Assertions.assertThat(listDetails.id()).isEqualTo(source.id());
        Assertions.assertThat(listDetails.name()).isEqualTo(source.name());
        Assertions.assertThat(listDetails.type()).isEqualTo(source.type());

        Assertions.assertThat(listDetails.maxCount()).isEqualTo(source.maxCount());
        Assertions.assertThat(listDetails.currentCount()).isEqualTo(source.currentCount());
    }
}