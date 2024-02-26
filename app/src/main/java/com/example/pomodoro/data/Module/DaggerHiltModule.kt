package com.example.pomodoro.data.Module

import com.example.pomodoro.domain.model.PomodoroTask
import com.example.pomodoro.domain.model.Timer
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.realm.kotlin.Realm
import io.realm.kotlin.RealmConfiguration
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DaggerHiltModule {
    @Provides
    @Singleton
    fun providesRealmDatabase(): Realm {
        val config = RealmConfiguration.create(schema = setOf(PomodoroTask::class, Timer::class))
        return Realm.open(config)

    }
}
