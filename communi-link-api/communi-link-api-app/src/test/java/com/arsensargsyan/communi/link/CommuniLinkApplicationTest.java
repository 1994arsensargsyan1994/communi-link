package com.arsensargsyan.communi.link;

import com.arsensargsyan.communi.link.api.facade.CommunityServiceFacade;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@Disabled
@SpringBootTest
@RunWith(SpringRunner.class)
public class CommuniLinkApplicationTest {

    @Autowired
    private CommunityServiceFacade communityServiceFacade;

    @Test
    public void contextLoads() {
        Assertions.assertNotNull(communityServiceFacade);
    }
}
