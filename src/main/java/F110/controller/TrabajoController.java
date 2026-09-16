package F110.controller;

import F110.dto.TrabajoDTO;
import F110.enums.model.EstadoTrabajo;
import F110.model.Operario;
import F110.model.Trabajo;
import F110.service.IIsometricoService;
import F110.service.IOperarioService;
import F110.service.ITrabajoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/trabajos")
@RequiredArgsConstructor
public class TrabajoController {

    private final ITrabajoService trabajoService;
    private final IIsometricoService isometricoService;
    private final IOperarioService operarioService;

    @GetMapping
    public List<TrabajoDTO> findAll() {
        return trabajoService.findAll().stream()
                .map(this::toDTO)
                .toList();
    }

    @GetMapping("/{id}")
    public TrabajoDTO findById(@PathVariable Long id) {
        return toDTO(trabajoService.findById(id));
    }

    @GetMapping("/isometrico/{isometricoId}")
    public List<TrabajoDTO> findByIsometrico(@PathVariable Long isometricoId) {
        return trabajoService.findByIsometricoId(isometricoId).stream()
                .map(this::toDTO)
                .toList();
    }

    @GetMapping("/estado/{estado}")
    public List<TrabajoDTO> findByEstado(@PathVariable EstadoTrabajo estado) {
        return trabajoService.findByEstado(estado).stream()
                .map(this::toDTO)
                .toList();
    }

    @GetMapping("/operario/{operarioId}")
    public List<TrabajoDTO> findByOperario(@PathVariable Long operarioId) {
        return trabajoService.findByOperarioId(operarioId).stream()
                .map(this::toDTO)
                .toList();
    }

    @PostMapping
    public ResponseEntity<TrabajoDTO> save(@RequestBody TrabajoDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(toDTO(trabajoService.save(toEntity(dto))));
    }

    @PutMapping("/{id}")
    public TrabajoDTO update(@PathVariable Long id, @RequestBody TrabajoDTO dto) {
        return toDTO(trabajoService.update(id, toEntity(dto)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        trabajoService.delete(id);
        return ResponseEntity.noContent().build();
    }

    private Trabajo toEntity(TrabajoDTO dto) {
        Trabajo trabajo = new Trabajo();
        trabajo.setEstado(dto.getEstado());
        trabajo.setIsometrico(isometricoService.findById(dto.getIsometricoId()));
        trabajo.setOperarios(dto.getOperarioIds().stream()
                .map(operarioService::findById)
                .collect(Collectors.toCollection(HashSet::new)));
        return trabajo;
    }

    private TrabajoDTO toDTO(Trabajo trabajo) {
        return TrabajoDTO.builder()
                .id(trabajo.getId())
                .estado(trabajo.getEstado())
                .isometricoId(trabajo.getIsometrico().getId())
                .operarioIds(trabajo.getOperarios().stream()
                        .map(Operario::getId)
                        .collect(Collectors.toSet()))
                .fechaActualizacion(trabajo.getFechaActualizacion())
                .build();
    }
}
