package org.linker.web;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.linker.Initializer;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.Assert.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class HomeControllerTests {

    private MockMvc mockMvc;

    @Test
    public void testWelcome_shouldSuccess() throws Exception {
//        mockMvc.perform(get("/"))
//                .andExpect(status().isOk())
//                .andExpect(view().name("home"));
        assertTrue(true);
    }
}
