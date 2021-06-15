package io.iamjosephmj.raccoonsample

import android.os.Bundle
import android.widget.TextView
import androidx.activity.ComponentActivity
import androidx.lifecycle.lifecycleScope
import io.iamjosephmj.raccoonsample.retrofit.ToDoApiClass
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    @DelicateCoroutinesApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        lifecycleScope.launch {
            val apiClass = ToDoApiClass()
            findViewById<TextView>(R.id.text).text = apiClass.getPage(
                1
            ).toString()
        }


    }
}