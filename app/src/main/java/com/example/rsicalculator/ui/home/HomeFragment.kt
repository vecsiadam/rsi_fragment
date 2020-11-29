package com.example.rsicalculator.ui.home

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.rsicalculator.R
import com.example.rsicalculator.model.RsiResult
import com.example.rsicalculator.service.CalculatorService

class HomeFragment : Fragment() {


    internal var calculatorService: CalculatorService = CalculatorService()
    internal var result: RsiResult = RsiResult()

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {

        val root = inflater.inflate(R.layout.fragment_home, container, false)

        val calculateButton = root.findViewById<Button>(R.id.btn_calculate)
        val ageText = root.findViewById<TextView>(R.id.txt_age)
        val weightText = root.findViewById<TextView>(R.id.txt_weight)
        val eetText = root.findViewById<TextView>(R.id.txt_eet)
        val eetTeethText = root.findViewById<TextView>(R.id.txt_ett_teeth)
        val ketaminIvSleep = root.findViewById<TextView>(R.id.ketamin_iv_sleep_text)
        val succynilcholin = root.findViewById<TextView>(R.id.succynilcholin_text)
        val rocuronium = root.findViewById<TextView>(R.id.rocuronium_text)
        val fentanyl = root.findViewById<TextView>(R.id.fentanyl_text)
        val morphium = root.findViewById<TextView>(R.id.morphium_text)
        val midazolam = root.findViewById<TextView>(R.id.midazolam_text)
        val ketaminIv = root.findViewById<TextView>(R.id.ketamin_iv_text)
        val ketaminIm = root.findViewById<TextView>(R.id.ketamin_im_text)
        val adrenalin = root.findViewById<TextView>(R.id.adrenalin_text)
        val atrophin = root.findViewById<TextView>(R.id.atrophin_text)
        val fluidReplacement = root.findViewById<TextView>(R.id.fluid_replacement_text)
        val defibrilation = root.findViewById<TextView>(R.id.defibrilation_text)
        val glucose = root.findViewById<TextView>(R.id.glucose_text)
        val tidalVolumen = root.findViewById<TextView>(R.id.tidal_volumen_text)

        val editWeight = root.findViewById<EditText>(R.id.edt_weight)
        val editAge = root.findViewById<EditText>(R.id.edt_age)


        calculateButton.setOnClickListener {
            val weight = editWeight.text.toString()
            val age = editAge.text.toString()

            if (age.isEmpty() && weight.isEmpty()) {
                setTextsEmpty(
                    weightText,
                    ageText,
                    eetText,
                    eetTeethText,
                    ketaminIvSleep,
                    succynilcholin,
                    rocuronium,
                    fentanyl,
                    morphium,
                    midazolam,
                    ketaminIv,
                    ketaminIm,
                    adrenalin,
                    atrophin,
                    fluidReplacement,
                    defibrilation,
                    glucose,
                    tidalVolumen
                )
            }

            if (weight.isNotEmpty()) {
                result = calculatorService.calculateMedicine(weight.toInt(), null)
                weightText.text = "Testsúly: " + result.weight.toString()
                ageText.text = "Életkor: -"
                eetText.text = "EET méret: -"
                eetTeethText.text = "EET hossz a fogaknál: -"
                setTexts(
                    ketaminIvSleep,
                    succynilcholin,
                    rocuronium,
                    fentanyl,
                    morphium,
                    midazolam,
                    ketaminIv,
                    ketaminIm,
                    adrenalin,
                    atrophin,
                    fluidReplacement,
                    defibrilation,
                    glucose,
                    tidalVolumen
                )


            }

            if (age.isNotEmpty()) {
                val weightByAge = (age.toInt() + 4) * 2
                result = calculatorService.calculateMedicine(weightByAge, age.toInt())
                weightText.text = "Testsúly: " + result.weight.toString()
                ageText.text = "Életkor: " + result.age.toString()
                eetText.text = String.format("EET méret: %.2f", result.eet)
                eetTeethText.text =
                    "EET hossz a fogaknál: " + String.format("%.2f", result.eetTeeth)

                setTexts(
                    ketaminIvSleep,
                    succynilcholin,
                    rocuronium,
                    fentanyl,
                    morphium,
                    midazolam,
                    ketaminIv,
                    ketaminIm,
                    adrenalin,
                    atrophin,
                    fluidReplacement,
                    defibrilation,
                    glucose,
                    tidalVolumen
                )

            }

            if (age.isNotEmpty() && weight.isNotEmpty()) {
                setTextsEmpty(
                    weightText,
                    ageText,
                    eetText,
                    eetTeethText,
                    ketaminIvSleep,
                    succynilcholin,
                    rocuronium,
                    fentanyl,
                    morphium,
                    midazolam,
                    ketaminIv,
                    ketaminIm,
                    adrenalin,
                    atrophin,
                    fluidReplacement,
                    defibrilation,
                    glucose,
                    tidalVolumen
                )
            }

            closeKeyBoard(root)
        }

        return root
    }

