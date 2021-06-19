package me.iamjoseph.raccoon.helper

import me.iamjoseph.raccoon.annotations.RaccoonController
import me.iamjoseph.raccoon.annotations.RaccoonService
import me.iamjoseph.raccoon.service.RaccoonServiceImpl

@RaccoonService
class GsonServiceInclude : RaccoonServiceImpl() {

    @RaccoonController
    fun providesMoshiMockController() = GsonControllerInclude::class

}