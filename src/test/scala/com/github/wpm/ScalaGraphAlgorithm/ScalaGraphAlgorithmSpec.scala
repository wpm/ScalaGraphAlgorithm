package com.github.wpm.ScalaGraphAlgorithm

import org.scalatest.FlatSpec
import scalax.collection.Graph
import scalax.collection.GraphPredef._

class ScalaGraphAlgorithmSpec extends FlatSpec {
  "A graph with edges 1 ~ 2, 1 ~ 3, 4 ~ 5 and a node 6" should "have components {1,2,3}, {4,5}, and {6}" in {
    expectResult(Set(Set(1, 2, 3), Set(4, 5), Set(6))) {
      Graph(1 ~ 2, 1 ~ 3, 4 ~ 5, 6).connectedComponents
    }
  }
}
