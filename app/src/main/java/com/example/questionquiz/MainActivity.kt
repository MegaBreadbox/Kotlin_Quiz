package com.example.questionquiz

import android.graphics.Paint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

class Questions<T>(
    val Question: String,
    //val questionList: List<String>,
    val Answer: T,
    val AnswerExplanation: String
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
    var currentState by remember { mutableStateOf(1) }
    var userCorrect by remember{ mutableStateOf(false) }
    val q1Array = arrayOf(stringResource(R.string.compass_north), stringResource(R.string.compass_south), stringResource(R.string.compass_west), stringResource(R.string.compass_east))
    q1Array.shuffle()
    val q1 = Questions(stringResource(R.string.question_one), stringResource(R.string.compass_south), stringResource(R.string.question_one_explanation))



    when (currentState){
        1-> Question(q1.Question, q1.Answer, q1Array,
            {  currentState++ },
            { userCorrect = false; currentState++ })
        2-> Answer(q1.Question, q1.AnswerExplanation, userCorrect, {currentState++})
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
    rightAnswer: () -> Unit,
    wrongAnswer: () -> Unit,
    modifier: Modifier = Modifier
){
    var userAnswer by remember { mutableStateOf("") }



    var buttonLogic:() -> Unit = {
            println(userAnswer)
            println(questionAnswer)
        if(userAnswer == questionAnswer) {
            println("My treasure!")
            rightAnswer
            println("My treasure!")
        }
        else{
            println("My treasure!")
            wrongAnswer
        }
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
                QuestionButton(
                    questionArray, 0, questionAnswer, buttonLogic,
                    {userAnswer = questionArray[0]}
                )
                QuestionButton(
                    questionArray, 1, questionAnswer, buttonLogic,
                    {userAnswer = questionArray[1]}
                )

            }
            Row(
                horizontalArrangement = Arrangement.SpaceAround,
                modifier = modifier
                    .fillMaxWidth()
            ) {
                QuestionButton(
                    questionArray, 2, questionAnswer, buttonLogic,
                    {userAnswer = questionArray[2]}
                )
                QuestionButton(
                    questionArray, 3, questionAnswer, buttonLogic,
                    {userAnswer = questionArray[3]}
                )
            }
        }
    }
    else{
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
                QuestionButton(
                    questionArray, 0, questionAnswer, buttonLogic,
                    {userAnswer = questionArray[0]}
                )
                QuestionButton(
                    questionArray, 1, questionAnswer, buttonLogic,
                    {userAnswer = questionArray[1]}
                )

            }
        }
    }

}

@Composable
fun QuestionButton(
    arrayOfQuestions: Array<String>,
    arrayPosition: Int,
    questionAnswer: String,
    logicFunction: () -> Unit,
    buttonContents:() -> Unit,
    modifier: Modifier = Modifier
){

    Button(onClick =  {buttonContents; logicFunction}) {
        Text(
            text = arrayOfQuestions[arrayPosition]
        )
    }
}

//Answer and question are gonna be very similar methods

@Composable
fun Answer(
    questionText: String,
    questionAnswer: String,
    answerCorrect: Boolean,
    incrementState: () -> Unit,
    modifier: Modifier = Modifier
){
    Column(
        verticalArrangement = Arrangement.Center,
        modifier = modifier
            .fillMaxSize()
    ){
        Text(
            text = questionText
        )
        if(answerCorrect){
            Text(
                text = questionAnswer,
                color = Color.Green
            )
            Button(onClick = incrementState) {
                Image(
                    painter = painterResource(R.drawable.nice_work),
                    contentDescription = stringResource(R.string.answer_is_correct)
                )
            }
        }
        else
        {
            Text(
                text = questionAnswer,
                color = Color.Red
            )
            Button(onClick = incrementState) {
                Image(
                    painter = painterResource(R.drawable.not_nice_work),
                    contentDescription = stringResource(R.string.answer_is_not_correct)
                )
            }
        }
    }
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