package edu.comillas.icai.gitt.pat.spring.jpa.Repositorio;

import edu.comillas.icai.gitt.pat.spring.jpa.Entidades.Planta;
import edu.comillas.icai.gitt.pat.spring.jpa.Entidades.Vecino;
import org.springframework.data.repository.CrudRepository;

public interface RepoVecino extends CrudRepository<Vecino, Long> {
   Vecino findByPlanta(Planta planta);
}
