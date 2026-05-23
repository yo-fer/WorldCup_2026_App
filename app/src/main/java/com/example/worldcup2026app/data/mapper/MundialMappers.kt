package com.example.worldcup2026app.data.mapper

import com.example.worldcup2026app.core.utils.WorldCupData
import com.example.worldcup2026app.data.local.entity.PlayerEntity
import com.example.worldcup2026app.data.local.entity.TeamEntity
import com.example.worldcup2026app.data.local.entity.VenueEntity
import com.example.worldcup2026app.data.remote.PlayerDto
import com.example.worldcup2026app.data.remote.TeamResponseDto
import com.example.worldcup2026app.domain.model.Player
import com.example.worldcup2026app.domain.model.Team
import com.example.worldcup2026app.domain.model.Venue

// función de red a local (guarda en DB)
fun TeamResponseDto.toEntity(): TeamEntity {
    return TeamEntity(
        id = this.team.id,
        name = this.team.name,
        code = this.team.code ?: "",
        country = this.team.country,
        foundedYear = this.team.founded,
        logoUrl = this.team.logoUrl,
        groupName = WorldCupData.getGroupForTeam(this.team.id),

        venue = VenueEntity(
            venueId = this.venue.id,
            venueName = this.venue.name,
            city = this.venue.city,
            capacity = this.venue.capacity,
            imageUrl = this.venue.imageUrl
        )
    )
}

// el jugador no tiene el ID de su equipo en su propio objeto
// se inyecta para obtener la FK
fun PlayerDto.toEntity(teamId: Int): PlayerEntity {
    return PlayerEntity(
        id = this.id,
        teamId = teamId,
        name = this.name,
        age = this.age,
        jerseyNumber = this.number,
        position = this.position,
        photoUrl = this.photoUrl
    )
}

// desde local a dominio (UI)
fun TeamEntity.toDomain(): Team {
    return Team(
        id = this.id,
        name = this.name,
        code = this.code ?: "",
        country = this.country,
        foundedYear = this.foundedYear,
        logoUrl = this.logoUrl,
        groupName = this.groupName,
        venue = Venue(
            id = this.venue.venueId,
            name = this.venue.venueName,
            city = this.venue.city,
            capacity = this.venue.capacity,
            imageUrl = this.venue.imageUrl
        )
    )
}

fun PlayerEntity.toDomain(): Player {
    return Player(
        id = this.id,
        name = this.name,
        age = this.age,
        number= this.jerseyNumber,
        position = this.position,
        photoUrl = this.photoUrl
    )
}
