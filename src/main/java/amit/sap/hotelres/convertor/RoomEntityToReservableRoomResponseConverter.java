package amit.sap.hotelres.convertor;

import org.springframework.core.convert.converter.Converter;

import amit.sap.hotelres.endpoints.ResourceConstants;
import amit.sap.hotelres.entity.RoomEntity;
import amit.sap.hotelres.models.Links;
import amit.sap.hotelres.models.Self;
import amit.sap.hotelres.response.ReservableRoomResponse;

public class RoomEntityToReservableRoomResponseConverter implements Converter<RoomEntity, ReservableRoomResponse> {

	@Override
	public ReservableRoomResponse convert(RoomEntity source) {
		// TODO Auto-generated method stub

		ReservableRoomResponse reservationResponse = new ReservableRoomResponse();
		if (null != source.getId())
			reservationResponse.setId(source.getId());
		reservationResponse.setRoomNumber(source.getRoomNumber());
		reservationResponse.setPrice(Integer.valueOf(source.getPrice()));

		Links links = new Links();
		Self self = new Self();
		self.setRef(ResourceConstants.ROOM_RESERVATION_V1 + "/" + source.getId());
		links.setSelf(self);

		reservationResponse.setLinks(links);

		return reservationResponse;
	}

}
