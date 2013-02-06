package br.com.caelum.argentum.reader;

import java.io.InputStream;
import java.io.Reader;
import java.util.List;

import br.com.caelum.argentum.modelo.Negocio;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

public class LeitorXML {
	
	public List<Negocio> carrega(InputStream inputStream) {
		XStream stream = new XStream(new DomDriver());
		stream.alias("negocio", Negocio.class);
		return (List<Negocio>) stream.fromXML(inputStream);
		}

}
