package extensions

import com.kotlindiscord.kord.extensions.checks.inGuild
import com.kotlindiscord.kord.extensions.extensions.Extension
import dev.kord.common.entity.PresenceStatus
import dev.kord.core.behavior.reply
import id
import kotlinx.coroutines.flow.count
import kotlinx.coroutines.flow.filter
import utils.completeEmbed

class Information : Extension() {
	override val bundle = "communaybot"
	override val name: String = "Information"
	
	override suspend fun setup() {
		command {
			name = "extensions.informations.guild-info.name"
			description = "extensions.informations.guild-info.description"
			aliasKey = "extensions.informations.guild-info.aliases"
			check(inGuild(id))
			
			action {
				message.reply {
					embed {
						completeEmbed(bot, this@action.translate("extensions.informations.guild-info.embed.title"), "")()
						val members = guild!!.members
						val bots = members.filter { it.isBot }
						val online = members.filter { it.getPresence().status == PresenceStatus.Online }
						val idle = members.filter { it.getPresence().status == PresenceStatus.Idle }
						val dnd = members.filter { it.getPresence().status == PresenceStatus.DoNotDisturb }
						val offline = members.filter { it.getPresence().status == PresenceStatus.Offline }
						
						field {
							name = this@action.translate("extensions.informations.guild-info.embed.fields.members.title")
							value = this@action.translate(
								"extensions.informations.guild-info.embed.fields.members.value",
								arrayOf(members, online, idle, dnd, offline, bots).map { it.count() }.toTypedArray()
							)
						}
					}
				}
			}
		}
	}
}

