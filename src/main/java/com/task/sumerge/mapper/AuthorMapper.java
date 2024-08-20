package com.task.sumerge.mapper;

import com.task.sumerge.dto.AuthorDTO;
import com.task.recommender.entity.AuthorEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface AuthorMapper {
    AuthorMapper INSTANCE = Mappers.getMapper(AuthorMapper.class);

    AuthorDTO toDTO(AuthorEntity author);
    AuthorEntity toEntity(AuthorDTO authorDTO);
}