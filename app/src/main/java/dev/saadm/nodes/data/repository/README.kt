package dev.saadm.nodes.data.repository

import dev.saadm.nodes.domain.repository.ChatRepository

/**
 * Data Layer - Repositories
 *
 * This package contains the implementations of the repository interfaces defined in the domain layer.
 * It is responsible for orchestrating data from different sources (Network, Database, or In-memory).
 *
 * ### Implementations
 * - [FakeChatRepository]: An in-memory implementation of [ChatRepository] used for development and testing.
 *   Provides static mock data for stories and chat conversations.
 */
