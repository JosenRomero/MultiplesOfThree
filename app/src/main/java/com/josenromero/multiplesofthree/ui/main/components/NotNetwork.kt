package com.josenromero.multiplesofthree.ui.main.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.josenromero.multiplesofthree.R

@Composable
fun NotNetwork() {

    AnimatedFadeIn {
        Column(
            modifier = Modifier
                .fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_launcher_foreground),
                contentDescription = "logo icon",
                modifier = Modifier
                    .background(
                        color = MaterialTheme.colorScheme.primary,
                        shape = CutCornerShape(50.dp)
                    )
            )
            Spacer(
                modifier = Modifier.height(30.dp)
            )
            Column(
                modifier = Modifier
                    .background(
                        color = MaterialTheme.colorScheme.secondary,
                        shape = RoundedCornerShape(10.dp, 0.dp, 10.dp, 0.dp)
                    )
                    .padding(horizontal = 16.dp, vertical = 8.dp)
            ) {
                CustomText(text = stringResource(id = R.string.text_connect_to_the_internet))
            }
        }
    }

}

@Composable
fun NotNetworkAndBtn(
    btn: () -> Unit
) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(bottom = 10.dp),
        verticalArrangement = Arrangement.Bottom,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        OutlinedButton(
            onClick = btn
        ) {
            CustomText(text = stringResource(id = R.string.text_connect_to_the_internet))
        }
    }

}