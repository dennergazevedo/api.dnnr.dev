package dev.dnnr.api.repositories;

import dev.dnnr.api.domain.chronometer.Chronometer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface ChronometerRepository extends JpaRepository<Chronometer, UUID> {
    List<Chronometer> findByUserId(UUID userId);
}