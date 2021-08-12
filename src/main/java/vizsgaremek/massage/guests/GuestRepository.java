package vizsgaremek.massage.guests;

import org.springframework.data.jpa.repository.JpaRepository;
import vizsgaremek.massage.guests.Guest;

public interface GuestRepository extends JpaRepository<Guest, Long> {
}
