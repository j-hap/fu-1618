package ea.ke5._01;

import java.awt.*;
import java.awt.event.*;

class NumberButton extends Button {
  NumberButton(int value) {
    super(String.valueOf(value));
  }
}

class OperatorButton extends Button {
  OperatorButton(char operator) {
    super(Character.toString(operator));
    this.setForeground(Color.RED);
  }
}

class NumberButtonPressedListener implements ActionListener {
  private final CalculatorCore core;

  @Override
  public void actionPerformed(ActionEvent e) {
    // delegates number appending to core class
    Button b = (Button) e.getSource();
    core.appendToText(b.getLabel());
  }

  public NumberButtonPressedListener(CalculatorCore core) {
    // stores reference to text box, that gets button number appended
    this.core = core;
  }
}

interface Operation {
  int calc(int op1, int op2);
}

class CalculatorCore {
  // core class is the model component of MVC pattern
  private final TextField text;
  private int operand1 = 0;
  private int operand2 = 0;
  private boolean error = false;
  Operation operation = null;

  void appendToText(String str) {
    if (error) {
      resetText();
      error = false;
    }
    text.setText(text.getText() + str);
  }

  void resetText() {
    text.setText("");
  }

  private int getCurrentOperand() {
    try {
      int op = Integer.parseInt(text.getText());
      resetText();
      return op;
    } catch (NumberFormatException ex) {
      error = true;
      text.setText("Invalid Number!");
      return 0;
    }
  }

  public void setOperation(Operation operation) {
    // each time the operation is set the current value in the text field is extracted and the text cleared
    operand1 = getCurrentOperand();
    this.operation = operation;
  }

  public CalculatorCore(TextField text) {
    this.text = text;
  }

  public void calcResult() {
    if (operation == null) {
      // does nothing if operation is not yet set
      return;
    }
    operand2 = getCurrentOperand();
    try {
      int result = operation.calc(operand1, operand2);
      text.setText(String.valueOf(result));
    } catch (ArithmeticException ex) {
      error = true;
      operand1 = 0;
      operand2 = 0;
      text.setText(ex.getMessage());
    }
    // repeated clicks on equals button aren't defined in assignment, so it's disabled
    // by invalidating the set operation
    operation = null;
  }
}

public class Calculator extends Frame {
  private CalculatorCore core;

  Calculator() {
    // Calculator is view and controller component of MVC pattern

    // frame layout
    BorderLayout borderLayoutMain = new BorderLayout();
    this.setLayout(borderLayoutMain);
    this.setSize(150, 200);
    this.setLocation(100, 100);
    this.setBackground(Color.GRAY);

    TextField text = new TextField("");
    text.setBackground(Color.WHITE);
    text.setEditable(false); // disables manual editing of text field
    text.setFocusable(false); // no blinking cursor
    // places text in main layout
    this.add(text, BorderLayout.NORTH);

    core = new CalculatorCore(text);

    // number buttons
    // creates and places numbers in panel with flow layout
    Panel numberPanel = new Panel(new FlowLayout());
    NumberButtonPressedListener numListener = new NumberButtonPressedListener(core);
    for (int i = 0; i < 10; ++i) {
      NumberButton b = new NumberButton(i);
      b.addActionListener(numListener);
      numberPanel.add(b);
    }
    this.add(numberPanel, BorderLayout.CENTER);

    // buttons with actionListeners that modify the operation to be done
    OperatorButton bPlus = new OperatorButton('+');
    bPlus.addActionListener((event) -> core.setOperation((op1, op2) -> Math.addExact(op1, op2)));
    OperatorButton bMinus = new OperatorButton('-');
    bMinus.addActionListener((event) -> core.setOperation((op1, op2) -> Math.subtractExact(op1, op2)));
    OperatorButton bTimes = new OperatorButton('*');
    bTimes.addActionListener((event) -> core.setOperation((op1, op2) -> Math.multiplyExact(op1, op2)));

    OperatorButton bEquals = new OperatorButton('=');
    bEquals.addActionListener((event) -> core.calcResult());

    // places buttons in frame
    Panel operatorPanel = new Panel(new FlowLayout());
    operatorPanel.add(bPlus);
    operatorPanel.add(bMinus);
    operatorPanel.add(bTimes);
    operatorPanel.add(bEquals);
    this.add(operatorPanel, BorderLayout.SOUTH);
  }

  public static void main(String[] args) {
    Calculator calculator = new Calculator();
    calculator.addWindowListener(new WindowAdapter() {
      public void windowClosing(WindowEvent e) {
        System.exit(0);
      }
    });
    calculator.setVisible(true);
  }
}