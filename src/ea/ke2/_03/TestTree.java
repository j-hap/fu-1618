package ea.ke2._03;

public class TestTree {
  public static void main(String[] args) {
    BinaryNode myTree = new BinaryNode(6);
    myTree.insert(5);
    myTree.insert(4);
    myTree.insert(12);
    myTree.insert(11);
    myTree.insert(10);
    myTree.inorder();
  }
}

