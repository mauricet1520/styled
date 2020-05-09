package com.styledbylovee.styled.repository

import com.styledbylovee.styled.model.AvailableDate
import org.springframework.data.repository.CrudRepository
import java.util.*

interface AvailableDateRepository: CrudRepository<AvailableDate, String>