package com.example.assignment1_android_ami_guy

import android.os.Bundle
import android.widget.Button
import android.widget.GridLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    private lateinit var game: TicTacToeGame
    private lateinit var boardButtons: Array<Array<Button>>
    private lateinit var statusTextView: TextView
    private lateinit var resetButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        game = TicTacToeGame()
        statusTextView = findViewById(R.id.statusTextView)
        resetButton = findViewById(R.id.resetButton)

        initializeBoard()
        setupResetButton()
        updateStatus()
    }

    private fun initializeBoard() {
        boardButtons = Array(3) { row ->
            Array(3) { col ->
                val buttonId = resources.getIdentifier("button$row$col", "id", packageName)
                findViewById<Button>(buttonId).apply {
                    setOnClickListener { onBoardButtonClick(row, col) }
                }
            }
        }
    }

    private fun onBoardButtonClick(row: Int, col: Int) {
        if (game.makeMove(row, col)) {
            updateButtonText(row, col)
            updateStatus()
        }
    }

    private fun updateButtonText(row: Int, col: Int) {
        boardButtons[row][col].text = game.board[row][col].toString()
    }

    private fun updateStatus() {
        statusTextView.text = when {
            game.checkWinner() != null -> "Winner: ${game.checkWinner()}"
            game.isBoardFull() -> "Draw!"
            else -> "Current Player: ${game.currentPlayer}"
        }
    }

    private fun setupResetButton() {
        resetButton.setOnClickListener {
            game.resetGame()
            resetBoardButtons()
            updateStatus()
        }
    }

    private fun resetBoardButtons() {
        boardButtons.forEach { row ->
            row.forEach { button ->
                button.text = ""
            }
        }
    }
}