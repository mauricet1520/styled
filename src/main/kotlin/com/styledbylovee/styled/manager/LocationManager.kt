package com.styledbylovee.styled.manager

import com.styledbylovee.styled.model.Location
import com.styledbylovee.styled.repository.ActiveZipCodeRepository
import com.styledbylovee.styled.repository.LocationRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import java.util.*

@Component
class LocationManager(@Autowired val locationRepository: LocationRepository,
                      @Autowired val zipCodeRepository: ActiveZipCodeRepository) {

    fun addLocation(location: Location): Boolean {
        try {
            val zip = zipCodeRepository.findByZipCode(location.zip)
            zip.let {
                location.locationId = UUID.randomUUID()
                location.active = true
                locationRepository.save(location)
                return location.active
            }
        }catch (e: Throwable) {
            location.locationId = UUID.randomUUID()
            location.active = false
            locationRepository.save(location)
            return location.active
        }
    }

    fun findAll(): MutableIterable<Location> {
        return locationRepository.findAll()
    }
}