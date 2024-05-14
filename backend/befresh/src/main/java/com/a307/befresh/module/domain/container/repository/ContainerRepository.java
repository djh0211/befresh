package com.a307.befresh.module.domain.container.repository;

import com.a307.befresh.module.domain.container.Container;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContainerRepository extends JpaRepository<Container, Long>, ContainerRepositoryCustom {
    Container findByQrId(String qrId);
    List<Container> findByRefrigerator_Id(Long refrigeratorId);
}
