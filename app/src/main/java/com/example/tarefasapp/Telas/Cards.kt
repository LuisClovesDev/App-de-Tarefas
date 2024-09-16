package com.example.tarefasapp.Telas

import android.app.Activity
import android.content.Intent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.tarefasapp.Dao.Tarefa
import com.example.tarefasapp.detalhesviewactivity


@Composable
fun Tarefa(tarefa: Tarefa, navigatiProfile: (Tarefa) -> Unit){
    val context = LocalContext.current
    Card(
        colors = CardDefaults.cardColors(containerColor = Color(0xFFF0FFFF)),
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(top = 10.dp, bottom = 10.dp)
            .clickable {
                // Criando Intent e adicionando o objeto Tarefa
                val intent = detalhesviewactivity.newIntent(context, tarefa)
                context.startActivity(intent)
            }

    ) {
        Text(
            text = "${tarefa.nome}",
            fontWeight = FontWeight.Bold,
            fontSize = 16.sp,
            modifier = Modifier.padding(start = 20.dp)
        )
        Text(
            text = "Descrição da tarefa: ${tarefa.descricao}",
            Modifier.padding(start = 16.dp, bottom = 10.dp)
        )
        Box(modifier = Modifier
            .fillMaxWidth()
            .background(Color.Red)
        ){ Text(text = "")}


    }
}