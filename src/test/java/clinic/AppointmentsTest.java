package clinic;

import clinic.dto.AddressDTO;
import clinic.dto.AppointmentDTO;
import clinic.dto.DentistDTO;
import clinic.dto.PatientDTO;
import clinic.exceptions.ResourseNotFountException;
import clinic.service.impl.AppointmentService;
import clinic.service.impl.DentistService;
import clinic.service.impl.PatientService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

@SpringBootTest
public class AppointmentsTest {
    @Autowired
    AppointmentService appointmentService;
    @Autowired
    DentistService dentistService;
    @Autowired
    PatientService patientService;

    @Test
    public void findByIdAppointment() throws ResourseNotFountException {
        /* DENTIST */
        DentistDTO d1 = new DentistDTO();
        d1.setEnrollment(123);
        d1.setName("dentist1");
        d1.setLast_name("test1");
        DentistDTO newd1 = dentistService.create(d1);
        /* PATIENT */
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
        /* APPOINTMENT */
        AppointmentDTO apnt1 = new AppointmentDTO();
        apnt1.setAppointment_date(LocalDate.of(2022,01,01));
        apnt1.setDentist(dentistService.mapEntity(newd1));
        apnt1.setPatient(patientService.mapEntity(newp1));
        AppointmentDTO newApnt1 = appointmentService.create(apnt1);

        apnt1.setId(newApnt1.getId());

        Assertions.assertEquals(appointmentService.findById(newApnt1.getId()), apnt1);
        appointmentService.deleteById(newApnt1.getId());
    }
    @Test
    public void updateAppointment()throws ResourseNotFountException{
        /* DENTIST */
        DentistDTO d1 = new DentistDTO();
        d1.setEnrollment(123);
        d1.setName("dentist1");
        d1.setLast_name("test1");
        DentistDTO newd1 = dentistService.create(d1);
        /* PATIENT */
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
        /* APPOINTMENT */
        AppointmentDTO apnt1 = new AppointmentDTO();
        apnt1.setAppointment_date(LocalDate.of(2022,01,01));
        apnt1.setDentist(dentistService.mapEntity(newd1));
        apnt1.setPatient(patientService.mapEntity(newp1));
        AppointmentDTO newApnt1 = appointmentService.create(apnt1);

        /* DENTIST 2*/
        DentistDTO d2 = new DentistDTO();
        d2.setEnrollment(456);
        d2.setName("dentist2");
        d2.setLast_name("test2");
        DentistDTO newd2 = dentistService.create(d2);
        /* PATIENT 2*/
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
        PatientDTO newp2 =patientService.create(p2);
        /* APPOINTMENT 2*/
        AppointmentDTO apnt2 = new AppointmentDTO();
        apnt1.setAppointment_date(LocalDate.of(2022,01,02));
        apnt1.setDentist(dentistService.mapEntity(newd2));
        apnt1.setPatient(patientService.mapEntity(newp2));
        apnt2.setId(newApnt1.getId());
        AppointmentDTO newApnt2 = appointmentService.update(apnt2);

        Assertions.assertEquals(appointmentService.findById(newApnt1.getId()),newApnt2);
        appointmentService.deleteById(newApnt2.getId());
    }

    @Test
    public void deleteAppointment() throws ResourseNotFountException {
        /* DENTIST */
        DentistDTO d1 = new DentistDTO();
        d1.setEnrollment(123);
        d1.setName("dentist1");
        d1.setLast_name("test1");
        DentistDTO newd1 = dentistService.create(d1);
        /* PATIENT */
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
        /* APPOINTMENT */
        AppointmentDTO apnt1 = new AppointmentDTO();
        apnt1.setAppointment_date(LocalDate.of(2022,01,01));
        apnt1.setDentist(dentistService.mapEntity(newd1));
        apnt1.setPatient(patientService.mapEntity(newp1));
        AppointmentDTO newApnt1 = appointmentService.create(apnt1);

        appointmentService.deleteById(newApnt1.getId());
        Assertions.assertFalse(appointmentService.existById(newApnt1.getId()));
    }

    @Test
    public void findAllAppointments() throws ResourseNotFountException{
        /* DENTIST */
        DentistDTO d1 = new DentistDTO();
        d1.setEnrollment(123);
        d1.setName("dentist1");
        d1.setLast_name("test1");
        DentistDTO newd1 = dentistService.create(d1);
        /* PATIENT */
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
        /* APPOINTMENT */
        AppointmentDTO apnt1 = new AppointmentDTO();
        apnt1.setAppointment_date(LocalDate.of(2022,01,01));
        apnt1.setDentist(dentistService.mapEntity(newd1));
        apnt1.setPatient(patientService.mapEntity(newp1));
        AppointmentDTO newApnt1 = appointmentService.create(apnt1);

        /* DENTIST 2*/
        DentistDTO d2 = new DentistDTO();
        d2.setEnrollment(456);
        d2.setName("dentist2");
        d2.setLast_name("test2");
        DentistDTO newd2 = dentistService.create(d2);
        /* PATIENT 2*/
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
        PatientDTO newp2 =patientService.create(p2);
        /* APPOINTMENT 2*/
        AppointmentDTO apnt2 = new AppointmentDTO();
        apnt1.setAppointment_date(LocalDate.of(2022,01,02));
        apnt1.setDentist(dentistService.mapEntity(newd2));
        apnt1.setPatient(patientService.mapEntity(newp2));
        AppointmentDTO newApnt2 = appointmentService.create(apnt2);

        Assertions.assertEquals(appointmentService.findAll().size(), 2);
    }
}
