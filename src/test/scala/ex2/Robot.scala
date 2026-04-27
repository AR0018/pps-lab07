package ex2

import ex2.Direction.{East, North}
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers

class RobotSpec extends AnyFlatSpec with Matchers:
  def turnTest(robot: Robot): Unit =
    robot.turn(Direction.East)
    robot.direction should be(Direction.East)

    robot.turn(Direction.South)
    robot.direction should be(Direction.South)

    robot.turn(Direction.West)
    robot.direction should be(Direction.West)

    robot.turn(Direction.North)
    robot.direction should be(Direction.North)

  "A SimpleRobot" should "turn correctly" in:
    val robot = new SimpleRobot((0, 0), Direction.North)
    turnTest(robot)

  it should "act correctly" in:
    val robot = new SimpleRobot((0, 0), Direction.North)

    robot.act()
    robot.position should be((0, 1))

    robot.turn(Direction.East)
    robot.act()
    robot.position should be((1, 1))

    robot.turn(Direction.South)
    robot.act()
    robot.position should be((1, 0))

    robot.turn(Direction.West)
    robot.act()
    robot.position should be((0, 0))

  "A RobotWithBattery" should "turn correctly" in:
    val batteryLevel = 20
    val actionCost = 10
    val robot: Robot = RobotWithBattery(SimpleRobot((0, 0), Direction.North), batteryLevel, actionCost)
    turnTest(robot)

  it should "act correctly" in:
    val batteryLevel = 20
    val actionCost = 10
    val robot: Robot = RobotWithBattery(SimpleRobot((0, 0), Direction.North), batteryLevel, actionCost)

    robot.act()
    robot.position shouldBe (0, 1)

    robot.act()
    robot.position shouldBe (0, 2)

    robot.act()
    robot.position shouldBe (0, 2)

  "A RobotRepeated" should "turn correctly" in:
    val repetitionNum = 3;
    val robot: Robot = RobotRepeated(SimpleRobot((0, 0), Direction.North), repetitionNum)
    turnTest(robot)

  it should "act correctly" in:
    val repetitionNum = 3;
    val robot: Robot = RobotRepeated(SimpleRobot((0, 0), Direction.North), repetitionNum)

    robot.act()
    robot.position should be (0, repetitionNum)

    robot.turn(East)
    robot.act()
    robot.position should be (repetitionNum, repetitionNum)

  "A RobotCanFail" should "turn correctly" in :
    val failureProbability = 0.0
    val robot: Robot = RobotCanFail(SimpleRobot((0, 0), Direction.North), failureProbability)
    turnTest(robot)

  it should "act correctly" in:
    val impossibleFailureProbability = 0.0
    val robotNeverFails: Robot = RobotCanFail(SimpleRobot((0, 0), Direction.North), impossibleFailureProbability)

    robotNeverFails.act()
    robotNeverFails.position should be ((0, 1))

    robotNeverFails.act()
    robotNeverFails.position should be ((0, 2))

    val certainFailureProbability = 1.0
    val robotAlwaysFails: Robot = RobotCanFail(SimpleRobot((0, 0), Direction.North), certainFailureProbability)

    robotAlwaysFails.act()
    robotAlwaysFails.position should be((0, 0))

    robotAlwaysFails.act()
    robotAlwaysFails.position should be((0, 0))

// TODO: test combination of robots