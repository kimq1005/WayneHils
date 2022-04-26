package test.map.waynehils

import android.content.Intent
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import test.map.waynehils.databinding.ActivityServiceIntroductionFirstBinding

class Service_introduction_First : AppCompatActivity() {

    private var serviceIntroductionFirstBinding: ActivityServiceIntroductionFirstBinding? = null
    private val binding get() = serviceIntroductionFirstBinding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        serviceIntroductionFirstBinding =
            ActivityServiceIntroductionFirstBinding.inflate(layoutInflater)
        setContentView(binding.root)


        intentnext()
        setTransparent()


    }

    //Login Imageview를 누르면 LoginActivity로, nextImageView를 누르면 다음 엑티비티로 Intent
    private fun intentnext() {
        binding.nextImageView.setOnClickListener {
            val intent = Intent(this, Service_introduction_Second::class.java)
            startActivity(intent)
        }

        binding.loginImageView.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
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