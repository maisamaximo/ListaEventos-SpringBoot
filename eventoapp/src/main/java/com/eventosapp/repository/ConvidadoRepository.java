package com.eventosapp.repository;

import com.eventosapp.models.Convidado;
import com.eventosapp.models.Eventos;
import org.springframework.data.repository.CrudRepository;

public interface ConvidadoRepository extends CrudRepository<Convidado, String> {
    Iterable<Convidado> findByEvento(Eventos eventos);

    Convidado findByRg(String rg);
}
