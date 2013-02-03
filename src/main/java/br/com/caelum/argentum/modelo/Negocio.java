package br.com.caelum.argentum.modelo;

import java.util.Calendar;

import br.com.caelum.argentum.ui.Column;

public final class Negocio {

	private final double preco;
	private final int quantidade;
	private final Calendar data;
	
	public Negocio(double preco, int quantidade, Calendar data) {
		this.preco = preco;
		this.quantidade = quantidade;
		this.data = (Calendar) data.clone();
	}

	@Column(posicao=1,nome="Preco",formato="%4.2f")
	public double getPreco() {
		return preco;
	}
	@Column(posicao=0,nome="Quantidade",formato="%d")
	public int getQuantidade() {
		return quantidade;
	}
	@Column(posicao=2,nome="Date",formato="%1$td/%1$tm/%1$tY")
	public Calendar getData() {
		return (Calendar) data.clone();
	}

	@Column(posicao=3,nome="Volume",formato="%5.2f")
	public double getVolume() {
		return this.preco * this.quantidade;
	}
}
