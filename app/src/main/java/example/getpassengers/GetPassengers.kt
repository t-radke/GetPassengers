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

    private var passList: MutableList<Passenger> = mutableListOf()
    private lateinit var textPut: TextView
    private lateinit var textFirst: EditText
    private lateinit var textLast: EditText
    private lateinit var textPhone: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_get_passengers)

        // Initialize UI elements by linking them to XML IDs
        textPut = findViewById(R.id.accum_list)
        textFirst = findViewById(R.id.first_name)
        textLast = findViewById(R.id.last_name)
        textPhone = findViewById(R.id.phone_number)
    }

    // Function to add a passenger to the accumulating list
    fun enterPassenger(view: View) {
        val firstName = textFirst.text.toString().trim()
        val lastName = textLast.text.toString().trim()
        val phone = textPhone.text.toString().trim()

        // Ensure all fields are filled
        if (firstName.isEmpty() || lastName.isEmpty() || phone.isEmpty()) {
            return
        }

        // Create Passenger object
        val newPassenger = Passenger(firstName, lastName, phone)
        passList.add(newPassenger)

       // Format text
        val formattedText = "${newPassenger.fName}  ${newPassenger.lName}  ${newPassenger.phone}"

        textPut.append("\n$formattedText")


        // Clear input fields after adding passenger
        textFirst.text.clear()
        textLast.text.clear()
        textPhone.text.clear()
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
