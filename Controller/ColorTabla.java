/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import java.awt.Color;
import java.awt.Component;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
/**
 *
 * @author LOQ78
 */
public class ColorTabla extends DefaultTableCellRenderer {
    
   @Override
    public Component getTableCellRendererComponent(JTable tabla, Object valor, boolean celdaSeleccionada, boolean tieneFoco, int fila, int columna) {
        super.getTableCellRendererComponent(tabla, valor, celdaSeleccionada, tieneFoco, fila, columna);
        
        if (valor instanceof Character) {
            char caracter = (Character) valor;
            switch (caracter) {
                case '#':
                    setBackground(Color.GRAY);
                    break;
                case 'E':
                    setBackground(Color.ORANGE);
                    break;
                case 'S':
                    setBackground(Color.RED);
                    break;
                default:
                    setBackground(Color.WHITE);
                    break;
            }
        } else {
            setBackground(Color.WHITE);
        }
        
        setText("");
        return this;
    }
}