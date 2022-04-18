package clinic.controller;

import clinic.dto.AppointmentDTO;

import clinic.exceptions.ResourseNotFountException;
import clinic.service.impl.AppointmentService;
import clinic.service.impl.DentistService;
import clinic.service.impl.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api/appointment")
public class AppointmentController {
    @Autowired
    private AppointmentService appointmentService;
    @Autowired
    private PatientService patientService;
    @Autowired
    private DentistService dentistService;
    @CrossOrigin(origins = "*")
    @GetMapping("/{id}")
    public ResponseEntity<AppointmentDTO> findById(@PathVariable("id") Integer id) throws ResourseNotFountException {
        return new ResponseEntity<>(appointmentService.findById(id), HttpStatus.OK);
    }
    @CrossOrigin(origins = "*")
    @PostMapping()
    public ResponseEntity<AppointmentDTO> create(@RequestBody AppointmentDTO appointmentDTO){
        return new ResponseEntity<>(appointmentService.create(appointmentDTO), HttpStatus.CREATED);

    }
    @CrossOrigin(origins = "*")
    @PutMapping()
    public ResponseEntity<AppointmentDTO> update(@RequestBody AppointmentDTO appointmentDTO){
        return new ResponseEntity<>(appointmentService.create(appointmentDTO), HttpStatus.CREATED);
    }
    @CrossOrigin(origins = "*")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Integer id) throws ResourseNotFountException {
            appointmentService.deleteById(id);
        return ResponseEntity.ok().body("Appointment deleted");
    }
    @CrossOrigin(origins = "*")
    @GetMapping("/all")
    public  ResponseEntity<Set<AppointmentDTO>> findAll(){
        return new ResponseEntity<>(appointmentService.findAll(), HttpStatus.OK);
    }

}