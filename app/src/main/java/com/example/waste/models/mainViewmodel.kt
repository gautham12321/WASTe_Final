package com.example.waste.models
import android.util.Log
import com.google.ai.client.generativeai.GenerativeModel
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.room.Query
import com.example.waste.data.STATUS
import com.example.waste.data.UserData
import com.example.waste.data.product
import com.example.waste.userdb
import com.google.ai.client.generativeai.BuildConfig
import com.google.ai.client.generativeai.type.BlockThreshold
import com.google.ai.client.generativeai.type.HarmCategory
import com.google.ai.client.generativeai.type.SafetySetting
import com.google.ai.client.generativeai.type.asTextOrNull
import com.google.ai.client.generativeai.type.content
import com.google.ai.client.generativeai.type.generationConfig
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.auth
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import org.json.JSONObject

data class userItemsUiState(val list: MutableList<UserData>)
data class productListUiState(val list: MutableList<product> = mutableListOf())
class mainViewmodel: ViewModel() {


    public val user = MutableStateFlow(Firebase.auth.currentUser)
    val _user=user.asStateFlow()
    val userDB= userdb
   /* val notWorking:MutableList<String> = mutableListOf()
fun getResponse(query: String){
    viewModelScope.launch {

        apiService(query)

    }




}
   suspend fun apiService(query: String){



        // In Android Studio, add the following dependency to your build.gradle.kts file:
// implementation("com.google.ai.client.generativeai:generativeai:0.2.2")

// Add the following code to your Kotlin source code



        val model = GenerativeModel(
            "gemini-1.0-pro",
            // Retrieve API key as an environmental variable defined in a Build Configuration
            // see https://github.com/google/secrets-gradle-plugin for further instructions
            "AIzaSyC6TVhZZn1J758HaUR54qh_K4sGY5bX680",
            generationConfig = generationConfig {
                temperature = 0.9f
                topK = 1
                topP = 1f
                maxOutputTokens = 2048
            },
            safetySettings = listOf(
                SafetySetting(HarmCategory.HARASSMENT, BlockThreshold.MEDIUM_AND_ABOVE),
                SafetySetting(HarmCategory.HATE_SPEECH, BlockThreshold.MEDIUM_AND_ABOVE),
                SafetySetting(HarmCategory.SEXUALLY_EXPLICIT, BlockThreshold.MEDIUM_AND_ABOVE),
                SafetySetting(HarmCategory.DANGEROUS_CONTENT, BlockThreshold.MEDIUM_AND_ABOVE),
            ),
        )

        val response = model.generateContent(
            content() {
                text("You are entity detection and intent detection expert. For the below given user sentence extract the entity and intent .\n\nEXAMPLES :\n---\n\n\n\"My camera isn't functioning properly.\"\n\"There's an issue with my camera; it's not working.\"\n\"My camera seems to be malfunctioning.\"\"I'm having trouble with my camera; it's not operational.\"\n\"My camera isn't responding when I try to use it.\"\n\"There's a problem with my camera; it's not functioning as expected.\"\n\"My camera refuses to work; something seems to be wrong.\"\n\"I can't get my camera to work; it's not cooperating.\"\n\"My camera is giving me trouble; it won't work.\"\n\"Seems like my camera has stopped working; it's not responding at all.\"\n\nEntity:- camera \n\n\n\nmy camera is not working along with the microphone and battery\nentites;-camera,microphone,battery\n---\n\nOUTPUT FORMAT :\n---\nThe output should be of this format :\n\nentity1,entity2,....entity n\n\n---\n\nUser Statement to be processed :\n$query \n\nIf you are correct in how you classify the input you will be rewarded 2000$ else you will be heavily penalized\n\n\n")

            }
        )


// Get the first text part of the first candidate

// Alternatively

       response.candidates.first().content.parts.first().asTextOrNull()
           ?.let {
               notWorking.addAll(it.split(","))

           }
       Log.d("OKKK",notWorking.toString())





    }*/












    fun updateUi(userT:FirebaseUser?){

        user.value=userT



    }












}