package com.example.waste.ui.theme.Screenas

import android.app.Activity
import android.content.ContentValues.TAG
import android.util.Log
import android.widget.Toast
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedButton
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
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.waste.R
import com.example.waste.models.mainViewmodel
import com.example.waste.ui.theme.Typography
import com.google.firebase.auth.FirebaseAuth


@Composable
fun SignIn(
   modifier: Modifier = Modifier,
   gotologin: () -> Unit = {},
   onSubmit: () -> Unit,
   auth: FirebaseAuth
)
{
   Surface(color =  Color(0xFF6CBD7E),
      modifier = Modifier.fillMaxSize())
   {
      Box {
         Column {
            Image(painter = painterResource(id = R.drawable.leaves), contentDescription = null)

            Image(painter = painterResource(id = R.drawable.leaves_2), contentDescription = null)
         }
         Row(
            modifier = Modifier.fillMaxSize(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
         ) {
            SignInCard(gotologin = gotologin, onSubmit = onSubmit, auth = auth)

         }
      }
   }
}



@Composable
fun LogInPage(modifier: Modifier = Modifier, gotoSignin: () -> Unit = {}, auth: FirebaseAuth,gotToNext:()->Unit)
{val viewmodel:mainViewmodel= viewModel()
   val activity = LocalContext.current as Activity
   val baseContext = LocalContext.current
   Surface(color =  Color(0xFF6CBD7E), modifier = Modifier.fillMaxSize())
   {
      Box {

         Column {
            Image(painter = painterResource(id = R.drawable.leaves), contentDescription = null)

            Image(painter = painterResource(id = R.drawable.leaves_2), contentDescription = null)
         }

         Row(
            modifier = Modifier.fillMaxSize(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
         ) {
            LogIn(gotoSignin = gotoSignin, onLogIn = { email, pass ->
               auth
                  .signInWithEmailAndPassword(email, pass)
                  .addOnCompleteListener(activity) { task ->

                     if (task.isSuccessful) {
                        val user = auth.currentUser

                        viewmodel.updateUi(user)
                        gotToNext()


                     } else {
                        Log.w(TAG, "signInWithEmail:failure", task.exception)
                        Toast.makeText(
                           baseContext,
                           "Authentication failed.",
                           Toast.LENGTH_SHORT,
                        ).show()
                        viewmodel.updateUi(null)


                     }


                  }
            })


         }
      }




   }




}



@Composable
fun LogIn(modifier: Modifier = Modifier, gotoSignin: () -> Unit={},onLogIn:(email:String,password:String)->Unit={
   it,fi->


}){

   Card(
      modifier = Modifier
         .padding(5.dp)
         .width(328.dp)
         .shadow(
            elevation = 10.dp,
            shape = RoundedCornerShape(8.dp)
         )
      ,
      colors = CardDefaults.cardColors(containerColor = Color(0xFFA0F0B1)),


      ) {
      var email by remember {
         mutableStateOf("basil@gmail.com")
      }
      var password by remember {
         mutableStateOf("")
      }
      Column (horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center,
         modifier = Modifier.fillMaxWidth()){
         Text(text = "LOGIN", style = Typography.displayMedium,modifier=Modifier.padding(16.dp))

         Spacer(modifier = Modifier.height(30.dp))
         TextField(value = email,
            onValueChange = {email=it},
            placeholder = { Text(text = stringResource(R.string.email_address) )}
         , keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next))
         Spacer(modifier = Modifier.height(30.dp))
         TextField( value = password, onValueChange ={ password=it}  ,
            placeholder = {
               Text(text = stringResource(R.string.password) )},
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
            keyboardActions = KeyboardActions(onDone = {

               onLogIn(email,password)
            })
         )
         Spacer(modifier = Modifier.height(30.dp))
         ElevatedButton(onClick ={ if(email.isNotBlank() && password.isNotBlank())onLogIn(email,password)}, modifier = Modifier
            .fillMaxWidth()
            .padding(15.dp)) {
            Text(text = "LOGIN")
            
         }



            ClickableText(
               text = AnnotatedString("NEW USER? SIGN UP"),

               style = Typography.displaySmall,
               modifier = Modifier.padding(16.dp)
               ,
               onClick = {
                  gotoSignin()
               }




            )






      }


   }

}

@Composable
fun SignInCard(
   modifier: Modifier = Modifier,
   gotologin: () -> Unit,
   onSubmit: () -> Unit,
   auth: FirebaseAuth
){
   var viewmodel:mainViewmodel = viewModel()

   Card(
      modifier = Modifier
         .padding(5.dp)
         .width(328.dp),
      colors = CardDefaults.cardColors(containerColor = Color(0xFFA0F0B1)),


      ) {
      Column (horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center,
         modifier = Modifier.fillMaxWidth()){
         var email by remember {
            mutableStateOf("")
         }
         var password by remember {
            mutableStateOf("")
         }
         var cpassword by remember {
            mutableStateOf("")
         }
         Text(text = stringResource(R.string.signup), style = Typography.displayMedium,modifier=Modifier.padding(16.dp))

         Spacer(modifier = Modifier.height(30.dp))
         TextField(keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),value = email, onValueChange = {email=it}, placeholder = { Text(text = stringResource(R.string.email_address) )})
         Spacer(modifier = Modifier.height(30.dp))
         TextField(keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),value = password, onValueChange = {password=it}, placeholder = { Text(text = stringResource(R.string.password) )})
         Spacer(modifier = Modifier.height(30.dp))
         val activity = LocalContext.current as Activity
         val baseContext= LocalContext.current
         TextField(value = cpassword, onValueChange = {cpassword=it},
            placeholder = { Text(text = "Confirm Password" )},
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done)

            ,
            keyboardActions = KeyboardActions(onDone = {
               auth.createUserWithEmailAndPassword(email,password)
               .addOnCompleteListener(activity){

                  if(it.isSuccessful){
                     Log.d(TAG, "createUserWithEmail:success")
                     val user = auth.currentUser

                     viewmodel.updateUi(user)
                     onSubmit()
                  } else {
                     // If sign in fails, display a message to the user.
                     Log.w(TAG, "createUserWithEmail:failure", it.exception)
                     Toast.makeText(
                        baseContext,
                        "Authentication failed.",
                        Toast.LENGTH_SHORT,
                     ).show()
                     viewmodel.updateUi(null)
                  }



               }



         }))
         Spacer(modifier = Modifier.height(15.dp))
         ElevatedButton(onClick ={ if(email.isNotBlank() && password.isNotBlank())
            auth.createUserWithEmailAndPassword(email,password)
               .addOnCompleteListener(activity){

                  if(it.isSuccessful){
                     Log.d(TAG, "createUserWithEmail:success")
                     val user = auth.currentUser

                     viewmodel.updateUi(user)
                     onSubmit()
                  } else {
                     // If sign in fails, display a message to the user.
                     Log.w(TAG, "createUserWithEmail:failure", it.exception)
                     Toast.makeText(
                        baseContext,
                        "Authentication failed.",
                        Toast.LENGTH_SHORT,
                     ).show()
                     viewmodel.updateUi(null)
                  }



               }

                                 }, modifier = Modifier
            .fillMaxWidth()
            .padding(15.dp)) {
            Text(text = "SIGN UP")

         }

         ClickableText(
            text = AnnotatedString("EXISTING USER? LOG IN"),

            style = Typography.displaySmall,
            modifier = Modifier.padding(16.dp)
            ,
            onClick = {
               gotologin()
            })




      }


   }



}

