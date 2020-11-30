package ea.ke3._01;

public class Graph {
  protected boolean[][] adjacencyMatrix;
  protected String[] nodes;

  protected int indexOf(String node) throws GraphException {
    for (int iNode = 0; iNode < nodes.length; ++iNode) {
      if (node.equals(nodes[iNode])) {
        return iNode;
      }
    }
    // node is not in list
    throw new GraphException();
  }

  private boolean isValidIndex(int index) {
    return index < nodes.length || index > 0;
  }

  public Graph(int nodeNumber) {
    nodes = new String[nodeNumber];
    adjacencyMatrix = new boolean[nodeNumber][nodeNumber];
  }

  public Graph(String[] nodes) {
    this(nodes.length);
    for (int iNode = 0; iNode < nodes.length; ++iNode) {
      this.nodes[iNode] = nodes[iNode];
    }
  }

  public String getNode(int index) throws GraphException {
    // returns name of node at given position. Throws GraphException if position does not exist.
    if (isValidIndex(index)) {
      return nodes[index];
    } else {
      throw new GraphException();
    }
  }

  public int getNumberOfNodes() {
    return nodes.length;
  }

  public void setNodes(String[] nodes) {
    int iMax;
    if (nodes.length < this.nodes.length) {
      iMax = nodes.length;
    } else {
      iMax = this.nodes.length;
    }
    for (int iNode = 0; iNode < iMax; ++iNode) {
      this.nodes[iNode] = nodes[iNode];
    }
  }

  public boolean isAdjacent(int index1, int index2) throws GraphException {
    if (isValidIndex(index1) && isValidIndex(index2)) {
      // this works, because the matrix default initialized to all false
      return adjacencyMatrix[index1][index2];
    } else {
      throw new GraphException();
    }
  }

  public boolean isAdjacent(String node1, String node2) throws GraphException {
    return isAdjacent(indexOf(node1), indexOf(node2));
  }

  public void addEdge(int index1, int index2) throws GraphException {
    // adds a graph connection for given node indices
    if (isValidIndex(index1) && isValidIndex(index2)) {
      adjacencyMatrix[index1][index2] = true;
      adjacencyMatrix[index2][index1] = true; // we don't really need to keep symmetry for the implemented methods
    } else {
      // index out of bounds
      throw new GraphException();
    }
  }

  public void addEdge(String node1, String node2) throws GraphException {
    // adds a graph connection for given node names
    addEdge(indexOf(node1), indexOf(node2));
  }
}