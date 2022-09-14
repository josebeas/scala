package examples


object Immutables {
  def main(args: Array[String]): Unit = {
    var listA = List("A", "B")
    var list1 = List(1, 2)
    println(listA)
    println(list1)
    //since list is immutable, no updates allowed
    //this line will throw: `value update is not a member of List[String]`
    //listA(1) = "D"

    //common functions for Seq, List, Map
    println(listA.head) //returns first element
    print(listA.tail) //returns last element
    println(listA.take(1)) //returns a list with first n elements
    println(listA.takeRight(1)) //returns a list with last n elements
    println(listA.isEmpty) //returns true is list does not contains elements

    // concat lists using ::: operator
    println(listA:::list1)

    // concat lists using ::: operator
    println(listA.concat(list1))

    // concat lists using ::: operator
    println(listA.:::(list1))

    // adding new elements, this will add listA as an element of list D, instead of concat
    val letterD = List("D")
    println(listA::letterD)

    //creating uniform lists,
    // this will create as first parameter number of elements of second parameter
    val seven1 = List.fill(7)(1)
    println(seven1)

    // tabulate function
    //creates a list of 7 elements with the result of given function, using each list member as parameter
    val twice = List.tabulate(7)(n => n*2)
    println(twice)

    //we can send two parameters to tabulate function:
    // first parameter indicates number of lists, second number of elements
    // then function that will be applied to each element
    val threeLists = List.tabulate(3, 7)(_ * _)
    println(threeLists)

    //Maps typed key, value pairs
    //declaring a empty map
    var emptyMap : Map[String, Int] = Map()
    var mapWithValues = Map("Jose" -> 35, "Bastian" -> 2)

    //operations
    println(mapWithValues.keys) //get keys which in fact is a set since no dupes allowed
    mapWithValues.keys.foreach(i => println(mapWithValues(i))) //prints all values using the key
    println(mapWithValues("Bastian")) //returns value for given key

    //creates a string builder with all maps element and uses specified separator if present
    var sb = mapWithValues.addString(new StringBuilder)
    var sbSeparator = mapWithValues.addString(new StringBuilder, "/--/")
    println(sb)
    println(sbSeparator)

  }
}
