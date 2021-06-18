package me.iamjoseph.raccoonsample.includetest.helpers

import me.iamjoseph.raccoon.annotations.ControllerModule
import me.iamjoseph.raccoon.controller.RaccoonController

@ControllerModule
class MockController2 : RaccoonController() {

    override fun setup() {

    }

    override fun tearDown() {

    }
}