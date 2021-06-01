import ch.qos.logback.core.joran.spi.ConsoleTarget

def defaultLevel = DEBUG

logger("io.ktor.util.random", ERROR)
logger("com.kotlindiscord.kord.extensions.i18n.ResourceBundleTranslations", ERROR)
logger("com.kotlindiscord.kord.extensions.utils._Environment", INFO)

appender("DEFAULT", ConsoleAppender) {
	encoder(PatternLayoutEncoder) {
		pattern = "%gray([%date{yyyy/MM/dd HH:mm:ss.SSS}]) %highlight([%.5level]) %yellow(%50.50logger{50}) - %message%n"
	}
	
	target = ConsoleTarget.SystemOut
}

root(defaultLevel, ["DEFAULT"])
