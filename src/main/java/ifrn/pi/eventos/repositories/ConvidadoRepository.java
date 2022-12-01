package ifrn.pi.eventos.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import ifrn.pi.eventos.moldels.Convidado;
import ifrn.pi.eventos.moldels.Evento;

public interface ConvidadoRepository extends JpaRepository<Convidado, Long> {

	List<Convidado> FindByEvento(Evento evento);
	}

