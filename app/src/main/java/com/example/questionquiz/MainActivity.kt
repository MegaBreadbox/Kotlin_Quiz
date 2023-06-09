package com.example.questionquiz

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.questionquiz.ui.theme.QuestionQuizTheme
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue

class Questions<T>(
    val Question: String,
    val Answer: T
)


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            QuestionQuizTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    QuizLayout()
                }
            }
        }
    }
}

@Composable
fun QuizLayout(modifier: Modifier = Modifier) {
    var currentStat by remember { mutableStateOf(1) }


}

@Composable
fun Start(modifier: Modifier = Modifier){

}

@Composable
fun Question(

    modifier: Modifier = Modifier
){
    val q1 = Questions<String>("What color is the sky?", "Blue")
    Column(){
        Text(
            text = q1.Question
        )
        Button(onClick = { /*TODO*/ }) {
            Text(
                text = q1.Answer
            )
        }
    }
}

@Composable
fun Answer(modifier: Modifier = Modifier){

}

@Composable
fun End(modifier: Modifier = Modifier){

}





@Preview(showBackground = true)
@Composable
fun QuizPreview() {
    QuestionQuizTheme {
        QuizLayout()
    }
}