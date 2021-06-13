package io.iamjosephmj.raccoon

import android.os.Bundle
import android.widget.TextView
import androidx.activity.ComponentActivity
import androidx.lifecycle.lifecycleScope
import io.iamjosephmj.raccoon.core.stub.RaccoonStub
import io.iamjosephmj.raccoon.core.stub.config.RaccoonConfig
import io.iamjosephmj.raccoon.retrofit.MockService
import io.iamjosephmj.raccoon.retrofit.ToDoApiClass
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    @DelicateCoroutinesApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        RaccoonStub.init(
            RaccoonConfig(
                serviceClasses = listOf(
                    MockService::class
                )
            )
        )

        lifecycleScope.launch {
            val apiClass = ToDoApiClass()
            findViewById<TextView>(R.id.text).text = apiClass.getPage(1).toString()
        }


    }
}