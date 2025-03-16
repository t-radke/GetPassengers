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

    private val passList: MutableList<Passenger> = mutableListOf()
    private lateinit var accumList: TextView
    private lateinit var firstNameField: EditText
    private lateinit var lastNameField: EditText
    private lateinit var phoneField: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_get_passengers)

        // Initialize UI elements by linking them to XML IDs
        accumList = findViewById(R.id.accum_list)
        firstNameField = findViewById(R.id.first_name)
        lastNameField = findViewById(R.id.last_name)
        phoneField = findViewById(R.id.phone_number)
    }

    // Function to add a passenger to the accumulating list
    fun enterPassenger(view: View) {
        val firstName = firstNameField.text.toString().trim()
        val lastName = lastNameField.text.toString().trim()
        val phone = phoneField.text.toString().trim()

        // Ensure all fields are filled
        if (firstName.isEmpty() || lastName.isEmpty() || phone.isEmpty()) {
            return
        }

        // Create Passenger object
        val newPassenger = Passenger(firstName, lastName, phone)
        passList.add(newPassenger)

       // Format text
        val formattedText = "${newPassenger.fName}  ${newPassenger.lName}  ${newPassenger.phone}"

        accumList.append("\n$formattedText")


        // Clear input fields after adding passenger
        firstNameField.text.clear()
        lastNameField.text.clear()
        phoneField.text.clear()
    }


    // Function to return the list of passengers to MainActivity
    fun backToMain(view: View) {
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

// Passenger Data Class
data class Passenger(val fName: String, val lName: String, val phone: String) {
    override fun toString(): String {
        return "$fName $lName - $phone"
    }
}
