package io.iamjosephmj.raccoon

import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import io.iamjosephmj.raccoon.core.stub.RaccoonStub
import io.iamjosephmj.raccoon.core.stub.config.RaccoonConfig
import io.iamjosephmj.raccoon.helper.MockService
import io.iamjosephmj.raccoon.parser.MoshiPlugin
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
class MainActivityTest {

    @Before
    fun setup() {
        RaccoonStub.setUp(
            RaccoonConfig.Builder()
                .setParserType(MoshiPlugin())
                .build()
        )

    }

    @get:Rule
    val rule = ActivityTestRule(
        MainActivity::class.java, false, false
    )

    @Test
    fun useAppContext() {
        rule.launchActivity(null)
        Thread.sleep(5000)
        // Context of the app under test.
        assert(true)
        rule.activity.finish()
    }

    @After
    fun tearDown() {
        RaccoonStub.teatDown()
    }

}