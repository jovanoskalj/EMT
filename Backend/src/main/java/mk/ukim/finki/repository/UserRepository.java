package mk.ukim.finki.repository;


import mk.ukim.finki.model.domain.User;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,String> {
    @EntityGraph(value = "User.withoutTemporaryReservations", type = EntityGraph.EntityGraphType.LOAD)
    Optional<User> findByUsernameAndPassword(String username, String password);

    @EntityGraph(value = "User.withoutTemporaryReservations", type = EntityGraph.EntityGraphType.LOAD)
    Optional<User> findByUsername(String username);
}
