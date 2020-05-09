package com.styledbylovee.styled.manager

import com.styledbylovee.styled.model.Appointment
import com.styledbylovee.styled.repository.AppointmentRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import org.springframework.stereotype.Repository

@Component
class AppointmentManager (@Autowired val repository: AppointmentRepository) {

    fun saveAppointments() {
        repository.save(Appointment("2/22/2020/4:00pm", "2/22/2020", "4:00pm"))
    }
}