package extensions

import adChannelID
import com.kotlindiscord.kord.extensions.checks.inGuild
import com.kotlindiscord.kord.extensions.extensions.Extension
import dev.kord.common.entity.ChannelType
import dev.kord.common.entity.PresenceStatus
import dev.kord.core.behavior.reply
import dev.kord.core.entity.channel.TextChannel
import id
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.toList
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
						completeEmbed(bot, translate("extensions.informations.guild-info.embed.title"), "")()
						val members = guild!!.members.toList()
						val channels = guild!!.channels.toList()
						
						field {
							name = translate("extensions.informations.guild-info.embed.fields.owner.title")
							value = "${guild!!.owner.mention} (`${guild!!.ownerId.asString}`)"
							inline = true
						}
						
						val membersPresences = members.filterNot { it.isBot }.groupBy { it.getPresenceOrNull()?.status }
						
						val bots = members.filter { it.isBot }
						val online = membersPresences[PresenceStatus.Online]
						val idle = membersPresences[PresenceStatus.Idle]
						val dnd = membersPresences[PresenceStatus.DoNotDisturb]
						val invisible = membersPresences[PresenceStatus.Invisible]
						val offline = membersPresences[null]!!.toMutableList()
						offline.addAll(invisible ?: emptyList())
						
						field {
							name = translate("extensions.informations.guild-info.embed.fields.members.title")
							value = translate(
								"extensions.informations.guild-info.embed.fields.members.value",
								arrayOf(members, online, idle, dnd, offline, bots).map { it?.size ?: 0 }.toTypedArray()
							)
							inline = true
						}
						
						val channelsTypes = channels.groupBy { it.type }
						
						val textual = channelsTypes[ChannelType.GuildText]
						val vocals = channelsTypes[ChannelType.GuildVoice]
						val categories = channelsTypes[ChannelType.GuildCategory]
						val announces = channelsTypes[ChannelType.GuildNews]
						val stages = channelsTypes[ChannelType.GuildStageVoice]
						
						field {
							name = translate("extensions.informations.guild-info.embed.fields.channels.title")
							value = translate(
								"extensions.informations.guild-info.embed.fields.channels.value",
								arrayOf(channels, textual, vocals, categories, announces, stages).map { it?.size ?: 0 }.toTypedArray()
							)
							inline = true
						}
						
						field {
							name = translate("extensions.informations.guild-info.embed.fields.roles.title")
							value = guild!!.roles.toList().sortedBy { it.rawPosition }.filterNot { it.managed }.asReversed().joinToString("\n") { it.mention }
							inline = true
						}
					}
				}
			}
		}
		
		command {
			name = "extensions.informations.ad.name"
			description = "extensions.informations.ad.description"
			aliasKey = "extensions.informations.ad.aliases"
			check(inGuild(id))
			
			action {
				val adChannel = guild!!.channels.first { it.id == adChannelID } as TextChannel
				val ad = adChannel.messages.toList().minByOrNull { it.timestamp }!!
				
				message.reply {
					content = ad.content
				}
			}
		}
	}
}

