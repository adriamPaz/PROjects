fun main(){
    val rm = ReproductorMidi("pugnodollari.mid")
    println("Cargando juego...")
    Thread.sleep(1000)

    val diccionario = listOf<String>("Portugal","España","Francia","Belgica","Alemania","Austria","Hungria","Chequia","Eslovaquia","Italia","Croacia",
        "Holanda","Grecia","Rumania","Bulgaria")
    println("Bienvenido al juego del ahorcado\nJugaremos con países de la UE")

    val pais = diccionario.random()
    var censor = ""
    for (z in 0 .. pais.lastIndex) censor+="*"

    var error = 0
    while (error < 7 && censor.contains("*")){
        Thread.sleep(200)
        println("Introduce una letra:")
        val letraPais = readln()[0]
        if (!(pais.contains(letraPais))){
            error++
            DibujoAhorcado.dibujar(error)

        }
        else{
            censor = uncensored(pais,letraPais,censor)
        }
        println("Tu palabra descubierta es: $censor")
        println("Llevas $error fallos (${7-error} fallos para perder)")
    }
    if (!(censor.contains("*"))) {
        if (error != 0) println("!Felecidades, ha ganado\nHa descubierto que el pais era $censor y solo se ha equivocado en $error letras!")
        else println("!Felecidades, ha ganado!\n¡Ha descubierto que el pais era $censor y NO SE HA EQUIVOCADO UNA SOLA VEZ!")
    }
    rm.cerrar()
}
fun uncensored(palabraFull:String,letra:Char,palabraAhora:String):String{
    val listletter = mutableListOf<Char>()
    for (a in 0 .. palabraFull.lastIndex){
        listletter.add(palabraAhora[a])
    }
    for (l in 0 .. palabraFull.lastIndex){
        if (palabraFull[l] == letra) listletter[l]=palabraFull[l]
    }
    var outString = ""
    for (b in 0 .. palabraFull.lastIndex){
        outString+=listletter[b]
    }
    return outString
}