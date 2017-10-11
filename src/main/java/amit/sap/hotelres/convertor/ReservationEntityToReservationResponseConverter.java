package amit.sap.hotelres.convertor;

import org.springframework.core.convert.converter.Converter;

import amit.sap.hotelres.entity.ReservationEntity;
import amit.sap.hotelres.response.ReservationResponse;
public class ReservationEntityToReservationResponseConverter implements Converter<ReservationEntity, ReservationResponse> {

    @Override
    public ReservationResponse convert(ReservationEntity source) {

        ReservationResponse reservationResponse = new ReservationResponse();
        reservationResponse.setCheckin(source.getCheckin());
        reservationResponse.setCheckout(source.getCheckout());

        if (null != source.getRoomEntity())
            reservationResponse.setId(source.getRoomEntity().getId());

        return reservationResponse;
    }
}