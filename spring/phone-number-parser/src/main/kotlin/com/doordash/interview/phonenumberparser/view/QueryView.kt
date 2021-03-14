package com.doordash.interview.phonenumberparser.view

data class QueryView (
    val id : Long,
    var phone_number : String,
    val phone_type : String,
    val occurrences: Int
)