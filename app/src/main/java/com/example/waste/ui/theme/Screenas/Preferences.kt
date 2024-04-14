package com.example.waste.ui.theme.Screenas

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
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ShapeDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.waste.R
import com.example.waste.ui.theme.Typography


@Composable
fun Preferences(modifier: Modifier=Modifier,onSellProduct:()->Unit,onRepairProduct:()->Unit){

    Surface(color =  Color(0xFF6CBD7E), modifier = Modifier.fillMaxSize())
    { Box {
        Column {
            Image(painter = painterResource(id = R.drawable.leaves), contentDescription = null)

            Image(painter = painterResource(id = R.drawable.leaves_2), contentDescription = null)
        }

        Row(
            modifier = Modifier.fillMaxSize(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            PreferencesCard(onSellProduct, onRepairProduct)

        }
    }




    }




}


@Composable
fun PreferencesCard(onSellProduct: () -> Unit, onRepairProduct: () -> Unit) {

    Card(
        modifier = Modifier
            .padding(5.dp)
            .width(350.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFFA0F0B1)),


        ) {
        Column (horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxWidth()){
            Text(text = "PREFERENCE", style = Typography.displayMedium,modifier=Modifier.padding(16.dp))

            Spacer(modifier = Modifier.height(30.dp))
            ElevatedButton(onClick = onSellProduct, shape = ShapeDefaults.Medium, modifier = Modifier.padding(16.dp).fillMaxWidth().height(50.dp)) {
                Text(text = "SELL PRODUCT")


            }
            ElevatedButton(onClick = onRepairProduct, shape = ShapeDefaults.Medium, modifier = Modifier.padding(16.dp).fillMaxWidth().height(50.dp)) {
                Text(text = "REPAIR PRODUCT")

            }
            Spacer(modifier = Modifier.height(30.dp ))




        }


    }

}


