package com.styledbylovee.styled.controller

import com.styledbylovee.styled.manager.LocationManager
import com.styledbylovee.styled.model.Location
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class LocationController (@Autowired val locationManager: LocationManager){

    @PostMapping(path = ["/location"])
    fun addLocation(@RequestBody location: Location): ResponseEntity<Any> {
        return ResponseEntity(locationManager.addLocation(location), HttpStatus.OK)
    }

    @GetMapping(path = ["/findAllLocations"])
    fun findAllLocations(): MutableIterable<Location> {
        return locationManager.findAll()
    }

}