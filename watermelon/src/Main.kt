fun main() {
    generateSequence {
        readln().split(" ").map { it.toInt() }.takeIf { it[0] != 0 || it[1] != 0 }
    }
        .map { it.let { (h, o) -> if (h > o) ((h / o) + 1) * 10 else 10 } }
        .forEach(::println)
}