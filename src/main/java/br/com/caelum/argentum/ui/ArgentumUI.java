package br.com.caelum.argentum.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.border.LineBorder;
import javax.swing.text.MaskFormatter;

import br.com.caelum.argentum.grafico.GeradorDeGrafico;
import br.com.caelum.argentum.modelo.Candle;
import br.com.caelum.argentum.modelo.CandlestickFactory;
import br.com.caelum.argentum.modelo.IndicadorFechamento;
import br.com.caelum.argentum.modelo.MediaMovelPonderada;
import br.com.caelum.argentum.modelo.MediaMovelSimples;
import br.com.caelum.argentum.modelo.Negocio;
import br.com.caelum.argentum.modelo.SerieTemporal;

public class ArgentumUI {

	public static void main(String[] args) {
		new ArgentumUI().montaTela();
	}

	private JFrame janela;
	private JPanel painelPrincipal;
	private JPanel painelBotao;
	private JTable tabela;
	private JTabbedPane abas;
	private JCheckBoxMenuItem mediaFechamento;
	private JCheckBoxMenuItem mediaAbertura;
	private JFormattedTextField campoDataInicio;
	
	
	
	public void montaTela() {
		this.montaJanela();
		this.montaPainelPrincipal();
		this.montaMenu();
		this.montaAbas();
		this.montaPainelBotoes();
		this.montaTabela();
		this.montaBotaoCarregar();
		this.montaBotaoSair();
		this.mostraJanela();
	}
	
	private void montaAbas() {
		this.abas = new JTabbedPane();
		this.abas.addTab("Tabela de Negocio", null);
		this.abas.addTab("Gráfico", null);
		this.painelPrincipal.add(abas);
	}
	
	private void montaTabela() {
		tabela = new JTable();
		tabela.setBorder(new LineBorder(Color.black));
		tabela.setGridColor(Color.black);
		tabela.setShowGrid(true);
		JScrollPane scroll = new JScrollPane();
		scroll.getViewport().setBorder(null);
		scroll.getViewport().add(tabela);
		scroll.setSize(450, 450);
		this.abas.setComponentAt(0, scroll);
		painelPrincipal.add(abas, BorderLayout.CENTER);
	}

	private void montaJanela() {
		janela = new JFrame("Argentum");
		janela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	
	private void montaPainelPrincipal() {
		painelPrincipal = new JPanel();
		painelPrincipal.setLayout(new BorderLayout());
		janela.add(painelPrincipal);
	}

	private void montaPainelBotoes() {
		painelBotao = new JPanel();
		painelBotao.setLayout(new GridLayout(1,2));
		
		MaskFormatter formatter;
		try {
			formatter = new MaskFormatter("##/##/####");
			formatter.setPlaceholderCharacter('_');
			this.campoDataInicio = new JFormattedTextField(formatter);
			painelBotao.add(campoDataInicio);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		this.painelPrincipal.add(painelBotao,BorderLayout.SOUTH);
		
	}
	
	
	private void montaMenu() {
		
		JMenuBar menu = new JMenuBar();
		janela.setJMenuBar(menu);
		
		JMenu menuIndicador = new JMenu("Indicadores");
		menu.add(menuIndicador);
		
		mediaFechamento = new JCheckBoxMenuItem("Média móvel simples de fechamento");
		mediaFechamento.setSelected(true);
		menuIndicador.add(mediaFechamento);
		
		
		mediaAbertura = new JCheckBoxMenuItem("Média móvel simples de abertura");
		mediaAbertura.setSelected(true);
		menuIndicador.add(mediaAbertura);
	}
	
	private void montaBotaoCarregar() {
		JButton botaoCarregar = new JButton("Carregar XML");
		botaoCarregar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				carregaDados();
			}

		});
		this.painelBotao.add(botaoCarregar);
	}

	private void carregaDados() {
		List<Negocio> negocios = new EscolheXML().escolher();
		
		try {
			String textoData = (String) this.campoDataInicio.getValue();
			Date date = new SimpleDateFormat("dd/MM/yyyy").parse(textoData);
			negocios = new NegocioFilter(negocios).filterPelo(date);
		} catch (Exception e) {
			this.campoDataInicio.setValue(null);
		}
		
		tabela.setModel(new ReflectionTableModel(negocios));
		
		CandlestickFactory factory = new CandlestickFactory();
		List<Candle> candles = factory.constroiCandles(negocios);
		SerieTemporal serie = new SerieTemporal(candles);
		
		GeradorDeGrafico geradorDeGrafico = new GeradorDeGrafico(serie, 50, serie.getTotal() -1);
		geradorDeGrafico.criaGrafico("Media Movel");
		
		
		if(this.mediaFechamento.isSelected()) {
			geradorDeGrafico.plotaIndicador(new IndicadorFechamento());
		}
		
		if(this.mediaFechamento.isSelected()) {
			geradorDeGrafico.plotaIndicador(new MediaMovelSimples(new IndicadorFechamento(), 20));
		}
		if(this.mediaAbertura.isSelected()) {
			geradorDeGrafico.plotaIndicador(new MediaMovelPonderada(new IndicadorFechamento(), 50));
		}

		JPanel grafico = geradorDeGrafico.getPanel();
		this.abas.setComponentAt(1, grafico);
	}	

	private void montaBotaoSair() {
		JButton botaoSair = new JButton("Sair");
		botaoSair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		painelBotao.add(botaoSair);
	}

	private void mostraJanela() {
		janela.pack();
		janela.setSize(540, 540);
		janela.setVisible(true);
		
	}

}
