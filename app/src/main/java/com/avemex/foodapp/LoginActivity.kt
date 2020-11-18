package com.avemex.foodapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        //Login
        setup()
    }

    private fun setup() {
        title = "Autenticación"

        //Button SingUp
        singUpButton.setOnClickListener {
            if (emailAddress.text.isNotEmpty() && password.text.isNotEmpty()) {
                FirebaseAuth.getInstance().createUserWithEmailAndPassword(
                    emailAddress.text.toString(),
                    password.text.toString()
                ).addOnCompleteListener {

                    if (it.isSuccessful) {
                        startActivity(Intent(this,MainActivity::class.java))
                    } else {
                        showAlert()
                    }
                }
            }
        }
        //Button Login
        loginButton.setOnClickListener {
            if (emailAddress.text.isNotEmpty() && password.text.isNotEmpty()) {
                FirebaseAuth.getInstance().signInWithEmailAndPassword(emailAddress.text.toString(),
                    password.text.toString()
                ).addOnCompleteListener {

                    if (it.isSuccessful) {
                        startActivity(Intent(this,MainActivity::class.java))
                    } else {
                        showAlert2()
                    }
                }
            }
        }
        //Button Invitate
        textView4.setOnClickListener {
            startActivity(Intent(this,MainActivity::class.java))
        }

    }
        private fun showAlert(){
            val builder = AlertDialog.Builder(this)
            builder.setTitle("Error")
            builder.setMessage("Se ha producido un error en la autenticación, verifique sus credenciales")
            builder.setPositiveButton("Aceptar", null)
            val dialog: AlertDialog = builder.create()
            dialog.show()
        }
        private fun showAlert2(){
            val builder = AlertDialog.Builder(this)
            builder.setTitle("Error")
            builder.setMessage("Se ha producido un error, verifique los datos")
            builder.setPositiveButton("Aceptar", null)
            val dialog: AlertDialog = builder.create()
            dialog.show()
        }
    }