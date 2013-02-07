package br.com.caelum.argentum.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import br.com.caelum.argentum.grafico.GeradorDeGrafico;
import br.com.caelum.argentum.modelo.Candle;
import br.com.caelum.argentum.modelo.CandlestickFactory;
import br.com.caelum.argentum.modelo.Negocio;
import br.com.caelum.argentum.modelo.SerieTemporal;

@Controller
@SessionAttributes("sessionForm")
public class ArgentumController {

	private static final String PAGINA_GRAFICO = "grafico";
	private static final String PAGINA_FORMULARIO_GRAFICO = "graficoForm";

	@RequestMapping(value="/",method=RequestMethod.GET)
	public  String index(Model model) {
		model.addAttribute("form", new GeraGraficoForm());
		return PAGINA_FORMULARIO_GRAFICO;
	}
	
	@RequestMapping(value="/validaForm",method=RequestMethod.POST)
	public  String validaForm(@Valid @ModelAttribute("form") GeraGraficoForm form, BindingResult result, Model model) {

		if(result.hasErrors()) {
			return PAGINA_FORMULARIO_GRAFICO;
		}

		model.addAttribute("sessionForm", form);
		
		return "redirect:grafico" ;
	}		
	
	@RequestMapping("/grafico")
	public String grafico() {
		return PAGINA_GRAFICO;
	}
	

	@RequestMapping(value="/geraGrafico",method=RequestMethod.GET)
	public  void criaGrafico(@ModelAttribute("sessionForm") GeraGraficoForm form, HttpServletResponse response) throws IOException{
	
		NegociosWS ws = new NegociosWS();
		
		List<Negocio> negocios = ws.getNegocios(form.getDataFiltro());
		
		List<Candle> candles = new CandlestickFactory().constroiCandles(negocios);
		SerieTemporal serie = new SerieTemporal(candles);
		
		GeradorDeGrafico geradorDeGrafico = new GeradorDeGrafico(serie, form.getDias(),candles.size()-1);
		geradorDeGrafico.criaGrafico(form.getTitulo());
		geradorDeGrafico.plotaIndicador(new MontaIndicador(form.getIndicador(), form.getMedia(), form.getDias()).toIndicador());
		
		geradorDeGrafico.salvar(response.getOutputStream());
		response.setContentType("image/png");      
		response.setHeader("Content-Disposition", "attachment; filename=" + form.getTitulo() + ".png");
		response.flushBuffer();
	}

}


