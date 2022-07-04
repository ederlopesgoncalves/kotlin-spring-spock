package com.github.ederlopesgoncalves.application.rest

import com.github.ederlopesgoncalves.infrastructure.repository.UserRepository
import spock.lang.Specification

/**
 * A Spock Spring MVC Rest unit test that doesn't require a full spring context
 */
class UserControllerSpec extends Specification {

    def controller = new UserController()

    def repository = Mock(UserRepository)

    void setup() {
        controller.userRepository = repository
    }

    void cleanup() {
    }

    def "Preload"() {
    }

    def "GetAll"() {
    }

    def "Get"() {
    }

    def "GetUserRepository"() {
    }
}
