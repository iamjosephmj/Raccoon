package io.iamjosephmj.raccoon.retrofit

import io.iamjosephmj.raccoon.service.RaccoonServiceImpl

@io.iamjosephmj.raccoon.annotations.RaccoonService
class MockService : RaccoonServiceImpl() {

    @io.iamjosephmj.raccoon.annotations.RaccoonController
    fun providesMockController() = MockController::class
}