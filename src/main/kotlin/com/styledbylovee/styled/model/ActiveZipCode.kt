package com.styledbylovee.styled.model

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.index.Indexed
import org.springframework.data.mongodb.core.mapping.Document

@Document
data class ActiveZipCode(
        @Id
        val id: String,
        @Indexed
        val zipCode: String,
        val market: String
)