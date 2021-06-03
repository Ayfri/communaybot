package utils

import com.kotlindiscord.kord.extensions.ExtensibleBot
import dev.kord.core.Kord
import dev.kord.core.supplier.EntitySupplyStrategy
import dev.kord.rest.builder.message.EmbedBuilder
import kotlinx.datetime.Clock

suspend fun basicEmbed(bot: ExtensibleBot) = basicEmbed(bot.getKoin().get<Kord>())
suspend fun basicEmbed(client: Kord): suspend EmbedBuilder.() -> Unit = {
	val user = client.getSelf(EntitySupplyStrategy.cacheWithRestFallback)
	
	footer {
		icon = user.avatar.url
		text = user.username
	}
	
	timestamp = Clock.System.now()
}

suspend fun completeEmbed(bot: ExtensibleBot, title: String, description: String) = completeEmbed(bot.getKoin().get<Kord>(), title, description)
suspend fun completeEmbed(client: Kord, title: String, description: String): suspend EmbedBuilder.() -> Unit = {
	basicEmbed(client)()
	
	this.title = title
	this.description = description
}