    private fun setTexts(
        ketaminIvSleep: TextView,
        succynilcholin: TextView,
        rocuronium: TextView,
        fentanyl: TextView,
        morphium: TextView,
        midazolam: TextView,
        ketaminIv: TextView,
        ketaminIm: TextView,
        adrenalin: TextView,
        atrophin: TextView,
        fluidReplacement: TextView,
        defibrilation: TextView,
        glucose: TextView,
        tidalVolumen: TextView
    ) {
        ketaminIvSleep.text = String.format("%dmg/ttkg",result.ketaminIvSleep)
        succynilcholin.text = String.format("%dmg/ttkg",result.succynilcholin)
        rocuronium.text = String.format("%dmg/ttkg",result.rocuronium)
        fentanyl.text = String.format("%dμg/ttkg",result.fentanyl)
        morphium.text = String.format("%.2fmg/ttkg", result.morphium)
        midazolam.text = String.format("%.2fmg/ttkg", result.midazolam)
        ketaminIv.text = String.format("%.2fmg/ttkg", result.ketaminIv)
        ketaminIm.text = String.format("%d-%dmg/ttkg", result.ketaminIm,result.ketaminIm?.times(2))
        adrenalin.text = String.format("%.2fmg/ttkg", result.adrenalin)
        atrophin.text = String.format("%.2fmg/ttkg", result.atropin)
        fluidReplacement.text = String.format("%d-%dml/ttkg", result.fluidReplacement,result.fluidReplacement?.times(2))
        defibrilation.text = String.format("%dj/ttkg",result.defibrilationEnergy)
        glucose.text = String.format("%.2fmg/ttkg", result.glucose)
        tidalVolumen.text = String.format("%dml/ttkg",result.tidalVolument)
    }

    private fun setTextsEmpty(
        weightText: TextView,
        ageText: TextView,
        eetText: TextView,
        eetTeethText: TextView,
        ketaminIvSleep: TextView,
        succynilcholin: TextView,
        rocuronium: TextView,
        fentanyl: TextView,
        morphium: TextView,
        midazolam: TextView,
        ketaminIv: TextView,
        ketaminIm: TextView,
        adrenalin: TextView,
        atrophin: TextView,
        fluidReplacement: TextView,
        defibrilation: TextView,
        glucose: TextView,
        tidalVolumen: TextView
    ) {
        weightText.text = ""
        ageText.text = ""
        eetText.text = ""
        eetTeethText.text = ""
        ketaminIvSleep.text = "-"
        succynilcholin.text = "-"
        rocuronium.text = "-"
        fentanyl.text = "-"
        morphium.text = "-"
        midazolam.text = "-"
        ketaminIv.text = "-"
        ketaminIm.text = "-"
        adrenalin.text = "-"
        atrophin.text = "-"
        fluidReplacement.text = "-"
        defibrilation.text = "-"
        glucose.text = "-"
        tidalVolumen.text = "-"
    }

    private fun closeKeyBoard(view : View) {
        if (view != null) {
            val imm = getActivity()?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(view.windowToken, 0)
        }
    }
}