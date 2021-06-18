package me.iamjoseph.raccoonsample.includetest.helpers

import me.iamjoseph.raccoon.annotations.ControllerModule
import me.iamjoseph.raccoon.controller.RaccoonController

@ControllerModule(includeControllers = [MockController2::class])
class MockController1 : RaccoonController() {
    override fun setup() {

    }

    override fun tearDown() {

    }
}