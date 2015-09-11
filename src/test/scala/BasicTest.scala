import collection.mutable.Stack
import org.scalatest._

class BasicTest extends FlatSpec with Matchers {

  "A Stack" should "pop values in last-in-first-out order" in {
    val stack = new Stack[Int]
    stack.push(1)
    stack.push(2)
    stack.pop() should be (2)
    stack.pop() should be (1)
  }

  it should "throw NoSuchElementException if an empty stack is popped" in {
    val emptyStack = new Stack[Int]
    a [NoSuchElementException] should be thrownBy {
      emptyStack.pop()
    }
  }
}
/*
 Different ways of doing unit tests with testspec
  */


class SetSuite extends FunSuite{

  test("An empty set should have sitze 0"){
    assert(Set.empty.size == 0)
  }
}

class SetSpec extends FlatSpec{
  "An empty Set" should "have size 0" in {
    assert(Set.empty.size == 0)
  }
}

class setSpecFunSpec extends FunSpec {

  describe("A Set") {
    describe("when empty"){
      it("should have size o"){
        assert(Set.empty.size == 0)
      }

      it("should produce NoSuchElementException when head is invoked"){
        intercept[NoSuchElementException]{
          Set.empty.head
        }
      }
    }
  }
}