/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelos.Tabla;

import Controller.Utidades.Casa;
import javax.swing.table.AbstractTableModel;
/**
 *
 * @author LOQ78
 */

public class Tabla_Casa extends AbstractTableModel {

    private Casa[] casas = new Casa[0];
    private final String[] columnas = {"Ancho", "Largo", "Alto", "Nro Pisos"};

    public void setCasas(Casa[] casas) {
        this.casas = casas;
        fireTableDataChanged();
    }

    @Override
    public int getRowCount() {
        return casas.length;
    }

    @Override
    public int getColumnCount() {
        return columnas.length;
    }

    @Override
    public String getColumnName(int col) {
        return columnas[col];
    }

    @Override
    public Object getValueAt(int row, int col) {
        Casa c = casas[row];
        switch (col) {
            case 0: return c.getAncho();
            case 1: return c.getLargo();
            case 2: return c.getAlto();
            case 3: return c.getNroPisos();
            default: return null;
        }
    }

    @Override
    public boolean isCellEditable(int row, int col) {
        return false;
    }
}
  

