package com.github.wpm

import scalax.collection.Graph
import scalax.collection.GraphPredef._
import scalax.collection.GraphTraversal.VisitorReturn._

package object ScalaGraphAlgorithm {

  final class RichGraph[N, E[X] <: EdgeLikeIn[X]](g: Graph[N, E]) {
    /**
     * The connected components of this graph
     * @return set of sets of nodes in connected components
     */
    def connectedComponents: Set[Set[N]] = {
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
      }._2.map(_.map(_.asInstanceOf[N]))
    }
  }

  implicit def graphToRichGraph[N, E[X] <: EdgeLikeIn[X]](g: Graph[N, E]) = new RichGraph[N, E](g)
}
