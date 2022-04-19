package clinic.dto;

import clinic.model.Dentist;
import clinic.model.Patient;
import lombok.Data;

import java.util.Date;

@Data
public class AppointmentDTO {
    private Integer id;
    private Date appointment_date;
    private PatientDTO patient;
    private DentistDTO dentist;
}
