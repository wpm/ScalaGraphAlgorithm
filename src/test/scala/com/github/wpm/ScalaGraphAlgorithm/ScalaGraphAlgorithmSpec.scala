package com.github.wpm.ScalaGraphAlgorithm

import org.scalatest.FlatSpec
import scalax.collection.Graph
import scalax.collection.GraphPredef._

class ScalaGraphAlgorithmSpec extends FlatSpec {
  "A graph with components 1 ~ 2 ~ 3, 4 ~ 5, 6" should "return those components" in {
    expectResult(Set(Set(1, 2, 3), Set(4, 5), Set(6))) {
      ScalaGraphAlgorithm.connectedComponents(Graph(1 ~ 2 ~ 3, 4 ~ 5, 6))
    }
  }
}
