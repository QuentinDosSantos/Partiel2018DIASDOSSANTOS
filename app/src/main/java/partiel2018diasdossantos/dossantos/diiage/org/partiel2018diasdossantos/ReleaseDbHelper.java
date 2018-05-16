package partiel2018diasdossantos.dossantos.diiage.org.partiel2018diasdossantos;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Quentin on 16/05/2018.
 */

public class ReleaseDbHelper extends SQLiteOpenHelper {
    private static final int VERSION = 2;
    private static final String DBNAME = "dbRelease";

    public ReleaseDbHelper(Context context) {
        super(context, DBNAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE `Release` ( `status` TEXT, `thumb` TEXT, `format` TEXT, `title` TEXT, `catno` TEXT, `year` INTEGER, `resource_url` TEXT, `artist` TEXT, `id` INTEGER, PRIMARY KEY(`id`) )");
        sqLiteDatabase.execSQL("CREATE TABLE `Artist` ( `artist` TEXT, `id` INTEGER PRIMARY KEY AUTOINCREMENT )");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        if (oldVersion == 1 && newVersion == 2) {
            sqLiteDatabase.execSQL("CREATE TABLE `Artist` (`artist` TEXT, `id` INTEGER, PRIMARY KEY(`id`))");

        }
    }
}
