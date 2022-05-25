package com.example.sampleapp

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import com.google.android.gms.tasks.Task
import com.moe.pushlibrary.MoEHelper
import com.moengage.cards.ui.CardActivity
import com.moengage.core.MoECoreHelper
import com.moengage.core.Properties
import com.moengage.core.analytics.MoEAnalyticsHelper
import com.moengage.core.enableAdIdTracking
import com.moengage.inapp.MoEInAppHelper
import com.moengage.inbox.ui.view.InboxActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        findViewById<Button>(R.id.nextActivity).setOnClickListener { nextActivity() }
        findViewById<Button>(R.id.button).setOnClickListener { trackUser() }
        findViewById<Button>(R.id.button2).setOnClickListener { trackEvent() }
        findViewById<Button>(R.id.button3).setOnClickListener { logout() }
        findViewById<Button>(R.id.button4).setOnClickListener { others() }
        findViewById<Button>(R.id.button6).setOnClickListener { appInbox() }
        findViewById<Button>(R.id.button7).setOnClickListener { cards() }
        var nudge = findViewById<com.moengage.widgets.NudgeView>(R.id.nudge)


    }

    private fun cards() {
        val intent = Intent(this, CardActivity::class.java)
        startActivity(intent)
    }

    private fun nextActivity(){
       val intent = Intent(this,SecondActivity::class.java)
        startActivity(intent)
    }

    private fun trackUser (){
        val name:String = findViewById<TextView?>(R.id.name).text.toString()
        val email:String = findViewById<TextView?>(R.id.email).text.toString()

        MoEAnalyticsHelper.setUniqueId(this,"$email")
        MoEAnalyticsHelper.setEmailId(this, "$email")
        MoEAnalyticsHelper.setFirstName(this,"$name")

    }

    private fun trackEvent (){
//        Log.d("Some Tag","This is an event log")
        MoEAnalyticsHelper.trackEvent(this,"addToCart", Properties().addAttribute("itemName","iphone"))
    }


    private fun logout(){
        MoECoreHelper.logoutUser(this)
    }

    private fun others(){
        MoEAnalyticsHelper.trackDeviceLocale(this);
    }

    private fun appInbox(){
        val intent = Intent(this, InboxActivity::class.java)
        startActivity(intent)
    }

    override fun onPause() {
        super.onPause()
        Log.d("MYTAG","Main onpause called...")
    }

    override fun onResume() {
        super.onResume()
        Log.d("MYTAG","Main onResume called...")
    }

    override fun onStart() {
        super.onStart()
        MoEInAppHelper.getInstance().showInApp(this)
        findViewById<com.moengage.widgets.NudgeView>(R.id.nudge).initialiseNudgeView(activity = Activity())
        Log.d("MYTAG","Main onStart called...")
    }

    override fun onStop() {
        super.onStop()
        Log.d("MYTAG","Main onStop called...")
    }



//    private fun background(){
////        MoEHelper.getInstance(this).logoutUser()
//        Log.i("some","app iis in background")
//    }
}





