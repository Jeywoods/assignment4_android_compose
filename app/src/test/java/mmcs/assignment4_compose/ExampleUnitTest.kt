package mmcs.assignment4_compose

import kotlinx.coroutines.runBlocking
import mmcs.assignment4_compose.viewmodel.CalculatorViewModel
import org.junit.Assert.*
import org.junit.Before
import mmcs.assignment4_compose.viewmodel.Operation
import org.junit.Test

class CalculatorViewModelTest {
    private lateinit var viewModel: CalculatorViewModel
    @Before
    fun setInitValue(){
        viewModel = CalculatorViewModel()
    }
    @Test
    fun testAddDigit1(){
        viewModel.addDigit(5)
        assertEquals("5", viewModel.display.value)
    }
    @Test
    fun testAddDigit2(){
        viewModel.addDigit(1)
        viewModel.addDigit(2)
        viewModel.addDigit(3)
        assertEquals("123", viewModel.display.value)
    }
    @Test
    fun testAddPoint1(){
        viewModel.addDigit(1)
        viewModel.addDigit(2)
        viewModel.addPoint()
        assertEquals("12.", viewModel.display.value)
    }
    @Test
    fun testAddPoint2() = runBlocking{
        viewModel.addDigit(2)
        viewModel.addPoint()
        viewModel.addDigit(7)
        assertEquals("2.7", viewModel.display.value)
    }
    @Test
    fun testAddOperationPlus(){
        viewModel.addDigit(4)
        viewModel.addOperation(Operation.ADD)
        viewModel.addDigit(5)
        viewModel.compute()
        assertEquals("9", viewModel.display.value)
    }
    @Test
    fun testAddOperationSub() = runBlocking{
        viewModel.addDigit(8)
        viewModel.addOperation(Operation.SUB)
        viewModel.addDigit(3)
        viewModel.compute()
        assertEquals("5", viewModel.display.value)
    }
    @Test
    fun testAddOperationMul(){
        viewModel.addDigit(12)
        viewModel.addOperation(Operation.MUL)
        viewModel.addDigit(5)
        viewModel.compute()
        assertEquals("60", viewModel.display.value)
    }
    @Test
    fun testAddOperationDiv(){
        viewModel.addDigit(12)
        viewModel.addOperation(Operation.DIV)
        viewModel.addDigit(4)
        viewModel.compute()
        assertEquals("3", viewModel.display.value)
    }
    @Test
    fun testAddOperationDiv0(){
        viewModel.addDigit(4)
        viewModel.addOperation(Operation.DIV)
        viewModel.addDigit(0)
        viewModel.compute()
        assertEquals("Не определен", viewModel.display.value)
    }
    @Test
    fun testAddOperationPerc(){
        viewModel.addDigit(120)
        viewModel.addOperation(Operation.PERC)
        viewModel.addDigit(5)
        viewModel.compute()
        assertEquals("6", viewModel.display.value)
    }
    @Test
    fun testAddOperationNegPostfix(){
        viewModel.addDigit(12)
        viewModel.addOperation(Operation.NEG)
        viewModel.compute()
        assertEquals("-12", viewModel.display.value)
    }
    @Test
    fun testAddOperationNegPrefix(){
        viewModel.addOperation(Operation.NEG)
        viewModel.addDigit(12)
        viewModel.compute()
        assertEquals("-12", viewModel.display.value)
    }
    @Test
    fun testClear1(){
        viewModel.addDigit(12)
        viewModel.clear()
        assertEquals("0", viewModel.display.value)
    }
    @Test
    fun testClear2(){
        viewModel.addDigit(3)
        viewModel.addOperation(Operation.MUL)
        viewModel.clear()
        viewModel.addDigit(5)
        viewModel.compute()
        assertEquals("15", viewModel.display.value)
    }
    @Test
    fun testReset1(){
        viewModel.addDigit(6)
        viewModel.reset()
        assertEquals("0", viewModel.display.value)
    }
    @Test
    fun testReset2(){
        viewModel.addDigit(6)
        viewModel.addOperation(Operation.DIV)
        viewModel.addDigit(3)
        viewModel.reset()
        viewModel.compute()
        assertEquals("0", viewModel.display.value)
    }
    @Test
    fun testReset3(){
        viewModel.addDigit(6)
        viewModel.addOperation(Operation.DIV)
        viewModel.addDigit(3)
        viewModel.compute()
        viewModel.reset()
        assertEquals("0", viewModel.display.value)
    }
    @Test
    fun testResultFormatted1(){
        viewModel.addDigit(10)
        viewModel.addOperation(Operation.DIV)
        viewModel.addDigit(4)
        viewModel.compute()
        assertEquals("2.5", viewModel.display.value)

    }
    @Test
    fun testResultFormatted2(){
        viewModel.addDigit(2)
        viewModel.addPoint()
        viewModel.addDigit(5)
        viewModel.addOperation(Operation.SUB)
        viewModel.addDigit(1)
        viewModel.addPoint()
        viewModel.addDigit(5)
        viewModel.compute()
        assertEquals("1", viewModel.display.value)
    }
}