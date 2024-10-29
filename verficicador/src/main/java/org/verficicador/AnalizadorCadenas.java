package org.verficicador;

/**
 *
 * @author andih
 */
import javax.swing.*;
import java.awt.*;
import com.formdev.flatlaf.FlatDarkLaf;
import java.util.ArrayList;
import java.util.List;

public class AnalizadorCadenas extends JFrame {
    private final JTextField txtEntrada;
    private final JTextArea txtResultado;
    private final JButton btnAnalizar;
    private final JButton btnLimpiar;
    private final JButton btnSalir;

    public AnalizadorCadenas() {
        setTitle("Analizador de Cadenas");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));
        setSize(600, 400);
        setLocationRelativeTo(null);

        iniciadorFlatlaf();
        
        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        JPanel panelEntrada = new JPanel(new BorderLayout(5, 0));
        txtEntrada = new JTextField();
        panelEntrada.add(new JLabel("Ingrese la cadena:"), BorderLayout.WEST);
        panelEntrada.add(txtEntrada, BorderLayout.CENTER);

        txtResultado = new JTextArea();
        txtResultado.setEditable(false);
        txtResultado.setBackground(new Color(45, 45, 45));
        txtResultado.setForeground(Color.WHITE);
        JScrollPane scrollPane = new JScrollPane(txtResultado);
        scrollPane.setBorder(BorderFactory.createLineBorder(Color.GRAY));

        JPanel panelBotones = new JPanel(new GridLayout(3, 1, 0, 10));
        panelBotones.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 0));
        
        btnAnalizar = new JButton("Analizar");
        btnLimpiar = new JButton("Limpiar");
        btnSalir = new JButton("Salir");
        
        configurarBoton(btnAnalizar);
        configurarBoton(btnLimpiar);
        configurarBoton(btnSalir);
        
        panelBotones.add(btnAnalizar);
        panelBotones.add(btnLimpiar);
        panelBotones.add(btnSalir);

        mainPanel.add(panelEntrada, BorderLayout.NORTH);
        mainPanel.add(scrollPane, BorderLayout.CENTER);
        mainPanel.add(panelBotones, BorderLayout.EAST);

        add(mainPanel);

        btnAnalizar.addActionListener(e -> analizarCadena());
        btnLimpiar.addActionListener(e -> limpiarCampos());
        btnSalir.addActionListener(e -> System.exit(0));
    }

    private void configurarBoton(JButton boton) {
        boton.setPreferredSize(new Dimension(100, 30));
        boton.setBackground(new Color(60, 63, 65));
        boton.setForeground(Color.WHITE);
        boton.setFocusPainted(false);
        boton.setBorderPainted(true);

        boton.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(Color.GRAY, 1),
            BorderFactory.createEmptyBorder(4, 8, 4, 8)
        ));

        boton.putClientProperty("JButton.buttonType", "roundRect");

        boton.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                boton.setBackground(new Color(70, 73, 75));
                boton.setBorder(BorderFactory.createCompoundBorder(
                    BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1),
                    BorderFactory.createEmptyBorder(4, 8, 4, 8)
                ));
            }

            @Override
            public void mouseExited(java.awt.event.MouseEvent evt) {
                boton.setBackground(new Color(60, 63, 65));
                boton.setBorder(BorderFactory.createCompoundBorder(
                    BorderFactory.createLineBorder(Color.GRAY, 1),
                    BorderFactory.createEmptyBorder(4, 8, 4, 8)
                ));
            }
        });
    }

    private void iniciadorFlatlaf() {
        try {
            UIManager.setLookAndFeel(new FlatDarkLaf());
        } catch (Exception ex) {
            System.err.println("Failed to initialize LaF");
        }
    }
    
    private void limpiarCampos() {
        txtEntrada.setText("");
        txtResultado.setText("");
        txtEntrada.requestFocus();
    }

    private void analizarCadena() {
        String cadena = txtEntrada.getText();
        List<String> errores = new ArrayList<>();
        boolean esValida = true;

        if (cadena.isEmpty()) {
            mostrarResultado("Cadena vacía, la cadena NO es válida");
            return;
        }
        
        if (!cadena.endsWith(".")) {
            errores.add("La cadena debe terminar con un punto (.)");
            esValida = false;
        }

        String cadenaAnalizar = cadena.endsWith(".") ? 
            cadena.substring(0, cadena.length() - 1) : cadena;

        if (cadenaAnalizar.contains(" ")) {
            errores.add("No se permiten espacios en la cadena");
            esValida = false;
        }

        boolean esNumerica = true;
        boolean esTexto = true;
        boolean tieneCaracteresEspeciales = false;
        int contadorComas = 0;

        for (int i = 0; i < cadenaAnalizar.length(); i++) {
            char c = cadenaAnalizar.charAt(i);

            if (!Character.isLetterOrDigit(c) && c != ',' && c != ';' && c != ':') {
                tieneCaracteresEspeciales = true;
            }

            if (!Character.isDigit(c)) {
                if (c == ',') {
                    contadorComas++;
                    if (contadorComas > 1 || i == 0 || i == cadenaAnalizar.length() - 1) {
                        esNumerica = false;
                    }

                    if (i > 0 && i < cadenaAnalizar.length() - 1) {
                        if (!Character.isDigit(cadenaAnalizar.charAt(i - 1)) || 
                            !Character.isDigit(cadenaAnalizar.charAt(i + 1))) {
                            esNumerica = false;
                        }
                    }
                } else {
                    esNumerica = false;
                }
            }

            if (!Character.isLetter(c) && c != ',' && c != ';' && c != ':') {
                esTexto = false;
            }
        }

        if (tieneCaracteresEspeciales) {
            errores.add("La cadena contiene caracteres especiales no permitidos");
            esValida = false;
        }

        if (!esNumerica && !esTexto) {
            errores.add("La cadena debe ser solo numérica (con posible coma decimal) o solo texto");
            esValida = false;
        }

        if (esTexto) {
            if (!Character.isUpperCase(cadenaAnalizar.charAt(0))) {
                errores.add("La cadena debe iniciar con mayúscula");
                esValida = false;
            }

            for (int i = 1; i < cadenaAnalizar.length(); i++) {
                char c = cadenaAnalizar.charAt(i);
                if (Character.isLetter(c) && Character.isUpperCase(c)) {
                    errores.add("Después de la primera letra, todo debe estar en minúsculas");
                    esValida = false;
                    break;
                }
            }

            for (int i = 0; i < cadenaAnalizar.length() - 1; i++) {
                char actual = Character.toLowerCase(cadenaAnalizar.charAt(i));
                char siguiente = Character.toLowerCase(cadenaAnalizar.charAt(i + 1));
                
                if (actual == siguiente) {
                    if (esConsonante(actual)) {
                        if (actual != 'l' && actual != 'c') {
                            errores.add("Consonante repetida no permitida en posición " + 
                                (i + 1) + ": " + actual + siguiente);
                            esValida = false;
                        }
                    }
                    else if (esVocal(actual)) {
                        if (actual != 'o') {
                            errores.add("Vocal repetida no permitida en posición " + 
                                (i + 1) + ": " + actual + siguiente);
                            esValida = false;
                        }
                    }
                }
            }
        }

        StringBuilder resultado = new StringBuilder();
        if (esValida) {
            resultado.append("La cadena es válida.");
        } else {
            resultado.append("La cadena NO es válida.\nErrores encontrados:\n");
            for (String error : errores) {
                resultado.append("- ").append(error).append("\n");
            }
        }
        mostrarResultado(resultado.toString());
    }
    private boolean esVocal(char c) {
        char letra = Character.toLowerCase(c);
        return letra == 'a' || letra == 'e' || letra == 'i' || 
               letra == 'o' || letra == 'u';
    }

    private boolean esConsonante(char c) {
        return Character.isLetter(c) && !esVocal(c);
    }

    private void mostrarResultado(String mensaje) {
        txtResultado.setText(mensaje);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new AnalizadorCadenas().setVisible(true);
        });
    }
}