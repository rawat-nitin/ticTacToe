import kotlin.random.Random

var board = arrayListOf<ArrayList<String>>()

fun main (args: Array<String>){
    for (i:Int in 0..2){
        val row:ArrayList<String> = arrayListOf<String>()
        for (j: Int in 0..2)
            row.add("")
        board.add(row)
    }

    printBoard()

    var continueGame = true

    do {
        println("Please enter a position (e.g. 1, 1)")
        val input:String = readLine()?:""
        var x = 0
        var y = 0
        try {
            val positions: List<String> = input.split(",")
            x = positions[0].trim().toInt()
            y = positions[1].trim().toInt()

            var skipRound = false

            if(board[x-1][y-1] != ""){
                println("That positions is already filled, try another")
                skipRound = true
            }else {
                board[x-1][y-1] = "X"
                printBoard()
            }

            if(!skipRound) {
                val playerWon = checkWinner(true)
                if(playerWon) {
                    println("\uD57E \uD57E \uD57E \uD57E \uD57E \uD57E \uD57E \uD57E \uD57E \uD57E \uD57E \uD57E")
                    println("You Won, congratulations")
                    continueGame = false
                }

                val boardFull: Boolean = checkBoardFull()
                if(!playerWon && boardFull) {
                    println("It is a tie...!! ")
                    continueGame = false
                }

                if(continueGame) {
                    placeComputerMove()
                    printBoard()
                    val computerWon: Boolean = checkWinner(false)
                    if(computerWon){
                        println("Computer Won")
                        continueGame = false
                    }
                }
            }


        }catch (e: Exception) {
            println("Invalid input, please use correct one")
        }

    } while(continueGame)
}

fun printBoard() {
    println("----------------")
    for (i: Int in 0..2) {
        for(j: Int in 0..2) {
            when (board[i][j]){
                "X" -> print("|  X ")
                "O" -> print("|  O ")
                else -> print("|    ")
            }
        }
        println("|")
        println("----------------")
    }
}

fun checkWinner(player: Boolean): Boolean {
    var won = false
    val checkSymbol: String = if(player) "X" else "O"

    for (i: Int in 0..2){
        //Horizontal Wins
        if (board[i][0] == checkSymbol && board[i][1] == checkSymbol && board[i][2] == checkSymbol){
            won = true
            break
        }

        //Vertical Wins
        if (board[0][i] == checkSymbol && board[1][i] == checkSymbol && board[2][i] == checkSymbol){
            won = true
            break
        }

        //Diagonal Wins
        if (board[0][0] == checkSymbol && board[1][1] == checkSymbol && board[2][2] == checkSymbol){
            won = true
        }
        if (board[2][0] == checkSymbol && board[1][1] == checkSymbol && board[0][2] == checkSymbol){
            won = true
        }
    }
    return won
}

fun checkBoardFull(): Boolean {
    var boardFull = true
    for (i: Int in 0..2) {
        for (j: Int in 0..2){
            if (board[i][j] == ""){
                boardFull = false
                break
            }
        }
    }
    return boardFull
}

fun placeComputerMove() {
    var i = 0
    var j = 0
    do {
        i = Random.nextInt(3)
        j = Random.nextInt(3)
    } while (board[i][j] != "")
    board[i][j] = "O"
}