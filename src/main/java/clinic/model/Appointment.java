package clinic.model;

import lombok.Data;
import javax.persistence.*;
import java.util.Date;
@Data //https://projectlombok.org/features/Data reemplaza getters, setters, constructores y hashcode
@Entity
@Table(name = "appointment")
public class Appointment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Date appointment_date;
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @JoinColumn(name = "dentist_id", referencedColumnName = "id")
    private Dentist dentist;
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @JoinColumn(name = "patient_id", referencedColumnName = "id")
    private Patient patient;

}
