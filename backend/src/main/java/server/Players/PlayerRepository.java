package server.Players;

import server.Players.Player;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

//@Repository
public interface PlayerRepository extends CrudRepository<Player, Long> {

    //public List<

}