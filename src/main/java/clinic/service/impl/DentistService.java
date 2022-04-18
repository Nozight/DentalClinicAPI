package clinic.service.impl;

import clinic.dto.DentistDTO;
import clinic.exceptions.ResourseNotFountException;
import clinic.model.Dentist;
import clinic.repository.IDentistRepository;
import clinic.service.IDentistService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class DentistService implements IDentistService {
    @Autowired
    private IDentistRepository dentistRepository;
    @Autowired
    private ObjectMapper objectMapper;

    public DentistDTO mapDTO(Dentist dentist){
        return objectMapper.convertValue(dentist,DentistDTO.class);
    }
    public Dentist mapEntity(DentistDTO dentistDTO){        return objectMapper.convertValue(dentistDTO,Dentist.class);
    }

    @Override
    public DentistDTO findById( Integer id) throws ResourseNotFountException {
        Optional<Dentist> dentist = dentistRepository.findById(id);
        if (dentist.isEmpty())
            throw new ResourseNotFountException(("Dentist doesn´t exist"));
        DentistDTO dentistDTO = objectMapper.convertValue(dentist,DentistDTO.class);
        return dentistDTO;
    }

    @Override
    public DentistDTO create( DentistDTO dentistDTO) {
        //DTO recibiendo por parametr
        //1- DTO a Dentist
        Dentist dentist = mapEntity(dentistDTO);
        //2- la entidad guardamos en base de datos
        Dentist newDentistSave = dentistRepository.save(dentist);
        //3- la entidad guardada en la base de datos la retornamos como DTO
        return mapDTO(newDentistSave);
    }

    @Override
    public void deleteById(Integer id) throws ResourseNotFountException {
        Optional<Dentist> dentist = dentistRepository.findById(id);
        if (dentist.isEmpty()){
            throw new ResourseNotFountException("Couldn´t delete dentist (id:"+ id +") because does not exist");
        }
        dentistRepository.deleteById(id);

    }

    @Override
    public DentistDTO update( DentistDTO dentistDTO) throws ResourseNotFountException {
        Dentist dentist = dentistRepository.findById(dentistDTO.getId())
                .orElseThrow(()-> new ResourseNotFountException("Couldn´t delete dentist (id:"+ dentistDTO.getId() +") because does not exist"));

                //Para mantener el mismo ID
        dentist.setEnrollment(dentistDTO.getEnrollment());
        dentist.setName(dentistDTO.getName());
        dentist.setLast_name(dentistDTO.getLast_name());
        Dentist newDentistSave = dentistRepository.save(dentist);
        return mapDTO(newDentistSave);
    }

    @Override
    public Set<DentistDTO> findAll() {
        List<Dentist> dentistList = dentistRepository.findAll();
        Set<DentistDTO> dentistDTOSSet =
                //Voy transformando cada dentista en DTO y con collect voy juntando los valores y guardandolos en una lista
                dentistList.stream().map(dentist -> mapDTO(dentist)).collect(Collectors.toSet());
        return dentistDTOSSet;

    }

    @Override
    public DentistDTO findDentistByName(String name) throws ResourseNotFountException {
        Dentist dentist = dentistRepository.findDentistByName(name);
        if (dentist.getAppointments().isEmpty())
            throw new ResourseNotFountException(("Dentist doesn´t exist"));
        DentistDTO dentistDTO = mapDTO(dentist);
        return dentistDTO;
    }
}
