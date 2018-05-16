package partiel2018diasdossantos.dossantos.diiage.org.partiel2018diasdossantos;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        class ApiTask extends AsyncTask<String, Void, JSONArray> {

            @Override
            protected JSONArray doInBackground(String... urls) {
                try {
                    URL url = new URL(urls[0]);
                    InputStream inputStream = url.openStream();
                    //Ouverture de la connexion
                    inputStream = url.openStream();
                    //Facilite la lecture de inputStream
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                    //Initialisation d'un StringBuilder pour stockerle contenu distant
                    StringBuilder stringBuilder = new StringBuilder();
                    //Declaration d'une variable buffer pour le parcours du flux
                    String lineBuffer = null;
                    while ((lineBuffer = bufferedReader.readLine()) != null) {
                        // ajout de la ligne buffer au contenui complet
                        stringBuilder.append(lineBuffer);
                    }
                    String data = stringBuilder.toString();
                    JSONArray jsonArray = new JSONArray(data);
                    return jsonArray;

                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                return null;
            }

            @Override
            protected void onPostExecute(JSONArray result) {
                ArrayList<Release> releases = new ArrayList<>();

                for (int i = 0; i < result.length(); i++) {
                    try {
                        JSONObject object = result.getJSONObject(i);
                        releases.add(Release.fromJsonObject(object));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

                //Creation d'Adapter pour afficher les releases
                ((ListView)MainActivity.this.findViewById(R.id.listRelease))
                        .setAdapter(new ArrayAdapter<Release>(MainActivity.this, R.layout.layout_item, releases));

                ReleaseDbHelper dbHelper = new ReleaseDbHelper(MainActivity.this);
                SQLiteDatabase db = dbHelper.getWritableDatabase();
                for (Release release : releases){
                    //Insertion des releases dans la base
                    Cursor cursorRelease = db.query("Release", new String[]{"*"}, "id = ?", new String[]{String.valueOf(release.getId())}, null, null, null);
                    if (cursorRelease.getCount() == 0) {
                        db.insert("Release", null, release.toContentValues());
                    }
                    cursorRelease.close();

                    Cursor cursorArtist = db.query("Artist", new String[]{"*"}, "artist = ?", new String[]{release.getArtist()}, null, null, null);
                    if (cursorArtist.getCount() == 0) {
                        ContentValues cv = new ContentValues();
                        cv.put("artist", release.getArtist());

                        db.insert("Artist", null, cv);
                    }
                    cursorArtist.close();
                }
                db.close();
            }
        }

        new ApiTask().execute("https://my-json-server.typicode.com/lpotherat/discogs-data/releases");
    }
}
