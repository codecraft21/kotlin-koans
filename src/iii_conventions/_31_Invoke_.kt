package iii_conventions

import util.TODO


class Invokable(private var c: Int = 0) {
    operator fun invoke(): Invokable {
        ++c
        return this
    }
    fun getNumberOfInvocations() = c
}

fun todoTask31(): Nothing = TODO(
    """
        Task 31.
        Change class Invokable to count the number of invocations (round brackets).
        Uncomment the commented code - it should return 4.
    """,
    references = { invokable: Invokable -> })

fun task31(invokable: Invokable): Int {
    return invokable()()()().getNumberOfInvocations()
}
