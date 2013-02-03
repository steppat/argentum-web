package br.com.caelum.argentum.ui;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import br.com.caelum.argentum.modelo.Negocio;

public class NegocioFilter {

	private final List<Negocio> negocios;

	public NegocioFilter(List<Negocio> negocios) {
		this.negocios = negocios;
	}

	public List<Negocio> filterPelo(Date date) {
		List<Negocio> lista = new ArrayList<Negocio>();
		
		for (Negocio negocio : negocios) {
			if(negocio.getData().getTime().after(date)){
				lista.add(negocio);
			}
		}
		return lista;
	}

}
