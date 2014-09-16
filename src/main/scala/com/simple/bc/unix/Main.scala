package com.simple.bc.unix

object Main extends App{

  println("Type expression: ")
  val input = readLine()
  val postfix = Postfix(input)

  println(postfix.notation.map{postfix.evaluate}.get)


}
