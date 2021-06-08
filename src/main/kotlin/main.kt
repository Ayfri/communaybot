
import com.kotlindiscord.kord.extensions.ExtensibleBot
import com.kotlindiscord.kord.extensions.i18n.SupportedLocales
import com.kotlindiscord.kord.extensions.utils.env
import dev.kord.common.entity.PresenceStatus
import dev.kord.gateway.Intents
import dev.kord.gateway.PrivilegedIntent
import extensions.Information
import extensions.Links
import kotlin.time.Duration
import kotlin.time.ExperimentalTime

lateinit var bot: ExtensibleBot

@OptIn(PrivilegedIntent::class, ExperimentalTime::class)
suspend fun main() {
	bot = ExtensibleBot(env("TOKEN")!!) {
		extensions {
			sentry = false
			
			help {
				deletePaginatorOnTimeout = true
				paginatorTimeout = Duration.minutes(5).inWholeMilliseconds
			}
			add(::Links)
			add(::Information)
		}
		
		intents {
			+Intents.all
		}
		
		i18n {
			defaultLocale = SupportedLocales.ENGLISH
			localeResolver { _, _, _ -> SupportedLocales.FRENCH }
		}
		
		members {
			fillPresences = true
			all()
		}
		
		slashCommands {
			enabled = true
			defaultGuild = id
		}
		
		presence {
			status = PresenceStatus.Online
			
			playing("!help")
		}
	}
	
	bot.start()
}
