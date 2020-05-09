package com.styledbylovee.styled.model

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.index.Indexed
import org.springframework.data.mongodb.core.mapping.Document
import java.sql.Timestamp

@Document
data class Appointment(
        @Id
        val id: String,
        val date: String,
        @Indexed
        var time: String

//        val scheduled: String? = null,
//        val address: String? = null,
//        val budget: String? = null,
//        val clientEmail: String? = null,
//        val clientName: String? = null,
//        val colors: String? = null,
//        val event: String? = null,
//        val image_url: String? = null,
//        val style: String? = null,
)