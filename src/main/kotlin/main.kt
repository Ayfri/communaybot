
import com.kotlindiscord.kord.extensions.ExtensibleBot
import com.kotlindiscord.kord.extensions.i18n.SupportedLocales
import com.kotlindiscord.kord.extensions.utils.env
import dev.kord.common.entity.PresenceStatus
import dev.kord.gateway.Intents
import dev.kord.gateway.PrivilegedIntent
import extensions.Information

lateinit var bot: ExtensibleBot;

@OptIn(PrivilegedIntent::class)
suspend fun main() {
	bot = ExtensibleBot(env("TOKEN")!!) {
		extensions {
			sentry = false
			add(::Information)
		}
		
		intents {
			+Intents.all
		}
		
		i18n {
			defaultLocale = SupportedLocales.ENGLISH
			localeResolver { _, _, _ -> SupportedLocales.FRENCH }
		}
		
		presence {
			status = PresenceStatus.Online
			
			playing("!help")
		}
	}
	
	bot.start()
}
