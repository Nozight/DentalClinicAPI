package clinic.controller;

import clinic.dto.PatientDTO;
import clinic.exceptions.ResourseNotFountException;
import clinic.service.impl.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("api/patient")
@CrossOrigin(origins = "*")
public class PatientController {
    @Autowired
    private PatientService patientService;
    @CrossOrigin(origins = "*")
    @GetMapping("/{id}")
    public ResponseEntity<PatientDTO> findById(@PathVariable("id") Integer id) throws ResourseNotFountException {
        return  new ResponseEntity<>(patientService.findById(id), HttpStatus.OK);
    }
    @CrossOrigin(origins = "*")
    @GetMapping("/name")
    public ResponseEntity<PatientDTO> findByName(@RequestParam String name) throws ResourseNotFountException {
        return new ResponseEntity<>(patientService.findPatientByName(name),HttpStatus.OK);
    }
    @CrossOrigin(origins = "*")
    @GetMapping("/dni")
    public ResponseEntity<PatientDTO> findByDni(@RequestParam String dni) throws ResourseNotFountException {
        return new ResponseEntity<>(patientService.findPatientByDni(dni),HttpStatus.OK);
    }
    @CrossOrigin(origins = "*")
    @PostMapping()
    public ResponseEntity<PatientDTO> create(@RequestBody PatientDTO PatientDTO){
        return new ResponseEntity<>(patientService.create(PatientDTO), HttpStatus.OK);
    }
    @CrossOrigin(origins = "*")
    @PutMapping()
    public ResponseEntity<PatientDTO> update(@RequestBody PatientDTO PatientDTO) throws ResourseNotFountException {

        return ResponseEntity.ok(patientService.update(PatientDTO));
    }
    @CrossOrigin(origins = "*")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Integer id) throws ResourseNotFountException {
            patientService.deleteById(id);
        return ResponseEntity.ok().body("Patient deleted");
    }
    @CrossOrigin(origins = "*")
    @GetMapping("/all")
    public ResponseEntity<Set<PatientDTO>> findAll() throws ResourseNotFountException {
        return  new ResponseEntity<>(patientService.findAll(), HttpStatus.OK);
    }
}
