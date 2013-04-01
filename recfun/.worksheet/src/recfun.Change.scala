package recfun

object Change {;import org.scalaide.worksheet.runtime.library.WorksheetSupport._; def main(args: Array[String])=$execute{;$skip(75); 
  println("Welcome to the Scala worksheet");$skip(276); 
  
  def countChange(money: Int, coins: List[Int]): Int =
  	if (money == 0) 1
  	else if (money < 0) 0
  	else if (coins.isEmpty) 0
  	else if (money < coins.head) countChange(money, coins.tail)
  	else countChange(money, coins.tail) + countChange(money - coins.head, coins);System.out.println("""countChange: (money: Int, coins: List[Int])Int""");$skip(28); val res$0 = 

	countChange(4, List(1,2));System.out.println("""res0: Int = """ + $show(res$0))}
}
