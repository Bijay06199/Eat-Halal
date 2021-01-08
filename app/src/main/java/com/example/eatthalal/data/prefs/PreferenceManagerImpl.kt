package com.example.eatthalal.data.prefs

import android.content.SharedPreferences
import androidx.core.content.edit

open class PreferenceManagerImpl(private val prefs: SharedPreferences):PreferenceManager {



    override fun setFirstName(firstName: String?) {
        prefs[IS_FIRSTNAME]=firstName
    }

    override fun setLastName(lastName: String?) {
        prefs[IS_LASTNAME]=lastName

    }

    override fun setPassword(password: String?) {
        prefs[IS_PASSWORD]=password
    }

    override fun setCustomerId(customerId: Int?) {
        prefs[IS_CUSTOMER_ID]=customerId
    }

    override fun getCustomerId(): Int? {
       return prefs[IS_CUSTOMER_ID]?:0
    }

    override fun setEmail(email: String?) {
        prefs[IS_CUSTOMER_EMAIL]=email
    }

    override fun setProuductId(productId: Int) {
        prefs[IS_PRODUCT_ID]=productId
    }

    override fun getProductId(): Int {
        return prefs[IS_PRODUCT_ID]?:0
    }

    override fun seQuantity(quantity: Int) {
        prefs[IS_QUANTITY]=quantity
    }

    override fun getQuantity(): Int{
        return prefs[IS_QUANTITY]?:0
    }

    override fun getEmail(): String? {
        return prefs[IS_CUSTOMER_EMAIL]?:""
    }

    override fun setNumber(number: String?) {
        prefs[IS_NUMBER]=number
    }

    override fun getNumber(): String? {
       return prefs[IS_NUMBER]?:""
    }

    override fun getAddress(): String? {
        return prefs[IS_ADDRESS]?:""
    }

    override fun setAddress(address: String?) {
       prefs[IS_ADDRESS]=address
    }


    override fun getFirstName(): String {
        return prefs[IS_FIRSTNAME]?:""
    }

    override fun getLastName(): String {
        return prefs[IS_LASTNAME]?:""

    }

    override fun getPassword(): String {
        return prefs[IS_PASSWORD]?:""

    }

    override fun setToken(token: String) {
        prefs[ACCESS_TOKEN]=token
    }

    override fun getToken(): String {
        return prefs[ACCESS_TOKEN] ?: ""

    }

    override fun setIsLoggedIn(isLoggedIn: Boolean) {
        prefs[IS_LOGGED_IN] = isLoggedIn
    }

    override fun getIsLoggedIn(): Boolean {
        return prefs[IS_LOGGED_IN] ?: false
    }

    /**
     * Puts a key-value pair in shared prefs if doesn't exists,
     * otherwise updates value on given [key]
     */
    operator fun SharedPreferences.set(key: String, value: Any?) {
        when (value) {
            is String? -> prefs.edit {
                putString(key, value)
            }
            is Int -> prefs.edit {
                putInt(key, value)
            }
            is Boolean -> prefs.edit {
                putBoolean(key, value)
            }
            is Float -> prefs.edit {
                putFloat(key, value)
            }
            is Long -> prefs.edit {
                putLong(key, value)
            }
            else -> throw UnsupportedOperationException("Not yet implemented")
        }
    }

    /**
     * Finds value on given key.
     * [T] is the type of value
     * @param defaultValue optional default value will take following if [defaultValue] is not specified
     * null for strings,
     * false for bool and
     * -1 for numeric values (int, float, long)
     */
    inline operator fun <reified T : Any> SharedPreferences.get(
        key: String,
        defaultValue: T? = null
    ): T? {
        return when (T::class) {
            String::class -> getString(key, defaultValue as? String) as T?
            Int::class -> getInt(key, defaultValue as? Int ?: -1) as T?
            Boolean::class -> getBoolean(key, defaultValue as? Boolean ?: false) as T?
            Float::class -> getFloat(key, defaultValue as? Float ?: -1f) as T?
            Long::class -> getLong(key, defaultValue as? Long ?: -1) as T?
            else -> throw UnsupportedOperationException("Not yet implemented")
        }
    }

}