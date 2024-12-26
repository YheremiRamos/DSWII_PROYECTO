package pe.edu.cibertec.dawii.ms.usuarios.rmq;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

	
	public static final String EXCHANGE_NAME = "usuario_notificaciones_exchange";
	public static final String ROUTING_KEY = "usuario.notificacion.key";
	public static final String QUEUE_NAME = "notificacionesQueue";

	// Beans de configuraciòn de RabbitMQ
	@Bean
	public DirectExchange exchange() {
		return new DirectExchange(EXCHANGE_NAME, true, false);
	}

	@Bean
	public Queue queue() {
		return new Queue(QUEUE_NAME, true); // true para que la cola persista
	}

	@Bean
	public Binding binding(Queue queue, DirectExchange exchange) {
		return BindingBuilder.bind(queue).to(exchange).with(ROUTING_KEY);

	}
	
	@Bean
    public Jackson2JsonMessageConverter messageConverter() {
        return new Jackson2JsonMessageConverter();
    }

}
