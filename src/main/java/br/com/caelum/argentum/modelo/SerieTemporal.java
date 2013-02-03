package br.com.caelum.argentum.modelo;

import java.util.List;


public class SerieTemporal {

	private final List<Candle> candles;

	public SerieTemporal(List<Candle> candles) {
		this.candles = candles;
	}
	
	public Candle get(int posicao) {
		return this.candles.get(posicao);
	}
	
	public int getTotal() {
		return this.candles.size();
	}
	
}
