package com.github.ederlopesgoncalves.infrastructure.repository

import com.github.ederlopesgoncalves.application.response.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import reactor.core.publisher.Mono
import reactor.core.scheduler.Schedulers

@Repository
interface UserRepository : JpaRepository<User, Long> {

}

fun UserRepository.rxSave(user: User): Mono<User> {
    return Mono.fromCallable { save(user) }
            .subscribeOn(Schedulers.elastic())
}

fun UserRepository.rxFind(id: Long): Mono<User> {
    return Mono.fromCallable { findById(id).orElse(null) }
            .subscribeOn(Schedulers.elastic())
}
