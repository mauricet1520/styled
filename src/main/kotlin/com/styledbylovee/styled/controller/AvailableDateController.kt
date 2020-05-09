package com.styledbylovee.styled.controller

import com.styledbylovee.styled.manager.AvailableDateManager
import com.styledbylovee.styled.model.AvailableDate
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/v1")
class AvailableDateController(@Autowired val availableDateManager: AvailableDateManager) {

    @GetMapping(path = ["/getAllAvailableDates"])
    fun getAllAvailableDates(): MutableIterable<AvailableDate>{
       return availableDateManager.getAllAvailableDates()
    }
}