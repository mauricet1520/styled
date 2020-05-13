package com.styledbylovee.styled.controller

import com.styledbylovee.styled.manager.PaymentProfileManager
import com.styledbylovee.styled.model.PaymentProfile
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping("/payment")
class StripeController(@Autowired val profileManager: PaymentProfileManager) {


    @PostMapping
    fun processPayment(@RequestBody paymentProfile: PaymentProfile) {
        profileManager.addPaymentProfile(paymentProfile)
    }

    @GetMapping
    fun getAllPayments(): MutableIterable<PaymentProfile> {
      return profileManager.getAllPayments()
    }
}