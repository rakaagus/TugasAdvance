package com.infinitelearning.tugasadvance.presentation.screen.alarm


import android.widget.Toast
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.outlined.AlarmAdd
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TimePicker
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.material3.rememberTimePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.infinitelearning.infiniteapp.presentation.screen.alarm.component.ScheduleDateTextField
import com.infinitelearning.infiniteapp.presentation.screen.alarm.component.ScheduleNameTextField
import com.infinitelearning.infiniteapp.presentation.screen.alarm.component.ScheduleTimeTextField
import com.infinitelearning.infiniteapp.presentation.screen.alarm.component.TimePickerDialog
import com.infinitelearning.tugasadvance.utils.cancelNotification
import com.infinitelearning.tugasadvance.utils.scheduleNotification
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AlarmScreen(
    navController: NavController,
    modifier: Modifier = Modifier,
    scrollState: ScrollState = rememberScrollState()
) {
    val context = LocalContext.current

    val date = remember { Calendar.getInstance().timeInMillis }
    val formatter = SimpleDateFormat("dd-MM-yyyy", Locale.getDefault())

    var scheduleText by remember { mutableStateOf("") }
    var scheduleDate by remember { mutableStateOf("") }
    var scheduleTime by rememberSaveable { mutableStateOf("") }

    val datePickerState = rememberDatePickerState(initialSelectedDateMillis = date)
    var showDatePicker by remember { mutableStateOf(false) }

    val timePickerState = rememberTimePickerState()
    var showTimePicker by remember { mutableStateOf(false) }

    if (showDatePicker) {
        DatePickerDialog(
            onDismissRequest = { showDatePicker = false },
            confirmButton = {
                TextButton(
                    onClick = {
                        val selectedDate = Calendar.getInstance().apply {
                            timeInMillis = datePickerState.selectedDateMillis!!
                        }
                        scheduleDate = formatter.format(selectedDate.time)
                        showDatePicker = false
                    }
                ) {
                    Text("OK", color = Color.White)
                }
            },
            dismissButton = {
                TextButton(onClick = { showDatePicker = false }
                ) { Text("Cancel", color = Color.White) }
            }
        ) {
            DatePicker(state = datePickerState)
        }
    }

    if (showTimePicker) {
        TimePickerDialog(
            onDismissRequest = { showTimePicker = false },
            confirmButton = {
                TextButton(
                    onClick = {
                        scheduleTime = "${timePickerState.hour}:${timePickerState.minute}"
                        showTimePicker = false
                    }
                ) {
                    Text("OK", color = Color.White)
                }
            },
            dismissButton = {
                TextButton(
                    onClick = { showTimePicker = false }
                ) {
                    Text("Cancel", color = Color.White)
                }
            }
        ) {
            TimePicker(state = timePickerState)
        }
    }
    Surface(
        modifier = modifier
            .fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp)
                .verticalScroll(scrollState),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = "Buat Alarm",
                style = MaterialTheme.typography.headlineLarge,
                fontWeight = FontWeight.Medium,
            )
            Spacer(modifier = Modifier.height(16.dp))
            ScheduleNameTextField(
                value = scheduleText,
                onValueChange = { if (it.length <= 25) scheduleText = it },
                label = "Nama Kegiatan"
            )
            ScheduleDateTextField(
                value = scheduleDate,
                onValueChange = { scheduleDate = it },
                label = "Atur Tanggal",
                icon = Icons.Default.DateRange,
                onIconClick = {
                    showDatePicker = true
                }
            )
            ScheduleTimeTextField(
                value = scheduleTime,
                label = "Atur Jam",
                icon = Icons.Outlined.AlarmAdd,
                onValueChange = { scheduleTime = it },
                onIconClick = { showTimePicker = true })
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.End
            ) {
                Button(
                    onClick = {
                        cancelNotification(context)
                    },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.Red.copy(0.8f)
                    ),
                    shape = MaterialTheme.shapes.small
                ) {
                    Text(
                        text = "Batal",
                        style = MaterialTheme.typography.bodyLarge,
                        color = Color.White
                    )
                }
                Spacer(modifier = Modifier.width(16.dp))
                Button(
                    onClick = {
                        if (scheduleText.isBlank() || scheduleDate.isBlank() || scheduleTime.isBlank()) {
                            Toast.makeText(
                                context,
                                "Semua field wajib diisi!",
                                Toast.LENGTH_SHORT
                            )
                                .show()
                        } else {
                            scheduleNotification(
                                context,
                                timePickerState,
                                datePickerState,
                                scheduleText
                            )
                            scheduleText = ""
                            scheduleDate = ""
                            scheduleTime = ""
                        }
                    },
                    shape = MaterialTheme.shapes.small
                ) {
                    Text(
                        text = "Simpan",
                        style = MaterialTheme.typography.bodyLarge,
                        color = Color.White
                    )
                }
            }
        }
    }
}
