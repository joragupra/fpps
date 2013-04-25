package funsets

import org.scalatest.FunSuite

import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner

/**
 * This class is a test suite for the methods in object FunSets. To run
 * the test suite, you can either:
 *  - run the "test" command in the SBT console
 *  - right-click the file in eclipse and chose "Run As" - "JUnit Test"
 */
@RunWith(classOf[JUnitRunner])
class FunSetSuite extends FunSuite {


  /**
   * Link to the scaladoc - very clear and detailed tutorial of FunSuite
   *
   * http://doc.scalatest.org/1.9.1/index.html#org.scalatest.FunSuite
   *
   * Operators
   *  - test
   *  - ignore
   *  - pending
   */

  /**
   * Tests are written using the "test" operator and the "assert" method.
   */
  test("string take") {
    val message = "hello, world"
    assert(message.take(5) == "hello")
  }

  /**
   * For ScalaTest tests, there exists a special equality operator "===" that
   * can be used inside "assert". If the assertion fails, the two values will
   * be printed in the error message. Otherwise, when using "==", the test
   * error message will only say "assertion failed", without showing the values.
   *
   * Try it out! Change the values so that the assertion fails, and look at the
   * error message.
   */
  test("adding ints") {
    assert(1 + 2 === 3)
  }

  
  import FunSets._

  test("contains is implemented") {
    assert(contains(x => true, 100))
  }
  
  /**
   * When writing tests, one would often like to re-use certain values for multiple
   * tests. For instance, we would like to create an Int-set and have multiple test
   * about it.
   * 
   * Instead of copy-pasting the code for creating the set into every test, we can
   * store it in the test class using a val:
   * 
   *   val s1 = singletonSet(1)
   * 
   * However, what happens if the method "singletonSet" has a bug and crashes? Then
   * the test methods are not even executed, because creating an instance of the
   * test class fails!
   * 
   * Therefore, we put the shared values into a separate trait (traits are like
   * abstract classes), and create an instance inside each test method.
   * 
   */

  trait TestSets {
    val s1 = singletonSet(1)
    val s2 = singletonSet(2)
    val s3 = singletonSet(3)
    val s1Us2 = union(s1, s2)
    val s1Us3 = union(s1, s3)
    val s1Us2Us3 = union(s1, union(s2, s3))
  }

  /**
   * This test is currently disabled (by using "ignore") because the method
   * "singletonSet" is not yet implemented and the test would fail.
   * 
   * Once you finish your implementation of "singletonSet", exchange the
   * function "ignore" by "test".
   */
  test("singletonSet(1) contains 1") {
    
    /**
     * We create a new instance of the "TestSets" trait, this gives us access
     * to the values "s1" to "s3". 
     */
    new TestSets {
      /**
       * The string argument of "assert" is a message that is printed in case
       * the test fails. This helps identifying which assertion failed.
       */
      assert(contains(s1, 1), "Singleton")
    }
  }

  test("union contains all elements") {
    new TestSets {
      val s = union(s1, s2)
      assert(contains(s, 1), "Union 1")
      assert(contains(s, 2), "Union 2")
      assert(!contains(s, 3), "Union 3")
    }
  }
  
  test("intersection contains only common elements") {
    new TestSets {
      val s = intersect(s1Us2, s1Us3)
      assert(contains(s, 1), "Intersection should contain 1")
      assert(!contains(s, 2), "Intersection should not contain 2")
      assert(!contains(s, 3), "Intersection should not contain 3")
    }
  }
  
  test("difference excludes second set elements") {
    new TestSets {
      val s = diff(s1Us2, s2)
      assert(contains(s, 1), "1 is the only remaining element in difference")
      assert(!contains(s, 2), "2 should not be in difference set")
    }
  }
  
  test("filter fuction excludes elements which does not satisfy predicate") {
    new TestSets {
      val s = filter(s1Us2Us3, (x: Int) => x > 2)
      assert(contains(s, 3), "3 is the only element greater than 2")
      assert(!contains(s, 1), "1 is not greater than 2")
      assert(!contains(s, 2), "2 is not greater than 2")
      assert(!contains(s, 4), "4 is not a set member")
    }
  }
  
  test("elements in union of s1 and s2 and s3 are less than 10") {
    new TestSets {
      assert(forall(s1Us2Us3, (x: Int) => x < 10), "All elements in union of s1 and s2 and s3 are less than 100")
    }
  }
  
  test("not all elements in union of s1 and s2 and s3 are odd") {
    new TestSets {
      assert(!forall(s1Us2Us3, (x: Int) => (x % 2) == 1), "2 is even")
    }
  }
  
  test("exist odd elements in union of s1 and s2 and s3") {
    new TestSets {
      assert(exists(s1Us2Us3, (x: Int) => x%2==1), "1 and 3 are even numbers")
    }
  }
  
  test("no elements greater than 10") {
    new TestSets {
      assert(!exists(s1Us2Us3, (x: Int) => x > 10), "1, 2 and 3 are not greater than 10")
    }
  }
  
  test("doubled set") {
    new TestSets {
      val doubleS = map(s1Us2Us3, (x: Int) => x * 2)
      assert(contains(doubleS, 2), "2 is double of 1")
      assert(contains(doubleS, 4), "4 is double of 2")
      assert(contains(doubleS, 6), "6 is double of 3")
    }
  }
  
  test("set negation") {
    new TestSets {
      val negationS = map(s1Us2Us3, (x: Int) => -x)
      assert(contains(negationS, -1), "1 was not negated")
      assert(contains(negationS, -2), "2 was not negated")
      assert(contains(negationS, -3), "3 was not negated")
    }
  }
}
