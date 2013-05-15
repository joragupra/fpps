package streams

import org.scalatest.FunSuite
import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner
import Bloxorz._
import com.sun.org.apache.xml.internal.serializer.ToStream

@RunWith(classOf[JUnitRunner])
class BloxorzSuite extends FunSuite {

  trait SolutionChecker extends GameDef with Solver with StringParserTerrain {
    /**
     * This method applies a list of moves `ls` to the block at position
     * `startPos`. This can be used to verify if a certain list of moves
     * is a valid solution, i.e. leads to the goal.
     */
    def solve(ls: List[Move]): Block =
      ls.foldLeft(startBlock) { case (block, move) => move match {
        case Left => block.left
        case Right => block.right
        case Up => block.up
        case Down => block.down
      }
    }
  }

  trait Level1 extends SolutionChecker {
      /* terrain for level 1*/

    val level =
    """ooo-------
      |oSoooo----
      |ooooooooo-
      |-ooooooooo
      |-----ooToo
      |------ooo-""".stripMargin

    val optsolution = List(Right, Right, Down, Right, Right, Right, Down)
    
    val testBlock = Block(Pos(1,1), Pos(1,1))
    
    val testBlock2 = Block(Pos(1,2),Pos(1,3))
    val testBlock3 = Block(Pos(2,1),Pos(3,1))
    
    val initialHistory = List(Left, Up)
    
    val initialHistory2 = List(Right,Left,Up)
    val initialHistory3 = List(Down,Left,Up)
    
    val exploredAlready = Set(Block(Pos(1,2),Pos(1,3)), Block(Pos(1,1),Pos(1,1)))
    
  }

  test("terrain function level 1") {
    new Level1 {
      assert(terrain(Pos(0,0)), "0,0")
      assert(!terrain(Pos(4,11)), "4,11")
    }
  }
  
  test("goal") {
    new Level1 {
      assert(goal.x==4, "x=4")
      assert(goal.y==7, "y=7")
    }
  }

  test("findChar level 1") {
    new Level1 {
      assert(startPos == Pos(1,1))
    }
  }
  
  test("neighbors with history") {
    new Level1 {
      assert(neighborsWithHistory(testBlock, initialHistory).size==2)
      assert(neighborsWithHistory(testBlock, initialHistory).contains((Block(Pos(1,2),Pos(1,3)), List(Right,Left,Up))))
      assert(neighborsWithHistory(testBlock, initialHistory).contains((Block(Pos(2,1),Pos(3,1)), List(Down,Left,Up))))
    }
  }
  
  test("new neighbors only") {
    new Level1 {
      assert(newNeighborsOnly(
          Set((testBlock2, initialHistory2), (testBlock3, initialHistory3)) toStream,
          exploredAlready) equals(Set((testBlock3, initialHistory3)) toStream))
    }
  }

  test("optimal solution for level 1") {
    new Level1 {
      assert(solve(solution) == Block(goal, goal))
    }
  }

  test("optimal solution length for level 1") {
    new Level1 {
      assert(solution.length == optsolution.length)
    }
  }
}
