package com.brq.jokenpo.repository;

import com.brq.jokenpo.domain.Jogador;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JogadoresRepository extends JpaRepository<Jogador, Long> {
}
