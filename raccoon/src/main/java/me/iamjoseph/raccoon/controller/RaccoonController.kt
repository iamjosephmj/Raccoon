package me.iamjoseph.raccoon.controller

import me.iamjoseph.raccoon.core.stub.RaccoonStub

/**
 * This is the base of all mock controller classes.
 *
 * @author Joseph
 */
abstract class RaccoonController {

    lateinit var raccoonStub: RaccoonStub

    /**
     * This method is useful to initialize the controller invocation.
     */
    abstract fun setup()

    /**
     * Clean up function.
     */
    abstract fun tearDown()

}