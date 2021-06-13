package io.iamjosephmj.raccoon.exception

class ControllerNotFoundException : RaccoonException() {
    override val message: String
        get() = "Cannot find the Controller class."
}