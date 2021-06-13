package io.iamjosephmj.raccoon.exception

class EndpointNotFoundException : RaccoonException() {
    override val message: String
        get() = "Cannot find the endpoint definition in the Controller class"
}