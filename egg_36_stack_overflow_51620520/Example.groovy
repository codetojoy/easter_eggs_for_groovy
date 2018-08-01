
@Grab(group='joda-time', module='joda-time', version='2.10') 

import org.joda.time.format.*
import org.joda.time.*

def getDateTime(def s) {
    def result = null

    def regex = /^\d\d\d\d-\d\d-\d\d$/

    if (s ==~ regex) {
        // yyyy-MM-dd, strict
        def formatter = new DateTimeFormatterBuilder()
                             .appendYear(4,4)
                             .appendLiteral('-')
                             .appendMonthOfYear(2)
                             .appendLiteral('-')
                             .appendDayOfMonth(2)
                             .toFormatter()
        try {
            result = formatter.parseDateTime(s)
        } catch (Exception ex) {
            // System.err.println "TRACER ex: " + ex.message
        }
    }

    return result
}

// --- main

assert new DateTime(2018,8,2,0,0) == getDateTime('2018-08-02')
assert null == getDateTime('18-08-02')
assert null == getDateTime('2018-8-02')
assert null == getDateTime('2018-08-2')

println "Ready."
