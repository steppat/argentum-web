package br.com.caelum.argentum.controller;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Date;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

import br.com.caelum.argentum.grafico.GeradorDeGrafico;
import br.com.caelum.argentum.modelo.Candle;
import br.com.caelum.argentum.modelo.CandlestickFactory;
import br.com.caelum.argentum.modelo.Negocio;
import br.com.caelum.argentum.modelo.SerieTemporal;

@ManagedBean
public class ArgentumBean {

	private String titulo;
	private String indicador;
	private String media;
	private Integer dias;
	private Date data;
	
	public void geraGrafico() throws IOException {
		
		NegociosWS ws = new NegociosWS();
		
		List<Negocio> negocios = ws.getNegocios(data);
		
		List<Candle> candles = new CandlestickFactory().constroiCandles(negocios);
		SerieTemporal serie = new SerieTemporal(candles);
		
		GeradorDeGrafico geradorDeGrafico = new GeradorDeGrafico(serie, getDias(),candles.size()-1);
		geradorDeGrafico.criaGrafico(titulo);
		geradorDeGrafico.plotaIndicador(new MontaIndicador(indicador, media, getDias()).toIndicador());
		
		OutputStream stream = FacesContext.getCurrentInstance().getExternalContext().getResponseOutputStream();
		geradorDeGrafico.salvar(stream);
		
//		response.setContentType("image/png");      
//		response.setHeader("Content-Disposition", "attachment; filename=" + titulo + ".png");
//		response.flushBuffer();
		System.out.println(titulo + ", " + indicador + ", " + media + ", " + dias + ", " + data);
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getIndicador() {
		return indicador;
	}

	public void setIndicador(String indicador) {
		this.indicador = indicador;
	}

	public String getMedia() {
		return media;
	}

	public void setMedia(String media) {
		this.media = media;
	}

	public Integer getDias() {
		if (dias == null || this.media == null || this.media.isEmpty()) {
			return 1;
		}
		return dias;
	}

	public void setDias(Integer dias) {
		this.dias = dias;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	
	
}

