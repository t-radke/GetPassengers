// Tyler Radke
// Major Program 1 - GetPassengers
// Main Activity - MainActivity.kt

package example.getpassengers

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Initialize TextView inside onCreate()
        val listText: TextView = findViewById(R.id.show_list)

        val startForResult = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == RESULT_OK) {
                val data = result.data
                val count = ((data?.getStringExtra("COUNT") ?: "")).toInt()

                listText.text = "RETURNED PASSENGER LIST:\n"

                // Loop through the received passengers and append them to the TextView
                for (i in 1..count) {
                    val passenger = data?.getStringExtra("PASS$i") ?: ""
                    listText.append("$passenger\n")
                }
            }
        }

        // Set click listener for the button
        findViewById<View>(R.id.get_list_button).setOnClickListener {
            listText.text = "RETURNED PASSENGER LIST"

            // Create an Intent to start the GetPassengers activity
            val intent = Intent(this, GetPassengers::class.java)

            // Start the second activity expecting a result back
            startForResult.launch(intent)
        }
    }
}

// Passenger Data Class
class Passenger(val fName: String, val lName: String, val phone: String) {
    override fun toString(): String {
        var s = this.fName + " " + this.lName + " " + this.phone
        return s
    }
}
