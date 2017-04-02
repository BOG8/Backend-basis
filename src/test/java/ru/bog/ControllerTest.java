package ru.bog;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.MockMvcPrint;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by zac on 02.04.17.
 */

@SpringBootTest
@RunWith(SpringRunner.class)
@AutoConfigureMockMvc(print = MockMvcPrint.NONE)
@Transactional
public class ControllerTest {
    public MockHttpSession mockHttpSession;

    @Autowired
    private MockMvc mockMvc;

    @Before
    public void setup() throws Exception {
        mockHttpSession = new MockHttpSession();
        mockMvc.perform(post("/api/user")
                .content("{\"login\":\"testLogin\",\"password\":\"testPass\",\"email\": \"test@mail.ru\"}")
                .contentType(MediaType.APPLICATION_JSON)
                .session(mockHttpSession))
                .andExpect(status().isOk());
    }

    @Test
    public void testDuplicateRegister() throws Exception {
        mockMvc.perform(post("/api/user")
                .content("{\"login\":\"testLogin\",\"password\":\"passTest\",\"email\": \"noTest@mail.ru\"}")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isForbidden());
    }
}