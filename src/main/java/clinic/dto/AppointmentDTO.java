package clinic.dto;

import clinic.model.Dentist;
import clinic.model.Patient;
import lombok.Data;

import java.time.LocalDate;

@Data
public class AppointmentDTO {
    private Integer id;
    private LocalDate appointment_date;
    private Patient patient;
    private Dentist dentist;
}
