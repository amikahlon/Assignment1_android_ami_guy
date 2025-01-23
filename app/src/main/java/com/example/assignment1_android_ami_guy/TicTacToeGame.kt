package com.example.assignment1_android_ami_guy

class TicTacToeGame {
    var board: Array<Array<Char>> = Array(3) { Array(3) { ' ' } }
    var currentPlayer: Char = 'X'

    fun makeMove(row: Int, col: Int): Boolean {
        if (board[row][col] == ' ' && checkWinner() == null) {
            board[row][col] = currentPlayer
            switchPlayer()
            return true
        }
        return false
    }

    fun checkWinner(): Char? {
        // Check rows
        for (row in 0..2) {
            if (board[row][0] != ' ' &&
                board[row][0] == board[row][1] &&
                board[row][1] == board[row][2]) {
                return board[row][0]
            }
        }

        // Check columns
        for (col in 0..2) {
            if (board[0][col] != ' ' &&
                board[0][col] == board[1][col] &&
                board[1][col] == board[2][col]) {
                return board[0][col]
            }
        }

        // Check diagonals
        if (board[0][0] != ' ' &&
            board[0][0] == board[1][1] &&
            board[1][1] == board[2][2]) {
            return board[0][0]
        }

        if (board[0][2] != ' ' &&
            board[0][2] == board[1][1] &&
            board[1][1] == board[2][0]) {
            return board[0][2]
        }

        return null
    }

    fun isBoardFull(): Boolean {
        return board.all { row -> row.all { it != ' ' } }
    }

    fun resetGame() {
        board = Array(3) { Array(3) { ' ' } }
        currentPlayer = 'X'
    }

    private fun switchPlayer() {
        currentPlayer = if (currentPlayer == 'X') 'O' else 'X'
    }
}