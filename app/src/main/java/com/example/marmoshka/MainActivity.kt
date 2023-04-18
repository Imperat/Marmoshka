package com.example.marmoshka

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlin.math.sqrt


const val COLUMNS_COUNT = 9


class MainActivity : AppCompatActivity() {
    private lateinit var recyclerViewResults: RecyclerView;
    private lateinit var recyclerViewKeyboard: RecyclerView;
    private lateinit var valuesChooser: ValuesChooser;
    private lateinit var valuesFiller: ValuesFiller;

    // Declaring sensorManager
    // and acceleration constants
    private var sensorManager: SensorManager? = null
    private var acceleration = 0f
    private var currentAcceleration = 0f
    private var lastAcceleration = 0f

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)
        recyclerViewResults = findViewById(R.id.recyclerViewResults)
        recyclerViewKeyboard = findViewById(R.id.recyclerViewKeyboard)

        valuesFiller = ValuesFiller()
        valuesChooser = ValuesChooser(valuesFiller)

        valuesChooser.onUpdateUI {
            updateKeyboardAdapter(it)
        }

        valuesFiller.onUpdateUI {
            updateValuesAdapter(it)
        }

        updateKeyboardUI()
        updateValuesUI()

        // Getting the Sensor Manager instance
        sensorManager = getSystemService(Context.SENSOR_SERVICE) as SensorManager
        sensorManager?.registerListener(sensorListener, sensorManager!!.getDefaultSensor(Sensor.TYPE_ACCELEROMETER), SensorManager.SENSOR_DELAY_NORMAL)

        acceleration = 10f
        currentAcceleration = SensorManager.GRAVITY_EARTH
        lastAcceleration = SensorManager.GRAVITY_EARTH

        Toast.makeText(this, "Давай, Мармошка! Выиграем $1000000 прямо сегодня!", Toast.LENGTH_SHORT).show()
    }

    override fun onResume() {
        sensorManager?.registerListener(sensorListener, sensorManager!!.getDefaultSensor(
            Sensor .TYPE_ACCELEROMETER), SensorManager.SENSOR_DELAY_NORMAL
        )
        super.onResume()
    }

    override fun onPause() {
        sensorManager!!.unregisterListener(sensorListener)
        super.onPause()
    }

    private val sensorListener: SensorEventListener = object : SensorEventListener {
        override fun onSensorChanged(event: SensorEvent) {
            // Fetching x,y,z values
            val x = event.values[0]
            val y = event.values[1]
            val z = event.values[2]
            lastAcceleration = currentAcceleration

            // Getting current accelerations
            // with the help of fetched x,y,z values
            currentAcceleration = sqrt((x * x + y * y + z * z).toDouble()).toFloat()
            val delta: Float = currentAcceleration - lastAcceleration
            acceleration = acceleration * 0.9f + delta

            // Display a Toast message if
            // acceleration value is over 12
            if (acceleration > 12) {
                valuesChooser.shuffle()
                updateKeyboardAdapter(null)
            }
        }
        override fun onAccuracyChanged(sensor: Sensor, accuracy: Int) {}
    }

    private fun updateKeyboardUI() {
        updateKeyboardLayoutManager()
        updateKeyboardAdapter(null)
    }

    private fun updateValuesUI() {
        updateValuesLayoutManager();
        updateValuesAdapter(null);
    }

    private fun updateValuesLayoutManager() {
        with (recyclerViewResults) {
            layoutManager = LinearLayoutManager(context)
        }
    }

    private fun updateValuesAdapter(position: Int?) {
        with (recyclerViewResults) {
            if (position != null) {
                adapter?.notifyItemChanged(position);
                return;
            }

            adapter = ValuesViewAdapter(valuesFiller);
        }
    }

    private fun updateKeyboardAdapter(position: Int?) {
        with (recyclerViewKeyboard) {
            if (position != null) {
                adapter?.notifyItemChanged(position)
                return
            }

            adapter = KeyBoardViewAdapter(valuesChooser)
        }
    }

    private fun updateKeyboardLayoutManager() {
        with (recyclerViewKeyboard) {
            layoutManager = GridLayoutManager(context, COLUMNS_COUNT)
        }
    }
}
