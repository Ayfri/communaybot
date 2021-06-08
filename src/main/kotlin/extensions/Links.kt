package extensions

import com.kotlindiscord.kord.extensions.commands.slash.AutoAckType
import com.kotlindiscord.kord.extensions.extensions.Extension
import dev.kord.common.annotation.KordPreview

@OptIn(KordPreview::class)
class Links : Extension() {
	override val name: String = "Links"
	
	override suspend fun setup() {
		slashCommand {
			name = "github"
			description = "extensions.links.github.description"
			autoAck = AutoAckType.PUBLIC
			
			action {
				publicFollowUp {
					content = "https://github.com/Ayfri"
				}
			}
		}
		
		slashCommand {
			name = "twitch"
			description = "extensions.links.twitch.description"
			autoAck = AutoAckType.PUBLIC
			
			action {
				publicFollowUp {
					content = "https://twitch.tv/Ayfri1015"
				}
			}
		}
		
	}
}
