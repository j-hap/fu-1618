package ea.ke7._02;

import java.io.*;
import java.net.Socket;

class Solver {
  public int solve(String instruction) {
    String[] parts = instruction.split(" ");
    int operand1 = Integer.parseInt(parts[0]);
    int operand2 = Integer.parseInt(parts[2]);
    char operator = parts[1].charAt(0);
    switch (operator) {
      case '+':
        return Math.addExact(operand1, operand2);
      case '-':
        return Math.subtractExact(operand1, operand2);
      case '*':
        return Math.multiplyExact(operand1, operand2);
      case '/':
        return operand1 / operand2;
      default:
        return 0;
    }
  }
}

public class Worker extends Thread {
  private final String SERVER;
  private final int PORT;
  private Socket socket;
  private Solver solver = new Solver();

  public Worker(String serverAddress, int port) {
    this.SERVER = serverAddress;
    this.PORT = port;
  }

  private void connectToServer() {
    try {
      socket = new Socket(SERVER, PORT);
    } catch (IOException e) {
      System.out.println("Failed to connect to server.");
      System.exit(1);
    }
  }

  private BufferedReader getInputReader() {
    try {
      return new BufferedReader(new InputStreamReader(socket.getInputStream(), "UTF-8"));
    } catch (IOException e) {
      System.out.println("Failed to get input stream from socket.");
      System.exit(1);
    }
    return null;
  }

  private BufferedWriter getOutputWriter() {
    try {
      return new BufferedWriter(new OutputStreamWriter(socket.getOutputStream(), "UTF-8"));
    } catch (IOException e) {
      System.out.println("Failed to get output stream from socket.");
      System.exit(1);
    }
    return null;
  }

  @Override
  public void start() {
    connectToServer();
    super.start();
  }

  private int solveInstruction(String instruction) {
    instruction = instruction.replace("AUFGABE", "");
    System.out.println("<== " + instruction);
    int result = solver.solve(instruction);
    System.out.println("==> " + result);
    return result;
  }

  @Override
  public void run() {
    boolean done = false;
    try (
            BufferedReader fromServer = getInputReader();
            BufferedWriter toServer = getOutputWriter();
    ) {
      while (!done) {
        if (fromServer.ready()) {
          String instruction = fromServer.readLine();
          if (instruction.equals("ENDE")) {
            done = true;
          } else if (instruction.startsWith("INFO")) {
            System.out.println(instruction.substring(4));
          } else {
            int result = solveInstruction(instruction);
            toServer.write(String.valueOf(result) + '\n');
            toServer.flush(); // send the message
          }
        }
      }
      System.out.println("Task completed.");
      socket.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public static void main(String[] args) {
    new Worker("mpaap.mooo.com", 7896).start();
  }
}
