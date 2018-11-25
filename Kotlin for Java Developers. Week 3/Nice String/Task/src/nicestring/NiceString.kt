package nicestring

fun String.isNice(): Boolean {
    fun rule1(s: String): Boolean = (
            !s.contains("bu")
            && !s.contains("ba")
            && !s.contains("be"))

    fun rule2(s: String): Boolean = s.toCharArray()
            .filter { it.isVowel() }.size >= 3

    fun pairWithNext(index: Int, s: String) =
            if (index < s.length - 1) Pair(s[index], s[index + 1]) else Pair(s[index], null)

    fun rule3(s: String): Boolean {
        val pair = s.mapIndexed { index, _ -> pairWithNext(index, s) }
        return pair.any { it.isSame() }
    }

    return arrayListOf(rule1(this), rule2(this), rule3(this))
            .count { it } >= 2
}

fun Pair<Char,Char?>.isSame(): Boolean = this.first == this.second

fun Char.isVowel(): Boolean =
        this == 'a'
        || this == 'e'
        || this == 'i'
        || this == 'o'
        || this == 'u'
