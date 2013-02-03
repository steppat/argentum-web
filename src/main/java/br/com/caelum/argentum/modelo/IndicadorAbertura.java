package br.com.caelum.argentum.modelo;

import br.com.caelum.argentum.modelo.Indicador;
import br.com.caelum.argentum.modelo.SerieTemporal;

public class IndicadorAbertura implements Indicador {

	@Override
	public double calculaPara(int posicao, SerieTemporal serie) {
		Candle candle = serie.get(posicao);
		System.out.println(candle);
		return candle.getAbertura();
	}

	public String toString() {
		return "Abertura";
	}
	
	
}
