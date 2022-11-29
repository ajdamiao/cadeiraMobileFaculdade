import android.provider.BaseColumns

object DBContract {

    class UserEntry : BaseColumns {
        companion object {
            val TABLE_NAME = "favorites"
            val RECIPE_ID = "recipeId"
            val RECIPE_NAME = "recipeTittle"
            val RECIPE_DESCRIPTION = "description"
            val RECIPE_IMAGE = "image"
        }
    }
}