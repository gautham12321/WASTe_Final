package com.example.waste

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.waste.data.AppDatabase
import com.example.waste.data.product
import com.example.waste.models.mainViewmodel
import com.example.waste.ui.theme.Screenas.EnterDetails
import com.example.waste.ui.theme.Screenas.LogInPage
import com.example.waste.ui.theme.Screenas.Preferences
import com.example.waste.ui.theme.Screenas.RepairScreen
import com.example.waste.ui.theme.Screenas.SignIn
import com.example.waste.ui.theme.Screenas.SplashScreen
import com.example.waste.ui.theme.Screenas.UserMain
import com.example.waste.ui.theme.WASTeTheme
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
var userdb:AppDatabase?=null
class MainActivity : ComponentActivity() {
    lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {

        auth = Firebase.auth
        GlobalScope.launch {
           userdb =  AppDatabase.getDatabase(applicationContext)
        }
        super.onCreate(savedInstanceState)
        setContent {
            WASTeTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    WasteApp()
                }
            }
        }
    }


    @Preview(showBackground = true)
    @Composable
    fun GreetingPreview() {
        WASTeTheme {
            WasteApp()
        }
    }

    enum class Destinations {
        SIGNUP, LOGIN, PREFERENCE,
        USERMODE,
        DEVICEDETAILS,
        REPAIRMODE,
        STARTUP

    }

    @Composable
    fun WasteApp(modifier: Modifier = Modifier) {
        val viewmodel:mainViewmodel=viewModel()
        val navController = rememberNavController()
        NavHost(navController = navController, startDestination = Destinations.STARTUP.name) {
            val currentUser = auth.currentUser
            var userList:MutableList<product> = mutableListOf()
            composable(Destinations.STARTUP.name){


                SplashScreen(navController = navController)
            }
            composable(Destinations.REPAIRMODE.name){
                RepairScreen(list = userList)
            }

            composable(Destinations.LOGIN.name) {


                LogInPage (auth=auth,gotoSignin={ navController.navigate(Destinations.SIGNUP.name)},
                    gotToNext = {navController.navigate(Destinations.PREFERENCE.name)}
                )
            }
            composable(Destinations.SIGNUP.name) {
                SignIn(auth=auth,
                    gotologin = { navController.navigate(Destinations.LOGIN.name) },
                    onSubmit = { navController.navigate(Destinations.PREFERENCE.name) })

            }
            composable(Destinations.PREFERENCE.name) {
                Preferences(
                    onSellProduct = { navController.navigate(Destinations.USERMODE.name) },
                    onRepairProduct = { navController.navigate(Destinations.REPAIRMODE.name) })

            }
            composable(Destinations.USERMODE.name) {
                UserMain(goHome={navController.popBackStack(Destinations.PREFERENCE.name,false)},list=userList,user=viewmodel.user,gotenterDetails = { navController.navigate(Destinations.DEVICEDETAILS.name) })

            }
            composable(Destinations.DEVICEDETAILS.name) {
                EnterDetails (
                    onsubmit={navController.popBackStack()},
                    addDevice = {userList.add(it)}

                        )


            }


        }
    }
}
