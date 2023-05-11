package ni.edu.uca.sistematicopersistencia.data.database.entities

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import ni.edu.uca.sistematicopersistencia.data.database.dao.BaseDatos
import ni.edu.uca.sistematicopersistencia.data.database.dao.ProductoDao

class EntityProductoViewModel(application: Application) : AndroidViewModel(application) {

    private val ProductoDao: ProductoDao

    init {
        val database = BaseDatos.obtBaseDatos(application.applicationContext)
        ProductoDao = database.productoDao()
    }

    suspend fun insertar(EntityProducto: EntityProducto) = withContext(Dispatchers.IO) {
        ProductoDao.insertarReg(EntityProducto)
    }

    suspend fun actualizar(EntityProducto: EntityProducto) = withContext(Dispatchers.IO) {
        ProductoDao.actualizarReg(EntityProducto)
    }

    suspend fun eliminar(EntityProducto: EntityProducto) = withContext(Dispatchers.IO) {
        ProductoDao.eliminarReg(EntityProducto)
    }

    val todos: Flow<List<EntityProducto>> = ProductoDao.obtRegistos()
}