package com.example.demo

import java.util.Optional
import org.springframework.data.repository.CrudRepository


interface CustomerRepository : CrudRepository<Customer?, Long?> {
    fun findByLastName(lastName: String?): List<Customer?>?
    override fun findById(id: Long): Optional<Customer?>
}