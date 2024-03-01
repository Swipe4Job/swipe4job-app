package filters

import SerializedFilter
import filters.filter.Filter

class FilterGroup (val filters: List<Filter>) {
    companion object {
        fun create(vararg filters: Filter): FilterGroup {
            val filterGroup = FilterGroup(filters.toList())

            if (filterGroup.isEmpty()) {
                throw Exception("Empty filters are not allowed")
            }
            return filterGroup
        }
        fun EMPTY(): FilterGroup {
            return FilterGroup(listOf())
        }
    }

    fun isEmpty(): Boolean {
        return this.filters.isEmpty()
    }

    fun equals(group: FilterGroup): Boolean {
        if (filters.size != group.filters.size) {
            return false;
        }

        for (i in filters.indices) {
            val groupFilter = group.filters[i]
            val filter = filters[i]

            if (!groupFilter.equals(filter)) {
                return false
            }
        }

        return true;
    }

    fun serialize(): List<SerializedFilter> {
        return this.filters.map { it.serialize() }
    }
}