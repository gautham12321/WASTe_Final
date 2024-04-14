package com.example.waste.ui.theme.Screenas

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.waste.R
import com.example.waste.data.STATUS
import com.example.waste.data.UserData
import com.example.waste.data.product
import com.example.waste.models.mainViewmodel
import com.example.waste.ui.theme.Typography
import kotlin.random.Random


@Composable
fun EnterDetails(modifier: Modifier = Modifier, onsubmit: () -> Unit,addDevice:(product)->Unit){
    var deviceType by remember {
        mutableStateOf("")
    }
    var viewmodel :mainViewmodel= viewModel()
    var selectedText by remember { mutableStateOf("Select Type") }

    Surface(color =  Color(0xFF6CBD7E), modifier = Modifier.fillMaxSize())
    {
        Box {
            Column {
                Image(painter = painterResource(id = R.drawable.leaves), contentDescription = null)

                Image(painter = painterResource(id = R.drawable.leaves_2), contentDescription = null)
            }
            Column {
                Column(horizontalAlignment = Alignment.Start, modifier = Modifier.padding(16.dp)) {
                    Text("ENTER", fontSize = 50.sp, style = Typography.displayMedium)
                    Text("DEVICE", fontSize = 70.sp, style = Typography.displayMedium)
                    Text("DETAILS", fontSize = 75.sp, style = Typography.displayMedium)
                }

                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                ) {

                    Column(modifier = Modifier.padding(25.dp)) {

                        DeviceTypes(selectedText, {
                            selectedText = it


                        })
                        Spacer(modifier = Modifier.height(16.dp))
                        var modelname by remember {
                            mutableStateOf("")
                        }
                        TextField(
                            value = modelname,
                            onValueChange = { modelname = it },
                            label = { Text("Model") },
                            modifier = Modifier.fillMaxWidth()
                        )
                        Spacer(modifier = Modifier.height(16.dp))
                        var issue by remember {
                            mutableStateOf("")
                        }
                        TextField(
                            value = issue,
                            onValueChange = { issue = it },
                            label = { Text("Enter issues with the device in detail") },
                            modifier = Modifier.fillMaxWidth(),
                            maxLines = 5,
                            minLines = 3,
                            textStyle = TextStyle(
                                fontSize = 14.sp,
                                color = Color.Gray
                            )
                        )
                        Spacer(modifier = Modifier.height(16.dp))
                        Button(
                            onClick = {
                                val userData = UserData(

                                    Type = selectedText,
                                    modelname = modelname,
                                    Price = Random.nextInt(100, 500).toString(),
                                    Status = STATUS.entries.random().name,
                                    id = Random.nextInt(0, 100)


                                )
                                addDevice(
                                    product(
                                        userData.modelname,
                                        userData.Price.toInt(),
                                        Status = STATUS.valueOf(userData.Status),
                                        type = userData.Type
                                    )
                                )







                                onsubmit()
                            },
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Text("SUBMIT")
                        }
                    }
                }
            }
        }
    }




}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DeviceTypes(selectedText:String,changeString:(String)->Unit) {
    val context = LocalContext.current
    val types = arrayOf("Phone","TV","Laptop","TABLET","Monitor")
    var expanded by remember { mutableStateOf(false) }
    var selectedText = selectedText
    Box(
        modifier = Modifier
            .fillMaxWidth()

    ) {
        ExposedDropdownMenuBox(
            expanded = expanded,
            onExpandedChange = {
                expanded = !expanded
            }, modifier = Modifier.fillMaxWidth()
        ) {
            TextField(
                value = selectedText,
                onValueChange = {},
                readOnly = true,
                trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },
                modifier = Modifier
                    .menuAnchor()
                    .fillMaxWidth(),

            )

            ExposedDropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false }
            ) {
                types.forEach { item ->
                    DropdownMenuItem(
                        text = { Text(text = item) },
                        onClick = {
                            selectedText = item
                            changeString(selectedText)
                            expanded = false
                           
                        }
                    )
                }
            }
        }
    }
}
