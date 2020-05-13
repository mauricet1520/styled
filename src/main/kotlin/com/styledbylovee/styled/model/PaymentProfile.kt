package com.styledbylovee.styled.model

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import java.util.*

@Document
data class PaymentProfile(
        @Id
        var paymentProfileId: UUID,
        var email: String,
        var phone: String,
        var number: String,
        var exp: String,
        var cvc: String,
        var customerId: String? = null,
        var subscription: String? = null,
        var type: String
)