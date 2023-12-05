package br.com.fiap.vertigo.repository;

import br.com.fiap.vertigo.model.Time;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface TimeRepository extends JpaRepository<Time, Long> {

}

