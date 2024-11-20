import java.io.File
const val BG_GREEN = "\u001B[42m"
const val BG_YELLOW = "\u001B[43m"
const val RESET = "\u001B[0m"
const val WHITE = "\u001B[37m"
const val BLACK = "\u001B[30m"


fun entryMenu(){
    println("Bienvenido a ElijaElNumero")
    println("Seleccione opción:")
    println("1.Jugar")
    println("2.Revisar último intento")
    println("3.Salir")
}
fun difMenu(){
    println("Escoja dificultad:")
    println("1.Fácil")
    println("2.Normal")
    println("3.Avanzado")
    println("4.Maestro")
    println("5.Legendario")
}
fun randomNum():String{
    val l=(0..9)
    val desordenada = l.shuffled()
    var numerito =""
    for (s in 0 .. 3){
        numerito+=desordenada[s]
    }
    return numerito
}
fun play(difficulty:Int){
    var tries = 0
    when(difficulty){
        1-> tries = 10
        2-> tries = 7
        3-> tries = 5
        4-> tries = 3
        5-> tries = 1
    }
    val exnum = randomNum()
    var sumNum = 0
    var sumPos = 0
    File("partidaVieja.txt").writeText("")
    var savedGame =StringBuilder("")
    for (i in 0 until tries){
        println("Introduzca su número de 4 cifras no repetidas:")
        val linePlayer = readln()
        savedGame.append("Intento nº ${i+1}: $linePlayer \n")

        for (j in 0 .. 3){
            if (linePlayer[j]==exnum[j]) sumPos++
            if (exnum.contains(linePlayer[j])) sumNum++
        }
        if(sumPos>0 && sumNum>0) sumNum=sumNum-sumPos
        savedGame.append("Aciertos: $sumPos Coincidencias: $sumNum \n")
        println("${BG_GREEN}${WHITE}$sumPos${BG_YELLOW}$sumNum${RESET}${WHITE}")
        if (sumPos==4){
            println("¡Felicidades, has ganado en tu ${i+1}º intento!")
            break
        }
        sumNum = 0
        sumPos = 0
    }
    if (sumPos!=4) {
        println("Has perdido, el número era $exnum")
    }
    val oldGame = savedGame.toString()
    File("partidaVieja.txt").writeText(oldGame)


}
fun partidAnterior(){
    val oldGame = File("partidaVieja.txt").readText()
    println(oldGame)
}
fun main(){
    entryMenu()
    val opt = readln().toInt()
    if (opt==1) {
        difMenu()
        val dif = readln().toInt()
        play(dif)
    }
    else if (opt==2){
        partidAnterior()
    }
    else if (opt==3) println("Gracias por jugar")




}



