package com.eventosapp.repository;

import com.eventosapp.models.Eventos;
import org.springframework.data.repository.CrudRepository;

public interface EventoRepository extends CrudRepository<Eventos, String> {
    Eventos findByCodigo(long codigo);
}
