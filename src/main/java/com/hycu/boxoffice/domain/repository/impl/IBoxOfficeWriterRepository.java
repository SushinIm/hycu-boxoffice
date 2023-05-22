package com.hycu.boxoffice.domain.repository.impl;

import com.hycu.boxoffice.domain.entity.BoxOfficeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IBoxOfficeWriterRepository extends JpaRepository<BoxOfficeEntity, Long> {

}
