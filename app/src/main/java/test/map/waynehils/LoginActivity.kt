package test.map.waynehils

import android.content.Intent
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import test.map.waynehils.databinding.ActivityLoginBinding


class LoginActivity : AppCompatActivity() {

    private var loginBinding: ActivityLoginBinding? = null
    private val binding get() = loginBinding!!


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        loginBinding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)


        setTransparent()
        testemailchecking()


    }

    //snackbar를 띄우기 위한 함수
    private fun FavroiteSnackBar(view: View) {
        val snackbar = Snackbar.make(view, "Invaild E-mail/Password", Snackbar.LENGTH_LONG)
        snackbar.setBackgroundTint(resources.getColor(R.color.snakbarColor))
        snackbar.show()
    }

    //login error message를 띄우기 위한 테스트 함수
    private fun testemailchecking() {
        binding.LoginBtn.setOnClickListener {
            val emailTextView = binding.inputEmail.text.toString()

            if (emailTextView == "") {
                binding.EmailTextField.error = " "
                binding.passwordTextField.error = "Invalid E-mail/Password"
                binding.forgetpasswordTextView.visibility = View.GONE
                FavroiteSnackBar(binding.loginActivity)
            }
        }

        binding.signupBtn.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }

    }

    // Status bar를 투명하게 바꾸기 위한 함수
    private fun setTransparent() {
        if (Build.VERSION.SDK_INT >= 19 && Build.VERSION.SDK_INT < 21) {
            setWindowFlag(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS, true)
        }
        if (Build.VERSION.SDK_INT >= 19) {
            window.decorView.systemUiVisibility =
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
        }
        if (Build.VERSION.SDK_INT >= 21) {
            setWindowFlag(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS, false)
            window.statusBarColor = Color.TRANSPARENT
        }
    }

    // Status bar를 투명하게 바꾸기 위한 함수
    private fun setWindowFlag(bits: Int, on: Boolean) {
        val win = window
        val winParams = win.attributes
        if (on) {
            winParams.flags = winParams.flags or bits
        } else {
            winParams.flags = winParams.flags and bits.inv()
        }
        win.attributes = winParams
    }


}