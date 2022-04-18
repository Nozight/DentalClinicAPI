package clinic.controller;

import clinic.dto.PatientDTO;
import clinic.service.impl.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("api/patient")
public class PatientController {
    @Autowired
    private PatientService patientService;
    @CrossOrigin(origins = "*")
    @GetMapping("/{id}")
    public ResponseEntity<PatientDTO> findById(@PathVariable("id") Integer id){
        PatientDTO PatientDTO = patientService.findById(id);
        return  new ResponseEntity<>(PatientDTO, HttpStatus.OK);
    }
    @CrossOrigin(origins = "*")
    @GetMapping("/name")
    public ResponseEntity<PatientDTO> findByName(@RequestParam String name){
        PatientDTO patientDTO = patientService.findPatientByName(name);
        return new ResponseEntity<>(patientDTO,HttpStatus.OK);
    }
    @CrossOrigin(origins = "*")
    @PostMapping()
    public ResponseEntity<PatientDTO> create(@RequestBody PatientDTO PatientDTO){
        PatientDTO newPatientDTO = patientService.create(PatientDTO);
        return new ResponseEntity<>(newPatientDTO, HttpStatus.OK);
    }
    @CrossOrigin(origins = "*")
    @PutMapping()
    public ResponseEntity<PatientDTO> update(@RequestBody PatientDTO PatientDTO){
        ResponseEntity<PatientDTO> response = null;
        if(PatientDTO.getId() != null && patientService.findById(PatientDTO.getId()) != null)
            response = ResponseEntity.ok(patientService.update(PatientDTO));
        else
            response = ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        return response;
    }
    @CrossOrigin(origins = "*")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Integer id){
        ResponseEntity<String> response = null;

        if (patientService.findById(id) !=null){
            patientService.deleteById(id);
            response = ResponseEntity.ok().body("Patient deleted");
        } else {
            response = ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return response;
    }
    @CrossOrigin(origins = "*")
    @GetMapping("/all")
    public ResponseEntity<Set<PatientDTO>> findAll(){
        Set<PatientDTO> dentistSet = patientService.findAll();
        return  new ResponseEntity<>(dentistSet, HttpStatus.OK);
    }
}
