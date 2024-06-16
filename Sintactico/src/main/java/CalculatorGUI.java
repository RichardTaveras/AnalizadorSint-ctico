import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CalculatorGUI {
    private JFrame frame;
    private JTextField inputField;
    private JButton parseButton;
    private JLabel resultLabel;

    public CalculatorGUI() {
        // Crear el JFrame
        frame = new JFrame("Expression Parser");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 200);

        // Crear los componentes de la interfaz gráfica
        inputField = new JTextField(20);
        parseButton = new JButton("Parse");
        resultLabel = new JLabel("Result:");

        // Crear un panel para organizar los componentes
        JPanel panel = new JPanel();
        panel.add(inputField);
        panel.add(parseButton);
        panel.add(resultLabel);

        // Agregar el panel al JFrame
        frame.getContentPane().add(panel);
        frame.setVisible(true);

        // Agregar el ActionListener al botón
        parseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String input = inputField.getText();
                Lexer lexer = new Lexer(input);
                Parser parser = new Parser(lexer);
                try {
                    int result = parser.parse();
                    resultLabel.setText("Result: " + result);
                } catch (Exception ex) {
                    resultLabel.setText("Error: " + ex.getMessage());
                }
            }
        });
    }

    public static void main(String[] args) {
        // Ejecutar la interfaz gráfica en el hilo de despacho de eventos
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new CalculatorGUI();
            }
        });
    }
}
