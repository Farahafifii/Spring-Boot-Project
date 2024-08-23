package com.task.sumerge.service;

import com.task.sumerge.dto.AuthorDTO;
import com.task.recommender.entity.AuthorEntity;
import com.task.sumerge.exception.ResourceNotFoundException;
import com.task.sumerge.mapper.AuthorMapper;
import com.task.sumerge.repository.AuthorRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthorService {

    @Autowired
    private AuthorRepo authorRepository;

    public AuthorDTO getAuthorByEmail(String email) {
        AuthorEntity author = authorRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("Author not found for this email :: " + email));
        return AuthorMapper.INSTANCE.toDTO(author);
    }
    public AuthorDTO getAuthorById(int id) {
        AuthorEntity author = authorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Author not found for this id :: " + id));
        return AuthorMapper.INSTANCE.toDTO(author);
    }
}
