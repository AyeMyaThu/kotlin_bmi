package me.myatminsoe.bmicalculator

import android.graphics.drawable.GradientDrawable
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.webkit.WebView
import kotlinx.android.synthetic.main.activity_main.*
import org.adw.library.widgets.discreteseekbar.DiscreteSeekBar
import java.math.RoundingMode
import java.text.DecimalFormat

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        sbWeight.setOnProgressChangeListener(object : DiscreteSeekBar.OnProgressChangeListener {
            override fun onProgressChanged(seekBar: DiscreteSeekBar, value: Int, fromUser: Boolean) {
                tvWeight.text = value.toString() + " Kg"
            }

            override fun onStartTrackingTouch(seekBar: DiscreteSeekBar) {

            }

            override fun onStopTrackingTouch(seekBar: DiscreteSeekBar) {

            }
        })

        sbHeight.setOnProgressChangeListener(object : DiscreteSeekBar.OnProgressChangeListener {
            override fun onProgressChanged(seekBar: DiscreteSeekBar, value: Int, fromUser: Boolean) {
                tvHeight.text = value.toString() + " cm"
            }

            override fun onStartTrackingTouch(seekBar: DiscreteSeekBar) {

            }

            override fun onStopTrackingTouch(seekBar: DiscreteSeekBar) {

            }
        })
    }

    fun calculate(v: View) {
        val weight = sbWeight.progress.toDouble()
        val height = sbHeight.progress.toDouble() / 100
        val df = DecimalFormat("#.#")
        df.roundingMode = RoundingMode.HALF_UP
        val bmi = weight / (height * height)
        tvBMI.text = "BMI\n" + df.format(bmi)
        when (bmi.toInt()) {
            in 0..18-> {
                tvResult.text = "Underweight"
                tvResult.setTextColor(ContextCompat.getColor(this, R.color.blue))
                (tvBMI.background as GradientDrawable).setColor(ContextCompat.getColor(this, R.color.blue))
            }
            in 19..24-> {
                tvResult.text = "Healthy"
                tvResult.setTextColor(ContextCompat.getColor(this, R.color.green))
                (tvBMI.background as GradientDrawable).setColor(ContextCompat.getColor(this, R.color.green))
            }
            in 25..29 -> {
                tvResult.text = "Overweight"
                tvResult.setTextColor(ContextCompat.getColor(this, R.color.yellow))
                (tvBMI.background as GradientDrawable).setColor(ContextCompat.getColor(this, R.color.yellow))
            }
            in 30..39 -> {
                tvResult.text = "Obese"
                tvResult.setTextColor(ContextCompat.getColor(this, R.color.orange))
                (tvBMI.background as GradientDrawable).setColor(ContextCompat.getColor(this, R.color.orange))
            }
            else -> {
                tvResult.text = "Extremely Obese"
                tvResult.setTextColor(ContextCompat.getColor(this, R.color.red))
                (tvBMI.background as GradientDrawable).setColor(ContextCompat.getColor(this, R.color.red))
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main, menu);
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if (item?.itemId == R.id.action_license) {
            val wv = WebView(this)
            wv.loadUrl("file:///android_asset/licenses.html")
            val builder = AlertDialog.Builder(this)
                    .setTitle("Licenses")
                    .setView(wv)
                    .setPositiveButton("OK") { dialog, which -> dialog.dismiss() }
            builder.show()
        }
        return super.onOptionsItemSelected(item)
    }
}
