package br.com.caelum.argentum.modelo;

import java.util.Calendar;

public final class Negocio {

	private final double preco;
	private final int quantidade;
	private final Calendar data;
	
	public Negocio(double preco, int quantidade, Calendar data) {
		this.preco = preco;
		this.quantidade = quantidade;
		this.data = (Calendar) data.clone();
	}

	public double getPreco() {
		return preco;
	}
	public int getQuantidade() {
		return quantidade;
	}
	public Calendar getData() {
		return (Calendar) data.clone();
	}

	public double getVolume() {
		return this.preco * this.quantidade;
	}
}
