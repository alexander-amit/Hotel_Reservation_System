package amit.sap.hotelres.repository;

import amit.sap.hotelres.entity.*;
import org.springframework.data.repository.CrudRepository;

public interface ReservationRepository extends CrudRepository<ReservationEntity, Long> {
}