package io.iamjosephmj.raccoonsample.helper

import io.iamjosephmj.raccoon.annotations.RaccoonController
import io.iamjosephmj.raccoon.annotations.RaccoonService
import io.iamjosephmj.raccoon.service.RaccoonServiceImpl
import io.iamjosephmj.raccoonsample.helper.MockController

@RaccoonService
class MockService : RaccoonServiceImpl() {

    @RaccoonController
    fun providesMockController() = MockController::class
}