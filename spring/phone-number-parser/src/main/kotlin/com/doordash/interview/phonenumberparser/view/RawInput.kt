package com.doordash.interview.phonenumberparser.view

import com.doordash.interview.phonenumberparser.model.Record
import javax.validation.constraints.NotBlank

data class RawInput (
        @NotBlank(message = "raw input is mandatory for further processing")
        val raw_phone_numbers: String
)

fun RawInput.toRecords(): List<Record> {
    return listOf(Record (1, "415-415-4159", "home",0))
    //return emptyList()
}