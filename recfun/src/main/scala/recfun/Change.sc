package recfun

object Change {
  println("Welcome to the Scala worksheet")       //> Welcome to the Scala worksheet
  
  def countChange(money: Int, coins: List[Int]): Int =
  	if (money == 0) 1
  	else if (money < 0) 0
  	else if (coins.isEmpty) 0
  	else if (money < coins.head) countChange(money, coins.tail)
  	else countChange(money, coins.tail) + countChange(money - coins.head, coins)
                                                  //> countChange: (money: Int, coins: List[Int])Int

	countChange(4, List(1,2))                 //> res0: Int = 3
}