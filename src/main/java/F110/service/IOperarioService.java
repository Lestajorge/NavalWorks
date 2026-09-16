package F110.service;

import F110.model.Operario;
import com.navantia.f110.enums.model.Especialista;

import java.util.List;

public interface IOperarioService {

    List<Operario> findAll();

    Operario findById(Long id);

    List<Operario> findByEspecialista(Especialista especialista);

    List<Operario> findByNombre(String nombre);

    List<Operario> findByApellidos(String apellidos);

    Operario save(Operario operario);

    Operario update(Long id, Operario operario);

    void delete(Long id);
}
