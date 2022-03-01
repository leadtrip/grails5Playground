package wood.mike

import groovy.transform.CompileStatic
import groovy.util.logging.Slf4j
import org.springframework.scheduling.annotation.Scheduled

import java.time.LocalDate
import java.time.LocalDateTime
import java.time.Month
import java.time.MonthDay
import java.time.Period
import java.time.Year
import java.time.ZoneId
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter
import java.time.format.FormatStyle

@Slf4j
@CompileStatic
/**
 * For scheduling to work you also need to add the following annotations to Application class
 *  @ComponentScan('wood.mike') <- packages you want scanned
    @EnableScheduling
   And turn quartz on in application.yml
     quartz:
         autoStartup: true
 */
class ScheduledTimePrinterService {

    static lazyInit = false

    @Scheduled(fixedDelay = 10000L)
    void chicagoTime() {
        log.info "DTS in Chicago :{}", ZonedDateTime.now(ZoneId.of('America/Chicago')).format(DateTimeFormatter.ofLocalizedDateTime(FormatStyle.SHORT))
    }

    @Scheduled(fixedDelay = 45000L, initialDelay = 5000L)
    void localTime() {
        log.info "DTS here :{}", LocalDateTime.now().format(DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM))
    }

    @Scheduled(cron = "0 * * * * ?")
    void execute() {
        def aprilTen = MonthDay.of( Month.APRIL, 10 )
        def before = aprilTen.isBefore(MonthDay.now())
        def until = LocalDate.now().until(LocalDate.of(Year.now().plusYears(before ? 1 : 0).value, aprilTen.month, aprilTen.dayOfMonth ))
        log.info "Next birthday is in {} months {} days", until.months, until.days
    }
}
