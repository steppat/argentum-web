package br.com.caelum.argentum.modelo;

public class MediaMovelPonderada implements Indicador {

	private final Indicador indicador;
	private final int quantidadeDias;
	private long factorialQuqantidadeDias;
	
	public MediaMovelPonderada(Indicador indicador, int dias) {
		this.indicador = indicador;
		this.quantidadeDias = dias;
		this.factorialQuqantidadeDias = calculaSuma(dias); 
	}
	
	

	private long calculaSuma(int dias) {
		if(dias == 1) return 1;
		return dias + calculaSuma(dias-1);
	}



	@Override
	public double calculaPara(int posicao, SerieTemporal serie) {
		
		double media = 0;
		int peso = 1;
		
		for(int i = posicao - (quantidadeDias -1); i <= posicao;i++ ){
			media += this.indicador.calculaPara(i, serie) * peso;
			peso++;
		}
		
		double d = media / this.factorialQuqantidadeDias;
		return d;
	}
	
	public String toString() {
		return "MMP-" + this.indicador+"-"+this.quantidadeDias;
	}
	
}
