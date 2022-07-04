package com.github.ederlopesgoncalves.application.rest

import com.github.ederlopesgoncalves.application.response.User
import com.github.ederlopesgoncalves.infrastructure.repository.UserRepository
import com.github.ederlopesgoncalves.infrastructure.repository.rxFind
import com.github.ederlopesgoncalves.infrastructure.repository.rxSave
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Mono
import javax.annotation.PostConstruct

@RestController
@RequestMapping("/api/users")
class UserController(val userRepository: UserRepository) {

    companion object {
        val logger: Logger = LoggerFactory.getLogger(UserController::class.java)
    }

    @PostConstruct
    fun preload() {
        userRepository.rxSave(User(username = "user"))
                .subscribe { logger.info("{}", it) }
        userRepository.rxSave(User(username = "user"))
                .subscribe { logger.info("{}", it) }
    }

    @GetMapping
    fun getAll(): List<User> {
        return userRepository.findAll()
    }

    @GetMapping("/{id}")
    fun get(@PathVariable("id") id: Long): Mono<User> {
        return userRepository.rxFind(id)
    }
}
