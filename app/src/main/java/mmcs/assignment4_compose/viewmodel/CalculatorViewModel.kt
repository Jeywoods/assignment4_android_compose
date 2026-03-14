package mmcs.assignment4_compose.viewmodel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import java.math.BigDecimal
import java.math.RoundingMode

class CalculatorViewModel: ViewModel(), Calculator {
    private var _display = MutableStateFlow<String>("0")
    override var display = _display.asStateFlow()
    private var firstNumber: BigDecimal = BigDecimal.ZERO
    private var secondNumber: BigDecimal = BigDecimal.ZERO
    private var previousOperation: Operation? = null


    override fun addDigit(dig: Int) {
        val currentNumber = _display.value
        val newNumber = if(currentNumber == "0") "$dig" else currentNumber + dig
        _display.value = newNumber
    }

    override fun addPoint() {
        val currentNumber = _display.value
        if(!currentNumber.contains("."))
            _display.value = "$currentNumber."
    }

    override fun addOperation(op: Operation) {

        if (previousOperation != null) {
            compute()
        }

        if (op == Operation.NEG) {
            val number = parseDisplay()
            _display.value = formatResult(number.negate())
            return
        }

        firstNumber = parseDisplay()
        previousOperation = op
        _display.value = "0"
    }

    override fun compute() {

        secondNumber = parseDisplay()

        if (previousOperation == Operation.DIV && secondNumber == BigDecimal.ZERO) {
            _display.value = "Не определен"
            previousOperation = null
            return
        }

        val result = when (previousOperation) {

            Operation.ADD ->
                firstNumber.add(secondNumber)

            Operation.SUB ->
                firstNumber.subtract(secondNumber)

            Operation.MUL ->
                firstNumber.multiply(secondNumber)

            Operation.DIV ->
                firstNumber.divide(secondNumber, 10, RoundingMode.HALF_UP)

            Operation.PERC ->
                firstNumber.multiply(secondNumber)
                    .divide(BigDecimal(100))

            Operation.NEG ->
                secondNumber.negate()

            null ->
                secondNumber
        }

        _display.value = formatResult(result)

        firstNumber = result
        previousOperation = null
    }


    override fun clear() {
        _display.value = "0"
    }

    override fun reset() {
        clear()
        firstNumber = BigDecimal.ZERO
        secondNumber = BigDecimal.ZERO
        previousOperation = null
    }
    private fun parseDisplay(): BigDecimal {
        return try {
            BigDecimal(_display.value)
        } catch (e: Exception) {
            BigDecimal.ZERO
        }
    }
    private fun formatResult(value: BigDecimal): String {

        val stripped = value.stripTrailingZeros()

        return if (stripped.scale() <= 0)
            stripped.toPlainString()
        else
            stripped.toPlainString()
    }
}