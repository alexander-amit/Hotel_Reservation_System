package amit.sap.hotelres.config;

import java.util.HashSet;
import java.util.Set;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ConversionServiceFactoryBean;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.converter.Converter;

import amit.sap.hotelres.convertor.ReservationEntityToReservationResponseConverter;
import amit.sap.hotelres.convertor.ReservationRequestToReservationEntityConverter;
import amit.sap.hotelres.convertor.RoomEntityToReservableRoomResponseConverter;

@Configuration
public class ConversionConfig {

	private Set<Converter> getConverters() {
		System.out.println("Inconvertor");
		Set<Converter> converters = new HashSet<Converter>();
		converters.add(new RoomEntityToReservableRoomResponseConverter());
		converters.add(new ReservationRequestToReservationEntityConverter());
		converters.add(new ReservationEntityToReservationResponseConverter());

		return converters;
	}

	@Bean
	public ConversionService conversionService() {
		System.out.println("Inconvertor service");
		ConversionServiceFactoryBean bean = new ConversionServiceFactoryBean();
		bean.setConverters(getConverters());
		bean.afterPropertiesSet();

		return bean.getObject();
	}
}