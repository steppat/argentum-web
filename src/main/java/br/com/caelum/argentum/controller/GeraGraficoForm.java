package br.com.caelum.argentum.controller;

import java.io.StringReader;
import java.util.List;

import javax.validation.constraints.NotNull;
	
import org.hibernate.validator.constraints.NotEmpty;

import br.com.caelum.argentum.modelo.Negocio;
import br.com.caelum.argentum.reader.LeitorXML;

public class GeraGraficoForm {
	
	@NotEmpty
	private String titulo;

	@NotEmpty
	private String indicador;
	private String media;

	private Integer dias;
	
	@NotEmpty
	private String xml;

	private List<Negocio> negocios;
	
	
	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public Integer getDias() {
		if(dias == null) {
			return 1;
		}
		return dias;
	}

	public void setDias(Integer dias) {
		this.dias = dias;
	}


	public void setIndicador(String indicador) {
		this.indicador = indicador;
	}

	public void setMedia(String media) {
		this.media = media;
	}

	public void setXml(String xml) {
		this.xml = xml;
		this.negocios = new LeitorXML().carrega(new StringReader(xml));
	}

	public List<Negocio> getNegocios() {
		return negocios;
	}
	
	public String getIndicador() {
		return indicador;
	}

	public String getMedia() {
		return media;
	}

	public String getXml() {
		return xml;
	}
}