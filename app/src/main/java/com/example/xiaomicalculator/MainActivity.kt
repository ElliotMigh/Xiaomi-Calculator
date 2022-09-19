package com.example.xiaomicalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ViewTreeObserver
import android.widget.Toast
import com.example.xiaomicalculator.databinding.ActivityMainBinding
import net.objecthunter.exp4j.ExpressionBuilder
import java.lang.Error

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    private var operator: Char = '+' //example
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        onNumbersClicked()
        onOperatorClicked()
    }

    private fun onNumbersClicked() {
        binding.btn0.setOnClickListener {
            if (binding.txtExpression.text.isNotEmpty()) {
                appendText("0")
            }
        }
        binding.btn1.setOnClickListener {
            appendText("1")
        }
        binding.btn2.setOnClickListener {
            appendText("2")
        }
        binding.btn3.setOnClickListener {
            appendText("3")
        }
        binding.btn4.setOnClickListener {
            appendText("4")
        }
        binding.btn5.setOnClickListener {
            appendText("5")
        }
        binding.btn6.setOnClickListener {
            appendText("6")
        }
        binding.btn7.setOnClickListener {
            appendText("7")
        }
        binding.btn8.setOnClickListener {
            appendText("8")
        }
        binding.btn9.setOnClickListener {
            appendText("9")
        }
        binding.btnDot.setOnClickListener {
            if (binding.txtExpression.text.isEmpty()) {
                appendText("0.")
            } else if (!binding.txtExpression.text.contains(".")) {
                appendText(".")
            }
        }
    }

    private fun onOperatorClicked() {
        binding.btnJam.setOnClickListener {
            if (binding.txtExpression.text.isNotEmpty()) {
                val myCharJam = binding.txtExpression.text.last()
                if (myCharJam != '+'
                    && myCharJam != '-' &&
                    myCharJam != '*' &&
                    myCharJam != '/'
                ) {
                    appendText("+")
                }
            }
        }
        binding.btnMenha.setOnClickListener {
            if (binding.txtExpression.text.isNotEmpty()) {
                val myCharMenha = binding.txtExpression.text.last()
                if (myCharMenha != '+'
                    && myCharMenha != '-' &&
                    myCharMenha != '*' &&
                    myCharMenha != '/'
                ) {
                    appendText("-")
                }
            }
        }
        binding.btnZarb.setOnClickListener {
            if (binding.txtExpression.text.isNotEmpty()) {
                val myCharZarb = binding.txtExpression.text.last()
                if (myCharZarb != '+'
                    && myCharZarb != '-' &&
                    myCharZarb != '*' &&
                    myCharZarb != '/'
                ) {
                    appendText("*")
                }
            }
        }
        binding.btnTaqsim.setOnClickListener {
            if (binding.txtExpression.text.isNotEmpty()) {
                val myCharTaqsim = binding.txtExpression.text.last()
                if (myCharTaqsim != '+'
                    && myCharTaqsim != '-' &&
                    myCharTaqsim != '*' &&
                    myCharTaqsim != '/'
                ) {
                    appendText("/")
                }
            }
        }
        binding.btnParantezBaz.setOnClickListener {
            appendText("(")
        }
        binding.btnParantezBaste.setOnClickListener {
            appendText(")")
        }
        binding.btnAc.setOnClickListener {
            binding.txtExpression.text = ""
            binding.txtJavab.text = ""
        }
        binding.btnPakidan.setOnClickListener {
            val oldText = binding.txtExpression.text.toString()
            if (oldText.isNotEmpty()) {
                binding.txtExpression.text = oldText.substring(0, oldText.length - 1)
            }
        }
        binding.btnMosavi.setOnClickListener {

            try {
                val expression = ExpressionBuilder(binding.txtExpression.text.toString()).build()
                val result = expression.evaluate()

                val longResult = result.toLong()

                if (result == longResult.toDouble()) {
                    binding.txtJavab.text = longResult.toString()
                } else {
                    //ashari:
                    binding.txtJavab.text = result.toString()
                }
            } catch (e: Exception) {
                binding.txtExpression.text = ""
                binding.txtJavab.text = ""
                Toast.makeText(this, "ERROR...!!!", Toast.LENGTH_LONG).show()
            }
        }
    }

    private fun appendText(newText: String) {

        if (binding.txtJavab.text.isNotEmpty()) {
            binding.txtExpression.text = ""
        }
        binding.txtJavab.text = ""
        binding.txtExpression.append(newText)

        val viewTree: ViewTreeObserver = binding.horizontalScrollViewTxtExpression.viewTreeObserver
        viewTree.addOnGlobalLayoutListener(object : ViewTreeObserver.OnGlobalLayoutListener {
            override fun onGlobalLayout() {
                binding.horizontalScrollViewTxtExpression.viewTreeObserver.removeOnGlobalLayoutListener(
                    this
                )
                binding.horizontalScrollViewTxtExpression.scrollTo(binding.txtExpression.width, 0)
            }

        })
    }
}