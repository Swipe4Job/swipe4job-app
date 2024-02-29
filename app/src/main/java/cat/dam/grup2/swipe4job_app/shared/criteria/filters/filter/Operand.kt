package filters.filter

class Operand(val value: Any) {
    fun equals(operand: Operand): Boolean {
        return this.value == operand.value
    }
}