package com.task.sumerge.author;

import com.task.sumerge.dto.AuthorDTO;
import com.task.recommender.entity.AuthorEntity;
import com.task.sumerge.mapper.AuthorMapper;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class AuthorMapperTest {

    @Test
    public void testToDTO() {

        AuthorEntity authorEntity = new AuthorEntity();
        authorEntity.setId(1);
        authorEntity.setName("John Doe");
        authorEntity.setEmail("john.doe@example.com");

        AuthorDTO authorDTO = AuthorMapper.INSTANCE.toDTO(authorEntity);

        assertNotNull(authorDTO);
        assertEquals(authorEntity.getId(), authorDTO.getId());
        assertEquals(authorEntity.getName(), authorDTO.getName());
        assertEquals(authorEntity.getEmail(), authorDTO.getEmail());
    }

    @Test
    public void testToEntity() {

        AuthorDTO authorDTO = new AuthorDTO();
        authorDTO.setId(1);
        authorDTO.setName("Jane Doe");
        authorDTO.setEmail("jane.doe@example.com");

        AuthorEntity authorEntity = AuthorMapper.INSTANCE.toEntity(authorDTO);

        assertNotNull(authorEntity);
        assertEquals(authorDTO.getId(), authorEntity.getId());
        assertEquals(authorDTO.getName(), authorEntity.getName());
        assertEquals(authorDTO.getEmail(), authorEntity.getEmail());
    }

    @Test
    public void testNullEntityToDTO() {

        AuthorDTO authorDTO = AuthorMapper.INSTANCE.toDTO(null);

        assertNull(authorDTO);
    }

    @Test
    public void testNullDTOToEntity() {

        AuthorEntity authorEntity = AuthorMapper.INSTANCE.toEntity(null);


        assertNull(authorEntity);
    }
}
