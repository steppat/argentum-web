package br.com.caelum.argentum.ws;

import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import br.com.caelum.argentum.modelo.Negocio;
import br.com.caelum.argentum.reader.LeitorXML;

public class NegociosWS {

//	private static final String URI_WEBSERVICE = "http://localhost:8080/argentum-ws/negocios";
	private static final String URI_WEBSERVICE = "http://argentum-ws.cloudfoundry.com/negocios";
//	private static final String QUERY_NAME = "?data=";
	
//	private static SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

	public List<Negocio> getNegocios(Date dataInicial) {

		try {
			HttpClient client = new DefaultHttpClient();
//			String dataFormatada = sdf.format(dataInicial);

			HttpGet request = new HttpGet(URI_WEBSERVICE); //+ QUERY_NAME + dataFormatada);
			HttpResponse response = client.execute(request);

			InputStream content = response.getEntity().getContent();
			
			return new LeitorXML().carrega(content);
		} catch (ClientProtocolException e) {
			throw new RuntimeException(e);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

}
