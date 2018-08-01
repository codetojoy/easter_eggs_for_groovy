
@Grab(group='joda-time', module='joda-time', version='2.10') 

import org.joda.time.format.*
import org.joda.time.*

def getDateTime(def s) {
    def result = null

    // yyyy-MM-dd, strict
    def formatter = new DateTimeFormatterBuilder()
                         .appendFixedDecimal(DateTimeFieldType.year(),4)
                         .appendLiteral('-')
                         .appendFixedDecimal(DateTimeFieldType.monthOfYear(),2)
                         .appendLiteral('-')
                         .appendFixedDecimal(DateTimeFieldType.dayOfMonth(),2)
                         .toFormatter()
    try {
        result = formatter.parseDateTime(s)
    } catch (Exception ex) {
        // System.err.println "TRACER ex: " + ex.message
    }

    return result
}

// --- main


assert new DateTime(2018,8,2,0,0) == getDateTime('2018-08-02')
assert null == getDateTime('18-08-02')
assert null == getDateTime('2018-8-02')
assert null == getDateTime('2018-08-2')

println "Ready."
