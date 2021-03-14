package com.doordash.interview.phonenumberparser.service

import com.doordash.interview.phonenumberparser.model.Record
import com.doordash.interview.phonenumberparser.repository.RecordRepository
import org.springframework.data.domain.Example

import org.springframework.data.domain.ExampleMatcher
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import java.util.*

@Service
class PhoneService (private val recordRepository: RecordRepository){

    fun findById(recordId: Long): ResponseEntity<Record> =
            recordRepository.findById(recordId).map { record ->
                /*record.apply {  phoneNumber = phoneNumber.replace("-","") }*/
                ResponseEntity.ok(record)
            }.orElse(ResponseEntity.notFound().build())


    fun upsertRecord(record: Record): ResponseEntity<Record> {

        val matcher = ExampleMatcher.matching()
                .withMatcher("phoneNumber", ExampleMatcher.GenericPropertyMatcher().exact())
                .withMatcher("phoneType", ExampleMatcher.GenericPropertyMatcher().exact())
                .withIgnorePaths("id", "numOfOccurences")

        val sample = Record(
                id = 1,
                phoneNumber = record.phoneNumber,
                phoneType = record.phoneType,
                numOfOccurences = 0
        )



        val result : ResponseEntity<Record>

        val existing: Optional<Record> = recordRepository.findOne(Example.of(sample, matcher))
        if (existing.isPresent) {
            result=updateRecord(existing.get().id)
        } else {
            result=addRecord(record)
        }
        return result
    }

    fun addRecord(record: Record): ResponseEntity<Record> {
        val saved = recordRepository.save(record.apply { numOfOccurences =1 })
                .apply {
                    phoneNumber = phoneNumber.replace("-","")
                }
        return ResponseEntity.ok(saved)
    }


    fun updateRecord(recordId: Long): ResponseEntity<Record> =
            recordRepository.findById(recordId).map { currentRecord -> currentRecord.apply{
                numOfOccurences +=1
               /* phoneNumber = phoneNumber.replace("-","")*/
            }
                ResponseEntity.ok().body(recordRepository.save(currentRecord))
            }.orElse(ResponseEntity.notFound().build())

}