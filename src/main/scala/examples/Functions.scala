package examples


import scala.math._


object Functions {

  //Higher-Order Functions
  // * Takes one or more functions as parameters
  // * Returns a function as a result

  // defines a function that receives two parameters
  // number and a object/function that returns a Double
  def SquareOfNum(num:Int, square:Double => Double) : Double = square(num)


  //Functions as first-class citizens
  // * assigned to a variable
  // * passed as an argument to other functions
  // * returned as a value from other functions
  def Square(num:Double) : Double = num * num

  def SquareFunction(num:Double): Double = {
    //some more lines of code
    pow(num, 2)
  }
  //Anonymous Functions, Function Literals or Lambda Function
  // * does not have a name
  // * have a body
  // * return type is optional
  val number = Seq(0, 1, 2, 3, 4, 5, 6, 7, 8, 9)
  println(number)
  val double = number.map(element => (element + element))
  println(double)


  //Closures
  // * difference between a function and closure is that a closure uses one or more free variables
  val rate = 0.10 // free variables outside function scope but accessible to function

  val time = 2

  def calcSimpleInterest(principal: Double): Double = {
    (principal * rate * time) / 100
  }

  //Curring
  // * Each function returns another function that takes the subsequent argument.
  // example: result = f(x)(y)(z)

  def addition(x: Int, y: Int): Int = x + y //named function

  def curriedAddition(x: Int)(y: Int): Int = x + y //curring function

  def main(args: Array[String]): Unit = {
    println(SquareOfNum(4, Square))
    println(SquareOfNum(4, SquareFunction))

    val additionResult = addition(8, 4) // using regular functions
    println(additionResult)
    val conciseCurriedAdditionResult = curriedAddition(8)(4) //using curring functions
    println(conciseCurriedAdditionResult)
  }

}
