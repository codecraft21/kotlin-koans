package iii_conventions

data class MyDate(val year: Int, val month: Int, val dayOfMonth: Int) : Comparable<MyDate> {
    override fun compareTo(other: MyDate): Int = when {
        year != other.year -> year - other.year
        month != other.month -> month - other.month
        else -> dayOfMonth - other.dayOfMonth
    }
}

operator fun MyDate.rangeTo(other: MyDate): DateRange = DateRange(this, other)

enum class TimeInterval {
    DAY,
    WEEK,
    YEAR
}

class DateRange(val start: MyDate, val endInclusive: MyDate) : Iterable<MyDate> {
    operator fun contains(d: MyDate) = d >= start && d <= endInclusive
    override fun iterator(): Iterator<MyDate> = DateIterator(this)

    private class DateIterator(val dr: DateRange) : Iterator<MyDate> {
        var current = dr.start

        override fun hasNext(): Boolean = current <= dr.endInclusive

        override fun next(): MyDate {
            val next = current
            current = current.nextDay()
            return next
        }
    }
}

operator fun MyDate.plus(ti: TimeInterval) = addTimeIntervals(ti, 1)

class RepeatedTimeInterval(val ti: TimeInterval, val n: Int)

operator fun TimeInterval.times(n: Int): RepeatedTimeInterval = RepeatedTimeInterval(this, n)

operator fun MyDate.plus(rti: RepeatedTimeInterval) = addTimeIntervals(rti.ti, rti.n)
