package com.example.itprom.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

import com.example.itprom.dto.profession.ProfessionCreateDTO;
import com.example.itprom.dto.profession.ProfessionDTO;
import com.example.itprom.dto.profession.ProfessionUpdateDTO;
import com.example.itprom.model.Profession;

@Mapper(
    componentModel = "spring",
    nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
    uses = {JsonNullableMapper.class, ReferenceMapper.class},
    unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public abstract class ProfessionMapper {

    public abstract Profession map(ProfessionCreateDTO data);

    public abstract ProfessionDTO map(Profession model);

    public abstract Profession map(ProfessionDTO data);

    public abstract void update(ProfessionUpdateDTO update, @MappingTarget Profession target);

}
