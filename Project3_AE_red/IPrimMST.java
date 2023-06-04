import java.util.List;

/**
 * This interface extends GraphADT to implement Prim's Algorithm to find the minimum
 * spanning tree for flights in our graph.
 */
public interface IPrimMST<NodeType, EdgeType extends Number> extends GraphADT<NodeType, EdgeType> {
  /**
   * This method finds a minimum spanning tree using Prim's algorithm and adds the flight
   * relationships between cities to an ArrayList to be printed out.
   * 
   * @param startingNode The node to branch out from to start the minimum
   *        spanning tree
   * @return An ArrayList containing strings with the format "(Starting City) -> (Ending City).
   *         For example if there is a flight from Chicago to New York in the MST one of the
   *         strings would be Chicago -> New York
   */
  public List<String> getMST(NodeType startingNode);
}
