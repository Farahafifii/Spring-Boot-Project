package com.task.sumerge.course;

import com.task.sumerge.dto.CourseDTO;
import com.task.recommender.entity.CourseEntity;
import com.task.sumerge.mapper.CourseMapper;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CourseMapperTest {

    @Test
    public void testToDTO() {
        // Create a CourseEntity instance
        CourseEntity courseEntity = new CourseEntity();
        courseEntity.setId(1);
        courseEntity.setName("Advanced Java");
        courseEntity.setDescription("A comprehensive Java course");
        courseEntity.setCredit(10);

        // Use the mapper to convert to DTO
        CourseDTO courseDTO = CourseMapper.INSTANCE.toDTO(courseEntity);

        // Assertions
        assertNotNull(courseDTO);
        assertEquals(courseEntity.getId(), courseDTO.getId());
        assertEquals(courseEntity.getName(), courseDTO.getName());
        assertEquals(courseEntity.getDescription(), courseDTO.getDescription());
        assertEquals(courseEntity.getCredit(), courseDTO.getCredit());
    }

    @Test
    public void testToEntity() {
        // Create a CourseDTO instance
        CourseDTO courseDTO = new CourseDTO();
        courseDTO.setId(1);
        courseDTO.setName("Spring Boot Essentials");
        courseDTO.setDescription("Learn the essentials of Spring Boot");
        courseDTO.setCredit(5);

        // Use the mapper to convert to Entity
        CourseEntity courseEntity = CourseMapper.INSTANCE.toEntity(courseDTO);

        // Assertions
        assertNotNull(courseEntity);
        assertEquals(courseDTO.getId(), courseEntity.getId());
        assertEquals(courseDTO.getName(), courseEntity.getName());
        assertEquals(courseDTO.getDescription(), courseEntity.getDescription());
        assertEquals(courseDTO.getCredit(), courseEntity.getCredit());
    }

    @Test
    public void testNullEntityToDTO() {
        // Use the mapper to convert a null entity to DTO
        CourseDTO courseDTO = CourseMapper.INSTANCE.toDTO(null);

        // Assertions
        assertNull(courseDTO);
    }

    @Test
    public void testNullDTOToEntity() {
        // Use the mapper to convert a null DTO to Entity
        CourseEntity courseEntity = CourseMapper.INSTANCE.toEntity(null);

        // Assertions
        assertNull(courseEntity);
    }
}
