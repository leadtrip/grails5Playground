package wood.mike

import io.micronaut.context.annotation.ConfigurationProperties

@ConfigurationProperties("address")
class AddressConfiguration {
    String street
    String city
    String country
}
