package clinic.dto;

import clinic.model.Appointment;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class DentistDTO {
    private Integer id;
    private Integer enrollment;
    private String name;
    private String last_name;
    private Set<Appointment> appointments;
}
