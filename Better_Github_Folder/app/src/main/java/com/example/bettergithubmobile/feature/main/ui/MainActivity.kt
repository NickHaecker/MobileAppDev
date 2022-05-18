package com.example.bettergithubmobile.feature.main.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Surface
import androidx.activity.ComponentActivity
import com.example.bettergithubmobile.R
import com.example.bettergithubmobile.ui.theme.GithubTheme
import java.lang.reflect.Modifier

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_main)
        setContent{
            GithubTheme{
                Surface(modifier = Modifier.fillMaxSize(),color= MaterialTheme.colors.background){
                    Box(
                        contentAlignment = Alignment.Center,
                        modifier = Modifier.fillMaxWidth().fillMaxHeight(),
                    ){
                        LoginScreen()
                    }
                }
            }
        }
    }
}