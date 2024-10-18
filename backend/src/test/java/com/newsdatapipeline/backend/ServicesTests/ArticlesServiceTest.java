package com.newsdatapipeline.backend.ServicesTests;

import com.newsdatapipeline.backend.Services.ArticlesService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.*;
import java.io.IOException;

@SpringBootTest
public class ArticlesServiceTest {

    @Autowired
    private ArticlesService articlesService;

    @Test
    void mentionTest() throws IOException {
        assertTrue(articlesService.isMentionsTheLocation("London is the capital of Great Britain","London"));
    }
}
