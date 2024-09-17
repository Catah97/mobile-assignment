package com.example.rocketapp.rocket.repository

import com.example.rocketapp.rocket.repository.model.Rocket

interface SpaceXRocketTestRepository : SpaceXRocketRepository {
    fun getRocketList(): List<Rocket>
    fun addRocket(rocket: Rocket): SpaceXRocketTestRepository
    fun addException(e: Throwable?): SpaceXRocketTestRepository
}
