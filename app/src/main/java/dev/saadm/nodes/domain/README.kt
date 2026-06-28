package dev.saadm.nodes.domain

import dev.saadm.nodes.domain.models.Chat
import dev.saadm.nodes.domain.models.MessageStatus
import dev.saadm.nodes.domain.models.Story
import dev.saadm.nodes.domain.repository.ChatRepository

/**
 * Domain Layer
 *
 * This package contains the core business logic and data structures of the application.
 * It is independent of any other layers (Data or UI).
 *
 * ### Data Structures (Models)
 * - [Chat]: Represents a conversation between users or a group.
 *   - `id`: Unique identifier for the chat.
 *   - `name`: Display name of the contact or group.
 *   - `lastMessage`: The most recent message in the conversation.
 *   - `timestamp`: Relative or absolute time of the last message.
 *   - `unreadCount`: Number of unread messages.
 *   - `isPinned`: Whether the chat is pinned to the top.
 *   - `isMuted`: Whether notifications are silenced for this chat.
 *   - `status`: [MessageStatus] of the last message (NONE, SENT, DELIVERED, READ).
 *   - `isGroup`: Indicates if the chat is a group conversation.
 *
 * - [Story]: Represents a user's status or story.
 *   - `userName`: Name of the user who posted the story.
 *   - `unreadCount`: Number of unwatched story segments.
 *   - `isUser`: Whether the story belongs to the current user.
 *
 * ### Repository Interfaces
 * - [ChatRepository]: Defines the contract for fetching chat and story data.
 */
