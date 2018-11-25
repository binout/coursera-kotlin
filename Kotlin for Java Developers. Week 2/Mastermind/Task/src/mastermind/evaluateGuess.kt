package mastermind

data class Evaluation(val rightPosition: Int, val wrongPosition: Int)

fun evaluateGuess(secret: String, guess: String): Evaluation {
    var rightPosition = 0
    var wrongPosition = 0
    val secretLeft = secret.toCharArray()
    guess.forEachIndexed { index, c ->
        if (secret[index] == c) {
            rightPosition++
            secretLeft[index] = '0'
        }
    }
    guess.forEachIndexed {index, c ->
        if (secret[index] != c) {
            val indexOf = secretLeft.indexOf(c)
            if (indexOf != -1 && guess[indexOf] != c) {
                wrongPosition++
                secretLeft[indexOf] = '0'
            }
        }
    }
    return Evaluation(rightPosition, wrongPosition)
}
