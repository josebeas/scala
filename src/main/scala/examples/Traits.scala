package examples

trait ShowA {
  def showA() :String = {
    "A"
  }
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
class Alphabet extends ShowA with ShowB with ShowC {
  override def showC(): String = {
    "C"
  }

  override def showB(): String = {
    "B"
  }

}

object TraitsSamples {

  def main(args: Array[String]): Unit = {
    var alphabet = new Alphabet()
    println(alphabet.showA())
    println(alphabet.showB())
    println(alphabet.showC())
  }
}