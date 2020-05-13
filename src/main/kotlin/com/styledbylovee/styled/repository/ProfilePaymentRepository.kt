package com.styledbylovee.styled.repository

import com.styledbylovee.styled.model.PaymentProfile
import org.springframework.data.repository.CrudRepository

interface ProfilePaymentRepository: CrudRepository<PaymentProfile, String>