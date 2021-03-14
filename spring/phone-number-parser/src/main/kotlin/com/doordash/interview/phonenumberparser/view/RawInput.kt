package com.doordash.interview.phonenumberparser.view

import com.doordash.interview.phonenumberparser.model.Record
import javax.validation.constraints.NotBlank

data class RawInput (
        @NotBlank(message = "raw input is mandatory for further processing")
        val raw_phone_numbers: String
)

fun RawInput.toRecords(): List<Record> {
    /*println("raw input: $raw_phone_numbers")

    val splitInput: List<String> = raw_phone_numbers.split("(")
    println("after split: $splitInput")

    val filteredSplit = splitInput.filter { it.length>0 }
    println("filteredSplit:  $filteredSplit")

    val mapped = filteredSplit.associate {
        val (key, value) = it.split(")")
        key.trim().toLowerCase() to value.trim()
    }
    println("mapped: $mapped")

    val listOfRecords = mapped.map({ (k, v) ->Record(0,v,k,0)})
    println("list of records: $listOfRecords")*/

    //return listOf(Record (1, "415-415-4159", "home",0))
    //return emptyList()
    return raw_phone_numbers
            .split("(")
            .filter { it.length>0 }
            .associate {
                val (key, value) = it.split(")")
                key.trim().toLowerCase() to value.trim()
            }
            .map({ (k, v) ->Record(0,v,k,0)})
}