package br.com.caelum.argentum.modelo;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import junit.framework.Assert;

import org.junit.Test;

public class CandlestickFactoryTest {

	@Test
	public void testEhNoMesmoDia() {
		CandlestickFactory factory = new CandlestickFactory();
		Calendar data1 = Calendar.getInstance();
		Calendar data2 = Calendar.getInstance();
		assertTrue(factory.isMesmoDia(data1 , data2)); 
	
	}

	@Test
	public void testEhNoMesmoDiaComHorariosDiferentes() {
		CandlestickFactory factory = new CandlestickFactory();
		Calendar data1 = new GregorianCalendar(2012,00,01,12,20);
		Calendar data2 = new GregorianCalendar(2012,00,01,13,10);
		assertTrue(factory.isMesmoDia(data1 , data2)); 
	}

	@Test
	public void testConsroiCandlesticksAPartirDeTodosOsNegocios() {
	
		Calendar hoje = Calendar.getInstance();
		
		Negocio negocio1 = new Negocio(20.5, 10, hoje);
		Negocio negocio2 = new Negocio(30.5, 11, hoje);
		Negocio negocio3 = new Negocio(40.5, 9, hoje);
		
		Calendar amanha = Calendar.getInstance();
		amanha.add(Calendar.DAY_OF_MONTH, 1);

		Negocio negocio4 = new Negocio(10.5, 10, amanha);
		Negocio negocio5 = new Negocio(20.5, 11, amanha);
		Negocio negocio6 = new Negocio(30.5, 9, amanha);

		List<Negocio> negocios = Arrays.asList(negocio1,negocio2,negocio3,negocio4,negocio5,negocio6);
		
		CandlestickFactory factory = new CandlestickFactory();
		
		List<Candle> candles = factory.constroiCandles(negocios);
		
		Assert.assertEquals(candles.get(0).getAbertura(), 20.5);
		Assert.assertEquals(candles.get(1).getAbertura(), 10.5);
		Assert.assertEquals(candles.size(), 2);
	}	
}
