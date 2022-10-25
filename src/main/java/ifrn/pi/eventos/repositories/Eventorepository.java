package ifrn.pi.eventos.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import ifrn.pi.eventos.moldels.Evento;

public interface Eventorepository extends JpaRepository<Evento, Long> {

}
