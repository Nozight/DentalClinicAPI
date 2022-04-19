package clinic.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.Set;

/*@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter*/
@Data //https://projectlombok.org/features/Data reemplaza getters, setters, constructores y hashcode
@Entity
@Table(name = "dentist")
public class Dentist {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    //@Column(name ="otro_nombre")
    private Integer enrollment;
    private String name;
    private String last_name;
    @OneToMany(mappedBy = "dentist", fetch = FetchType.LAZY)//Lazy si no quiero que traiga todos los turnos que tiene, Eager si queiro
    @JsonIgnore
    private Set<Appointment> appointments;

}
