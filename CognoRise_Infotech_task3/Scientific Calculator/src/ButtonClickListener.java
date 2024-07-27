import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

public class ButtonClickListener implements ActionListener {
    private JTextField display;
    private StringBuilder input;

    private static final ScriptEngine engine = new ScriptEngineManager().getEngineByName("JavaScript");

    public ButtonClickListener(JTextField display, StringBuilder input) {
        this.display = display;
        this.input = input;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton source = (JButton) e.getSource();
        String command = source.getText();

        switch (command) {
            case "C":
                input.setLength(0);
                display.setText("");
                break;
            case "Del":
                if (input.length() > 0) {
                    input.setLength(input.length() - 1);
                    display.setText(input.toString());
                }
                break;
            case "=":
                try {
                    String result = evaluate(input.toString());
                    display.setText(result);
                    input.setLength(0);
                    input.append(result);
                } catch (Exception ex) {
                    display.setText("Error");
                }
                break;
            case "sqrt":
                try {
                    double result = Math.sqrt(Double.parseDouble(input.toString()));
                    display.setText(String.valueOf(result));
                    input.setLength(0);
                    input.append(result);
                } catch (Exception ex) {
                    display.setText("Error");
                }
                break;
            case "pow":
                input.append("**");
                display.setText(input.toString());
                break;
            case "sin":
                try {
                    double result = Math.sin(Math.toRadians(Double.parseDouble(input.toString())));
                    display.setText(String.valueOf(result));
                    input.setLength(0);
                    input.append(result);
                } catch (Exception ex) {
                    display.setText("Error");
                }
                break;
            case "cos":
                try {
                    double result = Math.cos(Math.toRadians(Double.parseDouble(input.toString())));
                    display.setText(String.valueOf(result));
                    input.setLength(0);
                    input.append(result);
                } catch (Exception ex) {
                    display.setText("Error");
                }
                break;
            case "tan":
                try {
                    double result = Math.tan(Math.toRadians(Double.parseDouble(input.toString())));
                    display.setText(String.valueOf(result));
                    input.setLength(0);
                    input.append(result);
                } catch (Exception ex) {
                    display.setText("Error");
                }
                break;
            case "log":
                try {
                    double result = Math.log10(Double.parseDouble(input.toString()));
                    display.setText(String.valueOf(result));
                    input.setLength(0);
                    input.append(result);
                } catch (Exception ex) {
                    display.setText("Error");
                }
                break;
            case "ln":
                try {
                    double result = Math.log(Double.parseDouble(input.toString()));
                    display.setText(String.valueOf(result));
                    input.setLength(0);
                    input.append(result);
                } catch (Exception ex) {
                    display.setText("Error");
                }
                break;
            default:
                input.append(command);
                display.setText(input.toString());
                break;
        }
    }

    private String evaluate(String expression) throws ScriptException {
        if (expression.contains("**")) {
            String[] parts = expression.split("\\*\\*");
            double base = Double.parseDouble(parts[0]);
            double exponent = Double.parseDouble(parts[1]);
            return String.valueOf(Math.pow(base, exponent));
        }
        return String.valueOf(eval(expression));
    }

    private double eval(String expression) throws ScriptException {
        return new BigDecimal(engine.eval(expression).toString())
                .round(new MathContext(10, RoundingMode.HALF_UP))
                .doubleValue();
    }
}
