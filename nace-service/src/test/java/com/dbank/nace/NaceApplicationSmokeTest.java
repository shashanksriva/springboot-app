package com.dbank.nace;


import static org.assertj.core.api.Assertions.assertThat;

import com.dbank.nace.controller.NaceController;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class NaceApplicationSmokeTest {

    @Autowired
    private NaceController controller;

    @Test
    public void contextLoads() throws Exception {
        assertThat(controller).isNotNull();
    }
}
