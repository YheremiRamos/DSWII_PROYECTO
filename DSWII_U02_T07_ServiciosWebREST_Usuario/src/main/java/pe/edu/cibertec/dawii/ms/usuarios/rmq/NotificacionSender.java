package pe.edu.cibertec.dawii.ms.usuarios.rmq;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.stereotype.Component;

@Component
public class NotificacionSender {

	
	private final RabbitTemplate rabbitTemplate;
	
	public NotificacionSender(RabbitTemplate rabbitTemplate, Jackson2JsonMessageConverter messageConverter) {
		this.rabbitTemplate = rabbitTemplate;
		this.rabbitTemplate.setMessageConverter(messageConverter);
	}
	
	/*@Autowired
	private final RabbitTemplate rabbitTemplate;*/
	
	public void enviarNotificacion(Notificacion notificacion) {
		
		rabbitTemplate.convertAndSend(RabbitMQConfig.EXCHANGE_NAME, RabbitMQConfig.ROUTING_KEY, notificacion);
		System.out.println("Notificación enviada para el usuario " + notificacion);
	}
	
}
