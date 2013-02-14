package br.com.caelum.argentum.modelo;


public class IndicadorMinimo implements Indicador {

	@Override
	public double calculaPara(int posicao, SerieTemporal serie) {
		return serie.get(posicao).getMinimo();
	}

	
	public String toString() {
		return "M’nimo";
	}
}
