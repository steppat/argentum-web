package br.com.caelum.argentum.controller;

import br.com.caelum.argentum.modelo.Indicador;
import br.com.caelum.argentum.modelo.SerieTemporal;

public class IndicadorMinimo implements Indicador {

	@Override
	public double calculaPara(int posicao, SerieTemporal serie) {
		return serie.get(posicao).getMinimo();
	}

	
	public String toString() {
		return "M’nimo";
	}
}
