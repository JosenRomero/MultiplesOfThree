package com.josenromero.multiplesofthree.ui.main.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.geometry.Offset
import com.josenromero.multiplesofthree.data.Particle

@Composable
fun Confetti(
    times: Int = 30,
    initialPosition: Offset,
    range: IntRange = -300..300
) {

    val particles: MutableList<Particle> = mutableListOf()

    repeat(times = times) { i ->
        particles.add(
            Particle(id = i, coordinate = initialPosition)
        )
    }

    particles.forEach { particle ->
        AnimatedParticle(
            id = particle.id,
            initialPosition = particle.coordinate,
            posRange = range,
            particleDescription = "confetti particle ${particle.id}",
            isRandomColor = true
        )
    }

}