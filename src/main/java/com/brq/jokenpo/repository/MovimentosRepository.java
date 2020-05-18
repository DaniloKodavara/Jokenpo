package com.brq.jokenpo.repository;

import com.brq.jokenpo.domain.Movimento;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovimentosRepository extends JpaRepository<Movimento, Long> {
}
