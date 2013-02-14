package br.com.caelum.argentum.modelo;

import java.util.HashMap;
import java.util.Map;


public class MontadorDeIndicador {

	private String mediaParam;
	private String indicadorParam;
	private int dias;
	
	private static Map<String, Indicador> indicadores = new HashMap<String, Indicador>();
	
	static {
		indicadores.put("abertura", new IndicadorAbertura());
		indicadores.put("fechamento", new IndicadorFechamento());
		indicadores.put("maximo", new IndicadorMaximo());
		indicadores.put("minimo", new IndicadorMinimo());
	}

	public MontadorDeIndicador(String indicadorParam, String mediaParam, int dias) {
		this.indicadorParam = indicadorParam;
		this.mediaParam = mediaParam;
		this.dias = dias;
	}

	public Indicador getIndicador() {

		Indicador indicador = indicadores.get(indicadorParam);
		if(mediaParam != null) {
			if(mediaParam.equals("ponderada")) {
				indicador = new MediaMovelPonderada(indicador, dias);
			}
			if(mediaParam.equals("simples")) {
				indicador = new MediaMovelSimples(indicador, dias);
			}
		}
		return indicador;
	}
	
	

}
