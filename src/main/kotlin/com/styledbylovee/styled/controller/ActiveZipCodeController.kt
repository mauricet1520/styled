package com.styledbylovee.styled.controller


import com.styledbylovee.styled.manager.ActiveZipCodeManager
import com.styledbylovee.styled.model.ActiveZipCode
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/v1")
class ActiveZipCodeController(@Autowired val activeZipCodeManager: ActiveZipCodeManager) {

    @PostMapping(path = ["/addActiveZipCodes"])
    fun addActiveZipCodes(@RequestBody activeZipCode: ActiveZipCode) {
        activeZipCodeManager.addActiveZipCode(activeZipCode)
    }

    @PostMapping(path = ["/addActiveZipCodeList"])
    fun addActiveZipCodeList(@RequestBody zipCodes: List<Int>){
        activeZipCodeManager.addActiveZipCodeList(zipCodes)
    }

    @GetMapping(path = ["/getZipCodes"])
    fun getZipCodes(): MutableIterable<ActiveZipCode> {
        val zipCodes = activeZipCodeManager.getZipCodes()
        return zipCodes
    }


}