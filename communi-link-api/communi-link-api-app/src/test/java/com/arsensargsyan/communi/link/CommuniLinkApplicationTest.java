package com.arsensargsyan.communi.link;

import com.arsensargsyan.communi.link.api.facade.CommunityServiceFacade;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public class CommuniLinkApplicationTest {

    @Autowired
    private CommunityServiceFacade communityServiceFacade;

    @Test
    public void contextLoads() {
        Assert.assertNotNull(communityServiceFacade);
    }
}
