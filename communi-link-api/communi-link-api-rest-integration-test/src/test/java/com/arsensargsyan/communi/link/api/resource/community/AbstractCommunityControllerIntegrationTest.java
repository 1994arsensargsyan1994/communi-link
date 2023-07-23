package com.arsensargsyan.communi.link.api.resource.community;


import com.arsensargsyan.communi.link.api.resource.community.config.CommunityControllerIntegrationTestConfig;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

@SpringBootTest(
        classes = CommunityControllerIntegrationTestConfig.class,
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT
)
@AutoConfigureMockMvc
@Testcontainers
public abstract class AbstractCommunityControllerIntegrationTest {

    @Container
    private static final PostgreSQLContainer<?> POSTGRE_CONTAINER = new PostgreSQLContainer<>("postgres:13.1");

    @Autowired
    protected MockMvc mockMvc;

    @Autowired
    protected ObjectMapper objectMapper;

    @DynamicPropertySource
    static void registerDynamicProperties(final DynamicPropertyRegistry propertyRegistry) {
        propertyRegistry.add("spring.datasource.url", POSTGRE_CONTAINER::getJdbcUrl);
        propertyRegistry.add("spring.datasource.username", POSTGRE_CONTAINER::getUsername);
        propertyRegistry.add("spring.datasource.password", POSTGRE_CONTAINER::getPassword);

        propertyRegistry.add("spring.jpa.show-sql", () -> Boolean.TRUE);
        propertyRegistry.add("spring.batch.job.enabled", () -> Boolean.FALSE);

        propertyRegistry.add("spring.flyway.enabled", () -> Boolean.TRUE);
        propertyRegistry.add("spring.flyway.locations", () -> "classpath:");
    }
}