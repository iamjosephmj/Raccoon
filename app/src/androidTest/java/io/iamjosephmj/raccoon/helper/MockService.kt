package io.iamjosephmj.raccoon.helper

import io.iamjosephmj.raccoon.annotations.RaccoonController
import io.iamjosephmj.raccoon.annotations.RaccoonService
import io.iamjosephmj.raccoon.service.RaccoonServiceImpl

@RaccoonService
class MockService : RaccoonServiceImpl() {

    @RaccoonController
    fun providesMockController() = MockController::class
}