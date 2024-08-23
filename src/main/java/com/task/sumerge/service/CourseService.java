package com.task.sumerge.service;

import com.task.recommender.CourseRecommender;
import com.task.sumerge.dto.CourseDTO;
import com.task.recommender.entity.CourseEntity;
import com.task.sumerge.exception.BadRequestException;
import com.task.sumerge.exception.ResourceNotFoundException;
import com.task.sumerge.mapper.CourseMapper;
import com.task.sumerge.repository.CourseRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CourseService {

    @Autowired
    private CourseRepo courseRepository;

    @Autowired
    private CourseRecommender courseRecommenderImpl;


    public List<CourseDTO> findAllCourses(int page, int size) {
        Page<CourseEntity> coursePage = courseRepository.findAll(PageRequest.of(page, size));
        return coursePage.map(CourseMapper.INSTANCE::toDTO).getContent();
    }

    public CourseDTO addCourse(CourseDTO courseDTO) {
        validateCourseDTO(courseDTO); // Validate input data
        CourseEntity courseEntity = CourseMapper.INSTANCE.toEntity(courseDTO);
        CourseEntity savedCourse = courseRepository.save(courseEntity);
        return CourseMapper.INSTANCE.toDTO(savedCourse);
    }

    private void validateCourseDTO(CourseDTO courseDTO) {
        if (courseDTO.getName() == null || courseDTO.getName().isEmpty()) {
            throw new BadRequestException("Course name cannot be null or empty");
        }
        if (courseDTO.getDescription() == null || courseDTO.getDescription().isEmpty()) {
            throw new BadRequestException("Course description cannot be null or empty");
        }
        if (courseDTO.getCredit() <= 0) {
            throw new BadRequestException("Course credit hours must be greater than zero");
        }
    }

    public CourseDTO viewCourse(int id) {
        CourseEntity course = courseRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Course not found for this id :: " + id));
        return CourseMapper.INSTANCE.toDTO(course);
    }

    public CourseDTO updateCourse(CourseDTO courseDTO) {
        validateCourseDTO(courseDTO);
        CourseEntity course = courseRepository.findById(courseDTO.getId())
                .orElseThrow(() -> new ResourceNotFoundException("Course does not exist"));
        CourseEntity updatedCourse = CourseMapper.INSTANCE.toEntity(courseDTO);
        return CourseMapper.INSTANCE.toDTO(courseRepository.save(updatedCourse));
    }

    public void deleteCourse(int id) {
        courseRepository.deleteById(id);
    }

    public List<CourseDTO> discoverCourses(String keyword) {
        List<CourseEntity> courses = courseRepository.findByNameContaining(keyword);
        return courses.stream().map(CourseMapper.INSTANCE::toDTO).collect(Collectors.toList());
    }

    public List<CourseDTO> getAllCourses() {
        List<CourseEntity> courses = courseRecommenderImpl.recommendedCourses();
        return courses.stream().map(CourseMapper.INSTANCE::toDTO).collect(Collectors.toList());
    }
}
