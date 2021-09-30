package com.sds.teams;

import com.sds.teams.msg.MsgApplication;
import org.junit.jupiter.api.extension.RegisterExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest(
        classes = MsgApplication.class,
        webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@AutoConfigureMockMvc
@ActiveProfiles("integrationtest")
@TestPropertySource(locations = "classpath:application-integrationtest.properties")
public abstract class BaseIntegrationTest {

    @RegisterExtension
    SpringExtension springExtension = new SpringExtension();

    @Autowired
    public MockMvc mockMvc;


}
