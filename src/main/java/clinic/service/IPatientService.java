package clinic.service;

import clinic.dto.PatientDTO;

public interface IPatientService extends ICRUDService<PatientDTO>{
    PatientDTO findPatientByName(String name);
}
