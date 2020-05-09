package com.styledbylovee.styled.model

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.index.Indexed
import org.springframework.data.mongodb.core.mapping.Document
import java.time.DayOfWeek
import java.time.LocalDateTime

@Document
data class AvailableDate(
        @Id
        val id: String,
        var timeSlot: MutableList<String>,

        @Indexed
        val dateTime: String,
        val dayOfWeek: String
)

