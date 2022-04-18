package clinic.controller;

import clinic.dto.DentistDTO;
import clinic.exceptions.ResourseNotFountException;
import clinic.service.impl.DentistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("api/dentist")
public class DentistController {
    @Autowired
    private DentistService dentistService;
    @CrossOrigin(origins = "*")
    @GetMapping("/{id}")
    public ResponseEntity<DentistDTO> findById(@PathVariable("id") Integer id) throws ResourseNotFountException {
        DentistDTO dentistDTO = dentistService.findById(id);
        return  new ResponseEntity<>(dentistDTO, HttpStatus.OK);
    }
    @CrossOrigin(origins = "*")
    @GetMapping("/name")
    public ResponseEntity<DentistDTO> findByName(@RequestParam String name) throws ResourseNotFountException {
        DentistDTO dentistDTO = dentistService.findDentistByName(name);
        return new ResponseEntity<>(dentistDTO, HttpStatus.OK);
    }
    @CrossOrigin(origins = "*")
    @PostMapping()
    public ResponseEntity<DentistDTO> create(@RequestBody DentistDTO dentistDTO){
        DentistDTO newDentistDTO = dentistService.create(dentistDTO);
        return new ResponseEntity<>(newDentistDTO, HttpStatus.OK);
    }
    @CrossOrigin(origins = "*")
    @PutMapping()
    public ResponseEntity<DentistDTO> update(@RequestBody DentistDTO dentistDTO) throws ResourseNotFountException {
        ResponseEntity<DentistDTO> response = null;
        if(dentistDTO.getId() != null && dentistService.findById(dentistDTO.getId()) != null)
            response = ResponseEntity.ok(dentistService.update(dentistDTO));
        else
            response = ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        return response;
    }
    @CrossOrigin(origins = "*")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Integer id) throws ResourseNotFountException {
            dentistService.deleteById(id);
        return ResponseEntity.ok("Dentist deleted");//Llega acá solo si no salta la excepción
    }
    @CrossOrigin(origins = "*")
    @GetMapping("/all")
    public ResponseEntity<Set<DentistDTO>> findAll(){
        Set<DentistDTO> dentistDTOSet = dentistService.findAll();
        return  new ResponseEntity<>(dentistDTOSet, HttpStatus.OK);
    }

}
