package amit.sap.hotelres.endpoints;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import amit.sap.hotelres.convertor.RoomEntityToReservableRoomResponseConverter;
import amit.sap.hotelres.entity.ReservationEntity;
import amit.sap.hotelres.entity.RoomEntity;
import amit.sap.hotelres.repository.PageableRoomRepository;
import amit.sap.hotelres.repository.ReservationRepository;
import amit.sap.hotelres.repository.RoomRepository;
import amit.sap.hotelres.request.ReservationRequest;
import amit.sap.hotelres.response.ReservableRoomResponse;
import amit.sap.hotelres.response.ReservationResponse;

@RestController
@RequestMapping(ResourceConstants.ROOM_RESERVATION_V1)
@CrossOrigin
public class ReservationResourse {

	@Autowired
	PageableRoomRepository pageableRoomRepository;

	@Autowired
	RoomRepository roomRepository;

	@Autowired
	ReservationRepository reservationRepository;

	@Autowired
	ConversionService conversionService;

	@RequestMapping(path = "", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public Page<ReservableRoomResponse> getAvailableRooms(
			@RequestParam(value = "checkin") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate checkIn,
			@RequestParam(value = "checkout") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate checkOut,
			Pageable pageable) {
		Page<RoomEntity> roomEntityList = pageableRoomRepository.findAll(pageable);
		return roomEntityList.map(new RoomEntityToReservableRoomResponseConverter());
	}

	@RequestMapping(path = "/{roomId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<RoomEntity> getRoomById(@PathVariable Long roomId) {

		RoomEntity roomEntity = roomRepository.findById(roomId);

		return new ResponseEntity<>(roomEntity, HttpStatus.OK);
	}

	@RequestMapping(path = "", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<ReservationResponse> createReservation(@RequestBody ReservationRequest reservationRequest) {
		System.out.println("In post method");
		ReservationEntity reservationEntity = conversionService.convert(reservationRequest, ReservationEntity.class);
		reservationRepository.save(reservationEntity);

		RoomEntity roomEntity = roomRepository.findById(reservationRequest.getRoomId());
		roomEntity.addReservationEntity(reservationEntity);
		roomRepository.save(roomEntity);
		reservationEntity.setRoomEntity(roomEntity);

		ReservationResponse reservationResponse = conversionService.convert(reservationEntity,
				ReservationResponse.class);
		System.out.println("about to return");
		return new ResponseEntity<>(reservationResponse, HttpStatus.CREATED);
	}

	@RequestMapping(path = "", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_UTF8_VALUE, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<ReservableRoomResponse> updateReservation(
			@RequestBody ReservationRequest reservationRequest) {
		System.out.print("put");
		return new ResponseEntity<>(new ReservableRoomResponse(), HttpStatus.OK);
	}

	@RequestMapping(path = "/{reservationId}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> deleteReservation(@PathVariable long reservationId) {
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}

}
