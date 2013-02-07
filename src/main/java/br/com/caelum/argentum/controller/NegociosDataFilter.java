package br.com.caelum.argentum.controller;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import br.com.caelum.argentum.modelo.Negocio;

public class NegociosDataFilter {

	private Calendar dataFiltro;

	public NegociosDataFilter(Calendar dataFiltro) {
		this.dataFiltro = dataFiltro;
	}

	public List<Negocio> filter(List<Negocio> lista) {

		List<Negocio> negocios = new ArrayList<Negocio>();

		for (Negocio negocio : lista) {
			if (negocio.getData().after(dataFiltro)) {
				negocios.add(negocio);
			}
		}
		return negocios;
	}

}
