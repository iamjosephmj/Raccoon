package me.iamjoseph.raccoon.controller

/**
 * This is the base of all mock controller classes.
 *
 * @author Joseph
 */
abstract class RaccoonController {

    /**
     * This method is useful to initialize the controller invocation.
     */
    abstract fun setup()

    /**
     * Clean up function.
     */
    abstract fun tearDown()

}