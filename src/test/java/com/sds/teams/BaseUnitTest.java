package com.sds.teams;

import org.junit.jupiter.api.extension.RegisterExtension;
import org.mockito.junit.jupiter.MockitoExtension;

public abstract class BaseUnitTest {

    @RegisterExtension
    MockitoExtension mockitoExtension = new MockitoExtension();
}
