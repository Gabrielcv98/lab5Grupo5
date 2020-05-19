package sw2.lab5.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sw2.lab5.Entity.Users;

@Repository
public interface UserRepository extends JpaRepository<Users, Integer> {
    public Users findByEmail(String email);
}
