package com.example.tarefasapp

import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat.startActivity
import androidx.room.Room
import com.example.tarefasapp.Dao.AppDatabase
import com.example.tarefasapp.Dao.Tarefa
import com.example.tarefasapp.Dao.TarefaDao
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

class editarTarefaActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val db = Room.databaseBuilder(
            this,
            AppDatabase::class.java, "database"
        ).build()
        val tarefaDao = db.userDao()

        val tarefa = intent?.getSerializableExtra(TAREFA_ID) as? Tarefa

        if (tarefa == null) {
            finish()
            return
        }

        setContent {
            Editar_Tarefa(tarefa, tarefaDao, intent)
        }
    }

    companion object {
        private const val TAREFA_ID = "tarefa_id"

        fun newIntent(context: Context, tarefa: Tarefa): Intent {
            return Intent(context, editarTarefaActivity::class.java).apply {
                putExtra(TAREFA_ID, tarefa)
            }
        }
    }
}

@Composable
fun Editar_Tarefa(tarefa: Tarefa, tarefaDao: TarefaDao, intent: Intent) {

    val context = LocalContext.current

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Gray),
        contentAlignment = Alignment.Center
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize(0.9f)
                .clip(RoundedCornerShape(16.dp))
                .background(Color.White)
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color.LightGray),
                    contentAlignment = Alignment.Center
                ) {
                    Text(text = "Editar Tarefa", fontSize = 27.sp)
                }
                Spacer(modifier = Modifier.height(26.dp))

                var nome by remember { mutableStateOf(tarefa.nome) }
                TextField(
                    maxLines = 1,
                    label = { Text(text = "Nome da Tarefa") },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                        .clip(RoundedCornerShape(12.dp))
                        .border(1.dp, Color.Black, RoundedCornerShape(12.dp)),
                    value = nome,
                    onValueChange = { newText -> nome = newText }
                )

                var descricao by remember { mutableStateOf(tarefa.descricao) }
                TextField(
                    maxLines = 10,
                    label = { Text(text = "Descrição da Tarefa") },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                        .clip(RoundedCornerShape(12.dp))
                        .border(1.dp, Color.Black, RoundedCornerShape(12.dp)),
                    value = descricao,
                    onValueChange = { newText -> descricao = newText }
                )
                val activity = (LocalContext.current as? Activity)
                val coroutineScope = rememberCoroutineScope()
                FloatingActionButton(
                    onClick = {
                        coroutineScope.launch {
                            val tarefaAtualizada = tarefa.copy(
                                nome = nome,
                                descricao = descricao
                            )
                            tarefaDao.update(tarefaAtualizada) // Chamada ao método de atualização do DAO

                            // Após a atualização, iniciar MainActivity
                            val intent = Intent(
                                context,
                                MainActivity::class.java
                            ) // Use o contexto obtido do LocalContext
                            context.startActivity(intent) // Inicia a nova Activity usando o contexto correto
                            (context as? Activity)?.finish() // Finaliza a Activity atual
                        }
                    },
                    modifier = Modifier
                        .size(width = 120.dp, height = 100.dp)
                        .padding(bottom = 16.dp),
                    containerColor = Color.DarkGray
                ) {
                    Text(text = "Alterar", color = Color.White, fontSize = 26.sp)
                }
            }
        }
    }
}