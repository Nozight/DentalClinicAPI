package clinic.service;

import clinic.dto.PatientDTO;
import clinic.exceptions.ResourseNotFountException;

public interface IPatientService extends ICRUDService<PatientDTO>{
    PatientDTO findPatientByName(String name) throws ResourseNotFountException;
}
