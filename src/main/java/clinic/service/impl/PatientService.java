package clinic.service.impl;

import clinic.dto.PatientDTO;
import clinic.exceptions.ResourseNotFountException;
import clinic.model.Patient;
import clinic.repository.IPatientRepository;
import clinic.service.IPatientService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class PatientService implements IPatientService {
    @Autowired
    private IPatientRepository patientRepository;
    @Autowired
    ObjectMapper objectMapper;

    public PatientDTO mapDTO(Patient patient){
        return objectMapper.convertValue(patient,PatientDTO.class);
    }
    public Patient mapEntity(PatientDTO patientDTO){
        return objectMapper.convertValue(patientDTO,Patient.class);
    }

    @Override
    public PatientDTO findById(Integer id) throws ResourseNotFountException {
        Optional<Patient> patient = patientRepository.findById(id);
        if (patient.isEmpty())
            throw new ResourseNotFountException(("Patient doesn´t exist"));
        return objectMapper.convertValue(patient,PatientDTO.class);

    }
    @Override
    public PatientDTO findPatientByName(String name) throws ResourseNotFountException {
        Patient patient = patientRepository.findPatientByName(name);
        if (patient == null)
            throw new ResourseNotFountException(("Patient not found"));
        return objectMapper.convertValue(patient,PatientDTO.class);
    }

    @Override
    public PatientDTO create(PatientDTO patientDTO) {
        patientDTO.setAdmission_date(LocalDate.now());
        //DTO recibiendo por parametr
        //1- DTO a Patient
        Patient patient = mapEntity(patientDTO);
        //2- la entidad guardamos en base de datos
        Patient newPatientSave = patientRepository.save(patient);
        //3- la entidad guardada en la base de datos la retornamos como DTO
        return mapDTO(newPatientSave);
    }

    @Override
    public void deleteById(Integer id) throws ResourseNotFountException {
        if (patientRepository.findById(id).isEmpty())
            throw new ResourseNotFountException("Couldn´t delete patient (id:"+ id +") because does not exist");
        patientRepository.deleteById(id);
    }

    @Override
    public PatientDTO update(PatientDTO patientDTO) throws ResourseNotFountException {
        Patient patient = patientRepository.findById(patientDTO.getId())
                .orElseThrow(() -> new ResourseNotFountException("Couldn´t update patient (id:"+ patientDTO.getId() +") because does not exist"));
        return mapDTO(patientRepository.save(patient));
    }

    @Override
    public Set<PatientDTO> findAll() {
        List<Patient> patientList = patientRepository.findAll();
        Set<PatientDTO> patientDTOSSet =
                //Voy transformando cada atienta en DTO y con collect voy juntando los valores y guardandolos en una lista
                patientList.stream().map(patient -> mapDTO(patient)).collect(Collectors.toSet());
        return patientDTOSSet;
    }


}
