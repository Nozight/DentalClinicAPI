package clinic.model;

import lombok.Data;
import javax.persistence.*;
import java.time.LocalDate;
@Data //https://projectlombok.org/features/Data reemplaza getters, setters, constructores y hashcode
@Entity
@Table(name = "appointment")
public class Appointment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private LocalDate appointment_date;
    @ManyToOne
    @JoinColumn(name = "dentist_id", referencedColumnName = "id")
    private Dentist dentist;
    @ManyToOne
    @JoinColumn(name = "patient_id", referencedColumnName = "id")
    private Patient patient;

}
