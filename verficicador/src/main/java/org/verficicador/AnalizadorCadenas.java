/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.verficicador;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class AnalizadorCadenas extends JFrame {
    private JTextField txtEntrada;
    private JTextArea txtResultado;
    private JButton btnAnalizar;

    public AnalizadorCadenas() {
        // Configuración de la ventana
        setTitle("Analizador de Cadenas");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));
        setSize(500, 400);
        setLocationRelativeTo(null);

        // Componentes
        JPanel panelSuperior = new JPanel(new BorderLayout(5, 5));
        txtEntrada = new JTextField();
        btnAnalizar = new JButton("Analizar");
        panelSuperior.add(new JLabel("Ingrese la cadena:"), BorderLayout.WEST);
        panelSuperior.add(txtEntrada, BorderLayout.CENTER);
        panelSuperior.add(btnAnalizar, BorderLayout.EAST);

        txtResultado = new JTextArea();
        txtResultado.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(txtResultado);

        // Añadir componentes a la ventana
        add(panelSuperior, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);

        // Configurar acción del botón
        btnAnalizar.addActionListener(e -> analizarCadena());
    }

    private void analizarCadena() {
        String cadena = txtEntrada.getText();
        List<String> errores = new ArrayList<>();
        boolean esValida = true;

        // Verificar si la cadena está vacía
        if (cadena.isEmpty()) {
            mostrarResultado("Error: La cadena está vacía");
            return;
        }

        // Verificar si termina en punto
        if (!cadena.endsWith(".")) {
            errores.add("La cadena debe terminar con un punto (.)");
            esValida = false;
        }

        // Remover el punto final para el análisis
        String cadenaAnalizar = cadena.endsWith(".") ? 
            cadena.substring(0, cadena.length() - 1) : cadena;

        // Determinar si es numérica o texto
        boolean esNumerica = true;
        boolean esTexto = true;
        boolean tieneCaracteresEspeciales = false;
        int contadorComas = 0;

        for (int i = 0; i < cadenaAnalizar.length(); i++) {
            char c = cadenaAnalizar.charAt(i);
            
            // Verificar caracteres especiales
            if (!Character.isLetterOrDigit(c) && c != ',' && c != ';' && c != ':' && c != ' ') {
                tieneCaracteresEspeciales = true;
            }

            // Verificar si es numérica (permitiendo una coma)
            if (!Character.isDigit(c)) {
                if (c == ',') {
                    contadorComas++;
                    if (contadorComas > 1 || i == 0 || i == cadenaAnalizar.length() - 1) {
                        esNumerica = false;
                    }
                    // Verificar que haya dígitos antes y después de la coma
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

            // Verificar si es texto
            if (!Character.isLetter(c) && c != ',' && c != ';' && c != ':' && c != ' ') {
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
            // Verificar primera letra mayúscula
            if (!Character.isUpperCase(cadenaAnalizar.charAt(0))) {
                errores.add("La cadena debe iniciar con mayúscula");
                esValida = false;
            }

            // Verificar que el resto sean minúsculas
            for (int i = 1; i < cadenaAnalizar.length(); i++) {
                char c = cadenaAnalizar.charAt(i);
                if (Character.isLetter(c) && Character.isUpperCase(c)) {
                    errores.add("Después de la primera letra, todo debe estar en minúsculas");
                    esValida = false;
                    break;
                }
            }

            // Verificar consonantes y vocales repetidas
            for (int i = 0; i < cadenaAnalizar.length() - 1; i++) {
                char actual = Character.toLowerCase(cadenaAnalizar.charAt(i));
                char siguiente = Character.toLowerCase(cadenaAnalizar.charAt(i + 1));
                
                if (actual == siguiente) {
                    // Si son consonantes iguales
                    if (esConsonante(actual)) {
                        if (actual != 'l' && actual != 'c') {
                            errores.add("Consonante repetida no permitida en posición " + 
                                (i + 1) + ": " + actual + siguiente);
                            esValida = false;
                        }
                    }
                    // Si son vocales iguales
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

        // Mostrar resultado
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