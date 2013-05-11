package forcomp

import Anagrams._

object ang {;import org.scalaide.worksheet.runtime.library.WorksheetSupport._; def main(args: Array[String])=$execute{;$skip(73); 
  val w: Word = "Hello";System.out.println("""w  : forcomp.Anagrams.Word = """ + $show(w ));$skip(423); ;
  
  def wordOccurrences(w: Word): Occurrences = {
	  def frecuencies(w: Word): Map[Char, Int] = {
	  
	    def increment(occurrences: Map[Char, Int]): Map[Char, Int] =
	      occurrences ++ Map[Char, Int](w.charAt(0) -> (occurrences(w.charAt(0)) + 1))
	    
	    if (w.size == 0) Map() withDefaultValue(0)
	    else increment(frecuencies(w.substring(1)))
	    
	   }
	   
	  frecuencies(w.toLowerCase()).toList.sorted
  };System.out.println("""wordOccurrences: (w: forcomp.Anagrams.Word)forcomp.Anagrams.Occurrences""");$skip(24); val res$0 = 
  
  wordOccurrences(w);System.out.println("""res0: forcomp.Anagrams.Occurrences = """ + $show(res$0));$skip(36); val res$1 = 
  
  List("hola", "adios").mkString;System.out.println("""res1: String = """ + $show(res$1));$skip(49); 
  
  val dictionary: List[Word] = loadDictionary;System.out.println("""dictionary  : List[forcomp.Anagrams.Word] = """ + $show(dictionary ));$skip(101); 
  
  lazy val dictionaryByOccurrences = dictionary groupBy(wordOccurrences) withDefaultValue(List());System.out.println("""dictionaryByOccurrences: => scala.collection.immutable.Map[forcomp.Anagrams.Occurrences,List[forcomp.Anagrams.Word]]""");$skip(95); 
  def wordAnagram(word: Word): List[Word] =
    dictionaryByOccurrences(wordOccurrences(word));System.out.println("""wordAnagram: (word: forcomp.Anagrams.Word)List[forcomp.Anagrams.Word]""");$skip(26); val res$2 = 
    
  wordAnagram("eat");System.out.println("""res2: List[forcomp.Anagrams.Word] = """ + $show(res$2));$skip(61); 
  
  var occurrences: Occurrences = List(('a', 2), ('b', 2));System.out.println("""occurrences  : forcomp.Anagrams.Occurrences = """ + $show(occurrences ));$skip(220); 
                                                  
  val possibleOccurrences = {
	  for {
	    occurr <- occurrences
	    appearances <- 0 to occurr._2
	  } yield (occurr._1, appearances)}.groupBy((occurr) => occurr._1);System.out.println("""possibleOccurrences  : scala.collection.immutable.Map[Char,List[(Char, Int)]] = """ + $show(possibleOccurrences ));$skip(1281); 
  def combinations(occurrences: Occurrences): List[Occurrences] = {
    def combinations0(occurences: Occurrences): List[Occurrences] = occurrences match {
      case List() => List()
      case occurr::List() => {for (i <- 1 to occurr._2) yield List((occurr._1, i))}.toList
      case occurr::moreOccurrences => {
        val combHead = combinations0(List(occurr))
        val combRest = combinations0(moreOccurrences)
        val combHeadTail = for {
          h <- combHead
          r <- combRest
        } yield h ::: r
        (combHead ::: combRest ::: combHeadTail).toList
      }
    }
    
    def internal_comb2(occurrences: Occurrences):  List[Occurrences] =
        if(occurrences.isEmpty){
          List()
        }else if(occurrences.size == 1){
          val oc = occurrences.head
          val listoccs = for{ i <- 1 to oc._2 } yield List((oc._1, i))
          listoccs.toList
        }else{
          val comb_head = internal_comb2(List(occurrences.head))
          val comb_tail = internal_comb2(occurrences.tail)
          val comb_head_tail = for{
                h <- comb_head
                t <- comb_tail
          } yield h ::: t
          (comb_head ::: comb_tail ::: comb_head_tail).toList
        }
    
    List() :: internal_comb2(occurrences)
  };System.out.println("""combinations: (occurrences: forcomp.Anagrams.Occurrences)List[forcomp.Anagrams.Occurrences]""");$skip(28); val res$3 = 
  combinations(occurrences);System.out.println("""res3: List[forcomp.Anagrams.Occurrences] = """ + $show(res$3))}
}
