package com.a307.befresh.module.domain.container.repository;

import java.util.List;

public interface ContainerRepositoryCustom {
    List<Long> findFreshContainer();
    List<Long> findWarnContainer();

    List<Long> findDangerContainer();

    List<Long> findNoUpdateContainer();

    List<Long> findReUpdateContainer();
}
