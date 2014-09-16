package com.simple.bc.unix

import org.scalatest.FunSuite

class InputSpec extends FunSuite{

  test("An input when provided should not have whitespace after cleaned up") {

    val input = "2 + 8 (7* 9)"

    assert("2+8(7*9)" == input.replaceAll("\\s",""))

  }

  test("An input in infix has to be moved to postfix notation") {

    val input = "2*(3+5)"

    val postfix = Postfix(input)

    assert(Some("2 3 5 + *") == postfix.notation)

  }

  test("Given an expression calculate the result") {

    val input = "2*(3+5)"

    val postfix = Postfix(input)

    assert(postfix.notation.map{postfix.evaluate}.get == 16)

  }

  test("Given an expression 5 * (((9+8) * (4*6)) + 7) calculate the result") {

    val input = "5 * (((9+8) * (4*6)) + 7)"

    val postfix = Postfix(input)

    assert(postfix.notation.map{postfix.evaluate}.get == 2075)

  }


  test("Given an expression 3.1 + 4.5 * 5 calculate the result") {

    val input = "3.1 + 4.5 * 5"

    val postfix = Postfix(input)

    assert(postfix.notation.map{postfix.evaluate}.get == 25.6)

  }


  test("Given an expression (3+5)*2 calculate the result") {

    val input = "(3+5) * 2"

    val postfix = Postfix(input)

    assert(postfix.notation.map{postfix.evaluate}.get == 16)

  }

}
