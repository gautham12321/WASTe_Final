package com.example.waste.ui.theme.Screenas

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.waste.R
import com.example.waste.data.product
import com.example.waste.models.mainViewmodel
import com.example.waste.ui.theme.Typography
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.flow.MutableStateFlow
import java.util.Locale


@SuppressLint("StateFlowValueCalledInComposition")

@Composable
fun UserMain(
    modifier: Modifier = Modifier,

    gotenterDetails: () -> Unit={},
    user: MutableStateFlow<FirebaseUser?>,
    list: MutableList<product>,
    goHome:()->Unit
){
    var name:String="Gautham"



    Surface(color =  Color(0xFF6CBD7E), modifier = Modifier.fillMaxSize())
    {
        Box {
            Column {
                Image(painter = painterResource(id = R.drawable.leaves), contentDescription = null)

                Image(painter = painterResource(id = R.drawable.leaves_2), contentDescription = null)
            }
            if (user.value != null) {
                val endIndex: Int = user.value?.email.toString().indexOf('@')

                name = user.value?.email.toString().substring(0, endIndex)


            }
            /*IconButton(onClick = goHome) {
            Icon(imageVector = Icons.Default.Home, contentDescription ="GO HOME" )
            
        }*/

            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Top
            ) {
                Column(modifier = Modifier.padding(15.dp), horizontalAlignment = Alignment.Start) {
                    Text(text = "WELCOME", style = Typography.displayMedium)
                    Text(
                        text = name.uppercase(Locale.getDefault()),
                        style = Typography.displayLarge
                    )
                }
                MainPageCard(gotoenterdetails = gotenterDetails, list = list)

            }


        }
    }
}

@Composable
fun MainPageCard(
    modifier: Modifier = Modifier,
    gotoenterdetails: () -> Unit,
    list: MutableList<product>
) {

    var viewmodel:mainViewmodel= viewModel()
    Column (horizontalAlignment = Alignment.End){
        Button(onClick = { gotoenterdetails() }) {

            Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.padding(5.dp)) {
                Icon(imageVector = Icons.Default.Add, null)
                Text(text = "ADD DEVICES")
            }
        }
        Spacer(modifier = Modifier.height(5.dp))


        Card(
            modifier = Modifier
                .padding(5.dp)
                .width(328.dp),
            colors = CardDefaults.cardColors(containerColor = Color(0xFFA0F0B1)),


            ) {

            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
                modifier = Modifier.fillMaxWidth()
            ) {

                LazyColumn() {
                    items(list) {
                        ProductCard(
                            it

                        )


                    }


                }


            }


        }
    }


}
@Preview
@Composable
fun ProductCard(
   product: product= product(type = "Phone")


){

    Card (modifier = Modifier
        .fillMaxWidth()
        .padding(15.dp)){
        Row(verticalAlignment = Alignment.CenterVertically) {
            Image(painter =painterResource(id =
                when(product.type){

            "Phone"-> R.drawable.mobile
                "TV"->R.drawable.tv

                    else -> {R.drawable.klipartz_com}
                }
            ) , contentDescription =null, modifier = Modifier.size(70.dp) )
            Column() {
                Text(text = product.name, fontSize = 30.sp)

                Text(text = product.price.toString(), fontSize = 25.sp, color = Color(0xFF0C920E))

                Text(text =product.Status.toString(), fontSize = 20.sp )

            }
        }
    }
}
