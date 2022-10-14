import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteConstraintException
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteException
import android.database.sqlite.SQLiteOpenHelper
import com.example.shoppingunhas.model.ProductCart

class CartDBHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {
    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(SQL_CREATE_ENTRIES)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL(SQL_DELETE_ENTRIES)
        onCreate(db)
    }

    override fun onDowngrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        onUpgrade(db, oldVersion, newVersion)
    }

    @Throws(SQLiteConstraintException::class)
    fun insertProductToCart(productCart: ProductCart): Boolean {
        val db = writableDatabase
        val values = ContentValues()

        values.put(DBContract.UserEntry.PRODUCT_NAME, productCart.name)

        val newRowId = db.insert(DBContract.UserEntry.TABLE_NAME, null, values)

        newRowId.toInt().let {
            return it != -1
        }
    }

    @Throws(SQLiteConstraintException::class)
    fun deleteItemFromCart(productName: String): Boolean {
        val db = writableDatabase
        // Define 'where' part of query.
        val selection = DBContract.UserEntry.PRODUCT_NAME + " LIKE ?"
        val selectionArgs = arrayOf(productName)
        db.delete(DBContract.UserEntry.TABLE_NAME, selection, selectionArgs)

        return true
    }

    fun checkIfProductsAlreadyInDB(productName: String): Boolean {
        val db = writableDatabase
        var cursor: Cursor? = null
        try {
            cursor = db.rawQuery("select * from " + DBContract.UserEntry.TABLE_NAME + " WHERE " + DBContract.UserEntry.PRODUCT_ID + "='" + productName + "'", null)
            return true
        } catch (e: SQLiteException) {
            // if table not yet present, create it
            db.execSQL(SQL_CREATE_ENTRIES)
            return false
        }
    }

    fun getCartProducts(): ArrayList<ProductCart> {
        val productsCart = ArrayList<ProductCart>()
        val db = writableDatabase
        var cursor: Cursor? = null
        try {
            cursor = db.rawQuery("select * from " + DBContract.UserEntry.TABLE_NAME, null)
        } catch (e: SQLiteException) {
            db.execSQL(SQL_CREATE_ENTRIES)
            return ArrayList()
        }

        var name: String
        var price: String
        var finalPrice: String
        var image: String
        var quantity: String

        if (cursor!!.moveToFirst()) {
            while (cursor.isAfterLast == false) {

                name = cursor.getString(cursor.getColumnIndex(DBContract.UserEntry.PRODUCT_NAME))


                productsCart.add(ProductCart(name))
                cursor.moveToNext()
            }
        }
        return productsCart
    }

    companion object {
        val DATABASE_VERSION = 1
        val DATABASE_NAME = "sqllite.db"

        private val SQL_CREATE_ENTRIES =
            "CREATE TABLE " + DBContract.UserEntry.TABLE_NAME + " (" +
                    DBContract.UserEntry.ID + " INT PRIMARY KEY," +
                    DBContract.UserEntry.PRODUCT_NAME + " TEXT)"

        private val SQL_DELETE_ENTRIES = "DROP TABLE IF EXISTS " + DBContract.UserEntry.TABLE_NAME
    }
}