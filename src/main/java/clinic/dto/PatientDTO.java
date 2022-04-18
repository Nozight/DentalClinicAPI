package clinic.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PatientDTO {
    private Integer id;
    private String name;
    private String last_name;
    private String dni;
    private LocalDate admission_date;
    private AddressDTO address;
}
