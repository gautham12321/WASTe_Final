package com.example.waste.ui.theme.Screenas

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SearchBar
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.waste.R
import com.example.waste.data.product
import com.example.waste.ui.theme.Typography

@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun RepairScreen(modifier: Modifier=Modifier,list:MutableList<product> = mutableListOf(product(type = "Phone"))){
    Surface(color =  Color(0xFF6CBD7E), modifier = Modifier.fillMaxSize())
    {
        Box {
            Column {
                Image(painter = painterResource(id = R.drawable.leaves), contentDescription = null)

                Image(painter = painterResource(id = R.drawable.leaves_2), contentDescription = null)
            }


            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Top
            ) {
                var query by remember {
                    mutableStateOf("")
                }
                Column(modifier = Modifier.padding(15.dp)) {
                    Text(text = "WELCOME", style = Typography.displayMedium)
                    Spacer(modifier = Modifier.height(15.dp))
                    Text(
                        text = "Search For your Model",
                        style = Typography.displayMedium,
                        lineHeight = 40.sp
                    )


                    SearchBar(
                        query = query,
                        onQueryChange = { query = it },
                        onSearch = {},
                        active = false,
                        onActiveChange = {},
                        placeholder = {
                            Text(text = "Enter the model name")
                        },


                        ) {

                    }
                }
                RepairCard(list = list, query)

            }


        }
    }

}

@Composable
fun RepairCard(list: MutableList<product>, query: String) {
    val newList:MutableList<product> = mutableListOf()
    for(i in list){

    if(i.name.contains(query)){
        newList.add(i)


    }

    }

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
                items(newList) {
                    ProductCard(
                        it

                    )


                }


            }


        }


    }




}
