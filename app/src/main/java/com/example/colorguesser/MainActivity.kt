package com.example.colorguesser

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val colorText = findViewById<TextView>(R.id.colorText)
        val scoreText = findViewById<TextView>(R.id.scoreText)

        val button1 = findViewById<Button>(R.id.button1)
        val button2 = findViewById<Button>(R.id.button2)
        val button3 = findViewById<Button>(R.id.button3)
        val button4 = findViewById<Button>(R.id.button4)

        var colorArray: Array<String> = arrayOf(getRandomHex(), getRandomHex(), getRandomHex(), getRandomHex())
        val buttonArray: Array<Button> = arrayOf(button1, button2, button3, button4)

        setColors(colorArray, buttonArray)

        var score = 0

        var correctHex = colorArray[(0..3).random()]
        colorText.text = correctHex.chunked(2).joinToString(separator = " ")

        fun correctAnswer(){
            score++
            setColors(colorArray, buttonArray)
            correctHex = colorArray[(0..3).random()]
            colorText.text = correctHex.chunked(2).joinToString(separator = " ")
            scoreText.text = "Score: $score"
        }

        button1.setOnClickListener {
            if(isAnswerCorrect(colorArray[0], correctHex)){
                correctAnswer()
            } else {
                removeWrong(colorArray.indexOf(correctHex), buttonArray)
            }
        }

        button2.setOnClickListener {
            if(isAnswerCorrect(colorArray[1], correctHex)){
                correctAnswer()
            } else {
                removeWrong(colorArray.indexOf(correctHex), buttonArray)
            }
        }

        button3.setOnClickListener {
            if(isAnswerCorrect(colorArray[2], correctHex)){
                correctAnswer()
            } else {
                removeWrong(colorArray.indexOf(correctHex), buttonArray)
            }
        }

        button4.setOnClickListener {
            if(isAnswerCorrect(colorArray[3], correctHex)){
                correctAnswer()
            } else {
                removeWrong(colorArray.indexOf(correctHex), buttonArray)
            }
        }
    }

    private fun removeWrong(colorPos: Int, buttonArray: Array<Button>){
        for((index, button) in buttonArray.withIndex()){
            if(index != colorPos){
                button.visibility = View.GONE
            } else{
                button.text = "reset"
                button.setOnClickListener{
                    val intent = intent
                    finish()
                    startActivity(intent)
                }
            }
        }
    }

    private fun setColors(colorArray: Array<String>, buttonArray: Array<Button>) {
        for(index in colorArray.indices){
            colorArray[index] = getRandomHex()
        }

        for((index, button) in buttonArray.withIndex()){
            button.setBackgroundColor(Color.parseColor("#${colorArray[index]}"))
        }
    }

    private fun getRandomHex(): String {
        val red: Int = (0x10..0xFF).random()
        val green: Int = (0x10..0xFF).random()
        val blue: Int = (0x10..0xFF).random()

        return Integer.toHexString(red)+Integer.toHexString(green)+Integer.toHexString(blue)
    }

    private fun isAnswerCorrect(btnColor: String, textColor: String): Boolean = textColor == btnColor
}