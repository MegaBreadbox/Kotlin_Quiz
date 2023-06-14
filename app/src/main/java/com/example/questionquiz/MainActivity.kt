package com.example.questionquiz

import android.graphics.Paint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

class Questions<T>(
    val Question: String,
    //val questionList: List<String>,
    val Answer: T,
)
var userCorrect = false


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
    var currentState by remember { mutableStateOf(1) }
    val q1Array = arrayOf(stringResource(R.string.compass_north), stringResource(R.string.compass_south), stringResource(R.string.compass_west), stringResource(R.string.compass_east))
    q1Array.shuffle()
    val q1 = Questions(stringResource(R.string.question_one), stringResource(R.string.compass_south))



    when (currentState){
        1-> Question(q1.Question, q1.Answer, q1Array, {currentState++})
       // 2-> Answer(q1.Answer,{currentState++})
    }
}

@Composable
fun Start(modifier: Modifier = Modifier){

}

//Need a lambda because onclick doesn't have access to the state remember variable
@Composable
fun Question(
    questionText: String,
    questionAnswer: String,
    questionArray: Array<String>,
    incrementState: () -> Unit,
    modifier: Modifier = Modifier
){

    fun buttonLogic(answer: String){
        incrementState
        if(questionAnswer == answer)
            println("True")
            userCorrect = true


    }
    if(questionArray.size > 2){
        Column(
            verticalArrangement = Arrangement.Center,
            modifier = modifier
                .fillMaxSize()
        ) {
            Text(
                text = questionText,
                textAlign = TextAlign.Center,
            )
            Spacer(
                modifier = modifier
                    .padding(150.dp)
            )
            Row(
                horizontalArrangement = Arrangement.SpaceAround,
                modifier = modifier
                    .fillMaxWidth()
                    .padding(bottom = 32.dp)
            ) {
                Button(onClick = { buttonLogic(questionArray[0]) }) {

                    Text(
                        text = questionArray[0],
                        modifier = modifier
                    )
                }
                Button(onClick = { buttonLogic(questionArray[1]) }) {
                    Text(
                        text = questionArray[1]
                    )
                }
            }
            Row(
                horizontalArrangement = Arrangement.SpaceAround,
                modifier = modifier
                    .fillMaxWidth()
            ) {
                Button(onClick = { buttonLogic(questionArray[2]) }) {
                    Text(
                        text = questionArray[2]
                    )
                }
                Button(onClick = { buttonLogic(questionArray[3]) }) {
                    Text(
                        text = questionArray[3]
                    )
                }
            }
        }
    }
    else{

    }

}

//Answer and question are gonna be very similar methods
/*
@Composable
fun Answer(modifier: Modifier = Modifier){
    Column(){
        Text(
            text = questionText
        )
        Button(onClick = {incrementState}) {
            Text(
                text = answerText
            )
        }
    }
}
*/
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