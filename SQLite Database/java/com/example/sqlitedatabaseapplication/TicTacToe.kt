package com.example.sqlitedatabaseapplication

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class TicTacToe : AppCompatActivity() {

    private lateinit var buttons: Array<Button>
    private lateinit var tvStatus: TextView
    private lateinit var btnReset: Button

    private var currentPlayer = 'X'
    private var board = CharArray(9) { ' ' }
    private var gameActive = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tic_tac_toe)

        tvStatus = findViewById(R.id.tvStatus)
        btnReset = findViewById(R.id.btnReset)

        buttons = arrayOf(
            findViewById(R.id.btn0),
            findViewById(R.id.btn1),
            findViewById(R.id.btn2),
            findViewById(R.id.btn3),
            findViewById(R.id.btn4),
            findViewById(R.id.btn5),
            findViewById(R.id.btn6),
            findViewById(R.id.btn7),
            findViewById(R.id.btn8)
        )

        for (i in buttons.indices) {
            buttons[i].setOnClickListener {
                onButtonClick(i)
            }
        }

        btnReset.setOnClickListener {
            resetGame()
        }
    }

    private fun onButtonClick(index: Int) {
        if (board[index] == ' ' && gameActive) {
            board[index] = currentPlayer
            buttons[index].text = currentPlayer.toString()
            if (checkWinner()) {
                tvStatus.text = "Player $currentPlayer Wins!"
                gameActive = false
            } else if (board.all { it != ' ' }) {
                tvStatus.text = "It's a Draw!"
                gameActive = false
            } else {
                currentPlayer = if (currentPlayer == 'X') 'O' else 'X'
                tvStatus.text = "Player $currentPlayer's Turn"
            }
        }
    }

    private fun checkWinner(): Boolean {
        val winPatterns = arrayOf(
            intArrayOf(0, 1, 2),
            intArrayOf(3, 4, 5),
            intArrayOf(6, 7, 8),
            intArrayOf(0, 3, 6),
            intArrayOf(1, 4, 7),
            intArrayOf(2, 5, 8),
            intArrayOf(0, 4, 8),
            intArrayOf(2, 4, 6)
        )

        for (pattern in winPatterns) {
            if (board[pattern[0]] == currentPlayer &&
                board[pattern[1]] == currentPlayer &&
                board[pattern[2]] == currentPlayer
            ) {
                return true
            }
        }
        return false
    }

    private fun resetGame() {
        board.fill(' ')
        for (button in buttons) {
            button.text = ""
        }
        currentPlayer = 'X'
        tvStatus.text = "Player X's Turn"
        gameActive = true
    }
}
