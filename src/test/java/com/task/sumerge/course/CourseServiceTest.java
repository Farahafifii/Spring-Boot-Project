package com.task.sumerge.course;

import com.task.recommender.CourseRecommender;
import com.task.sumerge.dto.CourseDTO;
import com.task.recommender.entity.CourseEntity;
import com.task.sumerge.exception.BadRequestException;
import com.task.sumerge.exception.ResourceNotFoundException;
import com.task.sumerge.mapper.CourseMapper;
import com.task.sumerge.repository.CourseRepo;
import com.task.sumerge.service.CourseService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CourseServiceTest {

    @Mock
    private CourseRepo courseRepository;

    @InjectMocks
    private CourseService courseService;

    @Mock
    private CourseRecommender courseRecommenderImpl;

    private CourseEntity courseEntity;
    private CourseDTO courseDTO;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        courseEntity = new CourseEntity();
        courseEntity.setId(1);
        courseEntity.setName("Java Basics");
        courseEntity.setDescription("Introduction to Java");
        courseEntity.setCredit(3);

        courseDTO = CourseMapper.INSTANCE.toDTO(courseEntity);
    }

    @Test
    void testFindAllCourses() {
        PageRequest pageRequest = PageRequest.of(0, 10);
        Page<CourseEntity> coursePage = new PageImpl<>(Arrays.asList(courseEntity), pageRequest, 1);
        when(courseRepository.findAll(pageRequest)).thenReturn(coursePage);

        List<CourseDTO> result = courseService.findAllCourses(0, 10);
        assertEquals(1, result.size());
        assertEquals("Java Basics", result.get(0).getName());

        verify(courseRepository, times(1)).findAll(pageRequest);
    }

    @Test
    void testAddCourse() {
        when(courseRepository.save(any(CourseEntity.class))).thenReturn(courseEntity);

        courseService.addCourse(courseDTO);

        verify(courseRepository, times(1)).save(any(CourseEntity.class));
    }

    @Test
    void testViewCourse_Success() {
        when(courseRepository.findById(1)).thenReturn(Optional.of(courseEntity));

        CourseDTO result = courseService.viewCourse(1);
        assertNotNull(result);
        assertEquals("Java Basics", result.getName());

        verify(courseRepository, times(1)).findById(1);
    }

    @Test
    void testViewCourse_NotFound() {
        when(courseRepository.findById(999)).thenReturn(Optional.empty());

        ResourceNotFoundException thrown = assertThrows(
                ResourceNotFoundException.class,
                () -> courseService.viewCourse(999)
        );
        assertEquals("Course not found for this id :: 999", thrown.getMessage());

        verify(courseRepository, times(1)).findById(999);
    }

    @Test
    void testUpdateCourse() {
        CourseDTO courseDTO = new CourseDTO();
        courseDTO.setId(1);
        courseDTO.setName("Updated Course Name");
        courseDTO.setDescription("Updated Description");
        courseDTO.setCredit(4);

        CourseEntity existingCourseEntity = new CourseEntity();
        existingCourseEntity.setId(1);
        existingCourseEntity.setName("Old Course Name");
        existingCourseEntity.setDescription("Old Description");
        existingCourseEntity.setCredit(3);

        CourseEntity updatedCourseEntity = new CourseEntity();
        updatedCourseEntity.setId(1);
        updatedCourseEntity.setName("Updated Course Name");
        updatedCourseEntity.setDescription("Updated Description");
        updatedCourseEntity.setCredit(4);

        when(courseRepository.findById(1)).thenReturn(Optional.of(existingCourseEntity));
        when(courseRepository.save(any(CourseEntity.class))).thenReturn(updatedCourseEntity);

        CourseDTO result = courseService.updateCourse(courseDTO);

        // Verify interactions and results
        verify(courseRepository, times(1)).findById(1);
        verify(courseRepository, times(1)).save(any(CourseEntity.class));

        // Assert the result if necessary
        assertEquals("Updated Course Name", result.getName());
        assertEquals("Updated Description", result.getDescription());
        assertEquals(4, result.getCredit());
    }

    @Test
    void testDeleteCourse() {
        doNothing().when(courseRepository).deleteById(1);

        courseService.deleteCourse(1);

        verify(courseRepository, times(1)).deleteById(1);
    }

    @Test
    void testDiscoverCourses() {
        when(courseRepository.findByNameContaining("Java")).thenReturn(Arrays.asList(courseEntity));

        List<CourseDTO> result = courseService.discoverCourses("Java");
        assertEquals(1, result.size());
        assertEquals("Java Basics", result.get(0).getName());

        verify(courseRepository, times(1)).findByNameContaining("Java");
    }

    @Test
    void testDiscoverCourses_NoMatch() {
        when(courseRepository.findByNameContaining("Python")).thenReturn(Arrays.asList());

        List<CourseDTO> result = courseService.discoverCourses("Python");
        assertTrue(result.isEmpty());

        verify(courseRepository, times(1)).findByNameContaining("Python");
    }

