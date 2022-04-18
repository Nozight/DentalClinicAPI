package clinic;

import clinic.dto.AddressDTO;
import clinic.dto.PatientDTO;

import clinic.exceptions.ResourseNotFountException;
import clinic.service.impl.PatientService;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

@SpringBootTest
public class PatientTests {
    @Autowired
    PatientService patientService;
    @Test
    public void findByIdPatient() throws ResourseNotFountException {
        AddressDTO a1 = new AddressDTO();
        a1.setStreet("adrsTest");
        a1.setLocality("adrsTest");
        a1.setNumber("123");
        a1.setStreet("adrsTest");
        PatientDTO p1 = new PatientDTO();
        p1.setName("patient1");
        p1.setLast_name("Patient1");
        p1.setAdmission_date(LocalDate.now());
        p1.setDni("1234");
        p1.setAddress(a1);
        PatientDTO newp1 = patientService.create(p1);
        Assertions.assertEquals(patientService.findById(newp1.getId()).getName(), p1.getName());
    }

    @Test
    public void findAllPatients() throws ResourseNotFountException {
        Assertions.assertEquals(patientService.findAll().size(), 2);
    }
    @Test
    public void updatePatients() throws ResourseNotFountException {
        AddressDTO a2 = new AddressDTO();
        a2.setStreet("adrsTest");
        a2.setLocality("adrsTest");
        a2.setNumber("123");
        a2.setStreet("adrsTest");

        PatientDTO p2 = new PatientDTO();
        p2.setName("patient1");
        p2.setLast_name("Patient1");
        p2.setAdmission_date(LocalDate.now());
        p2.setDni("1234");
        p2.setAddress(a2);

        patientService.create(p2);

        AddressDTO a2test = new AddressDTO();
        a2test.setStreet("adrsTestUpdated");
        a2test.setLocality("adrsTestUpdated");
        a2test.setNumber("123Updated");
        a2test.setStreet("adrsTestUpdated");

        PatientDTO p2test = new PatientDTO();
        p2test.setName("patient2Updated");
        p2test.setLast_name("Patient2Updated");
        p2test.setAdmission_date(LocalDate.now());
        p2test.setDni("1234Updated");
        p2test.setAddress(a2);;

        PatientDTO newp2 = patientService.update(p2test);

        Assertions.assertEquals(patientService.findById(newp2.getId()).getName(), "patient2Updated");
    }
    @Test
    public void deletePatient() throws ResourseNotFountException {
        AddressDTO a3 = new AddressDTO();
        a3.setStreet("adrsTest");
        a3.setLocality("adrsTest");
        a3.setNumber("123");
        a3.setStreet("adrsTest");

        PatientDTO p3 = new PatientDTO();
        p3.setName("patient1");
        p3.setLast_name("Patient1");
        p3.setAdmission_date(LocalDate.now());
        p3.setDni("1234");
        p3.setAddress(a3);

        PatientDTO newp3 = patientService.create(p3);

        patientService.deleteById(newp3.getId());

        Assertions.assertNull(patientService.findById(newp3.getId()));
    }
}
