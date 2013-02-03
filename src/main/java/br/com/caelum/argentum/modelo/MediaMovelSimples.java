package br.com.caelum.argentum.modelo;


public class MediaMovelSimples implements Indicador{

	private final Indicador indicador;
	private final int quantidadeDias;

	public MediaMovelSimples(Indicador indicador, int dias) {
		this.indicador = indicador;
		this.quantidadeDias = dias;
	}

	public double calculaPara(int posicao, SerieTemporal serie) {
		
		double soma = 0;
		
		for(int i = posicao - (quantidadeDias -1); i <=posicao;i++ ){
			soma += this.indicador.calculaPara(i, serie);
		}
		
		return soma / quantidadeDias;
	}
	
	public String toString() {
		return "MMS-" + this.indicador.toString() +"-"+ this.quantidadeDias;
	}
	
}
