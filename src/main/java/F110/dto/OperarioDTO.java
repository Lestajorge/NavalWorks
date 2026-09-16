package F110.dto;

import com.navantia.f110.enums.model.Especialista;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OperarioDTO {

    private Long id;
    private String nombre;
    private String apellidos;
    private Especialista especialista;
}
