package br.com.caelum.argentum.grafico;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import br.com.caelum.argentum.modelo.Candle;
import br.com.caelum.argentum.modelo.IndicadorFechamento;
import br.com.caelum.argentum.modelo.Indicador;
import br.com.caelum.argentum.modelo.MediaMovelPonderada;
import br.com.caelum.argentum.modelo.MediaMovelSimples;
import br.com.caelum.argentum.modelo.SerieTemporal;


public class TestaGrafico {

	public static void main(String[] args) throws FileNotFoundException, IOException {
		
		SerieTemporal serie = criaSerie(1,2,3,4,5,6,4,6,4,2,4,6,7,7,8);
		GeradorDeGrafico grafico = new GeradorDeGrafico(serie, 2, 10);

		grafico.criaGrafico("Fechamento");
		Indicador indicador = new IndicadorFechamento();
		grafico.plotaIndicador(indicador);
		grafico.plotaIndicador(new MediaMovelPonderada(indicador, 2));
		grafico.plotaIndicador(new MediaMovelSimples(indicador, 2));
		grafico.salvar(new FileOutputStream(new File("grafico.png")));
	}

	private static SerieTemporal criaSerie(double ... valores) {

		List<Candle> candles = new ArrayList<Candle>();
		for (int j = 0; j < valores.length; j++) {
			candles.add(new Candle(valores[j],valores[j],valores[j], valores[j], valores[j], Calendar.getInstance()));
		}
		
		return new  SerieTemporal(candles);
	}
	
}
