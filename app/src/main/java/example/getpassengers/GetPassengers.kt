// Tyler Radke
// Major Program 1 - GetPassengers
// Second Main Activity - GetPassengers.kt

package example.getpassengers

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class GetPassengers : AppCompatActivity() {

    // Mutable ArrayList to accumulate Passenger objects
    var passList: MutableList<Passenger> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_get_passengers)
    }

    // Function to add a passenger to the accumulating list
    fun enterPassenger(v: View) {
        val textFirst: EditText = findViewById(R.id.first_name)
        val textLast: EditText = findViewById(R.id.last_name)
        val textPhone: EditText = findViewById(R.id.phone_number)
        val textPut: TextView = findViewById(R.id.accum_list)

        val firstName = textFirst.getText().toString()
        val lastName = textLast.getText().toString()
        val phone = textPhone.getText().toString()

        // Ensure all fields are filled
        if (firstName.isEmpty() || lastName.isEmpty() || phone.isEmpty()) {
            return
        }

        // Create Passenger object
        val newPass = Passenger(firstName, lastName, phone)
        passList.add(newPass) // Add to the ArrayList

        // Append using toString()
        textPut.append("\n${newPass.toString()}")

        // Clear input fields after adding passenger
        textFirst.text.clear()
        textLast.text.clear()
        textPhone.text.clear()
    }

    // Function to return the list of passengers to MainActivity
    fun backToMain(v: View) {
        val intent = Intent()
        intent.putExtra("COUNT", passList.size.toString())

        for (i in passList.indices) {
            intent.putExtra("PASS${i + 1}", passList[i].toString())
        }

        // Close this activity and return to MainActivity
        setResult(RESULT_OK, intent)
        finish()
    }
}


