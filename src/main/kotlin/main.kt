import com.kotlindiscord.kord.extensions.ExtensibleBot
import com.kotlindiscord.kord.extensions.utils.env
import dev.kord.common.entity.PresenceStatus
import dev.kord.gateway.Intents
import dev.kord.gateway.PrivilegedIntent
import java.util.*

lateinit var bot: ExtensibleBot;

@OptIn(PrivilegedIntent::class)
suspend fun main() {
	bot = ExtensibleBot(env("TOKEN")!!) {
		extensions {
			help = true
		}
		
		intents {
			+Intents.all
		}
		
		i18n {
			defaultLocale = Locale.FRENCH
		}
		
		presence {
			status = PresenceStatus.Online
			
			playing("!help")
		}
	}
	
	bot.start()
}
