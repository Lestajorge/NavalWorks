package F110.service;

import F110.model.Bloque;
import F110.repository.BloqueRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BloqueService implements IBloqueService {

    private final BloqueRepository bloqueRepository;

    @Override
    public List<Bloque> findAll() {
        return bloqueRepository.findAll();
    }

    @Override
    public Bloque findById(Long id) {
        return bloqueRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("No existe el bloque con id: " + id));
    }

    @Override
    public Bloque findByCodigoBloque(String codigoBloque) {
        return bloqueRepository.findByCodigoBloque(codigoBloque)
                .orElseThrow(() -> new EntityNotFoundException(
                        "No existe el bloque con código: " + codigoBloque));
    }

    @Override
    public Bloque save(Bloque bloque) {
        return bloqueRepository.save(bloque);
    }

    @Override
    public Bloque update(Long id, Bloque bloque) {
        Bloque bloqueExistente = findById(id);
        bloqueExistente.setCodigoBloque(bloque.getCodigoBloque());
        bloqueExistente.setZona(bloque.getZona());
        return bloqueRepository.save(bloqueExistente);
    }

    @Override
    public void delete(Long id) {
        bloqueRepository.delete(findById(id));
    }
}
