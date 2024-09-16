package com.example.tarefasapp.Telas


import android.content.Intent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Alignment
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.tarefasapp.Dao.Tarefa
import com.example.tarefasapp.NovaTarefaActivity
import kotlinx.coroutines.flow.Flow
import android.app.Activity
import com.example.tarefasapp.Dao.TarefaDao
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


@Composable
fun ListadeTarefas(tarefasList: Flow<List<Tarefa>>, tarefadao: TarefaDao, navigatiProfile: (Tarefa) -> Unit) {



    val context = LocalContext.current

    val tarefas by tarefasList.collectAsState(initial = emptyList())


    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.linearGradient(
                    colors = listOf(
                        Color(0xFF7A7A7A),
                        Color(0xFF66B1BB),
                        Color(0xFF1A1A1A)
                    )
                )
            )
    ) {

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 26.dp)

        ) {

            Box(modifier = Modifier.background(Color.Black)) {
                Text(
                    text = "LISTA DE TAREFAS",
                    fontWeight = FontWeight.Bold,
                    color = Color.White,
                    fontSize = 27.sp
                )
            }
            LazyColumn(
                contentPadding = PaddingValues(horizontal = 18.dp, vertical = 10.dp),

                ) {


                items(items = tarefas) { tarefa ->
                    Tarefa(tarefa = tarefa, navigatiProfile)
                }
            }


        }
        val activity = (LocalContext.current as? Activity)
        FloatingActionButton(
            onClick = {
                val intent = Intent(context, NovaTarefaActivity::class.java)

                activity?.finish()
                context.startActivity(intent)
            },
            modifier = Modifier
                .size(60.dp)
                .align(Alignment.BottomEnd)
                .padding(bottom = 16.dp, end = 16.dp)
        ) {

            Text(text = "+", color = Color.White) // Exemplo com um texto simples
        }
        FloatingActionButton(
            onClick = {
                CoroutineScope(Dispatchers.Main).launch {
                    tarefadao.deleteAll()
                }
            },
            modifier = Modifier
                .size(60.dp)
                .align(Alignment.BottomCenter)
                .padding(bottom = 16.dp, end = 10.dp)
        ) {

            Text(text = "Limpar", color = Color.White)
        }

    }


}