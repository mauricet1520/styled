package com.styledbylovee.styled.controller


import com.styledbylovee.styled.model.Appointment
import com.styledbylovee.styled.model.AvailableDate
import com.styledbylovee.styled.repository.AppointmentRepository
import com.styledbylovee.styled.repository.AvailableDateRepository
import com.styledbylovee.styled.util.generateDates
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/v1")
class AppointmentController(@Autowired val appointmentRepository: AppointmentRepository,
                            @Autowired val availableDateRepository: AvailableDateRepository) {

    @GetMapping(path = ["/getAllAppointments"])
    fun getAllAppointments(): MutableIterable<Appointment> {
        return appointmentRepository.findAll()
    }

    @GetMapping(path = ["/generateDatesWithNoAppointments"])
    fun generateDatesWithNoAppointments(): MutableList<AvailableDate> {
        val dates = generateDates(appointmentRepository.findAll() as MutableList<Appointment>)
        availableDateRepository.saveAll(dates)
        return dates
    }



}