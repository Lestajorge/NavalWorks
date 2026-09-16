package F110.service;

import F110.enums.model.EstadoTrabajo;
import F110.model.Trabajo;
import F110.repository.TrabajoRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TrabajoService implements ITrabajoService {

    private final TrabajoRepository trabajoRepository;

    @Override
    public List<Trabajo> findAll() {
        return trabajoRepository.findAll();
    }

    @Override
    public Trabajo findById(Long id) {
        return trabajoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(
                        "No existe el trabajo con id: " + id));
    }

    @Override
    public List<Trabajo> findByIsometricoId(Long isometricoId) {
        return trabajoRepository.findByIsometricoId(isometricoId);
    }

    @Override
    public List<Trabajo> findByEstado(EstadoTrabajo estado) {
        return trabajoRepository.findByEstado(estado);
    }

    @Override
    public List<Trabajo> findByOperarioId(Long operarioId) {
        return trabajoRepository.findByOperariosId(operarioId);
    }

    @Override
    public Trabajo save(Trabajo trabajo) {
        return trabajoRepository.save(trabajo);
    }

    @Override
    public Trabajo update(Long id, Trabajo trabajo) {
        Trabajo trabajoExistente = findById(id);
        trabajoExistente.setEstado(trabajo.getEstado());
        trabajoExistente.setIsometrico(trabajo.getIsometrico());
        trabajoExistente.setOperarios(trabajo.getOperarios());
        return trabajoRepository.save(trabajoExistente);
    }

    @Override
    public void delete(Long id) {
        trabajoRepository.delete(findById(id));
    }
}
