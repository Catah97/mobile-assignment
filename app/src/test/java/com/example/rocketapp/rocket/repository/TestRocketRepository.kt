package com.example.rocketapp.rocket.repository

import com.example.rocketapp.rocket.repository.model.Rocket
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.mock
import java.lang.RuntimeException
import java.util.*

class TestRocketRepository : SpaceXRocketTestRepository {

    private var exception: Throwable? = null
    private val rocketList = mutableListOf<Rocket>()

    private val rocketsData = MutableStateFlow<Result<List<Rocket>>?>(null)

    override fun getRocketData(): StateFlow<Result<List<Rocket>>?> {
        return rocketsData
    }

    override suspend fun loadRocketData(): Result<List<Rocket>> {
        val exception = exception
        val result = if (exception != null) {
            Result.failure<Nothing>(exception)
        } else {
            Result.success(rocketList)
        }
        rocketsData.value = result
        return result
    }

    override fun getRocketList(): List<Rocket> {
        return rocketList
    }

    override fun addRocket(rocket: Rocket): SpaceXRocketTestRepository {
        rocketList.add(rocket)
        return this
    }

    override fun addException(e: Throwable?): SpaceXRocketTestRepository {
        this.exception = e
        return this
    }

    override suspend fun clearCache() {
        TODO("Not yet implemented")
    }
}

class RocketLoadingFail() : RuntimeException()

fun createErrorRocketRepository(): SpaceXRocketTestRepository {
    return TestRocketRepository()
        .addException(
            RocketLoadingFail()
        )
}

fun createRocketRepository(): SpaceXRocketTestRepository {
    return TestRocketRepository()
        .addRocket(
            createRocket1()
        )
        .addRocket(
            createRocket2()
        )
}

private fun createRocket1(): Rocket {
    return mock {
        on { id } doReturn 1
        on { name } doReturn "Rocket Number one"
        on { firstFlight } doReturn Date()
    }
}

private fun createRocket2(): Rocket {
    return mock {
        on { id } doReturn 2
        on { name } doReturn "Rocket number two"
        on { description } doReturn "Some really really really awesome rocket"
        on { firstFlight } doReturn Date(0)
    }
}
