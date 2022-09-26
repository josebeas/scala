package examples

//Apply and unapply methods
class Person {
  var name: Option[String] = None

  var age: Option[Int] = None
  // this method prints `Some(Pedro Picapiedra), None` since it prints  the object itself,
  // if we wanna print the value we can use .get
  // but we will get `java.util.NoSuchElementException: None.get` for non-existing values (None)
  override def toString = s"${name}, ${age}"
}

object Person {

  // a one-arg constructor
  def apply(name: Option[String]): Person = {
    var p = new Person
    p.name = name
    p
  }

  // a two-arg constructor
  def apply(name: Option[String], age: Option[Int]): Person = {
    var p = new Person
    p.name = name
    p.age = age
    p
  }

  //def unapply(p: Person): String = s"${p.name}, ${p.age}"

  def unapply(p: Person): Tuple2[Option[String], Option[Int]] = (p.name, p.age)

  def main(args: Array[String]): Unit = {
    // Companion Objects
    // Objects with a class with same name
    // They share access to private members
    val p1 = Person.apply(Some("Pedro Picapiedra"))
    println(p1)

    //implicitly calls apply method
    val p2 = Person(Option("Pedro Picapiedra"))
    //val p9 = Person(Some(None))
    println(p2)

    // same as p1
    val p3 = Person(Some("Pedro Picapiedra"))
    println(p3)

    // Setting no value at all for both members, indirectly
    val p4 = Person(None)
    println(p4)

    //Using two arg constructor setting a value for second parameter
    val p5 = Person(Some("Wilma"), Some(33000000))
    println(p5)

    //Using two arg constructor setting second parameter as None
    val p6 = Person(Some("Wilma"), None)
    println(p6)

    //Building a immutable list of persons
    val teamMembers = List(
      Person(Some("Arturo")),
      Person(Some("Jose"), Some(35))
    )
    println(teamMembers)

    //Extracting values from objects using unapply
    val result = Person.unapply(p5)
    println(result)
    // prints Some(Wilma), Some(33000000) since unapply returns similar value to toString method
    // change unapply to look like this and uncomment next prints statements
    //  def unapply(p: Person): Tuple2[String, Int] = (p.name, p.age)
    println(result._1.get) //make sure you check element _1 exists
    println(result._2.get) //make sure you check element _2 exists
    println(if (result._2 != null) result._2.get else -1) // you can check element _2 exists using shorthand if

    //Since we defined this method as no return method this line has no effect
    p1
  }
}