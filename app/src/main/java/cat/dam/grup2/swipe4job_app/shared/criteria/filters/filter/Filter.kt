package filters.filter

import SerializedFilter

class Filter(val filterField: Field, val filterOperator: Operator, val filterValue: Operand) {
    companion object {
        fun create(field: String, operator: Int, value: Any): Filter {
            return Filter(Field(field), Operator.from(operator), Operand(value))
        }
    }

    fun equals(filter: Filter): Boolean {
        return (
                this.filterField.equals(filter.filterField) &&
                        this.filterOperator.equals(filter.filterOperator) &&
                        this.filterValue.equals(filter.filterValue)
                )
    }

    fun serialize(): SerializedFilter {
        return SerializedFilter(
            field = this.filterField.value,
            operator = this.filterOperator.value,
            operand = this.filterValue.value
        )
    }
}