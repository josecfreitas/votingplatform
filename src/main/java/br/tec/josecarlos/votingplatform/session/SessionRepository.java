package br.tec.josecarlos.votingplatform.session;

import br.tec.josecarlos.votingplatform.models.Session;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface SessionRepository extends CrudRepository<Session, UUID> {
}
