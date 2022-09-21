package examples

//regular traits
trait ShowA {
  def showA() :String = {
    "a"
  }

  def showTitle() :Unit = {
    println("Title")
  }
  //no empty methods allowed for extended trait since it will be used as class
  //def undefinedBodyMethod() :String
}

trait ShowB {
  def showB() :String = {
    "b"
  }
}

trait ShowC {
  def showC() :String
}

// on scala you can extend or implement more than one trait
class AlphabetDisplay extends ShowA with ShowB with ShowC {
  override def showC(): String = {
    "C"
  }

  override def showB(): String = {
    "B"
  }

}


// limit use of traits using inheritance, very rare use case
class Character {
  def printA() = {
    println("A")
  }

  def printB() = {
    println("B")
  }
}

class Number {

  def displayNumbers() = {
    //for ( value <- rangeStart to rangeEnd )
    //for ( value1 <- range1Start to range1End; value2 <- range2start until range2End ) nested loop
    //for ( value <- anyCollection [Seq, Array, List] )
    //for ( value <- anyCollection if value < condition1; if value > condition1 )

    for( n <- 0 to 10) {
      println(n);
    }
  }
}

trait Alphabet extends Character {

}

// this won't compile since we have defined superclasses
//   illegal inheritance; superclass Number
//   is not a subclass of the superclass Character
//   of the mixin trait Alphabet
// even if this seems to be right for the IDE
//class Alphanumeric extends Number with Alphabet

// limit traits to be mixed on classes with some specific method
// A.K.A. Structural type
trait User {
  this: {
    def signIn(password: String): Boolean
    def signOut: Unit
  } =>
}

class SalesUser

class AdminUser extends SalesUser with User {

  def signIn(password: String): Boolean = {
    if (password == "password") {
      println("Access granted!"); true }
    else
      false
  }

  def signOut {
    println("Session closed")
  }
}

//limiting use of traits to classes who are subclasses
trait AdminEmployee {
  this: Employee with CollegeDegree with SixSigma =>
}

class Employee
trait CollegeDegree // specific degree
trait SixSigma // general quality standards

// this works
class EnterpriseEmployee extends Employee with AdminEmployee with CollegeDegree with SixSigma
// won't compile
//class EnterpriseEmployee extends Employee with AdminEmployee with CollegeDegree

object TraitsSamples {

  def main(args: Array[String]): Unit = {
    var alphabet = new AlphabetDisplay()
    alphabet.showTitle()
    println(alphabet.showA())
    println(alphabet.showB())
    println(alphabet.showC())
  }
}