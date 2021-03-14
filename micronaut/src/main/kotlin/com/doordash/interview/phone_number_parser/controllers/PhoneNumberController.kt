package com.doordash.interview.phone_number_parser.controllers

import io.micronaut.http.HttpResponse
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get
import io.micronaut.http.annotation.Post

@Controller("/phone-numbers")
class PhoneNumberController {
    @Post("/")
    fun postPhoneNumber(): HttpResponse<*> {
        TODO("implement me")
    }

    @Get("/{id}")
    fun getPhoneNumber(id: String) {
        println(id)
        TODO("implement me")
    }
}