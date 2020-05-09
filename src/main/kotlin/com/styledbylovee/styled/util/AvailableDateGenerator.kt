package com.styledbylovee.styled.util


import com.styledbylovee.styled.model.Appointment
import com.styledbylovee.styled.model.AvailableDate
import org.slf4j.LoggerFactory
import java.time.LocalDate
import java.time.Period
import java.time.format.DateTimeFormatter

val logger = LoggerFactory.getLogger("AvailableDateGenerator")

fun generateDates(appointments: MutableList<Appointment>): MutableList<AvailableDate> {


    logger.info("Generating Dates")
    val availableDatesList = mutableListOf<AvailableDate>()
    val currentDatePlus3Days  = LocalDate.now().plusDays(3)
    for (x in 0 until 60) {
        val period = Period.of(0, 0, x)
        val modifiedDate =  currentDatePlus3Days.plus(period)
        if(modifiedDate.dayOfWeek.toString() != "SUNDAY") {
            availableDatesList.add(dateDecider((modifiedDate)))
        }
    }
    return deleteAppointmentDates(availableDatesList, appointments)

}

fun dateDecider(date: LocalDate): AvailableDate {
    val formatter = DateTimeFormatter.ofPattern("M/d/yyyy")
    logger.info("Deleting Date: ${formatter.format(date)}")
    if (date.dayOfWeek.toString() == "SATURDAY") {
        return AvailableDate( formatter.format(date), arrayListOf("10:00am","1:00pm", "4:00pm", "7:00pm"), formatter.format(date), date.dayOfWeek.toString())
    }
    return AvailableDate(formatter.format(date), arrayListOf("7:00pm"), formatter.format(date), date.dayOfWeek.toString())
}

fun deleteAppointmentDates(availableDateList: MutableList<AvailableDate>, appointments: MutableList<Appointment>): MutableList<AvailableDate> {
    logger.info("Deleting Dates")

    val availableList = mutableListOf<AvailableDate>()
    for (availableDate in availableDateList) {
        for(appointment in appointments) {
            if(appointment.date == availableDate.dateTime) {
                val timeSlots = mutableListOf<String>()
                for (time in availableDate.timeSlot) {
                    if (appointment.time != time) {
                        timeSlots.add(time)
                    }
                }
                availableDate.timeSlot = timeSlots
                logger.info("Available DateTime: ${availableDate.dateTime}")
            }
        }
        availableList.add(availableDate)
    }

    return availableList
}