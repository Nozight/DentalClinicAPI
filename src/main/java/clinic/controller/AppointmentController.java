package clinic.controller;

import clinic.dto.AppointmentDTO;

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
    public ResponseEntity<AppointmentDTO> findById(@PathVariable("id") Integer id) {
        AppointmentDTO appointmentDTO = appointmentService.findById(id);
        return new ResponseEntity<>(appointmentDTO, HttpStatus.OK);
    }
    @CrossOrigin(origins = "*")
    @PostMapping()
    public ResponseEntity<AppointmentDTO> create(@RequestBody AppointmentDTO appointmentDTO){
        AppointmentDTO newAppointmentDTO = appointmentService.create(appointmentDTO);
        return new ResponseEntity<>(newAppointmentDTO, HttpStatus.CREATED);

    }
    @CrossOrigin(origins = "*")
    @PutMapping()
    public ResponseEntity<AppointmentDTO> update(@RequestBody AppointmentDTO appointmentDTO){
        AppointmentDTO newAppointmentDTO = appointmentService.create(appointmentDTO);
        return new ResponseEntity<>(newAppointmentDTO, HttpStatus.CREATED);
    }
    @CrossOrigin(origins = "*")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Integer id){
        ResponseEntity<String> response = null;

        if (appointmentService.findById(id) != null){
            appointmentService.deleteById(id);
            response = ResponseEntity.ok().body("Appointment deleted");
        } else {
            ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return response;
    }
    @CrossOrigin(origins = "*")
    @GetMapping("/all")
    public  ResponseEntity<Set<AppointmentDTO>> findAll(){
        Set<AppointmentDTO> appointmentDTOSet = appointmentService.findAll();
        return new ResponseEntity<>(appointmentDTOSet, HttpStatus.OK);
    }

}