package com.task.sumerge.mapper;

import com.task.sumerge.dto.CourseDTO;
import com.task.recommender.entity.CourseEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CourseMapper {
    CourseMapper INSTANCE = Mappers.getMapper(CourseMapper.class);

    CourseDTO toDTO(CourseEntity course);
    CourseEntity toEntity(CourseDTO courseDTO);
}