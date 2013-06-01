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
        var component = Set[N]()

        def visit(n: g.NodeT) = {
          component += n.asInstanceOf[N]
          Continue
        }
        n.traverseNodes()(visit)
        component
      }

      ((Set[N](), Set[Set[N]]()) /: g.nodes) {
        case ((visited, ccs), n) =>
          if (visited.contains(n.asInstanceOf[N])) // Skip a node if it is already part of a component.
            (visited, ccs)
          else (visited ++ connectedComponent(n), ccs + connectedComponent(n))
      }._2
    }
  }

  implicit def graphToRichGraph[N, E[X] <: EdgeLikeIn[X]](g: Graph[N, E]) = new RichGraph[N, E](g)
}
