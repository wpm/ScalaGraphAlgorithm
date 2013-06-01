package com.github.wpm.ScalaGraphAlgorithm

import scalax.collection.Graph
import scalax.collection.GraphPredef._
import scalax.collection.GraphTraversal.VisitorReturn._

// The E[X] type parameter produces a warning unless we import scala.language.higherKinds.

import scala.language.higherKinds


object ScalaGraphAlgorithm {

  /**
   * Find the connected components of a graph
   * @param g graph
   * @tparam N node type
   * @tparam E edge type
   * @return set of sets of nodes in connected components
   */
  def connectedComponents[N, E[X] <: EdgeLikeIn[X]](g: Graph[N, E]): Set[Set[g.NodeT]] = {
    def connectedComponent(n: g.NodeT) = {
      var component = Set[g.NodeT]()

      def visit(n: g.NodeT) = {
        component += n
        Continue
      }
      n.traverseNodes()(visit)
      component
    }

    ((Set[g.NodeT](), Set[Set[g.NodeT]]()) /: g.nodes) {
      case ((visited, ccs), n) =>
        if (visited.contains(n)) // Skip a node if it is already part of a component.
          (visited, ccs)
        else (visited ++ connectedComponent(n), ccs + connectedComponent(n))
    }._2
  }
}
