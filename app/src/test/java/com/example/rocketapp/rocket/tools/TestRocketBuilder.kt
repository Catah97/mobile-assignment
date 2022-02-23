package com.example.rocketapp.rocket.tools

import com.example.rocketapp.rocket.repository.model.Rocket
import org.mockito.Mock
import org.mockito.Mockito.`when` as on
import org.mockito.Mockito.mock
import java.util.*


class TestRocketBuilder {

    private val rocket = mock(Rocket::class.java)

    fun setId(value: Int): TestRocketBuilder {
        on(rocket.id).thenReturn(value)
        return this
    }

    fun setName(value: String): TestRocketBuilder {
        on(rocket.name).thenReturn(value)
        return this
    }

    fun setDescription(value: String): TestRocketBuilder {
        on(rocket.name).thenReturn(value)
        return this
    }

    fun setFirthFlight(value: Date): TestRocketBuilder {
        on(rocket.firstFlight).thenReturn(value)
        return this
    }

    fun build(): Rocket {
        return rocket
    }
}
