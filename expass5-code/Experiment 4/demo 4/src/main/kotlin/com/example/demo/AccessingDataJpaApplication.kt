package com.example.demo

import java.util.Optional
import java.util.function.Consumer
import org.slf4j.LoggerFactory
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.context.annotation.Bean


@SpringBootApplication
class AccessingDataJpaApplication {
  @Bean
  fun demo(repository: CustomerRepository): CommandLineRunner {
    return CommandLineRunner { args: Array<String?>? ->
      // save a few customers
      repository.save(Customer("Jack", "Bauer"))
      repository.save(Customer("Chloe", "O'Brian"))
      repository.save(Customer("Kim", "Bauer"))
      repository.save(Customer("David", "Palmer"))
      repository.save(Customer("Michelle", "Dessler"))

      // fetch all customers
      log.info("Customers found with findAll():")
      log.info("-------------------------------")
      for (customer in repository.findAll()) {
        log.info(customer.toString())
      }
      log.info("")

      // fetch an individual customer by ID
      val customer: Optional<Customer?> = repository.findById(1L)
      log.info("Customer found with findById(1L):")
      log.info("--------------------------------")
      log.info(customer.toString())
      log.info("")

      // fetch customers by last name
      log.info("Customer found with findByLastName('Bauer'):")
      log.info("--------------------------------------------")
      repository.findByLastName("Bauer")!!.forEach(Consumer { bauer: Customer ->
        log.info(
          bauer.toString()
        )
      } as (Customer?) -> Unit)
      // for (Customer bauer : repository.findByLastName("Bauer")) {
      //  log.info(bauer.toString());
      // }
      log.info("")
    }
  }

  companion object {
    private val log = LoggerFactory.getLogger(AccessingDataJpaApplication::class.java)
    @JvmStatic
    fun main(args: Array<String>) {
      SpringApplication.run(AccessingDataJpaApplication::class.java)
    }
  }
}