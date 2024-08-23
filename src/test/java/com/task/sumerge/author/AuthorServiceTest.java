package com.task.sumerge.author;

import com.task.sumerge.dto.AuthorDTO;
import com.task.recommender.entity.AuthorEntity;
import com.task.sumerge.exception.ResourceNotFoundException;
import com.task.sumerge.mapper.AuthorMapper;
import com.task.sumerge.repository.AuthorRepo;
import com.task.sumerge.service.AuthorService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class AuthorServiceTest {

    @Mock
    private AuthorRepo authorRepository;

    @InjectMocks
    private AuthorService authorService;

    private AuthorEntity authorEntity;
    private AuthorDTO authorDTO;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        authorEntity = new AuthorEntity();
        authorEntity.setId(1);
        authorEntity.setName("John Doe");
        authorEntity.setEmail("john.doe@example.com");
        authorEntity.setBirthdate(LocalDate.of(1990, 1, 1));

        authorDTO = AuthorMapper.INSTANCE.toDTO(authorEntity);
    }

    @Test
    void testGetAuthorByEmail_Success() {
        when(authorRepository.findByEmail("john.doe@example.com")).thenReturn(Optional.of(authorEntity));

        AuthorDTO result = authorService.getAuthorByEmail("john.doe@example.com");
        assertNotNull(result);
        assertEquals("John Doe", result.getName());
        assertEquals("john.doe@example.com", result.getEmail());

        verify(authorRepository, times(1)).findByEmail("john.doe@example.com");
    }

    @Test
    void testGetAuthorByEmail_NotFound() {
        when(authorRepository.findByEmail("nonexistent@example.com")).thenReturn(Optional.empty());

        ResourceNotFoundException thrown = assertThrows(
                ResourceNotFoundException.class,
                () -> authorService.getAuthorByEmail("nonexistent@example.com")
        );
        assertEquals("Author not found for this email :: nonexistent@example.com", thrown.getMessage());

        verify(authorRepository, times(1)).findByEmail("nonexistent@example.com");
    }

    @Test
    void testGetAuthorById_Success() {
        when(authorRepository.findById(1)).thenReturn(Optional.of(authorEntity));

        AuthorDTO result = authorService.getAuthorById(1);
        assertNotNull(result);
        assertEquals("John Doe", result.getName());
        assertEquals("john.doe@example.com", result.getEmail());

        verify(authorRepository, times(1)).findById(1);
    }

    @Test
    void testGetAuthorById_NotFound() {
        when(authorRepository.findById(999)).thenReturn(Optional.empty());

        ResourceNotFoundException thrown = assertThrows(
                ResourceNotFoundException.class,
                () -> authorService.getAuthorById(999)
        );
        assertEquals("Author not found for this id :: 999", thrown.getMessage());

        verify(authorRepository, times(1)).findById(999);
    }
}
