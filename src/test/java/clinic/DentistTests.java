package clinic;

import clinic.dto.DentistDTO;

import clinic.exceptions.ResourseNotFountException;
import clinic.service.impl.DentistService;
import org.hamcrest.MatcherAssert;
import org.hamcrest.beans.SamePropertyValuesAs;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class DentistTests {
    @Autowired
    DentistService dentistService;

    @Test
    public void findByIdDentist() throws ResourseNotFountException {
        DentistDTO d1 = new DentistDTO();
        d1.setEnrollment(123);
        d1.setName("dentist1");
        d1.setLast_name("test1");
        DentistDTO newd1 = dentistService.create(d1);
        d1.setId(newd1.getId());
        MatcherAssert.assertThat(d1, SamePropertyValuesAs.samePropertyValuesAs(dentistService.findById(newd1.getId())));
        dentistService.deleteById(newd1.getId());
    }

    @Test

    public void findAllDentist() throws ResourseNotFountException {
        DentistDTO d1 = new DentistDTO();
        d1.setEnrollment(123);
        d1.setName("dentist1");
        d1.setLast_name("test1");
        DentistDTO newd1 = dentistService.create(d1);

        DentistDTO d2 = new DentistDTO();
        d2.setEnrollment(456);
        d2.setName("dentist2");
        d2.setLast_name("test2");
        DentistDTO newd2 = dentistService.create(d2);

        DentistDTO d3 = new DentistDTO();
        d3.setEnrollment(789);
        d3.setName("dentist3");
        d3.setLast_name("test3");
        DentistDTO newd3 = dentistService.create(d3);
        Assertions.assertEquals(dentistService.findAll().size(), 3);
        dentistService.deleteById(newd1.getId());
        dentistService.deleteById(newd2.getId());
        dentistService.deleteById(newd3.getId());
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
        MatcherAssert.assertThat(d2test, SamePropertyValuesAs.samePropertyValuesAs(dentistService.findById(newd2.getId())));
        dentistService.deleteById(newd2.getId());
    }
    @Test
    public void deleteDentists() throws ResourseNotFountException {
        DentistDTO d3 = new DentistDTO();
        d3.setEnrollment(789);
        d3.setName("dentist3");
        d3.setLast_name("test3");
        DentistDTO newd3 = dentistService.create(d3);
        dentistService.deleteById(newd3.getId());
        Assertions.assertFalse(dentistService.existById(newd3.getId()));
    }
}
