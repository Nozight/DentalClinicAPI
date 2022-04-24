package clinic.repository;

import clinic.model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IPatientRepository extends JpaRepository<Patient, Integer> {
    Patient findPatientByName(String name);//Metodo automagico de hibernate para buscar por atributo
    //find + NameClass + By + AtributeName
    Patient findPatientByDni(String dni);
}
