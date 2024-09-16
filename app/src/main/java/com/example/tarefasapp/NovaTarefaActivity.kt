package com.example.tarefasapp

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.room.Room
import com.example.tarefasapp.Dao.AppDatabase
import com.example.tarefasapp.Dao.Tarefa
import com.example.tarefasapp.Dao.tarefa
import com.example.tarefasapp.ui.theme.TarefasAppTheme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

class NovaTarefaActivity : AppCompatActivity() {
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
                Surface(color = MaterialTheme.colorScheme.background) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center,
                        modifier = Modifier
                            .fillMaxSize()
                            .background(Color.White)
                    ) {
                        Text(text = "Criar nova Tarefa", fontSize = 26.sp, fontWeight = FontWeight.Bold, color = Color.Black)

                        var nome by remember { mutableStateOf("") }
                        TextField(
                            maxLines = 1,
                            label = { Text(text = "Nome da Tarefa")},
                            modifier = Modifier
                                .background(Color.Gray)
                                .padding(16.dp)
                                .clip(RoundedCornerShape(12.dp))
                                .border(1.dp, Color.Black, RoundedCornerShape(12.dp))
                            ,
                            value = nome,
                            onValueChange = {
                                NewText ->
                            nome = NewText
                        })
                        var descricao by remember {
                            mutableStateOf("")
                        }
                        TextField(
                            maxLines = 10,
                            label = { Text(text = "Descrição da Tarefa")},
                            modifier = Modifier
                                .background(Color.Gray)
                                .padding(16.dp)
                                .clip(RoundedCornerShape(12.dp))
                                .border(1.dp, Color.Black, RoundedCornerShape(12.dp)),
                            value = descricao,
                            onValueChange = {
                                newText ->
                            descricao = newText
                        })
                        var tarefaIds by remember { mutableStateOf<List<Int>?>(null) }
                        val coroutineScope = rememberCoroutineScope()
                        val activity = (LocalContext.current as? Activity)

                        Button(
                            onClick = {
                                coroutineScope.launch {

                                    val ids = TarefaDao.getAll()
                                        .map { tarefasList -> tarefasList.map { tarefa -> tarefa.id } }
                                        .first()


                                    val novoId = (ids.maxOrNull() ?: 0) + 1


                                    val novaTarefa = Tarefa(
                                        id = novoId,
                                        nome = nome,
                                        descricao = descricao,
                                        prioridade = 1
                                    )
                                    TarefaDao.insert(novaTarefa)

                                }
                                val intent = Intent(this@NovaTarefaActivity, MainActivity::class.java)
                                startActivity(intent)
                                activity?.finish()
                            },
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp)
                        ) {
                            Text("SALVAR")
                        }

                    }
                }
            }
        }

    }
}