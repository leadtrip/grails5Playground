package wood.mike

import com.budjb.rabbitmq.publisher.RabbitMessagePublisher
import groovy.transform.CompileStatic
import groovy.util.logging.Log4j
import groovy.util.logging.Slf4j

@CompileStatic
@Slf4j
class BookShowInterceptor {
    RabbitMessagePublisher rabbitMessagePublisher

    BookShowInterceptor() {
        match(controller:"book", action:"show")
    }

    boolean after() {
        final Book book = (Book) model.bookInstance

        log.info "Book {} viewed", book

        rabbitMessagePublisher.send {
            routingKey = "bookQueue"
            body = [id: book.id, title: book.title]
        }
        true
    }
}
