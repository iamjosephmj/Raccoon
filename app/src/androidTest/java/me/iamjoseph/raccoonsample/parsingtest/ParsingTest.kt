package me.iamjoseph.raccoonsample.parsingtest

import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import me.iamjoseph.raccoon.core.stub.RaccoonStub
import me.iamjoseph.raccoon.core.stub.config.RaccoonConfig
import me.iamjoseph.raccoon.rules.RaccoonTestRule
import me.iamjoseph.raccoonsample.MainActivity
import me.iamjoseph.raccoonsample.parsingtest.helper.MockService
import org.junit.Rule
import org.junit.Test
import org.junit.rules.RuleChain
import org.junit.runner.RunWith


/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class ParsingTest {


    private val raccoonTestRule = RaccoonTestRule {
        RaccoonStub.setUp(
            RaccoonConfig.Builder()
                .addService(MockService::class)
                .build()
        )

    }

    private val activityTestRule = ActivityTestRule(MainActivity::class.java)

    @get:Rule
    val chain: RuleChain = RuleChain
        .outerRule(raccoonTestRule)
        .around(activityTestRule)


    @Test
    fun useAppContext() {
        Thread.sleep(5000)
        // Context of the app under test.
        assert(true)
    }

}