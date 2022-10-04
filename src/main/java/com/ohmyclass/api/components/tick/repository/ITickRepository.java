package com.ohmyclass.api.components.tick.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.ohmyclass.api.components.tick.entity.Tick;

public interface ITickRepository extends CrudRepository<Tick, Long>  {
    
    Optional<Tick> findTickById(Long id);

}
