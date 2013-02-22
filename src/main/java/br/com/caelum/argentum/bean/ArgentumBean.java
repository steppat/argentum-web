package br.com.caelum.argentum.bean;

import java.util.List;

import javax.faces.bean.ManagedBean;

import org.primefaces.model.chart.CartesianChartModel;

import br.com.caelum.argentum.grafico.GeradorChartModel;
import br.com.caelum.argentum.modelo.Candle;
import br.com.caelum.argentum.modelo.CandlestickFactory;
import br.com.caelum.argentum.modelo.MontadorIndicador;
import br.com.caelum.argentum.modelo.Negocio;
import br.com.caelum.argentum.modelo.SerieTemporal;
import br.com.caelum.argentum.ws.ClienteWebService;

@ManagedBean
//@SessionScoped
public class ArgentumBean {

	private String titulo;
	private String indicador ="abertura";
	private String media;
	private Integer dias;
	
	private List<Negocio> negocios;
	private CartesianChartModel negociosChartModel;
	
	
	public void preparaDados()  {
		
		ClienteWebService ws = new ClienteWebService();
		this.negocios = ws.getNegocios();
		
		List<Candle> candles = new CandlestickFactory().constroiCandles(negocios);
		SerieTemporal serie = new SerieTemporal(candles);
		
		GeradorChartModel gerador = new GeradorChartModel(serie, getDias(), serie.getTotal() - 1);
		
		MontadorIndicador montador = new MontadorIndicador(getIndicador(), getMedia(), getDias());
		
		gerador.plotaIndicador(montador.getIndicador());
		
		this.negociosChartModel = gerador.getNegociosChartModel();
	}
	
	public CartesianChartModel getNegociosChartModel() {
		return negociosChartModel;
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

	public List<Negocio> getNegocios() {
		return this.negocios;
	}
}