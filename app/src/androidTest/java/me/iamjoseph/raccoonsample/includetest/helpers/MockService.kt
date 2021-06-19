package me.iamjoseph.raccoonsample.includetest.helpers

import me.iamjoseph.raccoon.annotations.RaccoonController
import me.iamjoseph.raccoon.annotations.RaccoonService
import me.iamjoseph.raccoon.service.RaccoonServiceImpl

@RaccoonService
class MockService : RaccoonServiceImpl() {

    @RaccoonController
    fun providesMockController() = MockController1::class
}