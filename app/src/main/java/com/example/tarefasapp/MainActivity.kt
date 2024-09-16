package com.example.tarefasapp

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.room.Room
import com.example.tarefasapp.Dao.AppDatabase
import com.example.tarefasapp.Dao.Tarefa
import com.example.tarefasapp.Dao.TarefaDao
import com.example.tarefasapp.Telas.ListadeTarefas
import com.example.tarefasapp.ui.theme.TarefasAppTheme
import kotlinx.coroutines.flow.Flow

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val db = Room.databaseBuilder(
            this,
            AppDatabase::class.java, "database"
        ).build()
        val TarefaDao = db.userDao()
        val ListadeTarefas: Flow<List<Tarefa>> = TarefaDao.getAll()

        setContent {
            TarefasAppTheme {
                MyApp(ListadeTarefas, TarefaDao, {
                    startActivity(Intent(this, detalhesviewactivity::class.java))
                })
            }
        }
    }
}

@Composable
fun MyApp(tarefaslist:Flow<List<Tarefa>>, tarefadao: TarefaDao, navigateToProfile: (Tarefa) -> Unit) {
    ListadeTarefas(tarefaslist, tarefadao, navigateToProfile)
}