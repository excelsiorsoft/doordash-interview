package com.doordash.interview.phonenumberparser.model

import com.doordash.interview.phonenumberparser.view.CommandView
import com.doordash.interview.phonenumberparser.view.QueryView
import javax.persistence.*
import javax.validation.constraints.NotBlank

@Entity
@Table(name = "RECORD", schema="interview")
data class Record(
        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        val id : Long,

        @Column(name="phone_number")
        @NotBlank(message = "phone number is mandatory")
        var phoneNumber : String,

        @Column(name="phone_type")
        @NotBlank(message = "phone type is mandatory")
        val phoneType : String,

        @Column(name="occurrences")
        var numOfOccurences : Int


)

fun Record.toCommandView() = CommandView(
        id = id,
        phone_number = phoneNumber.replace("-",""),
        phone_type = phoneType
)

fun Record.toQueryView() = QueryView(
        id = id,
        phone_number = phoneNumber,
        phone_type = phoneType,
        occurrences = numOfOccurences
)

