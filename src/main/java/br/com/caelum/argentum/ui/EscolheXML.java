package br.com.caelum.argentum.ui;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.Reader;
import java.util.Collections;
import java.util.List;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

import br.com.caelum.argentum.modelo.Negocio;
import br.com.caelum.argentum.reader.LeitorXML;

public class EscolheXML {
	
	public static void main(String[] args) {
		new EscolheXML().escolher();
	}
	
	public List<Negocio> escolher() {
		try {
			JFileChooser chooser = new JFileChooser(".");
			chooser.setFileFilter(new FileNameExtensionFilter("Apenas XML", "xml"));
			
			int retorno = chooser.showOpenDialog(null);
			if(retorno == JFileChooser.APPROVE_OPTION) {
				File file = chooser.getSelectedFile();
				Reader fonte =	new FileReader(file);
				List<Negocio> negocios = new LeitorXML().carrega(fonte);
				return negocios;
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return Collections.emptyList();
	}

}
