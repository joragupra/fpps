package funsets

object Main extends App {
  import FunSets._
  println(contains(singletonSet(1), 1))
  
  val all = union(singletonSet(1), union(singletonSet(2), singletonSet(3)))
  printSet(all)
  printSet(map(all, (x: Int) => x * 2))
  printSet(map(all, (x: Int) => -x))
}
