package ifrn.pi.eventos.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import ifrn.pi.eventos.moldels.Evento;
import ifrn.pi.eventos.repositories.Eventorepository;

@Controller
public class EventosController {
	@Autowired
	private Eventorepository er;
	@RequestMapping("/eventos/form")
	public String form() {
		return "formEvento";
	}
	@RequestMapping("/eventos")
	public String adicionar(Evento evento) {
		System.out.println(evento);
		er.save(evento);
		return "evento-adicionado";
	}
}
