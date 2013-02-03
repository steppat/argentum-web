package br.com.caelum.argentum.modelo;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class CandlestickFactory {

	public Candle constroiCandlePara(List<Negocio> negocios, Calendar data) {

		double abertura = negocios.isEmpty() ? 0 : negocios.get(0).getPreco();
		double fechamento = negocios.isEmpty() ? 0 : negocios.get(negocios.size() -1 ).getPreco();
		double minimo = Double.MAX_VALUE;
		double maximo = Double.MIN_VALUE;
		double volume = 0;

		for (Negocio negocio : negocios) {
			volume += negocio.getVolume();
			if (negocio.getPreco() > maximo) {
				maximo = negocio.getPreco();
			}
			if (negocio.getPreco() < minimo) {
				minimo = negocio.getPreco();
			}
		}

		return new Candle(abertura, fechamento, minimo, maximo, volume, data);
	}


	public List<Candle> constroiCandles(List<Negocio> todosNegocios) {
		
		List<Candle> candles = new ArrayList<Candle>();
		List<Negocio> subList = new ArrayList<Negocio>();
		Calendar data = todosNegocios.get(0).getData();
		
		for (Negocio negocio : todosNegocios) {
			
			if(!isMesmoDia(data,negocio.getData())) {
				fechaCandle(candles, subList, data);
				subList = new ArrayList<Negocio>();
			}
			data = negocio.getData();
			subList.add(negocio);
		}
		fechaCandle(candles, subList, data);
		return candles;
	}


	private void fechaCandle(List<Candle> candles, List<Negocio> subList,
			Calendar data) {
		candles.add(this.constroiCandlePara(subList, data));
	}


	public boolean isMesmoDia(Calendar data, Calendar data2) {
		return data.get(Calendar.YEAR) == data2.get(Calendar.YEAR) && 
				data.get(Calendar.MONTH) == data2.get(Calendar.MONTH) && 
						data.get(Calendar.DAY_OF_MONTH) == data2.get(Calendar.DAY_OF_MONTH) ;	
	}
	
}
