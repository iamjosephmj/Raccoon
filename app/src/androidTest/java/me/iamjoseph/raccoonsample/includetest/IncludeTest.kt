package me.iamjoseph.raccoonsample.includetest

import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import me.iamjoseph.raccoon.core.stub.RaccoonStub
import me.iamjoseph.raccoon.core.stub.config.RaccoonConfig
import me.iamjoseph.raccoonsample.MainActivity
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class IncludeTest {


    @get:Rule
    val rule = ActivityTestRule(
        MainActivity::class.java, false, false
    )

    @Before
    fun before() {
        RaccoonStub.setUp(
            RaccoonConfig.Builder()
                .addService(me.iamjoseph.raccoonsample.includetest.helpers.MockService::class)
                .build()
        )
    }


    @Test
    fun testInclude() {
        rule.launchActivity(null)
        Thread.sleep(5000)
        // Context of the app under test.
        assert(true)
        rule.activity.finish()
    }


    @After
    fun after() {

    }
}