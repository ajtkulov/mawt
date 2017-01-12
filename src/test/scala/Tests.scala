import mawt.MinimumAverageWaitingTime._
import mawt.Order
import org.scalatest.FunSuite

class Tests extends FunSuite {
  test("Test-1") {
    assert(handle(List(
      Order(0, 1),
      Order(0, 2),
      Order(0, 3),
      Order(0, 4),
      Order(0, 5)
    )) == 7)
  }

  test("Test-2") {
    assert(handle(List(
      Order(0, 1),
      Order(0, 2),
      Order(0, 3),
      Order(0, 4),
      Order(0, 5)
    )) == 7)
  }

  test("Test-3") {
    assert(handle(List(
      Order(0, 3),
      Order(1, 9),
      Order(2, 5)
    )) == 8)
  }

  test("Test-4") {
    assert(handle(List(
      Order(0, 3),
      Order(1, 9),
      Order(2, 6)
    )) == 9)
  }

  test("Test-5") {
    assert(handle(List(
      Order(0, 1),
      Order(1, 1),
      Order(2, 1),
      Order(3, 1),
      Order(4, 1),
      Order(5, 1)
    )) == 1)
  }

  test("Test-6") {
    assert(handle((1 to 100000).map(x => Order(x, 1))) == 1)
  }

  test("Test-7") {
    assert(handle((1 to 100000).map(x => Order(1, 1))) == 50000)
  }

  test("Runners") {

    println(handle((1 to 100000).map(x => Order(x, x))))
    println(handle((1 to 10000).map(x => Order(x, x))))
    println(handle((1 to 1000).map(x => Order(x, x))))
    println(handle((1 to 100).map(x => Order(x, x))))

    println(handle((1 to 100000).map(x => Order(1, x))))
    println(handle((1 to 10000).map(x => Order(1, x))))
    println(handle((1 to 1000).map(x => Order(1, x))))
    println(handle((1 to 100).map(x => Order(1, x))))

    println(handle((1 to 100000).map(x => Order(1, 1))))
    println(handle((1 to 10000).map(x => Order(1, 1))))
    println(handle((1 to 1000).map(x => Order(1, 1))))
    println(handle((1 to 100).map(x => Order(1, 1))))

    println(handle((1 to 100000).map(x => Order(x / 10, x * 2))))
    println(handle((1 to 10000).map(x => Order(x / 10, x * 2))))
    println(handle((1 to 1000).map(x => Order(x / 10, x * 2))))
    println(handle((1 to 100).map(x => Order(x / 10, x * 2))))
  }
}
