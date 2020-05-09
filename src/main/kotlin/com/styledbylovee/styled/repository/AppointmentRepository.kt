package com.styledbylovee.styled.repository

import com.styledbylovee.styled.model.Appointment
import org.springframework.data.repository.CrudRepository

interface AppointmentRepository: CrudRepository<Appointment, String>