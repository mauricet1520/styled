package com.styledbylovee.styled.repository

import com.styledbylovee.styled.model.ActiveZipCode
import org.springframework.data.repository.CrudRepository

interface ActiveZipCodeRepository : CrudRepository<ActiveZipCode, String> {

    fun findByZipCode(zip: String): ActiveZipCode
}