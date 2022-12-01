package ifrn.pi.eventos.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import ifrn.pi.eventos.moldels.Convidado;
import ifrn.pi.eventos.moldels.Evento;
import ifrn.pi.eventos.repositories.ConvidadoRepository;
import ifrn.pi.eventos.repositories.Eventorepository;

@Controller
@RequestMapping("/eventos")
public class EventosController {
	@Autowired
	private Eventorepository er;
	@Autowired
	private ConvidadoRepository cr;

	@GetMapping("/form")
	public String form() {
		return "formEvento";
	}

	@PostMapping
	public String adicionar(Evento evento) {
		System.out.println(evento);
		er.save(evento);
		return "evento-adicionado";
	}

	@GetMapping
	public ModelAndView listar() {
		List<Evento> eventos = er.findAll();
		ModelAndView mv = new ModelAndView("eventos/lista");
		mv.addObject("eventos", eventos);
		return mv;
	}

	@GetMapping("/{id}")
	public ModelAndView detalhar(@PathVariable Long id) {
		ModelAndView md = new ModelAndView();
		Optional<Evento> opt = er.findById(id);

		if (opt.isEmpty()) {
			md.setViewName("redirect:/eventos");
			md = new ModelAndView("redirect:/listar");
			return md;
		}
		md.setViewName("eventos/detalhes");
		Evento evento = opt.get();
		md.addObject("evento", evento);

		List<Convidado> convidados = cr.FindByEvento(evento);
		md.addObject(convidados, convidados);
		return md;

	}
	@GetMapping("/{idEvento}")
	public String savarConvidado(@PathVariable Long idEvento, Convidado convidado) {
		
		System.out.println("id do evento: " + idEvento);
		System.out.println(convidado);
		
		
		Optional<Evento> opt = er.findById(idEvento);
		if(opt.isEmpty()) {
			return "redirect:/eventos";
			
		}
		
		Evento evento = opt.get();
		convidado.setEvento(evento);
		
		cr.save(convidado);
		return "redirect:/eventos/{idEvento}";
		
	}

	@GetMapping("/{id}/remover")
	public String apagarEvento(@PathVariable Long id) {
		
		Optional<Evento> opt = er.findById(id);
			if(opt.isEmpty()) {
				// apagar
			
			Evento evento = opt.get();	
			er.delete(evento);
	}
			return "redirect:/eventos";
			}
		
		
	}
