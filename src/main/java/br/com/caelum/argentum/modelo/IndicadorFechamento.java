package br.com.caelum.argentum.modelo;

import br.com.caelum.argentum.modelo.Indicador;
import br.com.caelum.argentum.modelo.SerieTemporal;

public class IndicadorFechamento implements Indicador {

	@Override
	public double calculaPara(int posicao, SerieTemporal serie) {
		return serie.get(posicao).getFechamento();
	}

	public String toString() {
		return "Fechamento";
	}
	
	
}
