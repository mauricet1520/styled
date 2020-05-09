package com.styledbylovee.styled.manager


import com.styledbylovee.styled.model.Appointment
import com.styledbylovee.styled.model.AvailableDate
import com.styledbylovee.styled.repository.AppointmentRepository
import com.styledbylovee.styled.repository.AvailableDateRepository
import com.styledbylovee.styled.util.generateDates
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import org.springframework.stereotype.Repository

@Component
class AvailableDateManager (
        @Autowired val repository: AvailableDateRepository,
        @Autowired val appointmentRepository: AppointmentRepository) {

    fun saveNewDates() {
        val appointments = appointmentRepository.findAll()
        repository.saveAll(generateDates(appointments as MutableList<Appointment>))
    }

    fun getAllAvailableDates(): MutableIterable<AvailableDate> {
        val appointments = appointmentRepository.findAll()
        repository.deleteAll()
        return repository.saveAll(generateDates(appointments as MutableList<Appointment>))
    }
}