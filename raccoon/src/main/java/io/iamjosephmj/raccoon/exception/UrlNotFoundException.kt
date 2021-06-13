package io.iamjosephmj.raccoon.exception

class UrlNotFoundException : RaccoonException() {
    override val message: String
        get() = "Cannot find the URL in Service classes"
}