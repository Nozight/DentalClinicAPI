package clinic;

import clinic.dto.DentistDTO;

import clinic.exceptions.ResourseNotFountException;
import clinic.service.impl.DentistService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
public class DentistTests {
    @Autowired
    DentistService dentistService;
    /*@BeforeAll
    public static void createDentists(){
        DentistDTO d1 = new DentistDTO();
        d1.setEnrollment(123);
        d1.setName("dentist1");
        d1.setLast_name("test1");
        dentistService.create(d1);
        DentistDTO d2 = new DentistDTO();
        d2.setEnrollment(456);
        d2.setName("dentist2");
        d2.setLast_name("test2");
        dentistService.create(d2);
        DentistDTO d3 = new DentistDTO();
        d3.setEnrollment(789);
        d3.setName("dentist3");
        d3.setLast_name("test3");
        dentistService.create(d3);
    }*/
    @Test
    public void findByIdDentist() throws ResourseNotFountException {
        DentistDTO d1 = new DentistDTO();
        d1.setEnrollment(123);
        d1.setName("dentist1");
        d1.setLast_name("test1");
        DentistDTO newd1 = dentistService.create(d1);
        Assertions.assertEquals(dentistService.findById(newd1.getId()).getName(), d1.getName());
    }

    @Test

    public void findAllDentist(){
        Assertions.assertEquals(dentistService.findAll().size(), 2);
    }
    @Test
    public void updateDentists() throws ResourseNotFountException {
        DentistDTO d2 = new DentistDTO();
        d2.setEnrollment(456);
        d2.setName("dentist2");
        d2.setLast_name("test2");
        DentistDTO newd2 = dentistService.create(d2);
        DentistDTO d2test = new DentistDTO();
        d2test.setId(newd2.getId());
        d2test.setEnrollment(456);
        d2test.setName("dentist2Updated");
        d2test.setLast_name("test2Updated");
        dentistService.update(d2test);
        Assertions.assertEquals(dentistService.findById(newd2.getId()).getName(), "dentist2Updated");
    }
    @Test
    public void deleteDentists() throws ResourseNotFountException {
        DentistDTO d3 = new DentistDTO();
        d3.setEnrollment(789);
        d3.setName("dentist3");
        d3.setLast_name("test3");
        DentistDTO newd3 = dentistService.create(d3);
        dentistService.deleteById(newd3.getId());
        Assertions.assertNull(dentistService.findById(newd3.getId()));
    }
}
