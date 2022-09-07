package examples

object Functions {

  // defines a function that receives two parameters
  // number and a object/function that returns a Integer
  def SquareOfNum(num:Int, square:Int => Int) : Int = square(num)

  def Square(num:Int) : Int = num * num


  def main(args: Array[String]): Unit = {
    println(SquareOfNum(4,Square))



  }

}
