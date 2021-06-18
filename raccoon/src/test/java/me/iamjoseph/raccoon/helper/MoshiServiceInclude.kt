package me.iamjoseph.raccoon.helper

import me.iamjoseph.raccoon.annotations.RaccoonController
import me.iamjoseph.raccoon.annotations.RaccoonService
import me.iamjoseph.raccoon.service.RaccoonServiceImpl

@RaccoonService
class MoshiServiceInclude : RaccoonServiceImpl() {

    @RaccoonController
    fun providesGsonMockController() = MoshiControllerInclude::class

}