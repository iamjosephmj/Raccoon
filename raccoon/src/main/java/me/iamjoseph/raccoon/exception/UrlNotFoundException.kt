package me.iamjoseph.raccoon.exception

class UrlNotFoundException : RaccoonException() {
    override val message: String
        get() = "Cannot find the URL in the controller class"
}