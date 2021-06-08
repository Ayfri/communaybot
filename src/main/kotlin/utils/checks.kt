package utils

import com.kotlindiscord.kord.extensions.checks.CheckFun
import com.kotlindiscord.kord.extensions.checks.memberFor
import dev.kord.core.event.Event
import id

fun isOwner(): CheckFun {
	suspend fun inner(event: Event): Boolean {
		val guild = event.kord.getGuild(id)!!
		return guild.ownerId == memberFor(event)!!.id
	}
	
	return ::inner
}
