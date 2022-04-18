package clinic.model;

import lombok.Data;

import javax.persistence.*;
@Data //https://projectlombok.org/features/Data reemplaza getters, setters, constructores y hashcode
@Entity
@Table(name = "address")
public class Address {
    @Id@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String street;
    private String number;
    private String locality;
    private String province;

}

