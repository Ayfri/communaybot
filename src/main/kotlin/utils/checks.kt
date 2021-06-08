package utils

import com.kotlindiscord.kord.extensions.checks.CheckFun
import com.kotlindiscord.kord.extensions.checks.memberFor
import communAyfriID
import dev.kord.core.event.Event

fun isOwner(): CheckFun {
	suspend fun inner(event: Event): Boolean {
		val guild = event.kord.getGuild(communAyfriID)!!
		return guild.ownerId == memberFor(event)!!.id
	}
	
	return ::inner
}
