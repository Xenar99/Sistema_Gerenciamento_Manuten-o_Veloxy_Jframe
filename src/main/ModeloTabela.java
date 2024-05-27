package main;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import java.util.ArrayList;

public class ModeloTabela extends AbstractTableModel {

    private static final long serialVersionUID = 4911139043149899799L;

    private static final String[] colunas = { "ID", "Equipamento", "Descrição", "Data da aquisição",
            "Valor do equipamento", "Validade" };

    private ArrayList<Equipamento> equipamentos;

    public ModeloTabela(ArrayList<Equipamento> equipamentos) {
        super();
        this.equipamentos = equipamentos;
    }

    public int getRowCount() {
        return equipamentos.size();
    }

    public int getColumnCount() {
        return colunas.length;
    }

    public Object getValueAt(int rowIndex, int columnIndex) {
        Equipamento equipamento = equipamentos.get(rowIndex);

        switch (columnIndex) {
            case 0:
                return equipamento.getID();
            case 1:
                return equipamento.getEquipamento();
            case 2:
                return equipamento.getDescricao();
            case 3:
                return equipamento.getDataAquisicao();
            case 4:
                return equipamento.getValor();
            case 5:
                return equipamento.getDataValidade();
            default:
                return null;
        }
    }

    public String getColumnName(int column) {
        return colunas[column];
    }

    public Class<?> getColumnClass(int columnIndex) {
        return String.class;
    }
    
    public DefaultTableCellRenderer getCellRenderer(int row, int column) {
        DefaultTableCellRenderer renderer = new CenterRenderer();
        renderer.setHorizontalAlignment(SwingConstants.CENTER);
        return renderer;
    }

    class CenterRenderer extends DefaultTableCellRenderer {
        private static final long serialVersionUID = 1L;

        public CenterRenderer() {
            setHorizontalAlignment(SwingConstants.CENTER);
        }
    }
    
	public void removeRow(int selectedRow) {
		// TODO Auto-generated method stub
		
	}
}