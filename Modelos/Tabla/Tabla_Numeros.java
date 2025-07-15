/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelos.Tabla;


import javax.swing.table.AbstractTableModel;
/**
 *
 * @author LOQ78
 */

public class Tabla_Numeros extends AbstractTableModel {
  private String[][] contenido = new String[0][0];

    public void actualizarDatos(String[][] nuevosDatos) {
        this.contenido = nuevosDatos;
        fireTableStructureChanged();
    }

    @Override
    public int getRowCount() {
        return contenido.length;
    }

    @Override
    public int getColumnCount() {
        return (contenido.length == 0) ? 0 : contenido[0].length;
    }

    @Override
    public Object getValueAt(int fila, int columna) {
        return contenido[fila][columna];
    }

    @Override
    public boolean isCellEditable(int fila, int columna) {
        return false;
    }
}