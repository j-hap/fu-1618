package ea.ke2._03;

class BinaryNode {
  private BinaryNode leftChild;
  private BinaryNode rightChild;
  private int value;

  public BinaryNode(int v) {
    value = v;
  }

  public boolean contains(int v) {
    if (this.value == v) {
      return true;
    } else if (v < this.value && leftChild != null) {
      return leftChild.contains(v);
    } else if (v > this.value && rightChild != null) {
      return rightChild.contains(v);
    } else {
      return false;
    }
  }

  public void insert(int v) {
    // overhead because the technically the position search also does the contains check
    if (this.contains(v)) {
      // nothing to do
      return;
    }

    // goes down tree to find insert position
    if (v < this.value) {
      if (leftChild == null) {
        leftChild = new BinaryNode(v);
      } else {
        leftChild.insert(v);
      }
    } else {
      if (rightChild == null) {
        rightChild = new BinaryNode(v);
      } else {
        rightChild.insert(v);
      }
    }
  }

  public void inorder() {
    // prints all node values in ascending order
    if (leftChild != null) {
      leftChild.inorder();
    }
    System.out.println(this.value);
    if (rightChild != null) {
      rightChild.inorder();
    }
  }
}