package com.example.findit.ui

import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.compose.animation.Crossfade
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.font.Typeface
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import com.example.findit.R
import com.github.mikephil.charting.charts.PieChart
import com.github.mikephil.charting.components.Legend
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry
import com.github.mikephil.charting.utils.ColorTemplate

data class PieChartData(
    var category: String?,
    var value: Float
)

val getPieChartData = listOf(
    PieChartData("Швейное дело", 151310f),
    PieChartData("Офис", 51392f),
    PieChartData("Стройка", 20310f),
    PieChartData("Не в обороте", 76988f)
)

@Composable
fun MoneyFlowFragment(
    modifier: Modifier = Modifier
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween,
        modifier = modifier
    ) {
        Text(
            text = "Оборот",
            fontWeight = FontWeight.Bold,
            fontSize = 36.sp,
            modifier = Modifier
                .fillMaxWidth(),
            textAlign = TextAlign.Left
        )

        PieChartView()

        val fontSizeSmall = 16.sp

        Column {
            Row {
                Text(text = "Всего денег: ", fontSize = fontSizeSmall)
                Text(text = "300000 р.", fontSize = fontSizeSmall, fontWeight = FontWeight.Bold)
            }

            Spacer(modifier = Modifier.height(12.dp))

            Row {
                Text(text = "В обороте: ", fontSize = fontSizeSmall)
                Text(text = "223012 р. (${223012*100/300000}%)", fontSize = fontSizeSmall, fontWeight = FontWeight.Bold)
            }

            Spacer(modifier = Modifier.height(6.dp))

            Row {
                Text(text = "Неиспользованных денег: ", fontSize = fontSizeSmall)
                Text(text = "${300000 - 223012} р. (${76988*100/300000 + 1}%)", fontSize = fontSizeSmall, fontWeight = FontWeight.Bold)
            }

            Spacer(modifier = Modifier.height(24.dp))

            Divider(color = Color.Black)

            Row(
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 24.dp, start = 12.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.Share,
                    contentDescription = "share moneyFlow"
                )
                Text(
                    text = "Поделиться",
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .padding(top = 6.dp, end = 6.dp)
                )
            }
        }
    }
}

@Composable
fun PieChartView() {
    AndroidView(
        factory = { context ->
            // on below line we are creating a pie chart
            // and specifying layout params.
            PieChart(context).apply {
                // on below line we are setting description
                // enables for our pie chart.
                this.description.isEnabled = false

                // on below line we are setting draw hole
                // to false not to draw hole in pie chart
                this.isDrawHoleEnabled = false

                // on below line we are enabling legend.
                this.legend.isEnabled = false
            }
        },
        // on below line we are specifying modifier
        // for it and specifying padding to it.
        modifier = Modifier
            .fillMaxWidth()
            .height(250.dp),
        update = {
            // on below line we are calling update pie chart
            // method and passing pie chart and list of data.
            updatePieChartWithData(it, getPieChartData)
        }
    )
}

// on below line we are creating a update pie
// chart function to update data in pie chart.
fun updatePieChartWithData(
    // on below line we are creating a variable
    // for pie chart and data for our list of data.
    chart: PieChart,
    data: List<PieChartData>
) {
    // on below line we are creating
    // array list for the entries.
    val entries = ArrayList<PieEntry>()
    val colors = mutableListOf<Int>()
    // on below line we are running for loop for
    // passing data from list into entries list.
    for (i in data.indices) {
        val item = data[i]
        entries.add(PieEntry(item.value, item.category ?: ""))
        if (item.category == "Не в обороте") {
            colors.add(Color.Gray.toArgb())
        } else {
            colors.add(ColorTemplate.MATERIAL_COLORS[i % ColorTemplate.MATERIAL_COLORS.size])
        }
    }
    // on below line we are creating
    // a variable for pie data set.
    val ds = PieDataSet(entries, "")

    // on below line we are specifying color
    // int the array list from colors.
    ds.colors = colors
    ds.isHighlightEnabled = true

    // on below line we are specifying position for value
    ds.yValuePosition = PieDataSet.ValuePosition.OUTSIDE_SLICE

    // on below line we are specifying position for value inside the slice.
    ds.xValuePosition = PieDataSet.ValuePosition.OUTSIDE_SLICE

    // on below line we are specifying
    // slice space between two slices.
    ds.sliceSpace = 5f

    // on below line we are specifying
    // text size for value.
    ds.valueTextSize = 18f

    // on below line we are specifying type face as bold.
    ds.valueTypeface = android.graphics.Typeface.DEFAULT_BOLD

    // on below line we are creating
    // a variable for pie data
    val d = PieData(ds)

    // on below line we are setting this
    // pie data in chart data.
    chart.data = d

    // on below line we are
    // calling invalidate in chart.
    chart.invalidate()
}

@Preview(showBackground = true, backgroundColor = 0xFF7FCCBD, showSystemUi = true)
@Composable
fun MoneyFlowFragmentPreview() {
    MoneyFlowFragment(modifier = Modifier
        .padding(horizontal = 32.dp, vertical = 64.dp)
        .fillMaxHeight()
    )
}