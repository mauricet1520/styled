package com.styledbylovee.styled.manager

import com.stripe.Stripe
import com.stripe.model.*
import com.styledbylovee.styled.model.PaymentProfile
import com.styledbylovee.styled.repository.ProfilePaymentRepository
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import java.util.*


@Component
class PaymentProfileManager(@Autowired val paymentRepository: ProfilePaymentRepository,
                            @Value(value = "\${stripeKey}") val stripeKey: String,
                            @Value(value = "\${subscriptionAmount}") val subscriptionAmount: Int,
                            @Value(value = "\${onDemandAmount}") val onDemandAmount: Int) {

    private var logger = LoggerFactory.getLogger(PaymentProfileManager::class.java)

    fun addPaymentProfile(paymentProfile: PaymentProfile) {
        logger.info("Profile: $paymentProfile")
        Stripe.apiKey = stripeKey
        if(paymentProfile.type == "subscription") {
            createSubscription(paymentProfile)
        } else {
            val custParams: MutableMap<String, Any> = HashMap()
            custParams["description"] = "Email: ${paymentProfile.email} Phone: ${paymentProfile.phone}"
            custParams["email"] = paymentProfile.email
            custParams["phone"] = paymentProfile.phone
            val customer = Customer.create(custParams)
            val params: MutableMap<String, Any> = HashMap()
            params["amount"] = onDemandAmount
            params["currency"] = "usd"
            params["source"] = createToken(paymentProfile).id
            params["description"] = "${paymentProfile.email} - ${paymentProfile.phone}"
            val charge = Charge.create(params)

            paymentProfile.customerId = customer.id
            paymentRepository.save(paymentProfile)
        }
    }

    private fun createToken(paymentProfile: PaymentProfile): Token {
        logger.info("In CreateToken")

        val card: MutableMap<String, Any> = HashMap()
        val expDate = paymentProfile.exp.split("/")
        card["number"] = paymentProfile.number
        card["exp_month"] = expDate[0].toInt()
        card["exp_year"] = expDate[1].toInt()
        card["cvc"] = paymentProfile.cvc
        val tokenParams: MutableMap<String, Any> = HashMap()
        tokenParams["card"] = card
        return Token.create(tokenParams)
    }

    fun getAllPayments(): MutableIterable<PaymentProfile> {
        return paymentRepository.findAll()
    }

    private fun createProduct(): Product {
        logger.info("createProduct")

        val params: MutableMap<String, Any> = HashMap()
        params["name"] = "Styled Subscription"
        params["type"] = "service"
        params["description"] = "Monthly Subscription"

      return Product.create(params)
    }

    private fun createPlan(): Plan {
        logger.info("createPlan")
        val params: MutableMap<String, Any> = HashMap()
        params["amount"] = subscriptionAmount
        params["currency"] = "usd"
        params["interval"] = "month"
        params["product"] = createProduct().id
     return Plan.create(params)
    }

    private fun createSubscription(paymentProfile: PaymentProfile): Subscription {
        logger.info("createSubscription")
        val items: MutableList<Any> = ArrayList()
        val item1: MutableMap<String, Any> = HashMap()
        item1["plan"] = createPlan().id
        items.add(item1)
        val params: MutableMap<String, Any> = HashMap()
        params["customer"] = createCustomer(paymentProfile).id
        params["items"] = items
        val subscription =  Subscription.create(params)
        paymentProfile.subscription = subscription.id
        paymentRepository.save(paymentProfile)
        return subscription
    }

    private fun createCustomer(paymentProfile: PaymentProfile): Customer {
        val params: MutableMap<String, Any> = HashMap()
        params["description"] = "Customer: ${paymentProfile.email}"
        params["email"] = paymentProfile.email
        params["phone"] = paymentProfile.phone
        params["source"] = createToken(paymentProfile).id
        return Customer.create(params)
    }
}