package grails5playground

import org.springframework.beans.factory.annotation.Autowired
import wood.mike.AddressConfiguration

class AddressTagLib {

    static namespace = "app"

    @Autowired
    AddressConfiguration addressConfiguration

    def address = { attrs, body ->
        out << """\
<div class='adr'>
    <div class='street-address'>${addressConfiguration.street}</div>
    <span class='locality'>${addressConfiguration.city}</span>,
    <div class='country-name'>${addressConfiguration.country}</div>
</div>"""
    }
}
