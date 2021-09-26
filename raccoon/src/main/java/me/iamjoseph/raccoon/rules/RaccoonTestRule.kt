package me.iamjoseph.raccoon.rules

import me.iamjoseph.raccoon.core.stub.RaccoonStub
import org.junit.rules.TestRule
import org.junit.runner.Description
import org.junit.runners.model.Statement

class RaccoonTestRule(
    val setup: () -> RaccoonStub
) : TestRule {
    override fun apply(base: Statement?, description: Description?): Statement {
        return object : Statement() {
            override fun evaluate() {

                try {
                    val stub = setup.invoke()
                    base?.evaluate()
                    stub.tearDown()
                } catch (ex: Exception) {
                    ex.printStackTrace()
                }
            }

        }
    }
}