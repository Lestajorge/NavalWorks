package F110.service;

import F110.enums.model.EstadoTrabajo;
import F110.model.Trabajo;

import java.util.List;

public interface ITrabajoService {

    List<Trabajo> findAll();

    Trabajo findById(Long id);

    List<Trabajo> findByIsometricoId(Long isometricoId);

    List<Trabajo> findByEstado(EstadoTrabajo estado);

    List<Trabajo> findByOperarioId(Long operarioId);

    Trabajo save(Trabajo trabajo);

    Trabajo update(Long id, Trabajo trabajo);

    void delete(Long id);
}
