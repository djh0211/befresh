package com.a307.befresh.module.domain.container.repository;

import com.a307.befresh.module.domain.container.Container;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContainerRepository extends JpaRepository<Container, Integer> {
    Container findByQrId(long qrId);
}