//    @Test
//    void testGetAllCourses() {
//        when(courseRepository.findAll()).thenReturn(Arrays.asList(courseEntity));
//
//        List<CourseDTO> result = courseService.getAllCourses();
//        assertEquals(1, result.size());
//        assertEquals("Java Basics", result.get(0).getName());
//
//        verify(courseRepository, times(1)).findAll();
//    }

    @Test
    void testGetAllCourses() {
        CourseEntity courseEntity = new CourseEntity();
        courseEntity.setName("Java Basics");

        when(courseRecommenderImpl.recommendedCourses()).thenReturn(Arrays.asList(courseEntity));

        // Mock the mapping logic if needed
        // when(CourseMapper.INSTANCE.toDTO(any(CourseEntity.class))).thenReturn(new CourseDTO());

        List<CourseDTO> result = courseService.getAllCourses();
        assertEquals(1, result.size());
        assertEquals("Java Basics", result.get(0).getName());

        verify(courseRecommenderImpl, times(1)).recommendedCourses();
    }

    @Test
    public void testAddCourse_InvalidData_Name() {
        // Given
        CourseDTO courseDTO = new CourseDTO();
        courseDTO.setName(""); // Invalid name
        courseDTO.setDescription("Some description");
        courseDTO.setCredit(5);

        Assertions.assertThrows(BadRequestException.class, () -> {
            courseService.addCourse(courseDTO);
        });

        verify(courseRepository, times(0)).save(any()); // Ensure save is never called
    }

    @Test
    public void testAddCourse_InvalidData_Description() {
        // Given
        CourseDTO courseDTO = new CourseDTO();
        courseDTO.setName("John Doe");
        courseDTO.setDescription("");
        courseDTO.setCredit(5);

        Assertions.assertThrows(BadRequestException.class, () -> {
            courseService.addCourse(courseDTO);
        });

        verify(courseRepository, times(0)).save(any());
    }

    @Test
    public void testAddCourse_InvalidData_Credit() {
        // Given
        CourseDTO courseDTO = new CourseDTO();
        courseDTO.setName("John Doe");
        courseDTO.setDescription("Hello World");
        courseDTO.setCredit(-1);

        Assertions.assertThrows(BadRequestException.class, () -> {
            courseService.addCourse(courseDTO);
        });

        verify(courseRepository, times(0)).save(any());
    }

    @Test
    public void testUpdateCourse_CourseNotFound() {
        CourseDTO courseDTO = new CourseDTO();
        courseDTO.setId(1);
        courseDTO.setName("Updated Course Name");
        courseDTO.setDescription("Updated Description");
        courseDTO.setCredit(4);

        when(courseRepository.findById(courseDTO.getId())).thenReturn(Optional.empty());

        Assertions.assertThrows(ResourceNotFoundException.class, () -> {
            courseService.updateCourse(courseDTO);
        });
        verify(courseRepository, times(1)).findById(courseDTO.getId());
    }
}
