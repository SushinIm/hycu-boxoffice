package com.hycu.boxoffice.domain.mapper;

import com.hycu.boxoffice.domain.entity.BoxOfficeApiEntity;
import com.hycu.boxoffice.domain.entity.BoxOfficeEntity;
import com.hycu.boxoffice.domain.entity.PeriodBoxOfficeEntity;
import com.hycu.boxoffice.domain.model.BoxOfficeModel;
import com.hycu.boxoffice.domain.model.PeriodBoxOfficeModel;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants.ComponentModel;
import org.mapstruct.Named;
import org.mapstruct.NullValueMappingStrategy;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = ComponentModel.SPRING,
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        nullValueMappingStrategy = NullValueMappingStrategy.RETURN_DEFAULT,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface IBoxOfficeMapper {

    @Mapping(target = "newRanked", source = "apiEntity.rankOldAndNew", qualifiedByName = "isNewRanked")
    @Mapping(target = "savedAt", expression = "java(java.time.LocalDate.now().minusDays(1))")
    BoxOfficeModel apiEntityToModel(BoxOfficeApiEntity apiEntity);

    BoxOfficeModel toModel(BoxOfficeEntity entity);

    BoxOfficeEntity toEntity(BoxOfficeModel model);

    PeriodBoxOfficeModel toPeriodBoxOfficeModel(PeriodBoxOfficeEntity entity);

    @Named(value = "isNewRanked")
    static Boolean isNewRanked(String rankOldAndNew) {
        return "New".equals(rankOldAndNew);
    }
}
