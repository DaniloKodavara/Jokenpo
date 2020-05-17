package com.brq.jokenpo.repository;

import com.brq.jokenpo.domain.Partida;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PartidasRepository extends JpaRepository<Partida, Long> {
}
