package clinic.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Set;

@Data //https://projectlombok.org/features/Data reemplaza getters, setters, constructores y hashcode
@Entity
@Table(name = "patient")
public class Patient {
    @Id@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String last_name;
    private String dni;
    private LocalDate admission_date;
    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)//cascade para eliminar los dos juntos
    @JoinColumn(name = "address_id", referencedColumnName = "id")
    private Address address;
    @OneToMany(mappedBy = "patient", fetch = FetchType.LAZY)
    @JsonIgnore
    private Set<Appointment> appointments;
}
