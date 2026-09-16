package F110.dto;

import F110.enums.model.EstadoTrabajo;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TrabajoDTO {

    private Long id;
    private EstadoTrabajo estado;
    private Long isometricoId;

    @Builder.Default
    private Set<Long> operarioIds = new HashSet<>();

    private LocalDateTime fechaActualizacion;
}
