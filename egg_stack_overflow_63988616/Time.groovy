
import java.time.*
import java.time.format.DateTimeFormatter
import java.util.concurrent.TimeUnit

def formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy'T'HH:mm:ss")
def text1 = "21/09/2020T03:00:00"

def dateTime1 = LocalDateTime.parse(text1, formatter)
def zonedDateTime1 = dateTime1.atZone(ZoneId.of("UTC"))
long milliValue1 = zonedDateTime1.toInstant().toEpochMilli()

def intervalInSeconds = TimeUnit.MINUTES.toSeconds(15)
def milliValue2 = zonedDateTime1.toInstant()
                              .plusSeconds(intervalInSeconds)
                              .toEpochMilli()
def dateTime2 = LocalDateTime.ofInstant(
                    Instant.ofEpochMilli(milliValue2), ZoneId.of("UTC"))
def text2 = dateTime2.format(formatter)

println "milliValue1  : " + milliValue1 
println "text1        : " + text1

println "milliValue2  : " + milliValue2 
println "text2        : " + text2
println "Ready."
