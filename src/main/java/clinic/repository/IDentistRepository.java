package clinic.repository;

import clinic.model.Dentist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IDentistRepository extends JpaRepository<Dentist, Integer> {
    //@Query("FROM Dentist d WHERE o.name = ?")
    Dentist findDentistByName(String name);//Metodo automagico de hibernate para buscar por atributo
    //find + NameClass + By + AtributeName
    Dentist findDentistByEnrollment(Integer enrollment);
}
