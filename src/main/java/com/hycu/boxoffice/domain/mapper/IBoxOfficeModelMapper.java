package com.hycu.boxoffice.domain.mapper;

import com.hycu.boxoffice.domain.payload.response.BoxOfficeRes;
import com.hycu.boxoffice.domain.payload.response.PeriodBoxOfficeRes;
import com.hycu.boxoffice.domain.model.BoxOfficeModel;
import com.hycu.boxoffice.domain.model.PeriodBoxOfficeModel;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants.ComponentModel;
import org.mapstruct.NullValueMappingStrategy;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = ComponentModel.SPRING,
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        nullValueMappingStrategy = NullValueMappingStrategy.RETURN_DEFAULT,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface IBoxOfficeModelMapper {
    BoxOfficeRes toRes(BoxOfficeModel model);

    PeriodBoxOfficeRes toPeriodBoxOfficeRes(PeriodBoxOfficeModel model);
}
