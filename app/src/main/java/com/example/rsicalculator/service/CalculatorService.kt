package com.example.rsicalculator.service

import com.example.rsicalculator.model.RsiResult

class CalculatorService {

    fun calculateMedicine(weight: Int, age: Int?) : RsiResult {
        var rsiResult: RsiResult = RsiResult()
        if(age != null){
            rsiResult.age = age
            rsiResult.eet = (age / 4.0) + 4
            rsiResult.eetTeeth = (age / 2.0) + 12
        }
        rsiResult.weight = weight
        rsiResult.ketaminIvSleep = 2 * weight
        rsiResult.succynilcholin = 2  * weight
        rsiResult.rocuronium = weight
        rsiResult.fentanyl =  weight
        rsiResult.morphium = 0.1 * weight
        rsiResult.midazolam = 0.02 * weight
        rsiResult.ketaminIv = 0.5 * weight
        rsiResult.ketaminIm = 5 * weight
        rsiResult.adrenalin = 0.01 * weight
        rsiResult.atropin = 0.02 * weight
        rsiResult.fluidReplacement = 10 * weight
        rsiResult.defibrilationEnergy = 4 * weight
        rsiResult.glucose = 2.5 * weight
        rsiResult.tidalVolument = 7 * weight
        return rsiResult;
    }

}