package F110.controller;

import F110.dto.OperarioDTO;
import F110.dto.OperarioCreateDTO;
import F110.model.Operario;
import F110.service.IOperarioService;
import com.navantia.f110.enums.model.Especialista;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/operarios")
@RequiredArgsConstructor
public class OperarioController {

    private final IOperarioService operarioService;

    @GetMapping
    public List<OperarioDTO> findAll() {
        return operarioService.findAll().stream()
                .map(this::toDTO)
                .toList();
    }

    @GetMapping("/{id}")
    public OperarioDTO findById(@PathVariable Long id) {
        return toDTO(operarioService.findById(id));
    }

    @GetMapping("/especialista/{especialista}")
    public List<OperarioDTO> findByEspecialista(@PathVariable Especialista especialista) {
        return operarioService.findByEspecialista(especialista).stream()
                .map(this::toDTO)
                .toList();
    }

    @GetMapping("/nombre/{nombre}")
    public List<OperarioDTO> findByNombre(@PathVariable String nombre) {
        return operarioService.findByNombre(nombre).stream()
                .map(this::toDTO)
                .toList();
    }

    @GetMapping("/apellidos/{apellidos}")
    public List<OperarioDTO> findByApellidos(@PathVariable String apellidos) {
        return operarioService.findByApellidos(apellidos).stream()
                .map(this::toDTO)
                .toList();
    }

    @PostMapping
    public ResponseEntity<OperarioDTO> save(@RequestBody OperarioCreateDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(toDTO(operarioService.save(toEntity(dto))));
    }

    @PutMapping("/{id}")
    public OperarioDTO update(@PathVariable Long id, @RequestBody OperarioCreateDTO dto) {
        return toDTO(operarioService.update(id, toEntity(dto)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        operarioService.delete(id);
        return ResponseEntity.noContent().build();
    }

    private Operario toEntity(OperarioCreateDTO dto) {
        Operario operario = new Operario();
        operario.setNombre(dto.getNombre());
        operario.setApellidos(dto.getApellidos());
        operario.setUsuario(dto.getUsuario());
        operario.setPassword(dto.getPassword());
        operario.setEspecialista(dto.getEspecialista());
        return operario;
    }

    private Operario toEntity(OperarioDTO dto) {
        Operario operario = new Operario();
        operario.setNombre(dto.getNombre());
        operario.setApellidos(dto.getApellidos());
        operario.setEspecialista(dto.getEspecialista());
        return operario;
    }

    private OperarioDTO toDTO(Operario operario) {
        return OperarioDTO.builder()
                .id(operario.getId())
                .nombre(operario.getNombre())
                .apellidos(operario.getApellidos())
                .especialista(operario.getEspecialista())
                .build();
    }
}
