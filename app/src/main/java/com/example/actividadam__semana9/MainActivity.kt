// Archivo: app/src/main/java/com/example/actividadam_semana9/MainActivity.kt
package com.example.actividadam__semana9

import android.content.Intent
import android.os.Bundle
import android.widget.*
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

    private lateinit var sharedPreferencesHelper: SharedPreferencesHelper

    // Vistas originales:
    private lateinit var editTextUsername: EditText
    private lateinit var buttonSave: Button
    private lateinit var buttonLoad: Button
    private lateinit var buttonClear: Button
    private lateinit var textViewResult: TextView

    // Botón para ir a Perfil:
    private lateinit var buttonIrPerfil: Button

    // Vistas de modo oscuro y contador:
    private lateinit var switchModoOscuro: Switch
    private lateinit var textViewContador: TextView
    private lateinit var buttonResetContador: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Edge-to-edge
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        // Ajuste de insets si usas full screen / Edge-to-edge:
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Instanciar SharedPreferencesHelper
        sharedPreferencesHelper = SharedPreferencesHelper(this)

        // Inicializar vistas
        initViews()

        // Configurar listeners
        setupListeners()

        // Lógica de primera vez
        checkFirstTime()

        // Aplicar tema guardado antes de mostrar UI
        applyTheme()

        // Actualizar e incrementar contador de visitas
        actualizarContador()
    }

    private fun initViews() {
        // Vistas originales
        editTextUsername = findViewById(R.id.editTextUsername)
        buttonSave = findViewById(R.id.buttonSave)
        buttonLoad = findViewById(R.id.buttonLoad)
        buttonClear = findViewById(R.id.buttonClear)
        textViewResult = findViewById(R.id.textViewResult)

        // Botón para abrir PerfilActivity
        buttonIrPerfil = findViewById(R.id.buttonIrPerfil)

        // Vistas de modo oscuro y contador
        switchModoOscuro = findViewById(R.id.switchModoOscuro)
        textViewContador = findViewById(R.id.textViewContador)
        buttonResetContador = findViewById(R.id.buttonResetContador)
    }

    private fun setupListeners() {
        // Guardar username
        buttonSave.setOnClickListener {
            saveData()
        }
        // Cargar username
        buttonLoad.setOnClickListener {
            loadData()
        }
        // Limpiar datos username
        buttonClear.setOnClickListener {
            clearAllData()
        }
        // Ir a PerfilActivity
        buttonIrPerfil.setOnClickListener {
            startActivity(Intent(this, PerfilActivity::class.java))
        }
        // Switch modo oscuro/claro
        switchModoOscuro.setOnCheckedChangeListener { _, isChecked ->
            // Guardar preferencia
            sharedPreferencesHelper.saveBoolean(SharedPreferencesHelper.KEY_THEME_MODE, isChecked)
            // Aplicar modo
            if (isChecked) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            } else {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            }
        }
        // Resetear contador
        buttonResetContador.setOnClickListener {
            sharedPreferencesHelper.saveInt("counter", 0)
            actualizarContador()
        }
    }

    private fun applyTheme() {
        // Leer preferencia y aplicar antes de mostrar UI
        val isDarkMode = sharedPreferencesHelper.getBoolean(SharedPreferencesHelper.KEY_THEME_MODE, false)
        // Ajustar estado del switch
        switchModoOscuro.isChecked = isDarkMode
        // Aplicar modo con AppCompatDelegate
        if (isDarkMode) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        }
    }

    private fun actualizarContador() {
        // Incrementa en 1 al abrir
        val contadorPrevio = sharedPreferencesHelper.getInt("counter", 0)
        val nuevoContador = contadorPrevio + 1
        sharedPreferencesHelper.saveInt("counter", nuevoContador)
        textViewContador.text = "Visitas: $nuevoContador"
    }

    private fun saveData() {
        val username = editTextUsername.text.toString().trim()
        if (username.isEmpty()) {
            Toast.makeText(this, "Por favor ingresa un nombre", Toast.LENGTH_SHORT).show()
            return
        }
        // Guardar en SharedPreferencesHelper con clave KEY_USERNAME
        sharedPreferencesHelper.saveString(SharedPreferencesHelper.KEY_USERNAME, username)
        // Marcar que ya no es primera vez
        sharedPreferencesHelper.saveBoolean(SharedPreferencesHelper.KEY_IS_FIRST_TIME, false)
        // Generar y guardar un user_id aleatorio como ejemplo
        sharedPreferencesHelper.saveInt(SharedPreferencesHelper.KEY_USER_ID, (1000..9999).random())
        Toast.makeText(this, "Datos guardados exitosamente", Toast.LENGTH_SHORT).show()
        editTextUsername.setText("")
    }

    private fun loadData() {
        val username = sharedPreferencesHelper.getString(SharedPreferencesHelper.KEY_USERNAME, "Sin nombre")
        val isFirstTime = sharedPreferencesHelper.getBoolean(SharedPreferencesHelper.KEY_IS_FIRST_TIME, true)
        val userId = sharedPreferencesHelper.getInt(SharedPreferencesHelper.KEY_USER_ID, 0)
        val result = "Usuario: $username\nID: $userId\nPrimera vez: ${if (isFirstTime) "Sí" else "No"}"
        textViewResult.text = result
    }

    private fun clearAllData() {
        // Limpiar todas las preferencias
        sharedPreferencesHelper.clearAll()
        // Limpiar vistas
        textViewResult.text = ""
        editTextUsername.setText("")
        textViewContador.text = "Visitas: 0"
        // También resetear switch a modo claro
        switchModoOscuro.isChecked = false
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        Toast.makeText(this, "Todas las preferencias han sido eliminadas", Toast.LENGTH_SHORT).show()
    }

    private fun checkFirstTime() {
        val isFirstTime = sharedPreferencesHelper.getBoolean(SharedPreferencesHelper.KEY_IS_FIRST_TIME, true)
        if (isFirstTime) {
            Toast.makeText(this, "¡Bienvenido por primera vez!", Toast.LENGTH_LONG).show()
        }
    }
}
