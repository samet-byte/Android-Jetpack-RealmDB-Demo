package com.sametb.realmdbdemo

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.sametb.realmdbdemo.model.placeholder.Course
import com.sametb.realmdbdemo.ui.theme.RealmDBDemoTheme

class MainActivity : ComponentActivity() {

    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RealmDBDemoTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val courses by viewModel.courses.collectAsState()

                    LazyColumn(
                        modifier = Modifier
                            .fillMaxSize(),
                        verticalArrangement = Arrangement.spacedBy(8.dp),
                    ) {
                        items(courses) { course ->
                            CourseItem(
                                course = course,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(16.dp)
                                    .clickable {
                                        Toast
                                            .makeText(
                                                this@MainActivity,
                                                "Clicked ${course.name}",
                                                Toast.LENGTH_SHORT
                                            )
                                            .show()

                                        viewModel.showCourseDetails(course)
                                    }
                            )
                        }
                    }
                }
                if (viewModel.courseDetails != null) {
                    Dialog(onDismissRequest = viewModel::hideCourseDetails) {
                        Column(
                            modifier = Modifier
                                .widthIn(200.dp, 300.dp)
                                .clip(RoundedCornerShape(10.dp))
                                .background(Color.Yellow)
                                .padding(16.dp)
                        ) {
                            viewModel.courseDetails?.teacher?.address?.let { address ->
                                Text(
                                    text = address.toString(),
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 20.sp,
                                )
                                Button(
                                    onClick = viewModel::deleteCourse,
                                    colors = ButtonDefaults.buttonColors(
                                        containerColor = MaterialTheme.colorScheme.errorContainer,
                                        contentColor = MaterialTheme.colorScheme.onErrorContainer
                                    )
                                ) {
                                    Text(text = "❌ DELETE ❌")
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}


@Composable
fun CourseItem(
    course: Course,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
    ) {
        Text(
            text = course.name,
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp,
        )
        Text(
            text = course.teacher?.address?.fullName ?: "No Name",
            fontStyle = FontStyle.Italic,
            fontSize = 16.sp,
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = course.enrolledStudents.joinToString { it.name },
            fontSize = 10.sp,
        )
    }
}
