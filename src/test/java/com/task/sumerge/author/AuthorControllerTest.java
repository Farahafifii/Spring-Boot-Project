package com.task.sumerge.author;

import com.task.sumerge.controller.AuthorController;
import com.task.sumerge.dto.AuthorDTO;
import com.task.sumerge.exception.ResourceNotFoundException;
import com.task.sumerge.service.AuthorService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@WebMvcTest(AuthorController.class)
public class AuthorControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AuthorService authorService;

    @Test
    public void testGetAuthorByEmail() throws Exception {
        AuthorDTO authorDTO = new AuthorDTO("John Doe", "author@example.com", LocalDate.of(1980, 5, 15));
        given(authorService.getAuthorByEmail("author@example.com")).willReturn(authorDTO);

        mockMvc.perform(get("/authors/findByEmail/author@example.com"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.email").value("author@example.com"))
                .andExpect(jsonPath("$.name").value("John Doe"))
                .andExpect(jsonPath("$.birthdate").value("1980-05-15"));

        verify(authorService).getAuthorByEmail("author@example.com");
    }

    @Test
    public void testGetAuthorById() throws Exception {
        AuthorDTO authorDTO = new AuthorDTO("Jane Doe", "jane@example.com", LocalDate.of(1990, 10, 25));
        authorDTO.setId(1);
        given(authorService.getAuthorById(1)).willReturn(authorDTO);

        mockMvc.perform(get("/authors/findById/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.email").value("jane@example.com"))
                .andExpect(jsonPath("$.name").value("Jane Doe"))
                .andExpect(jsonPath("$.birthdate").value("1990-10-25"));

        verify(authorService).getAuthorById(1);
    }

    @Test
    public void testGetAuthorByEmailNotFound() throws Exception {
        given(authorService.getAuthorByEmail("nonexistent@example.com"))
                .willThrow(new ResourceNotFoundException("Author not found for this email :: nonexistent@example.com"));

        mockMvc.perform(get("/authors/findByEmail/nonexistent@example.com"))
                .andExpect(status().isNotFound());  // Expecting a 404 Not Found status
    }

    @Test
    public void testGetAuthorByIdNotFound() throws Exception {
        given(authorService.getAuthorById(9999))
                .willThrow(new ResourceNotFoundException("Author not found for this id :: 9999"));

        mockMvc.perform(get("/authors/findById/9999"))
                .andExpect(status().isNotFound());  // Expecting a 404 Not Found status
    }

    @Test
    public void testGetAuthorByEmailServiceException() throws Exception {
        given(authorService.getAuthorByEmail("error@example.com"))
                .willThrow(new RuntimeException("Unexpected error"));

        mockMvc.perform(get("/authors/findByEmail/error@example.com"))
                .andExpect(status().isInternalServerError());  // Expecting a 500 Internal Server Error status
    }
    @Test
    public void testGetAuthorByIdServiceException() throws Exception {
        given(authorService.getAuthorById(1234))
                .willThrow(new RuntimeException("Unexpected error"));

        mockMvc.perform(get("/authors/findById/1234"))
                .andExpect(status().isInternalServerError());  // Expecting a 500 Internal Server Error status
    }

}
