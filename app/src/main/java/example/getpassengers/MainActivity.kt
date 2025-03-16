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

    private lateinit var listText: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Initialize the TextView for displaying the returned passenger list
        listText = findViewById(R.id.show_list)

    }

    // Handles the result received from the second activity (GetPassengers)
    private val startForResult = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == RESULT_OK) {
            val data = result.data
            val count = data?.getStringExtra("COUNT")?.toIntOrNull() ?: 0

            listText.text = "RETURNED PASSENGER LIST:\n"

            // Loop through the received passengers and append them to the TextView
            for (i in 1..count) {
                val passenger = data?.getStringExtra("PASS$i") ?: ""
                listText.append("$passenger\n")
            }
        }
    }

    // Function to open GetPassengers Activity
    fun getList(view: View) {
        listText.text = "RETURNED PASSENGER LIST"

        // Create an Intent to start the GetPassengers activity
        val intent = Intent(this, GetPassengers::class.java)

        // Start the second activity expecting a result back
        startForResult.launch(intent)
    }
}
