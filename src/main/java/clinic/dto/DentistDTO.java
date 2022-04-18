package clinic.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class DentistDTO {
    private Integer id;
    private Integer enrollment;
    private String name;
    private String last_name;

}
