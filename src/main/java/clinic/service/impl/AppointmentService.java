package clinic.service.impl;

import clinic.dto.AppointmentDTO;
import clinic.model.Appointment;

import clinic.repository.IAppointmentRepository;
import clinic.service.IAppointmentService;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.beans.factory.annotation.Autowired;

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
    public AppointmentDTO findById(Integer id) {
        Optional<Appointment> appointment = appointmentRepository.findById(id);
        AppointmentDTO appointmentDTO = null;
        if (appointment.isPresent())
            appointmentDTO = objectMapper.convertValue(appointment, AppointmentDTO.class);
        return appointmentDTO;
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
    public void deleteById(Integer id) {
        Optional<Appointment> patient = appointmentRepository.findById(id);
        String result = "Couldn´t find Appointment";
        if (patient != null)

        appointmentRepository.deleteById(id);

    }

    @Override
    public AppointmentDTO update(AppointmentDTO appointmentDTO) {
        //DTO recibiendo por parametr
        //1- DTO a Appointment
        Appointment appointment = mapEntity(appointmentDTO);
        //2- la entidad guardamos en base de datos
        Appointment newAppointmentSave = appointmentRepository.save(appointment);
        //3- la entidad guardada en la base de datos la retornamos como DTO
        return mapDTO(newAppointmentSave);
    }

    @Override
    public Set<AppointmentDTO> findAll() {
        List<Appointment> appointmentList = appointmentRepository.findAll();
        Set<AppointmentDTO> appointmentDTOSet =
                appointmentList.stream().map(appointment -> mapDTO(appointment)).collect(Collectors.toSet());
        return appointmentDTOSet;
    }

}
