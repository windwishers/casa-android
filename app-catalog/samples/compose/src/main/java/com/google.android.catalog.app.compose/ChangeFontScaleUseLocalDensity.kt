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

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Slider
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.google.android.catalog.app.compose.common.Spacer
import com.google.android.catalog.framework.annotations.Sample

@Suppress("unused","SpellCheckingInspection")
@Sample(
    name = "Change Font Scale",
    description = "change font Scale in limited scope , use  LocalDensity",
    tags = ["text","font","fontScale"]
)
@Composable
fun ChangeFontScaleUseLocalDensity() {
    Column(modifier = Modifier
        .fillMaxSize()
        .padding(16.dp)) {

        val density = LocalDensity.current
        val (scale,onChangeScale) = remember(density){
            mutableStateOf(density.fontScale)
        }
        Surface(modifier = Modifier.fillMaxWidth()) {
            Text(text = "제한 된 범위에서만 Font Scale 을 변경 하려면 LocalDensity의 fontScale 을 사용합니다.")
        }
        Spacer(height = 8.dp)

        Text(text = "LocalDensity.current.density = ${LocalDensity.current.density}")
        Text(text = "LocalDensity.current.fontScale = ${LocalDensity.current.fontScale}")

        Spacer(height = 8.dp)

        Slider(
            value = scale,
            onValueChange = onChangeScale,
            valueRange = 0.5f..3f
        )

        Spacer(height = 4.dp)

        FlowRow(horizontalArrangement = Arrangement.spacedBy(4.dp)) {

            for (s in listOf(0.5f, 0.8f, 1f, 1.3f, 2f)) {
                OutlinedButton(onClick = {
                    onChangeScale(s)
                }) {
                    Text(text = s.toString())
                }
            }
        }

        Spacer(height = 16.dp)

        Surface {
            Text(
                text = "CURRENT : Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed augue ante, lacinia eget tempus vel.",
                maxLines = 1,
                overflow = TextOverflow.Clip
            )
        }

        LocalFontScale(fontScale = scale) {
            Surface{
                Text(text = "LOCALY : Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed augue ante, lacinia eget tempus vel.")
            }
        }

    }
}