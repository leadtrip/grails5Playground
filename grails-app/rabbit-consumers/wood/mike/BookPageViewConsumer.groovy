package wood.mike

import com.budjb.rabbitmq.consumer.MessageContext
import groovy.transform.CompileStatic
import groovy.util.logging.Slf4j

@Slf4j
@CompileStatic
class BookPageViewConsumer {

    BookPageViewDataService bookPageViewDataService

    static rabbitConfig = [
        queue: 'bookQueue'
    ]

    /**
     * Handle an incoming RabbitMQ message.
     *
     * @param body    The converted body of the incoming message.
     * @param context Properties of the incoming message.
     * @return
     */
    def handleMessage(Map body, MessageContext messageContext) {
        log.debug 'body is {}', body.toString()
        bookPageViewDataService.increment((Long) body.id, (String) body.title)
    }
}
