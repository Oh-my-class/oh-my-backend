package com.ohmyclass.api.components.tick.repository;

import com.ohmyclass.api.components.tick.entity.Tick;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface ITickRepository extends CrudRepository<Tick, Long> {
    Optional<Tick> findTickById(Long id);

}
