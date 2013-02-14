package br.com.caelum.argentum.grafico;

import org.primefaces.model.chart.CartesianChartModel;
import org.primefaces.model.chart.LineChartSeries;

import br.com.caelum.argentum.modelo.Indicador;
import br.com.caelum.argentum.modelo.SerieTemporal;

public class GeradorDeGrafico {

	
	private final SerieTemporal serie;
	private final int comeco;
	private final int fim;
	private CartesianChartModel chartModel = new CartesianChartModel();
	
	public GeradorDeGrafico(SerieTemporal serie, int comeco, int fim) {
		this.serie = serie;
		this.comeco = comeco;
		this.fim = fim;
	}

	public void plotaIndicador(Indicador indicador) {
		
		LineChartSeries chartSerie = new LineChartSeries(indicador.toString());
		
		for (int i = comeco; i <= fim; i++) {
			double valor = indicador.calculaPara(i, serie);
			chartSerie.set(i, valor);	
		}
		
		this.chartModel.addSeries(chartSerie);
	}
	
	public CartesianChartModel getNegociosChartModel() {
		return chartModel;
	}
	
}
