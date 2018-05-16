package partiel2018diasdossantos.dossantos.diiage.org.partiel2018diasdossantos;

import android.content.ContentValues;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Quentin on 16/05/2018.
 */

public class Release {

    private String status;
    private String thumb;
    private String format;
    private String title;
    private String catno;
    private Integer year;
    private String resourceUrl;
    private String artist;
    private Integer id;

    /**
     * No args constructor for use in serialization
     *
     */
    public Release() {
    }

    public static Release fromJsonObject(JSONObject object) throws JSONException {
        Release release = new Release();

        if (object.has("artist"))
            release.setArtist(object.getString("artist"));
        if (object.has("catno"))
            release.setCatno(object.getString("catno"));
        if (object.has("format"))
            release.setFormat(object.getString("format"));
        if (object.has("id"))
            release.setId(object.getInt("id"));
        if (object.has("resource_url"))
            release.setResourceUrl(object.getString("resource_url"));
        if (object.has("status"))
            release.setStatus(object.getString("status"));
        if (object.has("thumb"))
            release.setThumb(object.getString("thumb"));
        if (object.has("title"))
            release.setTitle(object.getString("title"));
        if (object.has("year"))
            release.setYear(object.getInt("year"));

        return release;
    }

    public ContentValues toContentValues()
    {
        ContentValues cv = new ContentValues();
        if (id != null)
            cv.put("id", id);
        if (artist != null)
            cv.put("artist", artist);
        if (catno != null)
            cv.put("catno", catno);
        if (format != null)
            cv.put("format", format);
        if (resourceUrl != null)
            cv.put("resource_url", resourceUrl);
        if (status != null)
            cv.put("status", status);
        if (thumb != null)
            cv.put("thumb", thumb);
        if (title != null)
            cv.put("title", title);
        if (year != null)
            cv.put("year", year);

        return cv;
    }

    /**
     *
     * @param id
     * @param catno
     * @param title
     * @param status
     * @param year
     * @param artist
     * @param format
     * @param resourceUrl
     * @param thumb
     */
    public Release(String status, String thumb, String format, String title, String catno, int year, String resourceUrl, String artist, int id) {
        super();
        this.status = status;
        this.thumb = thumb;
        this.format = format;
        this.title = title;
        this.catno = catno;
        this.year = year;
        this.resourceUrl = resourceUrl;
        this.artist = artist;
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getThumb() {
        return thumb;
    }

    public void setThumb(String thumb) {
        this.thumb = thumb;
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCatno() {
        return catno;
    }

    public void setCatno(String catno) {
        this.catno = catno;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getResourceUrl() {
        return resourceUrl;
    }

    public void setResourceUrl(String resourceUrl) {
        this.resourceUrl = resourceUrl;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString()
    {
        return title;
    }

}
