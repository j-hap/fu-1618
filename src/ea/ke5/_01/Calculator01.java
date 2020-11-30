//package ea.ke5._01;
//
//import java.awt.*;
//import java.awt.event.*;
//
//class NumberButtonPressedListener implements ActionListener {
//  private final TextField text;
//
//  @Override
//  public void actionPerformed(ActionEvent e) {
//    Button b = (Button) e.getSource();
//    text.setText(text.getText() + b.getLabel());
//  }
//
//  public NumberButtonPressedListener(TextField text) {
//    // stores reference to text box, that gets button number appended
//    this.text = text;
//  }
//}
//
//class OperatorButtonPressedListener implements ActionListener {
//  private final TextField text;
//  private int operand1;
//  private String operation;
//
//  @Override
//  public void actionPerformed(ActionEvent e) {
//    int currentNumber;
//
//    try {
//      currentNumber = Integer.parseInt(text.getText());
//    } catch (NumberFormatException ex) {
//      ex.printStackTrace();
//      text.setText("");
//      return;
//    }
//
//    Button b = (Button) e.getSource();
//    if (b.getLabel().equals("=")) {
//      int result = 0;
//      try {
//        switch (operation) {
//          // only first operand can be negative, if the result from a previous operation is used
//          case "+":
//            result = Math.addExact(operand1, currentNumber);
//            break;
//          case "-":
//            result = Math.subtractExact(operand1, currentNumber);
//            break;
//          case "*":
//            result = Math.multiplyExact(operand1, currentNumber);
//            break;
//          default:
//            throw new IllegalStateException("Unexpected value: " + operation);
//        }
//        text.setText(String.valueOf(result));
//      } catch (ArithmeticException ex) {
//        ex.printStackTrace();
//        text.setText("");
//      }
//    } else {
//      // stores first operand and desired operation
//      operand1 = currentNumber;
//      operation = b.getLabel();
//      text.setText("");
//    }
//  }
//
//  public OperatorButtonPressedListener(TextField text) {
//    // stores reference to text box to extract current string
//    this.text = text;
//  }
//}
//
//class NumberButton extends Button {
//  NumberButton(int value) {
//    super(String.valueOf(value));
//  }
//}
//
//class OperatorButton extends Button {
//  OperatorButton(char operator) {
//    super(Character.toString(operator));
//    this.setForeground(Color.RED);
//  }
//}
//
//public class Calculator01 extends Frame {
//  public Calculator01() {
//    // frame layout
//    BorderLayout borderLayoutMain = new BorderLayout();
//    this.setLayout(borderLayoutMain);
//    this.setSize(150, 200);
//    this.setLocation(100, 100);
//    this.setBackground(Color.gray);
//
//    TextField text = new TextField("");
//    text.setBackground(Color.WHITE);
//    // disables manual editing of text field
//    text.setEditable(false);
//    // places text in main layout
//    this.add(text, BorderLayout.NORTH);
//
//    // creates and places numbers in panel with flow layout
//    Panel numberPanel = new Panel(new FlowLayout());
//    NumberButtonPressedListener numListener = new NumberButtonPressedListener(text);
//    for (int i = 0; i < 10; ++i) {
//      NumberButton b = new NumberButton(i);
//      b.addActionListener(numListener);
//      numberPanel.add(b);
//    }
//    this.add(numberPanel, BorderLayout.CENTER);
//
//    // creates and places operator buttons
//    Panel operatorPanel = new Panel(new FlowLayout());
//    OperatorButtonPressedListener opListener = new OperatorButtonPressedListener(text);
//    for (char c : "+-*=".toCharArray()) {
//      OperatorButton b = new OperatorButton(c);
//      b.addActionListener(opListener);
//      operatorPanel.add(b);
//    }
//    this.add(operatorPanel, BorderLayout.SOUTH);
//  }
//
//  public static void main(String[] args) {
//    Calculator01 calculator = new Calculator01();
//    calculator.addWindowListener(new WindowAdapter() {
//      public void windowClosing(WindowEvent e) {
//        System.exit(0);
//      }
//    });
//    calculator.setVisible(true);
//  }
//}