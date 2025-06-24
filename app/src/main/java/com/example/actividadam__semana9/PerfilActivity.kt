package com.example.actividadam__semana9

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.actividadam__semana9.databinding.ActivityPerfilBinding

class PerfilActivity : AppCompatActivity() {
    private lateinit var binding: ActivityPerfilBinding
    private lateinit var prefs: SharedPreferencesHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPerfilBinding.inflate(layoutInflater)
        setContentView(binding.root)

        prefs = SharedPreferencesHelper(this)

        // Botón Guardar Perfil
        binding.btnGuardarPerfil.setOnClickListener {
            val nombre = binding.editTextNombre.text.toString().trim()
            val edad = binding.editTextEdad.text.toString().trim()
            val email = binding.editTextEmail.text.toString().trim()
            if (nombre.isEmpty() || edad.isEmpty() || email.isEmpty()) {
                Toast.makeText(this, "Por favor completa todos los campos", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            prefs.saveString("perfil_nombre", nombre)
            prefs.saveString("perfil_edad", edad)
            prefs.saveString("perfil_email", email)
            Toast.makeText(this, "Perfil guardado", Toast.LENGTH_SHORT).show()
        }

        // Botón Cargar Perfil
        binding.btnCargarPerfil.setOnClickListener {
            val nombre = prefs.getString("perfil_nombre", "")
            val edad = prefs.getString("perfil_edad", "")
            val email = prefs.getString("perfil_email", "")
            binding.editTextNombre.setText(nombre)
            binding.editTextEdad.setText(edad)
            binding.editTextEmail.setText(email)
            Toast.makeText(this, "Perfil cargado", Toast.LENGTH_SHORT).show()
        }

        // Botón Volver
        binding.btnVolver.setOnClickListener {
            finish()
        }

        // Opcional: si quieres cargar automáticamente al entrar:
        // binding.editTextNombre.setText(prefs.getString("perfil_nombre", ""))
        // binding.editTextEdad.setText(prefs.getString("perfil_edad", ""))
        // binding.editTextEmail.setText(prefs.getString("perfil_email", ""))
    }
}

