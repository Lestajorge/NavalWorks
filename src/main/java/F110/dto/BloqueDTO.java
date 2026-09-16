package F110.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BloqueDTO {

    private Long id;
    private String codigoBloque;

    @Min(value = 1, message = "La zona debe ser como mínimo 1")
    @Max(value = 9, message = "La zona debe ser como máximo 9")
    private int zona;
}
