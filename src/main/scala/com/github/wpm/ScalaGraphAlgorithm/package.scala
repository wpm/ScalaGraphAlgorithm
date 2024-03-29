package com.github.wpm

import scalax.collection.Graph
import scalax.collection.GraphTraversal.VisitorReturn._
import scalax.collection.GraphEdge.UnDiEdge

package object ScalaGraphAlgorithm {

  final class RichUndirectedGraph[N, E[X] >: UnDiEdge[X] <: UnDiEdge[X]](val g: Graph[N, E]) {
    /**
     * The connected components of this graph
     * @return set of sets of nodes in connected components
     */
    def connectedComponents: Set[Set[g.NodeT]] = {
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

  implicit def graphToRichGraph[N, E[X] >: UnDiEdge[X] <: UnDiEdge[X]](g: Graph[N, E]) =
    new RichUndirectedGraph[N, E](g)
}
