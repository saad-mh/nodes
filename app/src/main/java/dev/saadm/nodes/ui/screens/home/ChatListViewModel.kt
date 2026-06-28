package dev.saadm.nodes.ui.screens.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.saadm.nodes.data.repository.FakeChatRepository
import dev.saadm.nodes.domain.repository.ChatRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class ChatListViewModel(
    private val repository: ChatRepository = FakeChatRepository()
) : ViewModel() {

    private val _uiState = MutableStateFlow(ChatListUiState(isLoading = true))
    val uiState: StateFlow<ChatListUiState> = _uiState.asStateFlow()

    init {
        loadData()
    }

    private fun loadData() {
        viewModelScope.launch {
            if (repository is FakeChatRepository) {
                combine(
                    repository.getChatsFlow(),
                    repository.getStoriesFlow()
                ) { chats, stories ->
                    ChatListUiState(
                        chats = chats,
                        stories = stories,
                        isLoading = false
                    )
                }.collect { state ->
                    _uiState.value = state
                }
            } else {
                // Fallback for actual repository implementation later
                val chats = repository.getChats()
                val stories = repository.getStories()
                _uiState.update { 
                    it.copy(chats = chats, stories = stories, isLoading = false) 
                }
            }
        }
    }
}
