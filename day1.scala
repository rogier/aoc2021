import scala.io.Source

println("hello world")

val numbers = Source.fromFile("input.txt").getLines()
  .map(_.toInt).toSeq

val day1_1 = numbers
  .sliding(2, 1)
  .count { case Seq(a, b) => a < b }

val day1_2 = numbers
  .sliding(3, 1)
  .map(_.sum)
  .sliding(2, 1)
  .count { case Seq(a, b) => a < b }

println(s"day1 1: $day1_1")
println(s"day1 2: $day1_2")

val commands = Source.fromFile("input2.txt").getLines()
  .map(_.split(' '))
  .map{
    case Array(cmd, n) => (cmd, n.toInt)
  }.toSeq

val tuple = commands.foldLeft((0, 0)) {
  case ((down, forward), ("forward", n)) => (down, forward + n)
  case ((down, forward), ("down", n)) => (down + n, forward)
  case ((down, forward), ("up", n)) => (down - n, forward)
}

val triple = commands.foldLeft((0, 0, 0)) {
  case ((down, forward, aim), ("forward", n)) => (down + aim * n, forward + n, aim)
  case ((down, forward, aim), ("down", n)) => (down, forward, aim + n)
  case ((down, forward, aim), ("up", n)) => (down, forward, aim - n)
}

val day2_1 = tuple._1 * tuple._2
val day2_2 = triple._1 * triple._2

println(s"day2 1: $day2_1")
println(s"day2 2: $day2_2")
