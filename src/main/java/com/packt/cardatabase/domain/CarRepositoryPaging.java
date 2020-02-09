package com.packt.cardatabase.domain;

import org.springframework.data.repository.PagingAndSortingRepository;

public interface CarRepositoryPaging extends PagingAndSortingRepository<Car, Long> {

  // CarRepositoryPaging provides Itzravle<T> findllSort sort)
  // Page<T> findAll(Pageable p)

}
