package com.simple.bc.unix

import scala.collection.mutable

class Postfix(infix: String) {

  /**
   * Return some postfix notation in case the input was a valid infix, otherwise none
   * @return
   */
  def notation: Option[String] = {

    val stack = new mutable.Stack[Char]
    val buffer = new mutable.StringBuilder()
    var char: Char = 0
    var pos: Int = 0

    while(pos < infix.length){

      char = infix.charAt(pos)

      char match{
        case ')' =>
          while (stack.nonEmpty && stack.top != '(') buffer.append(stack.pop())

          if (stack.nonEmpty) stack.pop()

        case '+' =>
          if (stack.nonEmpty && (stack.top == '+' || stack.top == '-' || stack.top == '*' || stack.top == '/'))
            buffer.append(stack.pop())

          stack.push(char)

        case '-' =>
          if (stack.nonEmpty && (stack.top == '+' || stack.top == '-' || stack.top == '*' || stack.top == '/'))
            buffer.append(stack.pop())

          stack.push(char)

        case '*' =>
          if (stack.nonEmpty && (stack.top == '*' || stack.top == '/'))
            buffer.append(stack.pop())

          stack.push(char)

        case '/' =>
          if (stack.nonEmpty && (stack.top == '*' || stack.top == '/'))
            buffer.append(stack.pop())

          stack.push(char)

        case '(' => stack.push(char)

        case i if i >= '0' && i <= '9' =>

          while ( (char >= '0' && char <= '9') || char == '.'){

            buffer.append(char)
            if (pos + 1 < infix.length()) {
              pos += 1
              char = infix.charAt(pos)
            } else {
              char = 0
              pos = infix.length()
            }
          }
          pos -= 1

        case _ =>
      }

      pos += 1
      buffer.append(" ")

    }

    while (stack.nonEmpty){
      buffer.append(stack.pop())
      buffer.append(" ")
    }

    buffer.deleteCharAt(buffer.length - 1)

    Option(buffer.toString().replaceAll(" +", " "))

  }

  /**
   * Evaluate the postfix notation and return the arithmetic
   * @param postfix
   * @return
   */
  def evaluate(postfix: String): Double = {

    val stack = new mutable.Stack[Double]
    var pos = 0

    while (pos < postfix.length){

      postfix.charAt(pos) match {

        case '+' =>
          val result = stack.pop() + stack.pop()
          stack.push(result)

        case '-' =>
          val result = stack.pop() - stack.pop()
          stack.push(result)
        case '*' =>
          val result = stack.pop() * stack.pop()
          stack.push(result)

        case '/' =>
          val result = stack.pop() / stack.pop()
          stack.push(result)

        case i if i >= '0' && i <= '9' =>
          val partial = postfix.substring(pos)

          val sResult = partial.takeWhile(p => p != ' ')
          pos += sResult.length
          stack.push(sResult.toDouble)

        case _ => //bang nothing

      }

      pos += 1
    }

    stack.pop()
  }

}

object Postfix{
  def apply(infix: String) = new Postfix(infix)
}

