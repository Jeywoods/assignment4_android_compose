package mmcs.assignment4_compose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.border
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.runtime.getValue
import androidx.compose.runtime.collectAsState
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import mmcs.assignment4_compose.ui.theme.Assignment4_composeTheme
import mmcs.assignment4_compose.viewmodel.Operation
import mmcs.assignment4_compose.viewmodel.Calculator
import mmcs.assignment4_compose.viewmodel.CalculatorViewModel

class MainActivity : ComponentActivity() {
    private val viewModel: CalculatorViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Assignment4_composeTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    CalculatorScreen(viewModel)
                }
            }
        }
    }
}

@Composable
fun CalculatorScreen(viewModel: Calculator) {
    val display by viewModel.display.collectAsState()
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ){
    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        verticalArrangement = Arrangement.Bottom

    ) {
        val isDark = isSystemInDarkTheme()

        Surface(
            modifier = Modifier
                .fillMaxWidth()
                .weight(2f)
                .padding(10.dp)
                .shadow(
                    elevation = if (isDark) 20.dp else 4.dp,
                    shape = RoundedCornerShape(16.dp)
                )
                .border(
                    width = if (isDark) 0.dp else 1.dp,
                    color = Color.White.copy(alpha = 1f),
                    shape = RoundedCornerShape(16.dp)
                ),
            shape = RoundedCornerShape(16.dp),
            color = MaterialTheme.colorScheme.background
        ) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(
                        start = 16.dp,
                        end = 16.dp,
                        top= 16.dp
                    ),
                contentAlignment = Alignment.TopEnd
            ) {
                Text(
                    text = display,
                    fontSize = 48.sp,
                    lineHeight = 60.sp,
                    color = MaterialTheme.colorScheme.onPrimaryContainer,
                    modifier = Modifier
                        .fillMaxWidth()
                        .testTag("DISPLAY"),
                    textAlign = TextAlign.End,
                    softWrap = true,
                    maxLines = 3,
                    overflow = TextOverflow.Clip
                )
            }
        }
        Column(
            modifier = Modifier.weight(4f).padding(top = 20.dp),
            verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                CalculatorButton(
                    text = "AC",
                    onClick = { viewModel.reset() },
                    modifier = Modifier.weight(1f),
                    backgroundColor = MaterialTheme.colorScheme.primaryContainer,
                    textColor = MaterialTheme.colorScheme.primary
                )
                CalculatorButton(
                    text = "C",
                    onClick = { viewModel.clear() },
                    modifier = Modifier.weight(1f),
                    backgroundColor = MaterialTheme.colorScheme.primaryContainer,
                    textColor = MaterialTheme.colorScheme.primary
                )
                CalculatorButton(
                    text = "%",
                    onClick = { viewModel.addOperation(Operation.PERC) },
                    modifier = Modifier.weight(1f),
                    backgroundColor = MaterialTheme.colorScheme.primaryContainer,
                    textColor = MaterialTheme.colorScheme.primary
                )
                CalculatorButton(
                    text = "÷",
                    onClick = { viewModel.addOperation(Operation.DIV) },
                    modifier = Modifier.weight(1f),
                    backgroundColor = MaterialTheme.colorScheme.primaryContainer,
                    textColor = MaterialTheme.colorScheme.primary
                )
            }
            Spacer(modifier = Modifier.height(4.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                CalculatorButton(
                    text = "7",
                    onClick = { viewModel.addDigit(7) },
                    modifier = Modifier.weight(1f),
                    backgroundColor = MaterialTheme.colorScheme.primaryContainer,
                    textColor = MaterialTheme.colorScheme.onPrimaryContainer
                )
                CalculatorButton(
                    text = "8",
                    onClick = { viewModel.addDigit(8) },
                    modifier = Modifier.weight(1f),
                    backgroundColor = MaterialTheme.colorScheme.primaryContainer,
                    textColor = MaterialTheme.colorScheme.onPrimaryContainer
                )
                CalculatorButton(
                    text = "9",
                    onClick = { viewModel.addDigit(9) },
                    modifier = Modifier.weight(1f),
                    backgroundColor = MaterialTheme.colorScheme.primaryContainer,
                    textColor = MaterialTheme.colorScheme.onPrimaryContainer
                )
                CalculatorButton(
                    text = "×",
                    onClick = { viewModel.addOperation(Operation.MUL) },
                    modifier = Modifier.weight(1f),
                    backgroundColor = MaterialTheme.colorScheme.primaryContainer,
                    textColor = MaterialTheme.colorScheme.primary
                )
            }
            Spacer(modifier = Modifier.height(4.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                CalculatorButton(
                    text = "4",
                    onClick = { viewModel.addDigit(4) },
                    modifier = Modifier.weight(1f),
                    backgroundColor = MaterialTheme.colorScheme.primaryContainer,
                    textColor = MaterialTheme.colorScheme.onPrimaryContainer
                )
                CalculatorButton(
                    text = "5",
                    onClick = { viewModel.addDigit(5) },
                    modifier = Modifier.weight(1f),
                    backgroundColor = MaterialTheme.colorScheme.primaryContainer,
                    textColor = MaterialTheme.colorScheme.onPrimaryContainer
                )
                CalculatorButton(
                    text = "6",
                    onClick = { viewModel.addDigit(6) },
                    modifier = Modifier.weight(1f),
                    backgroundColor = MaterialTheme.colorScheme.primaryContainer,
                    textColor = MaterialTheme.colorScheme.onPrimaryContainer
                )
                CalculatorButton(
                    text = "-",
                    onClick = { viewModel.addOperation(Operation.SUB) },
                    modifier = Modifier.weight(1f),
                    backgroundColor = MaterialTheme.colorScheme.primaryContainer,
                    textColor = MaterialTheme.colorScheme.primary
                )
            }
            Spacer(modifier = Modifier.height(4.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                CalculatorButton(
                    text = "1",
                    onClick = { viewModel.addDigit(1) },
                    modifier = Modifier.weight(1f),
                    backgroundColor = MaterialTheme.colorScheme.primaryContainer,
                    textColor = MaterialTheme.colorScheme.onPrimaryContainer
                )
                CalculatorButton(
                    text = "2",
                    onClick = { viewModel.addDigit(2) },
                    modifier = Modifier.weight(1f),
                    backgroundColor = MaterialTheme.colorScheme.primaryContainer,
                    textColor = MaterialTheme.colorScheme.onPrimaryContainer
                )
                CalculatorButton(
                    text = "3",
                    onClick = { viewModel.addDigit(3) },
                    modifier = Modifier.weight(1f),
                    backgroundColor = MaterialTheme.colorScheme.primaryContainer,
                    textColor = MaterialTheme.colorScheme.onPrimaryContainer
                )
                CalculatorButton(
                    text = "+",
                    onClick = { viewModel.addOperation(Operation.ADD) },
                    modifier = Modifier.weight(1f),
                    backgroundColor = MaterialTheme.colorScheme.primaryContainer,
                    textColor = MaterialTheme.colorScheme.primary
                )
            }
            Spacer(modifier = Modifier.height(4.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                CalculatorButton(
                    text = "+/-",
                    onClick = { viewModel.addOperation(Operation.NEG) },
                    modifier = Modifier.weight(1f),
                    backgroundColor = MaterialTheme.colorScheme.primaryContainer,
                    textColor = MaterialTheme.colorScheme.primary
                )
                CalculatorButton(
                    text = "0",
                    onClick = { viewModel.addDigit(0) },
                    modifier = Modifier.weight(1f),
                    backgroundColor = MaterialTheme.colorScheme.primaryContainer,
                    textColor = MaterialTheme.colorScheme.onPrimaryContainer
                )
                CalculatorButton(
                    text = ".",
                    onClick = { viewModel.addPoint() },
                    modifier = Modifier.weight(1f),
                    backgroundColor = MaterialTheme.colorScheme.primaryContainer,
                    textColor = MaterialTheme.colorScheme.onPrimaryContainer
                )
                CalculatorButton(
                    text = "=",
                    onClick = { viewModel.compute() },
                    modifier = Modifier.weight(1f),
                    backgroundColor = MaterialTheme.colorScheme.secondaryContainer,
                    textColor = MaterialTheme.colorScheme.onSecondaryContainer
                )
            }
        }
    }
    }
}


