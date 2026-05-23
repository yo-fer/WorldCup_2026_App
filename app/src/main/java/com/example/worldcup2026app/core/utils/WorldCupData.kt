package com.example.worldcup2026app.core.utils

object WorldCupData {
    val teamGroups = mapOf(
        // Grupo A
        16 to "Grupo A",    // México
        1531 to "Grupo A",  // Sudáfrica
        17 to "Grupo A",    // Corea del Sur
        770 to "Grupo A",   // República Checa

        // Grupo B
        5529 to "Grupo B",  // Canadá
        1113 to "Grupo B",  // Bosnia y Herzegovina
        1569 to "Grupo B",  // Catar
        15 to "Grupo B",    // Suiza

        // Grupo C
        6 to "Grupo C",     // Brasil
        31 to "Grupo C",    // Marruecos
        2386 to "Grupo C",  // Haití
        1108 to "Grupo C",  // Escocia

        // Grupo D
        2384 to "Grupo D",  // Estados Unidos
        2380 to "Grupo D",  // Paraguay
        20 to "Grupo D",    // Australia
        777 to "Grupo D",   // Turquía

        // Grupo E
        25 to "Grupo E",    // Alemania
        5530 to "Grupo E",  // Curazao
        1501 to "Grupo E",  // Costa de Marfil
        2382 to "Grupo E",  // Ecuador

        // Grupo F
        1118 to "Grupo F",  // Países Bajos
        12 to "Grupo F",    // Japón
        5 to "Grupo F",     // Suecia
        28 to "Grupo F",    // Túnez

        // Grupo G
        1 to "Grupo G",     // Bélgica
        32 to "Grupo G",    // Egipto
        22 to "Grupo G",    // Irán
        4673 to "Grupo G",  // Nueva Zelanda

        // Grupo H
        9 to "Grupo H",     // España
        19128 to "Grupo H", // Cabo Verde
        23 to "Grupo H",    // Arabia Saudita
        7 to "Grupo H",     // Uruguay

        // Grupo I
        2 to "Grupo I",     // Francia
        13 to "Grupo I",    // Senegal
        1567 to "Grupo I",  // Irak
        1090 to "Grupo I",  // Noruega

        // Grupo J
        26 to "Grupo J",    // Argentina
        1532 to "Grupo J",  // Argelia
        775 to "Grupo J",   // Austria
        1548 to "Grupo J",  // Jordania

        // Grupo K
        27 to "Grupo K",    // Portugal
        1508 to "Grupo K",  // República Democrática del Congo
        1568 to "Grupo K",  // Uzbekistán
        8 to "Grupo K",     // Colombia

        // Grupo L
        10 to "Grupo L",    // Inglaterra
        3 to "Grupo L",     // Croacia
        1504 to "Grupo L",  // Ghana
        11 to "Grupo L"     // Panamá
    )

    fun getGroupForTeam(teamId: Int): String {
        return teamGroups[teamId] ?: "Unknown Group"
    }
}