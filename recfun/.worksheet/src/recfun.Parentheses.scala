package recfun

object Parentheses {;import org.scalaide.worksheet.runtime.library.WorksheetSupport._; def main(args: Array[String])=$execute{;$skip(80); 
  println("Welcome to the Scala worksheet");$skip(198); 
  
  def balanceAux(acc: Int, pos: Int, chars: List[Char]) : Boolean =
  	if (acc < 0) false
  	else if (pos >= chars.length) acc == 0
  	else balanceAux(acc + evaluate(pos, chars), pos + 1, chars);System.out.println("""balanceAux: (acc: Int, pos: Int, chars: List[Char])Boolean""");$skip(128); 
  	
  def evaluate(pos: Int, chars: List[Char]) : Int =
  	if (chars(pos) == '(') 1
  	else if (chars(pos) == ')') -1
  	else 0;System.out.println("""evaluate: (pos: Int, chars: List[Char])Int""");$skip(74); 
  
  def balance(chars: List[Char]): Boolean =
  	balanceAux(0, 0, chars);System.out.println("""balance: (chars: List[Char])Boolean""");$skip(29); val res$0 = 
  	
  balance("())(".toList);System.out.println("""res0: Boolean = """ + $show(res$0))}
}
