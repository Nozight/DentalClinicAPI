package clinic;
import clinic.model.Dentist;
import clinic.util.Jsons;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.io.UnsupportedEncodingException;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
public class DentistIntegrationTest {

        @Autowired
        private MockMvc mockMvc;


        @Test
        public void registrarOdontologo() throws Exception {
            Dentist d1 = new Dentist();
            d1.setName("Tomas");
            d1.setLast_name("Romero");
            d1.setEnrollment(123);
            MvcResult response = this.mockMvc.perform(MockMvcRequestBuilders.post("/api/dentist")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(Jsons.asJsonString(d1)))
                    .andDo(MockMvcResultHandlers.print())
                    .andExpect(MockMvcResultMatchers.status().isOk()).andReturn();

            Assert.assertFalse(response.getResponse().getContentAsString().isEmpty());
        }
    }

