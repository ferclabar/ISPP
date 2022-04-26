package org.springframework.samples.commandfast.plate;
import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.samples.commandfast.configuration.SecurityConfiguration;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
class PlateControllerTests {
	
	@Autowired
	protected PlateController plateController;
	
    @Autowired  
    private MockMvc mockMvc;
    
    @Test
    void contextLoads() throws Exception {
        assertThat(plateController).isNotNull();
    }
    
    @WithMockUser
    @Test
    void testDeberiaMostrarTodosLosPlatos() throws Exception{
        mockMvc.perform(get("/carta/{id_comanda}",1)).andExpect(status().isOk())
        .andExpect(model().attributeExists("platos"))
        .andExpect(model().attributeExists("lines"))
        .andExpect(model().attributeExists("id_commanda"))
        .andExpect(view().name("carta/plateList"));
    }
    

    
    @WithMockUser
    @Test
    void testDeberiaDevolverFormularioEditarLinea() throws Exception{
        mockMvc.perform(get("/carta/{id_comanda}/edit",1)).andExpect(status().isOk())
        .andExpect(model().attributeExists("lines"))
        .andExpect(model().attributeExists("platos"))
        .andExpect(model().attributeExists("id_commanda"))
        .andExpect(view().name("carta/plateList"));
    }
    
    @WithMockUser
    @Test
    void testDeberiaEditarLinea() throws Exception{
        mockMvc.perform(get("/carta/{id_comanda}",1)).andExpect(status().isOk())
        .andExpect(view().name("carta/plateList"));

    }
    /*
    @WithMockUser(username = "gomezmartin", authorities = {"restaurant"})
    @Test
    void testDeberiaGuardarLinea() throws Exception{
        mockMvc.perform(post("/carta/{id_comanda}",1).with(csrf())).andExpect(status().isOk())
        .andExpect(view().name("redirect:/carta/1/edit"));

    }
    */
}