package com.doordash.interview.phonenumberparser.repository

import com.doordash.interview.phonenumberparser.model.Record
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import javax.transaction.Transactional


@Repository
@Transactional(Transactional.TxType.MANDATORY)
interface RecordRepository: JpaRepository<Record,Long>