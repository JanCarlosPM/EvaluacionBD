package ni.edu.uca.sistematicopersistencia

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import ni.edu.uca.sistematicopersistencia.data.database.entities.EntityProducto
import ni.edu.uca.sistematicopersistencia.data.database.entities.EntityProductoViewModel
import ni.edu.uca.sistematicopersistencia.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val viewModel: EntityProductoViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnGuardar.setOnClickListener {
            try {
                val nombreProd = binding.txtNombre.text.toString()
                val precioProd = binding.txtPrecio.text.toString().toDouble()
                val existProd = binding.txtPrecio.text.toString().toInt()

                val producto = EntityProducto(
                    nombre = nombreProd, precio = precioProd, existencia = existProd
                )
                CoroutineScope(Dispatchers.IO).launch {
                    viewModel.insertar(producto)
                    limpiarCampos()
                }

            } catch (ex: Exception) {
                Toast.makeText(
                    this@MainActivity, ": ${ex.toString()}",
                    Toast.LENGTH_LONG
                ).show()
            }
        }

    }

    private fun limpiarCampos() {
        binding.txtNombre.text.clear()
        binding.txtPrecio.text.clear()
        binding.txtExistencia.text.clear()
    }
}