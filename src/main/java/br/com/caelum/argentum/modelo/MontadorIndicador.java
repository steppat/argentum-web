package br.com.caelum.argentum.modelo;

public class MontadorIndicador {

	private String mediaParam;
	private String indicadorParam;
	private int dias;

	public MontadorIndicador(String indicadorParam, String mediaParam, int dias) {
		this.indicadorParam = indicadorParam;
		this.mediaParam = mediaParam;
		this.dias = dias;
	}

	public Indicador getIndicador() {

		try {
			Indicador indicador = (Indicador) Class.forName(
					"br.com.caelum.argentum.modelo." + this.indicadorParam)
					.newInstance();

			if (mediaParam != null) {
				if (mediaParam.equals("ponderada")) {
					indicador = new MediaMovelPonderada(indicador, dias);
				}
				if (mediaParam.equals("simples")) {
					indicador = new MediaMovelSimples(indicador, dias);
				}
			}
			return indicador;
		} catch (InstantiationException e) {
			throw new RuntimeException(e);
		} catch (IllegalAccessException e) {
			throw new RuntimeException(e);
		} catch (ClassNotFoundException e) {
			throw new RuntimeException(e);
		}

	}

}
