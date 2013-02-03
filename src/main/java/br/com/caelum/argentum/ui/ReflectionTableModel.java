package br.com.caelum.argentum.ui;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import br.com.caelum.argentum.modelo.Negocio;

public class ReflectionTableModel extends AbstractTableModel {

	private static final long serialVersionUID = 1L;

	private final List<?> lista;

	private final NumberFormat numberFormat = NumberFormat
			.getCurrencyInstance();
	private final DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

	public ReflectionTableModel(List<?> negocios) {
		this.lista = negocios;
	}

	@Override
	public int getRowCount() {
		return this.lista.size();
	}

	@Override
	public int getColumnCount() {

		Object object = lista.get(0);
		Class<?> classe = object.getClass();

		int qtd = 0;

		for (Method m : classe.getDeclaredMethods()) {
			if (m.isAnnotationPresent(Column.class)) {
				qtd++;
			}
		}

		return qtd;
	}

	@Override
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		return false;
	}

	@Override
	public String getColumnName(int column) {

		Object objeto = this.lista.get(0);
		Class<?> classe = objeto.getClass();

		for (Method m : classe.getDeclaredMethods()) {
			if (m.isAnnotationPresent(Column.class)) {
				Column coluna = m.getAnnotation(Column.class);
				int posicao = coluna.posicao();
				if (posicao == column) {
					return coluna.nome();
				}
			}
		}
		return null;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {

		Object objeto = this.lista.get(rowIndex);
		Class<?> classe = objeto.getClass();

		try {
			for (Method m : classe.getDeclaredMethods()) {
				if (m.isAnnotationPresent(Column.class)) {
					Column column = m.getAnnotation(Column.class);
					int posicao = column.posicao();
					if (columnIndex == posicao) {
						return String.format(column.formato(), m.invoke(objeto));
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
