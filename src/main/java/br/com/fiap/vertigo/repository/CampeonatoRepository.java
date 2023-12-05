package br.com.fiap.vertigo.repository;

import br.com.fiap.vertigo.model.Campeonato;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CampeonatoRepository extends JpaRepository<Campeonato, Long> {

}


