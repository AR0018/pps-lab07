package ex1

import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers
import Parsers.*

class TestParsers extends AnyFlatSpec with Matchers:
  "A BasicParser" should "parse correctly" in:
    def parser = new BasicParser(Set('a', 'b', 'c'))
    parser.parseAll("aabc".toList) should be (true)
    parser.parseAll("aabcdc".toList) should be (false)
    parser.parseAll("".toList) should be (true)

  "A NonEmptyParser" should "parse correctly" in:
    def parserNE = new NonEmptyParser(Set('0', '1'))
    parserNE.parseAll("0101".toList) shouldBe true
    parserNE.parseAll("0123".toList) shouldBe false
    parserNE.parseAll(List()) shouldBe false

  "A NotTwoConsecutiveParser" should "parse correctly" in :
    def parserNTC = new NotTwoConsecutiveParser(Set('X', 'Y', 'Z'))
    parserNTC.parseAll("XYZ".toList) should be (true)
    parserNTC.parseAll("XYYZ".toList) should be (false)
    parserNTC.parseAll("".toList) should be (true)

  "A BasicParser with NotTwoConsecutive and NonEmptyChar" should "parse correctly" in :
    def parserNTCNE = new BasicParser(Set('X', 'Y', 'Z')) with NotTwoConsecutive[Char] with NonEmpty[Char]
    parserNTCNE.parseAll("XYZ".toList) should be (true)
    parserNTCNE.parseAll("XYYZ".toList) should be (false)
    parserNTCNE.parseAll("".toList) should be (false)

  "A StringParser" should "parse correctly" in:
    def sparser = "abc".charParser()
    sparser.parseAll("aabc".toList) should be (true)
    sparser.parseAll("aabcdc".toList) should be (false)
    sparser.parseAll("".toList) should be (true)
