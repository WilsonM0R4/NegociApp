package com.example.wilson.negociapp

import android.app.Activity
import android.content.ContentValues
import android.os.Bundle
import android.support.design.widget.TextInputEditText
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.FrameLayout
import com.example.wilson.negociapp.provider.NegociAppProvider

/**
 * Created by wilso on 14/03/2018.
 */
class RegisterActivity : Activity() {

    //***** Views
    private lateinit var basicDataView : View
    private lateinit var missionVisionView : View
    private lateinit var viewContainer : FrameLayout

    //***** Widgets
    private lateinit var inputNit : TextInputEditText
    private lateinit var inputName : TextInputEditText
    private lateinit var inputAddress : TextInputEditText
    private lateinit var inputEmail : TextInputEditText
    private lateinit var inputPhone : TextInputEditText
    private lateinit var inputMobilePhone : TextInputEditText
    private lateinit var inputMission : EditText
    private lateinit var inputVision : EditText
    private lateinit var btnAccept : Button
    private lateinit var btnCancel : Button

    //***** other values
    private var secondScreen = true
    private lateinit var data : ContentValues

    private lateinit var provider : NegociAppProvider

    override fun onCreate (savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        provider = NegociAppProvider()
        provider.sendContext(this)
        initViews()

        viewContainer.addView(basicDataView)

    }

    private fun initViews (){

        //***** Views
        basicDataView = LayoutInflater.from(this).inflate(R.layout.view_basic_data, null, false)
        missionVisionView = LayoutInflater.from(this).inflate(R.layout.view_mission_vission, null, false)

        viewContainer = findViewById<FrameLayout>(R.id.viewContainer) as FrameLayout

        //***** Widgets
        inputNit = basicDataView.findViewById(R.id.inputNit)
        inputName = basicDataView.findViewById(R.id.inputName)
        inputAddress = basicDataView.findViewById(R.id.inputEmail)
        inputEmail = basicDataView.findViewById(R.id.inputEmail)
        inputPhone = basicDataView.findViewById(R.id.inputPhone)
        inputMobilePhone = basicDataView.findViewById(R.id.inputMobilePhone)
        inputMission = missionVisionView.findViewById(R.id.inputMission)
        inputVision = missionVisionView.findViewById(R.id.inputVision)
        btnAccept = findViewById(R.id.acceptButton)
        btnCancel = findViewById(R.id.cancelButton)
    }

    private fun collectData () {
        data = ContentValues()

        data.put("nit", inputNit.text.toString())
        data.put("name", inputName.text.toString())
        data.put("address", inputAddress.text.toString())
        data.put("email", inputEmail.text.toString())
        data.put("phone", inputPhone.text.toString())
        data.put("mobilePhone", inputMobilePhone.text.toString())
        data.put("mission", inputMission.text.toString())
        data.put("vision", inputVision.text.toString())

    }

    fun nextClicked (view: View) {

        if (secondScreen) {
            viewContainer.removeAllViews()
            viewContainer.addView(missionVisionView)
            btnAccept.text = resources.getString(R.string.accept_button_text)
            btnCancel.text = resources.getString(R.string.back_button_text)
            secondScreen = !secondScreen
        } else {
            collectData()
            //insert()
            finish()
        }

    }

    fun cancelClicked (view : View) {

        if (!secondScreen) {
            viewContainer.removeAllViews()
            viewContainer.addView(basicDataView)
            btnAccept.text = resources.getString(R.string.next_button_text)
            btnCancel.text = resources.getString(R.string.cancel_button_text)
            secondScreen = !secondScreen
        } else {
           finish()
        }

    }

    private fun insert() {

        //etName?.text, etEmail?.text

        val contentValues = ContentValues()
        contentValues.putAll(data)

        provider.insert(NegociAppProvider.CONTENT_URI, contentValues)

    }

    fun read (view : View) {

        val array  = Array(2) { "nit"}
        array[1] = "name"
        array[2] = "address"
        array[3] = "email"
        array[4] = "phone"
        array[5] = "mobilePhone"
        array[6] = "mission"
        array[7] = "vision"

        if (!inputName.text.toString().isEmpty()){
            val args  = Array(1){inputName.text.toString()}
            val cursor = provider.query(NegociAppProvider.CONTENT_URI, array, "email=?",args, null)

            if (cursor.moveToFirst()) {
                do  {
                    Log.d("MainActivity", "name: ${cursor.getString(0)}; email: ${cursor.getString(1)}")
                } while (cursor.moveToNext())
            } else {
                Log.d("MainActivity", "query did not return any value")
            }

            cursor.close()
        } else {
            val cursor = provider.query(NegociAppProvider.CONTENT_URI, array, null,null, null)

            if (cursor.moveToFirst()) {
                do  {
                    Log.d("MainActivity", "name: ${cursor.getString(0)}; email: ${cursor.getString(1)}")
                } while (cursor.moveToNext())
            } else {
                Log.d("MainActivity", "query did not return any value")
            }

            cursor.close()
        }


    }


}