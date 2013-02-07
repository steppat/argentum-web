package br.com.caelum.argentum.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.hibernate.validator.constraints.NotEmpty;

public class GeraGraficoForm {

	private static final SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

	@NotEmpty
	private String titulo;

	@NotEmpty
	private String indicador;
	
	private String media;
	private Integer dias;

	@NotEmpty
	private String data;

	private Date dataFiltro;

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
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

	public void setIndicador(String indicador) {
		this.indicador = indicador;
	}

	public void setMedia(String media) {
		this.media = media;
	}

	public String getIndicador() {
		return indicador;
	}

	public String getMedia() {
		return media;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) throws ParseException {
		this.data = data;
		this.dataFiltro = sdf.parse(data);;
	}

	public Date getDataFiltro() {
		return dataFiltro;
	}
	

}