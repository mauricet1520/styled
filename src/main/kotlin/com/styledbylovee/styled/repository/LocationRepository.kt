package com.styledbylovee.styled.repository

import com.styledbylovee.styled.model.Location
import org.springframework.data.repository.CrudRepository
import java.util.*

interface LocationRepository: CrudRepository<Location, UUID> {
}