@Composable
fun CalculatorButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    backgroundColor: Color = MaterialTheme.colorScheme.primaryContainer,
    textColor: Color = MaterialTheme.colorScheme.onPrimaryContainer
){
    val isDark = isSystemInDarkTheme()
    Button(
        onClick = onClick,
        modifier = modifier
            .aspectRatio(1f)
            .padding(4.dp)
            .shadow(
                elevation = if (isDark) 2.dp else 8.dp,
                shape = CircleShape,
                ambientColor = if (isDark)
                    Color.White.copy(alpha = 1f)
                else
                    Color.Black.copy(alpha = 1f),
                spotColor = if (isDark)
                    Color.White.copy(alpha = 1f)
                else
                    Color.Black.copy(alpha = 1f)
            ),
        shape = CircleShape,
        colors = ButtonDefaults.buttonColors(
            contentColor = textColor,
            containerColor = backgroundColor
        )
    ){
        Text(
            text = text,
            fontSize = 18.sp,
        )
    }

}
@Preview(
    name = "Light Theme",
    showBackground = true,
    uiMode = android.content.res.Configuration.UI_MODE_NIGHT_NO
)
@Preview(
    name = "Dark Theme",
    showBackground = true,
    uiMode = android.content.res.Configuration.UI_MODE_NIGHT_YES
)
@Composable
fun CalculatorScreenPreview() {
    Assignment4_composeTheme {
        CalculatorScreen(CalculatorViewModel())
    }
}