package amit.sap.hotelres.convertor;

import org.springframework.core.convert.converter.Converter;

import amit.sap.hotelres.entity.ReservationEntity;
import amit.sap.hotelres.request.ReservationRequest;

public class ReservationRequestToReservationEntityConverter
		implements Converter<ReservationRequest, ReservationEntity> {

	@Override
	public ReservationEntity convert(ReservationRequest source) {

		ReservationEntity reservationEntity = new ReservationEntity();
		reservationEntity.setCheckin(source.getCheckin());
		reservationEntity.setCheckout(source.getCheckout());
		if (null != source.getId())
			reservationEntity.setId(source.getId());

		return reservationEntity;
	}
}