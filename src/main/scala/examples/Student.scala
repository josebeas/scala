package examples

// defines a kind POJO with implicit methods such as constructor, getter, toString, copy etc
case class Student(name:String, age:Int, score:Double)


object CaseClass {
  def main(args: Array[String]): Unit = {
    // This line does not compile since default constructor requires two parameters
    //val Foy = Student("Foy")

    // calls out the default constructor, the only one available
    var jose = Student("Jose", 35, 82.4)

    // this line fails to compile also since case class are immutable, means does not change any of its values
    //jose.name = "Jose Beas"

    // creates a copy making use of default constructor and named parameters,
    // keeps original values for not specified properties
    var student1 = jose.copy(name="New Name")

    //making a copy and compare objects, they are equal
    var student2 = student1.copy()
    println("Equals? " + (student1 == student2))

    // even if they are different objects, if the content is equal, they are equal too
    var student3 =  Student("Jose", 35, 82.4)
    println("Equals? " + (jose == student3))

    //updating original object, will affect copied one?
    // remember:
    //  var student2 = student1.copy() and
    //  var student1 = jose.copy(name="New Name")
    // no, since they are different objects
    student1 = Student("Jose B", 35, 82.4)
    println("original " + student1)
    println("copy " + student2)
    //so they are no longer equals
    println("Still Equals? " + (student1 == student2))

    //Calls out the getter of name property
    println(jose.name)

    //Calls the toString method
    println(student1)
  }
}