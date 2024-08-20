package com.task.sumerge.service;

import com.task.sumerge.dto.CourseDTO;
import com.task.recommender.entity.CourseEntity;
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

    public List<CourseDTO> findAllCourses(int page, int size) {
        Page<CourseEntity> coursePage = courseRepository.findAll(PageRequest.of(page, size));
        return coursePage.map(CourseMapper.INSTANCE::toDTO).getContent();
    }

    public void addCourse(CourseDTO courseDTO) {
        CourseEntity course = CourseMapper.INSTANCE.toEntity(courseDTO);
        courseRepository.save(course);
    }

    public CourseDTO viewCourse(int id) {
        CourseEntity course = courseRepository.findById(id).orElse(null);
        return CourseMapper.INSTANCE.toDTO(course);
    }

    public void updateCourse(CourseDTO courseDTO) {
        CourseEntity course = CourseMapper.INSTANCE.toEntity(courseDTO);
        courseRepository.save(course);
    }

    public void deleteCourse(int id) {
        courseRepository.deleteById(id);
    }

    public List<CourseDTO> discoverCourses(String keyword) {
        List<CourseEntity> courses = courseRepository.findByNameContaining(keyword);
        return courses.stream().map(CourseMapper.INSTANCE::toDTO).collect(Collectors.toList());
    }

    public List<CourseDTO> getAllCourses() {
        List<CourseEntity> courses = courseRepository.findAll();
        return courses.stream().map(CourseMapper.INSTANCE::toDTO).collect(Collectors.toList());
    }
}
