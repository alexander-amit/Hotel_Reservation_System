package amit.sap.hotelres.repository;

import org.springframework.data.repository.CrudRepository;

import amit.sap.hotelres.entity.RoomEntity;


public interface RoomRepository extends CrudRepository<RoomEntity, Long> {

	RoomEntity findById(Long id);
}
