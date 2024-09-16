package com.example.tarefasapp

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.tarefasapp.Dao.Tarefa

class detalhesviewactivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Recuperando o objeto Tarefa do Intent usando o Companion Object corretamente
        val tarefa = intent?.getSerializableExtra(TAREFA_ID) as? Tarefa

        if (tarefa == null) {
            // Se a Tarefa for nula, você pode tratar o erro aqui
            finish()  // Fecha a Activity se não houver uma Tarefa válida
            return
        }

        setContent {
            DetlhesTela(tarefa)
        }
    }
    companion object {
        private const val TAREFA_ID = "tarefa_id"

        fun newIntent(context: Context, tarefa: Tarefa): Intent {
            return Intent(context, detalhesviewactivity::class.java).apply {
                putExtra(TAREFA_ID, tarefa)
            }
        }
    }
}


@Composable
fun DetlhesTela(tarefa: Tarefa) {

    val context = LocalContext.current
    val intent = Intent(context, editarTarefaActivity::class.java)

    Box(
        modifier =
        Modifier
            .background(Color.White)
            .fillMaxSize()
    )
    {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .background(Color.Gray)
                .fillMaxSize()

        ) {
            Text(
                text = "Detalhes da Tarefa",
                fontWeight =
                FontWeight.Bold,
                fontSize = 30.sp,
                color = Color.White,
                modifier = Modifier
                    .padding(bottom = 30.dp)

            )
            Text(
                text = "Nome da Tarefa: ${tarefa.nome}",
                fontWeight =
                FontWeight.Bold,
                fontSize = 24.sp,
                color = Color.White,
                modifier = Modifier.padding(bottom = 20.dp)
            )
            Text(
                text = "Descrição  da tarefa da Tarefa: ${tarefa.descricao} ",
                fontWeight =
                FontWeight.Bold,
                fontSize = 24.sp,
                color = Color.White,
                modifier = Modifier.padding(bottom = 20.dp)
            )

        }
        FloatingActionButton(
            onClick = {
                val intent = editarTarefaActivity.newIntent(context, tarefa)
                context.startActivity(intent)
            },
            modifier = Modifier
                .size(90.dp)
                .align(Alignment.BottomEnd)
                .padding(bottom = 16.dp, end = 16.dp)
        ) {

            Text(text = "Edit", color = Color.White) // Exemplo com um texto simples
        }
    }

}

