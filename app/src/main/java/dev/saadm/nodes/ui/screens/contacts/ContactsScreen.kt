package dev.saadm.nodes.ui.screens.contacts

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.rounded.Person
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ContactsScreen(onBackClick: () -> Unit = {}) {
    val contacts = listOf("Alice", "Bob", "Charlie", "David", "Eve", "Frank", "Grace", "Heidi")

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Contacts") }
            )
        }
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            items(contacts) { contact ->
                ListItem(
                    headlineContent = { Text(contact) },
                    leadingContent = {
                        Icon(Icons.Rounded.Person, contentDescription = null)
                    }
                )
            }
        }
    }
}

@Preview
@Composable
fun PreviewContactsScreen() {
    ContactsScreen()
}