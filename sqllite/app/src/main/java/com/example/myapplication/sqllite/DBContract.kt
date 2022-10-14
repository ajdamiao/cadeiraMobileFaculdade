import android.provider.BaseColumns

object DBContract {

    /* Inner class that defines the table contents */
    class UserEntry : BaseColumns {
        companion object {
            val TABLE_NAME = "users"
            val PRODUCT_ID = "productId"
            val ID = "id"
            val PRODUCT_NAME = "name"
        }
    }
}