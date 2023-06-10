/*
 * Copyright 2023 Google LLC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.google.android.catalog.app.compose

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.material.ripple.LocalRippleTheme
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.google.android.catalog.app.compose.common.Spacer
import com.google.android.catalog.framework.annotations.Sample


@Suppress("unused","SpellCheckingInspection")
@Sample(
    name = "ripple color change",
    description = "ripple color controll," +
        " Ripple effect doesn't hidden with Color.Transparent, " +
        " When disabling the ripple effect, you use ripple color and alpha."
    ,
    tags = ["click","ripple"]
)
@Composable
fun RippleColorControl() {

    val currentColor = LocalRippleTheme.current.defaultColor()

    Column(modifier = Modifier
        .fillMaxSize()
        .padding(16.dp),
    ) {


        val (color, onColorChange) = remember{
            mutableStateOf(currentColor)
        }

        Surface(modifier = Modifier.fillMaxWidth()) {
            Text(text = "LocalRippleThmem 을 이용하면 리플이펙트의 생상을 조절 할 수 있습니다. \n " +
                "다만 Color.Tranparent 를 지정해도 리플 이펙트가 완전히 투명하지는 않습니다. (bom 2023.04.00)")
        }

        Spacer(height = 16.dp)

        FlowRow(horizontalArrangement = Arrangement.spacedBy(8.dp)) {

            ColorButton(
                text = "Theme Color",
                color = currentColor,
                isChecked = color == currentColor,
                onClick = { onColorChange(currentColor) })

            ColorButton(
                text = "Red",
                color = Color.Red,
                isChecked = color == Color.Red,
                onClick = { onColorChange(Color.Red) })

            ColorButton(
                text = "Cyan",
                color = Color.Cyan,
                isChecked = color == Color.Cyan,
                onClick = { onColorChange(Color.Cyan) })

            ColorButton(
                text = "Transparent",
                color = Color.Gray.copy(alpha = 0.3f),
                isChecked = color == Color.Transparent,
                onClick = { onColorChange(Color.Transparent) })
        }


        Spacer(height = 32.dp)

        LocalRippleColor(color = color) {


            Surface(
                modifier = Modifier.fillMaxWidth(),
                onClick = {},
            ) {
                Box(modifier = Modifier.padding(8.dp)){
                    Text(text = "Surface Ripple Color")
                }
            }

            Button(onClick = {  }) {
                Text(text = "Button Ripple Color")
            }

        }
    }
}

@Composable
private fun ColorButton(
    text : String,
    color : Color,
    isChecked : Boolean,
    onClick: () -> Unit,
    borderStoke : BorderStroke = BorderStroke(2.dp,color = color),
){

    OutlinedButton(
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(
            contentColor = color
        ),
        border = borderStoke,
        shape = ButtonDefaults.outlinedShape.takeIf { !isChecked } ?: CutCornerShape(8.dp)
    ) {

        Row(verticalAlignment = Alignment.CenterVertically) {
            Surface(modifier = Modifier.size(24.dp), color = color, shape = CircleShape) {}
            Spacer(modifier = Modifier.size(8.dp))
            Text(text = text)
        }

    }

}



