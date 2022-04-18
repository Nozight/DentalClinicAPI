package clinic.service;

import clinic.dto.DentistDTO;
import clinic.exceptions.ResourseNotFountException;


public interface IDentistService extends ICRUDService<DentistDTO>{
     DentistDTO findDentistByName(String name) throws ResourseNotFountException;
}
