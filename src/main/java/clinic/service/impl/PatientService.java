package clinic.service.impl;

import clinic.dto.PatientDTO;
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
    public PatientDTO findById(Integer id) {
        Optional<Patient> patient = patientRepository.findById(id);
        PatientDTO patientDTO = null;
        if (patient.isPresent())
                patientDTO = objectMapper.convertValue(patient,PatientDTO.class);
        return patientDTO;
    }
    @Override
    public PatientDTO findPatientByName(String name) {
        Patient patient = patientRepository.findPatientByName(name);
        PatientDTO patientDTO = null;
        if (patient != null)
            patientDTO = objectMapper.convertValue(patient,PatientDTO.class);
        return patientDTO;
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
    public void deleteById(Integer id) {
        //1- buscar la entidad 2- verificar que se encontro 3- eliminarla
        Optional<Patient> patient = patientRepository.findById(id);
        String result = "Couldn´t find Patient";
        if (patient != null)
            result="Patient Deleted";
        patientRepository.deleteById(id);

    }

    @Override
    public PatientDTO update(PatientDTO patientDTO) {
        //DTO recibiendo por parametr
        //1- DTO a Patient
        Patient patient = mapEntity(patientDTO);
        //2- la entidad guardamos en base de datos
        Patient newPatientSave = patientRepository.save(patient);
        //3- la entidad guardada en la base de datos la retornamos como DTO
        return mapDTO(newPatientSave);
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
