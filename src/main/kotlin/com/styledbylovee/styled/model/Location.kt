package com.styledbylovee.styled.model

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import java.util.*

@Document
data class Location (
        @Id
        var locationId: UUID? = null,
        var firstName: String,
        var lastName: String,
        var address: String,
        var city: String,
        var state: String,
        var zip: String,
        var active: Boolean
)
