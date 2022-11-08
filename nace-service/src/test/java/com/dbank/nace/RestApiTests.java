package com.dbank.nace;

import com.dbank.nace.entity.NaceData;
import com.dbank.nace.exceptions.NaceNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class RestApiTests {

    @Autowired
    private MockMvc mockMvc;

    private NaceData testNaceData;

    @BeforeEach
    public void setup(){
        testNaceData = NaceData.builder()
                .orderId(100003404L)
                .level(1)
                .description("Fisheries")
                .item_includes("All types of fishes")
                .item_excludes("Molluscs and crustaceans")
                .build();
    }

    @Test
    public void testIfDataPresent() throws Exception {
        this.mockMvc.perform(get("/nace/")).andExpect(status().isOk());
    }

    @Test
    public void testGetMethod() throws Exception {
        this.mockMvc.perform(get("/nace/398481")).andDo(print()).andExpect(status().isOk());
        // .andExpect(content().string(containsString("Hello, World")));
    }

    @Test
    public void testPostMethod() throws Exception {
        String jsonPayload = "{\"orderId\": \"44444444\",\"level\": \"3\",\"code\": \"89.1\",\"parent\": \"80\"," +
                "\"description\": \"Manufacture of electronic components and boards\"," +
                "\"item_includes\": \"Included transistors and resistors\"," +
                "\"itemAlsoIncludes\": \"\",\"rulings\": \"\"," +
                "\"item_excludes\": \"Wires and batteries\"," +
                "\"referencesToIsic\": \"261\" }";
        this.mockMvc.perform(post("/nace/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonPayload))
                .andExpect(status().isOk());
    }

    @Test
    public void testErrorOnNotPresent() throws Exception {
        this.mockMvc.perform(get("/nace/0000000")).andDo(print()).andExpect(status().is4xxClientError());
    }
}
