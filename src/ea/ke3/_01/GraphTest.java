package ea.ke3._01;

public class GraphTest {
  static public void main(String[] args) throws GraphException {
    String[] countries = {"D", "F", "S"};
    Graph g = new Graph(countries);
    printMatrix(g);
    g.addEdge(0, 1);
    g.addEdge("F", "S");
    printMatrix(g);
    // g.addEdge("F", "Y"); // expected error is thrown
    System.out.println(g.getNode(0));
    System.out.println(g.getNode(1));
    System.out.println(g.getNode(2));
    // System.out.println(g.getNode(3)); // error works
    System.out.println(g.getNumberOfNodes());
    printNames(g);
    String[] newNames = {"Germany", "France", "Spain", "Poland"};
    g.setNodes(newNames);
    printNames(g);
  }

  static private void printNames(Graph g) throws GraphException {
    for (int iNode = 0; iNode < g.getNumberOfNodes(); ++iNode) {
      System.out.println(g.getNode(iNode));
    }
  }

  static private void printMatrix(Graph g) throws GraphException {
    for (int iRow = 0; iRow < g.getNumberOfNodes(); ++iRow) {
      for (int iCol = 0; iCol < g.getNumberOfNodes(); ++iCol) {
        System.out.print(g.isAdjacent(iRow,iCol) + " ");
      }
      System.out.println();
    }
  }
}