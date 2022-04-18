package clinic.controller;

import clinic.dto.DentistDTO;
import clinic.exceptions.ResourseNotFountException;
import clinic.service.impl.DentistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("api/dentist")
public class DentistController {
    @Autowired
    private DentistService dentistService;
    @CrossOrigin(origins = "*")
    @GetMapping("/{id}")
    public ResponseEntity<DentistDTO> findById(@PathVariable("id") Integer id) throws ResourseNotFountException {
        return  new ResponseEntity<>(dentistService.findById(id), HttpStatus.OK);
    }
    @CrossOrigin(origins = "*")
    @GetMapping("/name")
    public ResponseEntity<DentistDTO> findByName(@RequestParam String name) throws ResourseNotFountException {
        return new ResponseEntity<>(dentistService.findDentistByName(name), HttpStatus.OK);
    }
    @CrossOrigin(origins = "*")
    @PostMapping()
    public ResponseEntity<DentistDTO> create(@RequestBody DentistDTO dentistDTO){
        return new ResponseEntity<>(dentistService.create(dentistDTO), HttpStatus.OK);
    }
    @CrossOrigin(origins = "*")
    @PutMapping()
    public ResponseEntity<DentistDTO> update(@RequestBody DentistDTO dentistDTO) throws ResourseNotFountException {
        return ResponseEntity.ok(dentistService.update(dentistDTO));
    }
    @CrossOrigin(origins = "*")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Integer id) throws ResourseNotFountException {
            dentistService.deleteById(id);
        return ResponseEntity.ok("Dentist deleted");//Llega acá solo si no salta la excepción
    }
    @CrossOrigin(origins = "*")
    @GetMapping("/all")
    public ResponseEntity<Set<DentistDTO>> findAll() throws ResourseNotFountException {
        return  new ResponseEntity<>(dentistService.findAll(), HttpStatus.OK);
    }

}
