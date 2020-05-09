package com.styledbylovee.styled.config

import com.styledbylovee.styled.model.Appointment
import com.styledbylovee.styled.repository.AppointmentRepository
import com.styledbylovee.styled.repository.AvailableDateRepository
import com.styledbylovee.styled.util.generateDates
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Configuration
import org.springframework.scheduling.annotation.EnableScheduling
import org.springframework.scheduling.annotation.Scheduled

@Configuration
@EnableScheduling
class SpringConfig(@Autowired var repositoryAppointment: AppointmentRepository,
                   @Autowired var availableDateRepository: AvailableDateRepository) {

    @Scheduled(cron = "0 25 * * * ?")
    fun scheduleDateGenerator() {
        availableDateRepository.deleteAll()
        availableDateRepository.saveAll(generateDates(repositoryAppointment.findAll() as MutableList<Appointment>))
    }
}