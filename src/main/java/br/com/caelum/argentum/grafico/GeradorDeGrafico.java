package br.com.caelum.argentum.grafico;

import java.io.IOException;
import java.io.OutputStream;

import javax.swing.JPanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

import br.com.caelum.argentum.modelo.Indicador;
import br.com.caelum.argentum.modelo.SerieTemporal;

public class GeradorDeGrafico {

	
	private final SerieTemporal serie;
	private final int comeco;
	private final int fim;
	private DefaultCategoryDataset dataset;
	private JFreeChart chart;
	
	public GeradorDeGrafico(SerieTemporal serie, int comeco, int fim) {
		this.serie = serie;
		this.comeco = comeco;
		this.fim = fim;
	}

	public void criaGrafico(String titulo) {
		dataset = new DefaultCategoryDataset();
		chart = ChartFactory.createLineChart(titulo, "Dias", "Values", dataset, PlotOrientation.VERTICAL, true, true, false);
	}
	
	public void plotaIndicador(Indicador indicador) {
		for (int i = comeco; i <= fim; i++) {
			double valor = indicador.calculaPara(i, serie);
			this.dataset.addValue(valor, indicador.toString(), "" + i);	
		}
	}
	
	public void salvar(OutputStream stream) throws IOException {
		ChartUtilities.writeChartAsPNG(stream, this.chart, 800, 600);
	}

	public JPanel getPanel() {
		return new ChartPanel(chart);
	}
	
}
