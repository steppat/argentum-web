package br.com.caelum.argentum.controller;

import org.hibernate.validator.constraints.NotEmpty;

public class GeraGraficoForm {

	@NotEmpty
	private String titulo;

	@NotEmpty
	private String indicador;
	private String media;

	private Integer dias;

	// @NotEmpty
	private String data;

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public Integer getDias() {
		if (dias == null) {
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

	public void setData(String data) {
		this.data = data;
	}

}