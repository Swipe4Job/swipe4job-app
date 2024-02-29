package filters.filter

object Operators {
    val EQUAL = 0
    val NOT_EQUAL = 1
    val GREATER = 2
    val LOWER = 3
    val GREATER_EQUAL = 4
    val LOWER_EQUAL = 5
    val IN = 6
    val LIKE = 7
}

class Operator(val value: Int) {
    companion object {
        fun from(value: Int): Operator {
            return when (value) {
                Operators.EQUAL -> Operator(Operators.EQUAL)
                Operators.NOT_EQUAL -> Operator(Operators.NOT_EQUAL)
                Operators.GREATER -> Operator(Operators.GREATER)
                Operators.LOWER -> Operator(Operators.LOWER)
                Operators.GREATER_EQUAL -> Operator(Operators.GREATER_EQUAL)
                Operators.LOWER_EQUAL -> Operator(Operators.LOWER_EQUAL)
                Operators.IN -> Operator(Operators.IN)
                Operators.LIKE -> Operator(Operators.LIKE)
                else -> {
                    throw Error("$value is not a valid operator")
                }
            }
        }
    }

    fun equals(operator: Operator): Boolean {
        return this.value == operator.value
    }
}