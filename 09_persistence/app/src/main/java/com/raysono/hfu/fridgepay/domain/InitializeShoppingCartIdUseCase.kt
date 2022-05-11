package com.raysono.hfu.fridgepay.domain

import com.raysono.hfu.fridgepay.data.UserSettingsRepository
import com.raysono.hfu.fridgepay.domain.model.ShoppingCartId
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.util.UUID
import kotlin.coroutines.CoroutineContext

/** Initializes the shopping cart ID of the current user. */
class InitializeShoppingCartIdUseCase(
    private val userSettingsRepository: UserSettingsRepository,
    private val context: CoroutineContext = Dispatchers.Default,
) {

    /**
     * Generates a random [ShoppingCartId] and updates the user settings with it if no cart ID is set.
     */
    suspend operator fun invoke() = withContext(context) {
        if (userSettingsRepository.getSettings().cartId.value.isBlank()) {
            userSettingsRepository.updateSettings {
                it.copy(cartId = ShoppingCartId(UUID.randomUUID().toString()))
            }
        }
    }
}
