package clinic.service.impl;

import clinic.dto.AppointmentDTO;
import clinic.exceptions.ResourseNotFountException;
import clinic.model.Appointment;

import clinic.repository.IAppointmentRepository;
import clinic.service.IAppointmentService;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.beans.factory.annotation.Autowired;

import java.lang.module.ResolutionException;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

public class AppointmentService implements IAppointmentService {
    @Autowired
    private IAppointmentRepository appointmentRepository;
    @Autowired
    ObjectMapper objectMapper;

    public AppointmentDTO mapDTO(Appointment appointment){
        return objectMapper.convertValue(appointment,AppointmentDTO.class);
    }
    public Appointment mapEntity(AppointmentDTO appointmentDTO){
        return objectMapper.convertValue(appointmentDTO,Appointment.class);
    }

    @Override
    public AppointmentDTO findById(Integer id) throws ResourseNotFountException {
        Optional<Appointment> appointment = appointmentRepository.findById(id);
        if (appointment.isEmpty())
            throw new ResourseNotFountException(("Appointment (id:"+ id +")  doesn´t exist"));
        return objectMapper.convertValue(appointment, AppointmentDTO.class);
    }

    @Override
    public AppointmentDTO create(AppointmentDTO appointmentDTO) {
        //DTO recibiendo por parametr
        //1- DTO a Appointment
        Appointment appointment = mapEntity(appointmentDTO);
        //2- la entidad guardamos en base de datos
        Appointment newAppointmentSave = appointmentRepository.save(appointment);
        //3- la entidad guardada en la base de datos la retornamos como DTO
        return mapDTO(newAppointmentSave);
    }

    @Override
    public void deleteById(Integer id) throws ResourseNotFountException {
        if (appointmentRepository.findById(id).isEmpty())
            throw new ResourseNotFountException("Couldn´t delete appointment(id:"+ id +") because does not exist");
        appointmentRepository.deleteById(id);
    }

    @Override
    public AppointmentDTO update(AppointmentDTO appointmentDTO) {
        Appointment appointment = appointmentRepository.findById(appointmentDTO.getId())
                .orElseThrow(() -> new ResolutionException("Couldn´t identify appointment"));
        return mapDTO(appointment);
    }

    @Override
    public Set<AppointmentDTO> findAll() {
        List<Appointment> appointmentList = appointmentRepository.findAll();
        Set<AppointmentDTO> appointmentDTOSet =
                appointmentList.stream().map(appointment -> mapDTO(appointment)).collect(Collectors.toSet());
        return appointmentDTOSet;
    }
    public boolean existById(Integer id){
        if (appointmentRepository.existsById(id))
            return true;
        else
            return false;
    }
}
