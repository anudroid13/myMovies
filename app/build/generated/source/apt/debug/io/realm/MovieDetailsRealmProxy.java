package io.realm;


import android.annotation.TargetApi;
import android.os.Build;
import android.util.JsonReader;
import android.util.JsonToken;
import io.realm.ProxyUtils;
import io.realm.exceptions.RealmMigrationNeededException;
import io.realm.internal.ColumnInfo;
import io.realm.internal.OsList;
import io.realm.internal.OsObject;
import io.realm.internal.OsObjectSchemaInfo;
import io.realm.internal.OsSchemaInfo;
import io.realm.internal.Property;
import io.realm.internal.RealmObjectProxy;
import io.realm.internal.Row;
import io.realm.internal.Table;
import io.realm.internal.android.JsonUtils;
import io.realm.log.RealmLog;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

@SuppressWarnings("all")
public class MovieDetailsRealmProxy extends myapp.nigam.com.mymoviesapp.models.MovieDetails
    implements RealmObjectProxy, MovieDetailsRealmProxyInterface {

    static final class MovieDetailsColumnInfo extends ColumnInfo {
        long idIndex;
        long voteCountIndex;
        long videoIndex;
        long voteAverageIndex;
        long titleIndex;
        long popularityIndex;
        long posterPathIndex;
        long originalLanguageIndex;
        long originalTitleIndex;
        long backdropPathIndex;
        long adultIndex;
        long overviewIndex;
        long releaseDateIndex;

        MovieDetailsColumnInfo(OsSchemaInfo schemaInfo) {
            super(13);
            OsObjectSchemaInfo objectSchemaInfo = schemaInfo.getObjectSchemaInfo("MovieDetails");
            this.idIndex = addColumnDetails("id", objectSchemaInfo);
            this.voteCountIndex = addColumnDetails("voteCount", objectSchemaInfo);
            this.videoIndex = addColumnDetails("video", objectSchemaInfo);
            this.voteAverageIndex = addColumnDetails("voteAverage", objectSchemaInfo);
            this.titleIndex = addColumnDetails("title", objectSchemaInfo);
            this.popularityIndex = addColumnDetails("popularity", objectSchemaInfo);
            this.posterPathIndex = addColumnDetails("posterPath", objectSchemaInfo);
            this.originalLanguageIndex = addColumnDetails("originalLanguage", objectSchemaInfo);
            this.originalTitleIndex = addColumnDetails("originalTitle", objectSchemaInfo);
            this.backdropPathIndex = addColumnDetails("backdropPath", objectSchemaInfo);
            this.adultIndex = addColumnDetails("adult", objectSchemaInfo);
            this.overviewIndex = addColumnDetails("overview", objectSchemaInfo);
            this.releaseDateIndex = addColumnDetails("releaseDate", objectSchemaInfo);
        }

        MovieDetailsColumnInfo(ColumnInfo src, boolean mutable) {
            super(src, mutable);
            copy(src, this);
        }

        @Override
        protected final ColumnInfo copy(boolean mutable) {
            return new MovieDetailsColumnInfo(this, mutable);
        }

        @Override
        protected final void copy(ColumnInfo rawSrc, ColumnInfo rawDst) {
            final MovieDetailsColumnInfo src = (MovieDetailsColumnInfo) rawSrc;
            final MovieDetailsColumnInfo dst = (MovieDetailsColumnInfo) rawDst;
            dst.idIndex = src.idIndex;
            dst.voteCountIndex = src.voteCountIndex;
            dst.videoIndex = src.videoIndex;
            dst.voteAverageIndex = src.voteAverageIndex;
            dst.titleIndex = src.titleIndex;
            dst.popularityIndex = src.popularityIndex;
            dst.posterPathIndex = src.posterPathIndex;
            dst.originalLanguageIndex = src.originalLanguageIndex;
            dst.originalTitleIndex = src.originalTitleIndex;
            dst.backdropPathIndex = src.backdropPathIndex;
            dst.adultIndex = src.adultIndex;
            dst.overviewIndex = src.overviewIndex;
            dst.releaseDateIndex = src.releaseDateIndex;
        }
    }

    private static final OsObjectSchemaInfo expectedObjectSchemaInfo = createExpectedObjectSchemaInfo();
    private static final List<String> FIELD_NAMES;
    static {
        List<String> fieldNames = new ArrayList<String>(13);
        fieldNames.add("id");
        fieldNames.add("voteCount");
        fieldNames.add("video");
        fieldNames.add("voteAverage");
        fieldNames.add("title");
        fieldNames.add("popularity");
        fieldNames.add("posterPath");
        fieldNames.add("originalLanguage");
        fieldNames.add("originalTitle");
        fieldNames.add("backdropPath");
        fieldNames.add("adult");
        fieldNames.add("overview");
        fieldNames.add("releaseDate");
        FIELD_NAMES = Collections.unmodifiableList(fieldNames);
    }

    private MovieDetailsColumnInfo columnInfo;
    private ProxyState<myapp.nigam.com.mymoviesapp.models.MovieDetails> proxyState;

    MovieDetailsRealmProxy() {
        proxyState.setConstructionFinished();
    }

    @Override
    public void realm$injectObjectContext() {
        if (this.proxyState != null) {
            return;
        }
        final BaseRealm.RealmObjectContext context = BaseRealm.objectContext.get();
        this.columnInfo = (MovieDetailsColumnInfo) context.getColumnInfo();
        this.proxyState = new ProxyState<myapp.nigam.com.mymoviesapp.models.MovieDetails>(this);
        proxyState.setRealm$realm(context.getRealm());
        proxyState.setRow$realm(context.getRow());
        proxyState.setAcceptDefaultValue$realm(context.getAcceptDefaultValue());
        proxyState.setExcludeFields$realm(context.getExcludeFields());
    }

    @Override
    @SuppressWarnings("cast")
    public int realmGet$id() {
        proxyState.getRealm$realm().checkIfValid();
        return (int) proxyState.getRow$realm().getLong(columnInfo.idIndex);
    }

    @Override
    public void realmSet$id(int value) {
        if (proxyState.isUnderConstruction()) {
            // default value of the primary key is always ignored.
            return;
        }

        proxyState.getRealm$realm().checkIfValid();
        throw new io.realm.exceptions.RealmException("Primary key field 'id' cannot be changed after object was created.");
    }

    @Override
    @SuppressWarnings("cast")
    public int realmGet$voteCount() {
        proxyState.getRealm$realm().checkIfValid();
        return (int) proxyState.getRow$realm().getLong(columnInfo.voteCountIndex);
    }

    @Override
    public void realmSet$voteCount(int value) {
        if (proxyState.isUnderConstruction()) {
            if (!proxyState.getAcceptDefaultValue$realm()) {
                return;
            }
            final Row row = proxyState.getRow$realm();
            row.getTable().setLong(columnInfo.voteCountIndex, row.getIndex(), value, true);
            return;
        }

        proxyState.getRealm$realm().checkIfValid();
        proxyState.getRow$realm().setLong(columnInfo.voteCountIndex, value);
    }

    @Override
    @SuppressWarnings("cast")
    public boolean realmGet$video() {
        proxyState.getRealm$realm().checkIfValid();
        return (boolean) proxyState.getRow$realm().getBoolean(columnInfo.videoIndex);
    }

    @Override
    public void realmSet$video(boolean value) {
        if (proxyState.isUnderConstruction()) {
            if (!proxyState.getAcceptDefaultValue$realm()) {
                return;
            }
            final Row row = proxyState.getRow$realm();
            row.getTable().setBoolean(columnInfo.videoIndex, row.getIndex(), value, true);
            return;
        }

        proxyState.getRealm$realm().checkIfValid();
        proxyState.getRow$realm().setBoolean(columnInfo.videoIndex, value);
    }

    @Override
    @SuppressWarnings("cast")
    public float realmGet$voteAverage() {
        proxyState.getRealm$realm().checkIfValid();
        return (float) proxyState.getRow$realm().getFloat(columnInfo.voteAverageIndex);
    }

    @Override
    public void realmSet$voteAverage(float value) {
        if (proxyState.isUnderConstruction()) {
            if (!proxyState.getAcceptDefaultValue$realm()) {
                return;
            }
            final Row row = proxyState.getRow$realm();
            row.getTable().setFloat(columnInfo.voteAverageIndex, row.getIndex(), value, true);
            return;
        }

        proxyState.getRealm$realm().checkIfValid();
        proxyState.getRow$realm().setFloat(columnInfo.voteAverageIndex, value);
    }

    @Override
    @SuppressWarnings("cast")
    public String realmGet$title() {
        proxyState.getRealm$realm().checkIfValid();
        return (java.lang.String) proxyState.getRow$realm().getString(columnInfo.titleIndex);
    }

    @Override
    public void realmSet$title(String value) {
        if (proxyState.isUnderConstruction()) {
            if (!proxyState.getAcceptDefaultValue$realm()) {
                return;
            }
            final Row row = proxyState.getRow$realm();
            if (value == null) {
                row.getTable().setNull(columnInfo.titleIndex, row.getIndex(), true);
                return;
            }
            row.getTable().setString(columnInfo.titleIndex, row.getIndex(), value, true);
            return;
        }

        proxyState.getRealm$realm().checkIfValid();
        if (value == null) {
            proxyState.getRow$realm().setNull(columnInfo.titleIndex);
            return;
        }
        proxyState.getRow$realm().setString(columnInfo.titleIndex, value);
    }

    @Override
    @SuppressWarnings("cast")
    public double realmGet$popularity() {
        proxyState.getRealm$realm().checkIfValid();
        return (double) proxyState.getRow$realm().getDouble(columnInfo.popularityIndex);
    }

    @Override
    public void realmSet$popularity(double value) {
        if (proxyState.isUnderConstruction()) {
            if (!proxyState.getAcceptDefaultValue$realm()) {
                return;
            }
            final Row row = proxyState.getRow$realm();
            row.getTable().setDouble(columnInfo.popularityIndex, row.getIndex(), value, true);
            return;
        }

        proxyState.getRealm$realm().checkIfValid();
        proxyState.getRow$realm().setDouble(columnInfo.popularityIndex, value);
    }

    @Override
    @SuppressWarnings("cast")
    public String realmGet$posterPath() {
        proxyState.getRealm$realm().checkIfValid();
        return (java.lang.String) proxyState.getRow$realm().getString(columnInfo.posterPathIndex);
    }

    @Override
    public void realmSet$posterPath(String value) {
        if (proxyState.isUnderConstruction()) {
            if (!proxyState.getAcceptDefaultValue$realm()) {
                return;
            }
            final Row row = proxyState.getRow$realm();
            if (value == null) {
                row.getTable().setNull(columnInfo.posterPathIndex, row.getIndex(), true);
                return;
            }
            row.getTable().setString(columnInfo.posterPathIndex, row.getIndex(), value, true);
            return;
        }

        proxyState.getRealm$realm().checkIfValid();
        if (value == null) {
            proxyState.getRow$realm().setNull(columnInfo.posterPathIndex);
            return;
        }
        proxyState.getRow$realm().setString(columnInfo.posterPathIndex, value);
    }

    @Override
    @SuppressWarnings("cast")
    public String realmGet$originalLanguage() {
        proxyState.getRealm$realm().checkIfValid();
        return (java.lang.String) proxyState.getRow$realm().getString(columnInfo.originalLanguageIndex);
    }

    @Override
    public void realmSet$originalLanguage(String value) {
        if (proxyState.isUnderConstruction()) {
            if (!proxyState.getAcceptDefaultValue$realm()) {
                return;
            }
            final Row row = proxyState.getRow$realm();
            if (value == null) {
                row.getTable().setNull(columnInfo.originalLanguageIndex, row.getIndex(), true);
                return;
            }
            row.getTable().setString(columnInfo.originalLanguageIndex, row.getIndex(), value, true);
            return;
        }

        proxyState.getRealm$realm().checkIfValid();
        if (value == null) {
            proxyState.getRow$realm().setNull(columnInfo.originalLanguageIndex);
            return;
        }
        proxyState.getRow$realm().setString(columnInfo.originalLanguageIndex, value);
    }

    @Override
    @SuppressWarnings("cast")
    public String realmGet$originalTitle() {
        proxyState.getRealm$realm().checkIfValid();
        return (java.lang.String) proxyState.getRow$realm().getString(columnInfo.originalTitleIndex);
    }

    @Override
    public void realmSet$originalTitle(String value) {
        if (proxyState.isUnderConstruction()) {
            if (!proxyState.getAcceptDefaultValue$realm()) {
                return;
            }
            final Row row = proxyState.getRow$realm();
            if (value == null) {
                row.getTable().setNull(columnInfo.originalTitleIndex, row.getIndex(), true);
                return;
            }
            row.getTable().setString(columnInfo.originalTitleIndex, row.getIndex(), value, true);
            return;
        }

        proxyState.getRealm$realm().checkIfValid();
        if (value == null) {
            proxyState.getRow$realm().setNull(columnInfo.originalTitleIndex);
            return;
        }
        proxyState.getRow$realm().setString(columnInfo.originalTitleIndex, value);
    }

    @Override
    @SuppressWarnings("cast")
    public String realmGet$backdropPath() {
        proxyState.getRealm$realm().checkIfValid();
        return (java.lang.String) proxyState.getRow$realm().getString(columnInfo.backdropPathIndex);
    }

    @Override
    public void realmSet$backdropPath(String value) {
        if (proxyState.isUnderConstruction()) {
            if (!proxyState.getAcceptDefaultValue$realm()) {
                return;
            }
            final Row row = proxyState.getRow$realm();
            if (value == null) {
                row.getTable().setNull(columnInfo.backdropPathIndex, row.getIndex(), true);
                return;
            }
            row.getTable().setString(columnInfo.backdropPathIndex, row.getIndex(), value, true);
            return;
        }

        proxyState.getRealm$realm().checkIfValid();
        if (value == null) {
            proxyState.getRow$realm().setNull(columnInfo.backdropPathIndex);
            return;
        }
        proxyState.getRow$realm().setString(columnInfo.backdropPathIndex, value);
    }

    @Override
    @SuppressWarnings("cast")
    public boolean realmGet$adult() {
        proxyState.getRealm$realm().checkIfValid();
        return (boolean) proxyState.getRow$realm().getBoolean(columnInfo.adultIndex);
    }

    @Override
    public void realmSet$adult(boolean value) {
        if (proxyState.isUnderConstruction()) {
            if (!proxyState.getAcceptDefaultValue$realm()) {
                return;
            }
            final Row row = proxyState.getRow$realm();
            row.getTable().setBoolean(columnInfo.adultIndex, row.getIndex(), value, true);
            return;
        }

        proxyState.getRealm$realm().checkIfValid();
        proxyState.getRow$realm().setBoolean(columnInfo.adultIndex, value);
    }

    @Override
    @SuppressWarnings("cast")
    public String realmGet$overview() {
        proxyState.getRealm$realm().checkIfValid();
        return (java.lang.String) proxyState.getRow$realm().getString(columnInfo.overviewIndex);
    }

    @Override
    public void realmSet$overview(String value) {
        if (proxyState.isUnderConstruction()) {
            if (!proxyState.getAcceptDefaultValue$realm()) {
                return;
            }
            final Row row = proxyState.getRow$realm();
            if (value == null) {
                row.getTable().setNull(columnInfo.overviewIndex, row.getIndex(), true);
                return;
            }
            row.getTable().setString(columnInfo.overviewIndex, row.getIndex(), value, true);
            return;
        }

        proxyState.getRealm$realm().checkIfValid();
        if (value == null) {
            proxyState.getRow$realm().setNull(columnInfo.overviewIndex);
            return;
        }
        proxyState.getRow$realm().setString(columnInfo.overviewIndex, value);
    }

    @Override
    @SuppressWarnings("cast")
    public String realmGet$releaseDate() {
        proxyState.getRealm$realm().checkIfValid();
        return (java.lang.String) proxyState.getRow$realm().getString(columnInfo.releaseDateIndex);
    }

    @Override
    public void realmSet$releaseDate(String value) {
        if (proxyState.isUnderConstruction()) {
            if (!proxyState.getAcceptDefaultValue$realm()) {
                return;
            }
            final Row row = proxyState.getRow$realm();
            if (value == null) {
                row.getTable().setNull(columnInfo.releaseDateIndex, row.getIndex(), true);
                return;
            }
            row.getTable().setString(columnInfo.releaseDateIndex, row.getIndex(), value, true);
            return;
        }

        proxyState.getRealm$realm().checkIfValid();
        if (value == null) {
            proxyState.getRow$realm().setNull(columnInfo.releaseDateIndex);
            return;
        }
        proxyState.getRow$realm().setString(columnInfo.releaseDateIndex, value);
    }

    private static OsObjectSchemaInfo createExpectedObjectSchemaInfo() {
        OsObjectSchemaInfo.Builder builder = new OsObjectSchemaInfo.Builder("MovieDetails", 13, 0);
        builder.addPersistedProperty("id", RealmFieldType.INTEGER, Property.PRIMARY_KEY, Property.INDEXED, Property.REQUIRED);
        builder.addPersistedProperty("voteCount", RealmFieldType.INTEGER, !Property.PRIMARY_KEY, !Property.INDEXED, Property.REQUIRED);
        builder.addPersistedProperty("video", RealmFieldType.BOOLEAN, !Property.PRIMARY_KEY, !Property.INDEXED, Property.REQUIRED);
        builder.addPersistedProperty("voteAverage", RealmFieldType.FLOAT, !Property.PRIMARY_KEY, !Property.INDEXED, Property.REQUIRED);
        builder.addPersistedProperty("title", RealmFieldType.STRING, !Property.PRIMARY_KEY, !Property.INDEXED, !Property.REQUIRED);
        builder.addPersistedProperty("popularity", RealmFieldType.DOUBLE, !Property.PRIMARY_KEY, !Property.INDEXED, Property.REQUIRED);
        builder.addPersistedProperty("posterPath", RealmFieldType.STRING, !Property.PRIMARY_KEY, !Property.INDEXED, !Property.REQUIRED);
        builder.addPersistedProperty("originalLanguage", RealmFieldType.STRING, !Property.PRIMARY_KEY, !Property.INDEXED, !Property.REQUIRED);
        builder.addPersistedProperty("originalTitle", RealmFieldType.STRING, !Property.PRIMARY_KEY, !Property.INDEXED, !Property.REQUIRED);
        builder.addPersistedProperty("backdropPath", RealmFieldType.STRING, !Property.PRIMARY_KEY, !Property.INDEXED, !Property.REQUIRED);
        builder.addPersistedProperty("adult", RealmFieldType.BOOLEAN, !Property.PRIMARY_KEY, !Property.INDEXED, Property.REQUIRED);
        builder.addPersistedProperty("overview", RealmFieldType.STRING, !Property.PRIMARY_KEY, !Property.INDEXED, !Property.REQUIRED);
        builder.addPersistedProperty("releaseDate", RealmFieldType.STRING, !Property.PRIMARY_KEY, !Property.INDEXED, !Property.REQUIRED);
        return builder.build();
    }

    public static OsObjectSchemaInfo getExpectedObjectSchemaInfo() {
        return expectedObjectSchemaInfo;
    }

    public static MovieDetailsColumnInfo createColumnInfo(OsSchemaInfo schemaInfo) {
        return new MovieDetailsColumnInfo(schemaInfo);
    }

    public static String getSimpleClassName() {
        return "MovieDetails";
    }

    public static List<String> getFieldNames() {
        return FIELD_NAMES;
    }

    @SuppressWarnings("cast")
    public static myapp.nigam.com.mymoviesapp.models.MovieDetails createOrUpdateUsingJsonObject(Realm realm, JSONObject json, boolean update)
        throws JSONException {
        final List<String> excludeFields = Collections.<String> emptyList();
        myapp.nigam.com.mymoviesapp.models.MovieDetails obj = null;
        if (update) {
            Table table = realm.getTable(myapp.nigam.com.mymoviesapp.models.MovieDetails.class);
            MovieDetailsColumnInfo columnInfo = (MovieDetailsColumnInfo) realm.getSchema().getColumnInfo(myapp.nigam.com.mymoviesapp.models.MovieDetails.class);
            long pkColumnIndex = columnInfo.idIndex;
            long rowIndex = Table.NO_MATCH;
            if (!json.isNull("id")) {
                rowIndex = table.findFirstLong(pkColumnIndex, json.getLong("id"));
            }
            if (rowIndex != Table.NO_MATCH) {
                final BaseRealm.RealmObjectContext objectContext = BaseRealm.objectContext.get();
                try {
                    objectContext.set(realm, table.getUncheckedRow(rowIndex), realm.getSchema().getColumnInfo(myapp.nigam.com.mymoviesapp.models.MovieDetails.class), false, Collections.<String> emptyList());
                    obj = new io.realm.MovieDetailsRealmProxy();
                } finally {
                    objectContext.clear();
                }
            }
        }
        if (obj == null) {
            if (json.has("id")) {
                if (json.isNull("id")) {
                    obj = (io.realm.MovieDetailsRealmProxy) realm.createObjectInternal(myapp.nigam.com.mymoviesapp.models.MovieDetails.class, null, true, excludeFields);
                } else {
                    obj = (io.realm.MovieDetailsRealmProxy) realm.createObjectInternal(myapp.nigam.com.mymoviesapp.models.MovieDetails.class, json.getInt("id"), true, excludeFields);
                }
            } else {
                throw new IllegalArgumentException("JSON object doesn't have the primary key field 'id'.");
            }
        }

        final MovieDetailsRealmProxyInterface objProxy = (MovieDetailsRealmProxyInterface) obj;
        if (json.has("voteCount")) {
            if (json.isNull("voteCount")) {
                throw new IllegalArgumentException("Trying to set non-nullable field 'voteCount' to null.");
            } else {
                objProxy.realmSet$voteCount((int) json.getInt("voteCount"));
            }
        }
        if (json.has("video")) {
            if (json.isNull("video")) {
                throw new IllegalArgumentException("Trying to set non-nullable field 'video' to null.");
            } else {
                objProxy.realmSet$video((boolean) json.getBoolean("video"));
            }
        }
        if (json.has("voteAverage")) {
            if (json.isNull("voteAverage")) {
                throw new IllegalArgumentException("Trying to set non-nullable field 'voteAverage' to null.");
            } else {
                objProxy.realmSet$voteAverage((float) json.getDouble("voteAverage"));
            }
        }
        if (json.has("title")) {
            if (json.isNull("title")) {
                objProxy.realmSet$title(null);
            } else {
                objProxy.realmSet$title((String) json.getString("title"));
            }
        }
        if (json.has("popularity")) {
            if (json.isNull("popularity")) {
                throw new IllegalArgumentException("Trying to set non-nullable field 'popularity' to null.");
            } else {
                objProxy.realmSet$popularity((double) json.getDouble("popularity"));
            }
        }
        if (json.has("posterPath")) {
            if (json.isNull("posterPath")) {
                objProxy.realmSet$posterPath(null);
            } else {
                objProxy.realmSet$posterPath((String) json.getString("posterPath"));
            }
        }
        if (json.has("originalLanguage")) {
            if (json.isNull("originalLanguage")) {
                objProxy.realmSet$originalLanguage(null);
            } else {
                objProxy.realmSet$originalLanguage((String) json.getString("originalLanguage"));
            }
        }
        if (json.has("originalTitle")) {
            if (json.isNull("originalTitle")) {
                objProxy.realmSet$originalTitle(null);
            } else {
                objProxy.realmSet$originalTitle((String) json.getString("originalTitle"));
            }
        }
        if (json.has("backdropPath")) {
            if (json.isNull("backdropPath")) {
                objProxy.realmSet$backdropPath(null);
            } else {
                objProxy.realmSet$backdropPath((String) json.getString("backdropPath"));
            }
        }
        if (json.has("adult")) {
            if (json.isNull("adult")) {
                throw new IllegalArgumentException("Trying to set non-nullable field 'adult' to null.");
            } else {
                objProxy.realmSet$adult((boolean) json.getBoolean("adult"));
            }
        }
        if (json.has("overview")) {
            if (json.isNull("overview")) {
                objProxy.realmSet$overview(null);
            } else {
                objProxy.realmSet$overview((String) json.getString("overview"));
            }
        }
        if (json.has("releaseDate")) {
            if (json.isNull("releaseDate")) {
                objProxy.realmSet$releaseDate(null);
            } else {
                objProxy.realmSet$releaseDate((String) json.getString("releaseDate"));
            }
        }
        return obj;
    }

    @SuppressWarnings("cast")
    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    public static myapp.nigam.com.mymoviesapp.models.MovieDetails createUsingJsonStream(Realm realm, JsonReader reader)
        throws IOException {
        boolean jsonHasPrimaryKey = false;
        final myapp.nigam.com.mymoviesapp.models.MovieDetails obj = new myapp.nigam.com.mymoviesapp.models.MovieDetails();
        final MovieDetailsRealmProxyInterface objProxy = (MovieDetailsRealmProxyInterface) obj;
        reader.beginObject();
        while (reader.hasNext()) {
            String name = reader.nextName();
            if (false) {
            } else if (name.equals("id")) {
                if (reader.peek() != JsonToken.NULL) {
                    objProxy.realmSet$id((int) reader.nextInt());
                } else {
                    reader.skipValue();
                    throw new IllegalArgumentException("Trying to set non-nullable field 'id' to null.");
                }
                jsonHasPrimaryKey = true;
            } else if (name.equals("voteCount")) {
                if (reader.peek() != JsonToken.NULL) {
                    objProxy.realmSet$voteCount((int) reader.nextInt());
                } else {
                    reader.skipValue();
                    throw new IllegalArgumentException("Trying to set non-nullable field 'voteCount' to null.");
                }
            } else if (name.equals("video")) {
                if (reader.peek() != JsonToken.NULL) {
                    objProxy.realmSet$video((boolean) reader.nextBoolean());
                } else {
                    reader.skipValue();
                    throw new IllegalArgumentException("Trying to set non-nullable field 'video' to null.");
                }
            } else if (name.equals("voteAverage")) {
                if (reader.peek() != JsonToken.NULL) {
                    objProxy.realmSet$voteAverage((float) reader.nextDouble());
                } else {
                    reader.skipValue();
                    throw new IllegalArgumentException("Trying to set non-nullable field 'voteAverage' to null.");
                }
            } else if (name.equals("title")) {
                if (reader.peek() != JsonToken.NULL) {
                    objProxy.realmSet$title((String) reader.nextString());
                } else {
                    reader.skipValue();
                    objProxy.realmSet$title(null);
                }
            } else if (name.equals("popularity")) {
                if (reader.peek() != JsonToken.NULL) {
                    objProxy.realmSet$popularity((double) reader.nextDouble());
                } else {
                    reader.skipValue();
                    throw new IllegalArgumentException("Trying to set non-nullable field 'popularity' to null.");
                }
            } else if (name.equals("posterPath")) {
                if (reader.peek() != JsonToken.NULL) {
                    objProxy.realmSet$posterPath((String) reader.nextString());
                } else {
                    reader.skipValue();
                    objProxy.realmSet$posterPath(null);
                }
            } else if (name.equals("originalLanguage")) {
                if (reader.peek() != JsonToken.NULL) {
                    objProxy.realmSet$originalLanguage((String) reader.nextString());
                } else {
                    reader.skipValue();
                    objProxy.realmSet$originalLanguage(null);
                }
            } else if (name.equals("originalTitle")) {
                if (reader.peek() != JsonToken.NULL) {
                    objProxy.realmSet$originalTitle((String) reader.nextString());
                } else {
                    reader.skipValue();
                    objProxy.realmSet$originalTitle(null);
                }
            } else if (name.equals("backdropPath")) {
                if (reader.peek() != JsonToken.NULL) {
                    objProxy.realmSet$backdropPath((String) reader.nextString());
                } else {
                    reader.skipValue();
                    objProxy.realmSet$backdropPath(null);
                }
            } else if (name.equals("adult")) {
                if (reader.peek() != JsonToken.NULL) {
                    objProxy.realmSet$adult((boolean) reader.nextBoolean());
                } else {
                    reader.skipValue();
                    throw new IllegalArgumentException("Trying to set non-nullable field 'adult' to null.");
                }
            } else if (name.equals("overview")) {
                if (reader.peek() != JsonToken.NULL) {
                    objProxy.realmSet$overview((String) reader.nextString());
                } else {
                    reader.skipValue();
                    objProxy.realmSet$overview(null);
                }
            } else if (name.equals("releaseDate")) {
                if (reader.peek() != JsonToken.NULL) {
                    objProxy.realmSet$releaseDate((String) reader.nextString());
                } else {
                    reader.skipValue();
                    objProxy.realmSet$releaseDate(null);
                }
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        if (!jsonHasPrimaryKey) {
            throw new IllegalArgumentException("JSON object doesn't have the primary key field 'id'.");
        }
        return realm.copyToRealm(obj);
    }

    public static myapp.nigam.com.mymoviesapp.models.MovieDetails copyOrUpdate(Realm realm, myapp.nigam.com.mymoviesapp.models.MovieDetails object, boolean update, Map<RealmModel,RealmObjectProxy> cache) {
        if (object instanceof RealmObjectProxy && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm() != null) {
            final BaseRealm otherRealm = ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm();
            if (otherRealm.threadId != realm.threadId) {
                throw new IllegalArgumentException("Objects which belong to Realm instances in other threads cannot be copied into this Realm instance.");
            }
            if (otherRealm.getPath().equals(realm.getPath())) {
                return object;
            }
        }
        final BaseRealm.RealmObjectContext objectContext = BaseRealm.objectContext.get();
        RealmObjectProxy cachedRealmObject = cache.get(object);
        if (cachedRealmObject != null) {
            return (myapp.nigam.com.mymoviesapp.models.MovieDetails) cachedRealmObject;
        }

        myapp.nigam.com.mymoviesapp.models.MovieDetails realmObject = null;
        boolean canUpdate = update;
        if (canUpdate) {
            Table table = realm.getTable(myapp.nigam.com.mymoviesapp.models.MovieDetails.class);
            MovieDetailsColumnInfo columnInfo = (MovieDetailsColumnInfo) realm.getSchema().getColumnInfo(myapp.nigam.com.mymoviesapp.models.MovieDetails.class);
            long pkColumnIndex = columnInfo.idIndex;
            long rowIndex = table.findFirstLong(pkColumnIndex, ((MovieDetailsRealmProxyInterface) object).realmGet$id());
            if (rowIndex == Table.NO_MATCH) {
                canUpdate = false;
            } else {
                try {
                    objectContext.set(realm, table.getUncheckedRow(rowIndex), realm.getSchema().getColumnInfo(myapp.nigam.com.mymoviesapp.models.MovieDetails.class), false, Collections.<String> emptyList());
                    realmObject = new io.realm.MovieDetailsRealmProxy();
                    cache.put(object, (RealmObjectProxy) realmObject);
                } finally {
                    objectContext.clear();
                }
            }
        }

        return (canUpdate) ? update(realm, realmObject, object, cache) : copy(realm, object, update, cache);
    }

    public static myapp.nigam.com.mymoviesapp.models.MovieDetails copy(Realm realm, myapp.nigam.com.mymoviesapp.models.MovieDetails newObject, boolean update, Map<RealmModel,RealmObjectProxy> cache) {
        RealmObjectProxy cachedRealmObject = cache.get(newObject);
        if (cachedRealmObject != null) {
            return (myapp.nigam.com.mymoviesapp.models.MovieDetails) cachedRealmObject;
        }

        // rejecting default values to avoid creating unexpected objects from RealmModel/RealmList fields.
        myapp.nigam.com.mymoviesapp.models.MovieDetails realmObject = realm.createObjectInternal(myapp.nigam.com.mymoviesapp.models.MovieDetails.class, ((MovieDetailsRealmProxyInterface) newObject).realmGet$id(), false, Collections.<String>emptyList());
        cache.put(newObject, (RealmObjectProxy) realmObject);

        MovieDetailsRealmProxyInterface realmObjectSource = (MovieDetailsRealmProxyInterface) newObject;
        MovieDetailsRealmProxyInterface realmObjectCopy = (MovieDetailsRealmProxyInterface) realmObject;

        realmObjectCopy.realmSet$voteCount(realmObjectSource.realmGet$voteCount());
        realmObjectCopy.realmSet$video(realmObjectSource.realmGet$video());
        realmObjectCopy.realmSet$voteAverage(realmObjectSource.realmGet$voteAverage());
        realmObjectCopy.realmSet$title(realmObjectSource.realmGet$title());
        realmObjectCopy.realmSet$popularity(realmObjectSource.realmGet$popularity());
        realmObjectCopy.realmSet$posterPath(realmObjectSource.realmGet$posterPath());
        realmObjectCopy.realmSet$originalLanguage(realmObjectSource.realmGet$originalLanguage());
        realmObjectCopy.realmSet$originalTitle(realmObjectSource.realmGet$originalTitle());
        realmObjectCopy.realmSet$backdropPath(realmObjectSource.realmGet$backdropPath());
        realmObjectCopy.realmSet$adult(realmObjectSource.realmGet$adult());
        realmObjectCopy.realmSet$overview(realmObjectSource.realmGet$overview());
        realmObjectCopy.realmSet$releaseDate(realmObjectSource.realmGet$releaseDate());
        return realmObject;
    }

    public static long insert(Realm realm, myapp.nigam.com.mymoviesapp.models.MovieDetails object, Map<RealmModel,Long> cache) {
        if (object instanceof RealmObjectProxy && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
            return ((RealmObjectProxy) object).realmGet$proxyState().getRow$realm().getIndex();
        }
        Table table = realm.getTable(myapp.nigam.com.mymoviesapp.models.MovieDetails.class);
        long tableNativePtr = table.getNativePtr();
        MovieDetailsColumnInfo columnInfo = (MovieDetailsColumnInfo) realm.getSchema().getColumnInfo(myapp.nigam.com.mymoviesapp.models.MovieDetails.class);
        long pkColumnIndex = columnInfo.idIndex;
        long rowIndex = Table.NO_MATCH;
        Object primaryKeyValue = ((MovieDetailsRealmProxyInterface) object).realmGet$id();
        if (primaryKeyValue != null) {
            rowIndex = Table.nativeFindFirstInt(tableNativePtr, pkColumnIndex, ((MovieDetailsRealmProxyInterface) object).realmGet$id());
        }
        if (rowIndex == Table.NO_MATCH) {
            rowIndex = OsObject.createRowWithPrimaryKey(table, pkColumnIndex, ((MovieDetailsRealmProxyInterface) object).realmGet$id());
        } else {
            Table.throwDuplicatePrimaryKeyException(primaryKeyValue);
        }
        cache.put(object, rowIndex);
        Table.nativeSetLong(tableNativePtr, columnInfo.voteCountIndex, rowIndex, ((MovieDetailsRealmProxyInterface) object).realmGet$voteCount(), false);
        Table.nativeSetBoolean(tableNativePtr, columnInfo.videoIndex, rowIndex, ((MovieDetailsRealmProxyInterface) object).realmGet$video(), false);
        Table.nativeSetFloat(tableNativePtr, columnInfo.voteAverageIndex, rowIndex, ((MovieDetailsRealmProxyInterface) object).realmGet$voteAverage(), false);
        String realmGet$title = ((MovieDetailsRealmProxyInterface) object).realmGet$title();
        if (realmGet$title != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.titleIndex, rowIndex, realmGet$title, false);
        }
        Table.nativeSetDouble(tableNativePtr, columnInfo.popularityIndex, rowIndex, ((MovieDetailsRealmProxyInterface) object).realmGet$popularity(), false);
        String realmGet$posterPath = ((MovieDetailsRealmProxyInterface) object).realmGet$posterPath();
        if (realmGet$posterPath != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.posterPathIndex, rowIndex, realmGet$posterPath, false);
        }
        String realmGet$originalLanguage = ((MovieDetailsRealmProxyInterface) object).realmGet$originalLanguage();
        if (realmGet$originalLanguage != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.originalLanguageIndex, rowIndex, realmGet$originalLanguage, false);
        }
        String realmGet$originalTitle = ((MovieDetailsRealmProxyInterface) object).realmGet$originalTitle();
        if (realmGet$originalTitle != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.originalTitleIndex, rowIndex, realmGet$originalTitle, false);
        }
        String realmGet$backdropPath = ((MovieDetailsRealmProxyInterface) object).realmGet$backdropPath();
        if (realmGet$backdropPath != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.backdropPathIndex, rowIndex, realmGet$backdropPath, false);
        }
        Table.nativeSetBoolean(tableNativePtr, columnInfo.adultIndex, rowIndex, ((MovieDetailsRealmProxyInterface) object).realmGet$adult(), false);
        String realmGet$overview = ((MovieDetailsRealmProxyInterface) object).realmGet$overview();
        if (realmGet$overview != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.overviewIndex, rowIndex, realmGet$overview, false);
        }
        String realmGet$releaseDate = ((MovieDetailsRealmProxyInterface) object).realmGet$releaseDate();
        if (realmGet$releaseDate != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.releaseDateIndex, rowIndex, realmGet$releaseDate, false);
        }
        return rowIndex;
    }

    public static void insert(Realm realm, Iterator<? extends RealmModel> objects, Map<RealmModel,Long> cache) {
        Table table = realm.getTable(myapp.nigam.com.mymoviesapp.models.MovieDetails.class);
        long tableNativePtr = table.getNativePtr();
        MovieDetailsColumnInfo columnInfo = (MovieDetailsColumnInfo) realm.getSchema().getColumnInfo(myapp.nigam.com.mymoviesapp.models.MovieDetails.class);
        long pkColumnIndex = columnInfo.idIndex;
        myapp.nigam.com.mymoviesapp.models.MovieDetails object = null;
        while (objects.hasNext()) {
            object = (myapp.nigam.com.mymoviesapp.models.MovieDetails) objects.next();
            if (cache.containsKey(object)) {
                continue;
            }
            if (object instanceof RealmObjectProxy && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
                cache.put(object, ((RealmObjectProxy) object).realmGet$proxyState().getRow$realm().getIndex());
                continue;
            }
            long rowIndex = Table.NO_MATCH;
            Object primaryKeyValue = ((MovieDetailsRealmProxyInterface) object).realmGet$id();
            if (primaryKeyValue != null) {
                rowIndex = Table.nativeFindFirstInt(tableNativePtr, pkColumnIndex, ((MovieDetailsRealmProxyInterface) object).realmGet$id());
            }
            if (rowIndex == Table.NO_MATCH) {
                rowIndex = OsObject.createRowWithPrimaryKey(table, pkColumnIndex, ((MovieDetailsRealmProxyInterface) object).realmGet$id());
            } else {
                Table.throwDuplicatePrimaryKeyException(primaryKeyValue);
            }
            cache.put(object, rowIndex);
            Table.nativeSetLong(tableNativePtr, columnInfo.voteCountIndex, rowIndex, ((MovieDetailsRealmProxyInterface) object).realmGet$voteCount(), false);
            Table.nativeSetBoolean(tableNativePtr, columnInfo.videoIndex, rowIndex, ((MovieDetailsRealmProxyInterface) object).realmGet$video(), false);
            Table.nativeSetFloat(tableNativePtr, columnInfo.voteAverageIndex, rowIndex, ((MovieDetailsRealmProxyInterface) object).realmGet$voteAverage(), false);
            String realmGet$title = ((MovieDetailsRealmProxyInterface) object).realmGet$title();
            if (realmGet$title != null) {
                Table.nativeSetString(tableNativePtr, columnInfo.titleIndex, rowIndex, realmGet$title, false);
            }
            Table.nativeSetDouble(tableNativePtr, columnInfo.popularityIndex, rowIndex, ((MovieDetailsRealmProxyInterface) object).realmGet$popularity(), false);
            String realmGet$posterPath = ((MovieDetailsRealmProxyInterface) object).realmGet$posterPath();
            if (realmGet$posterPath != null) {
                Table.nativeSetString(tableNativePtr, columnInfo.posterPathIndex, rowIndex, realmGet$posterPath, false);
            }
            String realmGet$originalLanguage = ((MovieDetailsRealmProxyInterface) object).realmGet$originalLanguage();
            if (realmGet$originalLanguage != null) {
                Table.nativeSetString(tableNativePtr, columnInfo.originalLanguageIndex, rowIndex, realmGet$originalLanguage, false);
            }
            String realmGet$originalTitle = ((MovieDetailsRealmProxyInterface) object).realmGet$originalTitle();
            if (realmGet$originalTitle != null) {
                Table.nativeSetString(tableNativePtr, columnInfo.originalTitleIndex, rowIndex, realmGet$originalTitle, false);
            }
            String realmGet$backdropPath = ((MovieDetailsRealmProxyInterface) object).realmGet$backdropPath();
            if (realmGet$backdropPath != null) {
                Table.nativeSetString(tableNativePtr, columnInfo.backdropPathIndex, rowIndex, realmGet$backdropPath, false);
            }
            Table.nativeSetBoolean(tableNativePtr, columnInfo.adultIndex, rowIndex, ((MovieDetailsRealmProxyInterface) object).realmGet$adult(), false);
            String realmGet$overview = ((MovieDetailsRealmProxyInterface) object).realmGet$overview();
            if (realmGet$overview != null) {
                Table.nativeSetString(tableNativePtr, columnInfo.overviewIndex, rowIndex, realmGet$overview, false);
            }
            String realmGet$releaseDate = ((MovieDetailsRealmProxyInterface) object).realmGet$releaseDate();
            if (realmGet$releaseDate != null) {
                Table.nativeSetString(tableNativePtr, columnInfo.releaseDateIndex, rowIndex, realmGet$releaseDate, false);
            }
        }
    }

    public static long insertOrUpdate(Realm realm, myapp.nigam.com.mymoviesapp.models.MovieDetails object, Map<RealmModel,Long> cache) {
        if (object instanceof RealmObjectProxy && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
            return ((RealmObjectProxy) object).realmGet$proxyState().getRow$realm().getIndex();
        }
        Table table = realm.getTable(myapp.nigam.com.mymoviesapp.models.MovieDetails.class);
        long tableNativePtr = table.getNativePtr();
        MovieDetailsColumnInfo columnInfo = (MovieDetailsColumnInfo) realm.getSchema().getColumnInfo(myapp.nigam.com.mymoviesapp.models.MovieDetails.class);
        long pkColumnIndex = columnInfo.idIndex;
        long rowIndex = Table.NO_MATCH;
        Object primaryKeyValue = ((MovieDetailsRealmProxyInterface) object).realmGet$id();
        if (primaryKeyValue != null) {
            rowIndex = Table.nativeFindFirstInt(tableNativePtr, pkColumnIndex, ((MovieDetailsRealmProxyInterface) object).realmGet$id());
        }
        if (rowIndex == Table.NO_MATCH) {
            rowIndex = OsObject.createRowWithPrimaryKey(table, pkColumnIndex, ((MovieDetailsRealmProxyInterface) object).realmGet$id());
        }
        cache.put(object, rowIndex);
        Table.nativeSetLong(tableNativePtr, columnInfo.voteCountIndex, rowIndex, ((MovieDetailsRealmProxyInterface) object).realmGet$voteCount(), false);
        Table.nativeSetBoolean(tableNativePtr, columnInfo.videoIndex, rowIndex, ((MovieDetailsRealmProxyInterface) object).realmGet$video(), false);
        Table.nativeSetFloat(tableNativePtr, columnInfo.voteAverageIndex, rowIndex, ((MovieDetailsRealmProxyInterface) object).realmGet$voteAverage(), false);
        String realmGet$title = ((MovieDetailsRealmProxyInterface) object).realmGet$title();
        if (realmGet$title != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.titleIndex, rowIndex, realmGet$title, false);
        } else {
            Table.nativeSetNull(tableNativePtr, columnInfo.titleIndex, rowIndex, false);
        }
        Table.nativeSetDouble(tableNativePtr, columnInfo.popularityIndex, rowIndex, ((MovieDetailsRealmProxyInterface) object).realmGet$popularity(), false);
        String realmGet$posterPath = ((MovieDetailsRealmProxyInterface) object).realmGet$posterPath();
        if (realmGet$posterPath != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.posterPathIndex, rowIndex, realmGet$posterPath, false);
        } else {
            Table.nativeSetNull(tableNativePtr, columnInfo.posterPathIndex, rowIndex, false);
        }
        String realmGet$originalLanguage = ((MovieDetailsRealmProxyInterface) object).realmGet$originalLanguage();
        if (realmGet$originalLanguage != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.originalLanguageIndex, rowIndex, realmGet$originalLanguage, false);
        } else {
            Table.nativeSetNull(tableNativePtr, columnInfo.originalLanguageIndex, rowIndex, false);
        }
        String realmGet$originalTitle = ((MovieDetailsRealmProxyInterface) object).realmGet$originalTitle();
        if (realmGet$originalTitle != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.originalTitleIndex, rowIndex, realmGet$originalTitle, false);
        } else {
            Table.nativeSetNull(tableNativePtr, columnInfo.originalTitleIndex, rowIndex, false);
        }
        String realmGet$backdropPath = ((MovieDetailsRealmProxyInterface) object).realmGet$backdropPath();
        if (realmGet$backdropPath != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.backdropPathIndex, rowIndex, realmGet$backdropPath, false);
        } else {
            Table.nativeSetNull(tableNativePtr, columnInfo.backdropPathIndex, rowIndex, false);
        }
        Table.nativeSetBoolean(tableNativePtr, columnInfo.adultIndex, rowIndex, ((MovieDetailsRealmProxyInterface) object).realmGet$adult(), false);
        String realmGet$overview = ((MovieDetailsRealmProxyInterface) object).realmGet$overview();
        if (realmGet$overview != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.overviewIndex, rowIndex, realmGet$overview, false);
        } else {
            Table.nativeSetNull(tableNativePtr, columnInfo.overviewIndex, rowIndex, false);
        }
        String realmGet$releaseDate = ((MovieDetailsRealmProxyInterface) object).realmGet$releaseDate();
        if (realmGet$releaseDate != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.releaseDateIndex, rowIndex, realmGet$releaseDate, false);
        } else {
            Table.nativeSetNull(tableNativePtr, columnInfo.releaseDateIndex, rowIndex, false);
        }
        return rowIndex;
    }

    public static void insertOrUpdate(Realm realm, Iterator<? extends RealmModel> objects, Map<RealmModel,Long> cache) {
        Table table = realm.getTable(myapp.nigam.com.mymoviesapp.models.MovieDetails.class);
        long tableNativePtr = table.getNativePtr();
        MovieDetailsColumnInfo columnInfo = (MovieDetailsColumnInfo) realm.getSchema().getColumnInfo(myapp.nigam.com.mymoviesapp.models.MovieDetails.class);
        long pkColumnIndex = columnInfo.idIndex;
        myapp.nigam.com.mymoviesapp.models.MovieDetails object = null;
        while (objects.hasNext()) {
            object = (myapp.nigam.com.mymoviesapp.models.MovieDetails) objects.next();
            if (cache.containsKey(object)) {
                continue;
            }
            if (object instanceof RealmObjectProxy && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
                cache.put(object, ((RealmObjectProxy) object).realmGet$proxyState().getRow$realm().getIndex());
                continue;
            }
            long rowIndex = Table.NO_MATCH;
            Object primaryKeyValue = ((MovieDetailsRealmProxyInterface) object).realmGet$id();
            if (primaryKeyValue != null) {
                rowIndex = Table.nativeFindFirstInt(tableNativePtr, pkColumnIndex, ((MovieDetailsRealmProxyInterface) object).realmGet$id());
            }
            if (rowIndex == Table.NO_MATCH) {
                rowIndex = OsObject.createRowWithPrimaryKey(table, pkColumnIndex, ((MovieDetailsRealmProxyInterface) object).realmGet$id());
            }
            cache.put(object, rowIndex);
            Table.nativeSetLong(tableNativePtr, columnInfo.voteCountIndex, rowIndex, ((MovieDetailsRealmProxyInterface) object).realmGet$voteCount(), false);
            Table.nativeSetBoolean(tableNativePtr, columnInfo.videoIndex, rowIndex, ((MovieDetailsRealmProxyInterface) object).realmGet$video(), false);
            Table.nativeSetFloat(tableNativePtr, columnInfo.voteAverageIndex, rowIndex, ((MovieDetailsRealmProxyInterface) object).realmGet$voteAverage(), false);
            String realmGet$title = ((MovieDetailsRealmProxyInterface) object).realmGet$title();
            if (realmGet$title != null) {
                Table.nativeSetString(tableNativePtr, columnInfo.titleIndex, rowIndex, realmGet$title, false);
            } else {
                Table.nativeSetNull(tableNativePtr, columnInfo.titleIndex, rowIndex, false);
            }
            Table.nativeSetDouble(tableNativePtr, columnInfo.popularityIndex, rowIndex, ((MovieDetailsRealmProxyInterface) object).realmGet$popularity(), false);
            String realmGet$posterPath = ((MovieDetailsRealmProxyInterface) object).realmGet$posterPath();
            if (realmGet$posterPath != null) {
                Table.nativeSetString(tableNativePtr, columnInfo.posterPathIndex, rowIndex, realmGet$posterPath, false);
            } else {
                Table.nativeSetNull(tableNativePtr, columnInfo.posterPathIndex, rowIndex, false);
            }
            String realmGet$originalLanguage = ((MovieDetailsRealmProxyInterface) object).realmGet$originalLanguage();
            if (realmGet$originalLanguage != null) {
                Table.nativeSetString(tableNativePtr, columnInfo.originalLanguageIndex, rowIndex, realmGet$originalLanguage, false);
            } else {
                Table.nativeSetNull(tableNativePtr, columnInfo.originalLanguageIndex, rowIndex, false);
            }
            String realmGet$originalTitle = ((MovieDetailsRealmProxyInterface) object).realmGet$originalTitle();
            if (realmGet$originalTitle != null) {
                Table.nativeSetString(tableNativePtr, columnInfo.originalTitleIndex, rowIndex, realmGet$originalTitle, false);
            } else {
                Table.nativeSetNull(tableNativePtr, columnInfo.originalTitleIndex, rowIndex, false);
            }
            String realmGet$backdropPath = ((MovieDetailsRealmProxyInterface) object).realmGet$backdropPath();
            if (realmGet$backdropPath != null) {
                Table.nativeSetString(tableNativePtr, columnInfo.backdropPathIndex, rowIndex, realmGet$backdropPath, false);
            } else {
                Table.nativeSetNull(tableNativePtr, columnInfo.backdropPathIndex, rowIndex, false);
            }
            Table.nativeSetBoolean(tableNativePtr, columnInfo.adultIndex, rowIndex, ((MovieDetailsRealmProxyInterface) object).realmGet$adult(), false);
            String realmGet$overview = ((MovieDetailsRealmProxyInterface) object).realmGet$overview();
            if (realmGet$overview != null) {
                Table.nativeSetString(tableNativePtr, columnInfo.overviewIndex, rowIndex, realmGet$overview, false);
            } else {
                Table.nativeSetNull(tableNativePtr, columnInfo.overviewIndex, rowIndex, false);
            }
            String realmGet$releaseDate = ((MovieDetailsRealmProxyInterface) object).realmGet$releaseDate();
            if (realmGet$releaseDate != null) {
                Table.nativeSetString(tableNativePtr, columnInfo.releaseDateIndex, rowIndex, realmGet$releaseDate, false);
            } else {
                Table.nativeSetNull(tableNativePtr, columnInfo.releaseDateIndex, rowIndex, false);
            }
        }
    }

    public static myapp.nigam.com.mymoviesapp.models.MovieDetails createDetachedCopy(myapp.nigam.com.mymoviesapp.models.MovieDetails realmObject, int currentDepth, int maxDepth, Map<RealmModel, CacheData<RealmModel>> cache) {
        if (currentDepth > maxDepth || realmObject == null) {
            return null;
        }
        CacheData<RealmModel> cachedObject = cache.get(realmObject);
        myapp.nigam.com.mymoviesapp.models.MovieDetails unmanagedObject;
        if (cachedObject == null) {
            unmanagedObject = new myapp.nigam.com.mymoviesapp.models.MovieDetails();
            cache.put(realmObject, new RealmObjectProxy.CacheData<RealmModel>(currentDepth, unmanagedObject));
        } else {
            // Reuse cached object or recreate it because it was encountered at a lower depth.
            if (currentDepth >= cachedObject.minDepth) {
                return (myapp.nigam.com.mymoviesapp.models.MovieDetails) cachedObject.object;
            }
            unmanagedObject = (myapp.nigam.com.mymoviesapp.models.MovieDetails) cachedObject.object;
            cachedObject.minDepth = currentDepth;
        }
        MovieDetailsRealmProxyInterface unmanagedCopy = (MovieDetailsRealmProxyInterface) unmanagedObject;
        MovieDetailsRealmProxyInterface realmSource = (MovieDetailsRealmProxyInterface) realmObject;
        unmanagedCopy.realmSet$id(realmSource.realmGet$id());
        unmanagedCopy.realmSet$voteCount(realmSource.realmGet$voteCount());
        unmanagedCopy.realmSet$video(realmSource.realmGet$video());
        unmanagedCopy.realmSet$voteAverage(realmSource.realmGet$voteAverage());
        unmanagedCopy.realmSet$title(realmSource.realmGet$title());
        unmanagedCopy.realmSet$popularity(realmSource.realmGet$popularity());
        unmanagedCopy.realmSet$posterPath(realmSource.realmGet$posterPath());
        unmanagedCopy.realmSet$originalLanguage(realmSource.realmGet$originalLanguage());
        unmanagedCopy.realmSet$originalTitle(realmSource.realmGet$originalTitle());
        unmanagedCopy.realmSet$backdropPath(realmSource.realmGet$backdropPath());
        unmanagedCopy.realmSet$adult(realmSource.realmGet$adult());
        unmanagedCopy.realmSet$overview(realmSource.realmGet$overview());
        unmanagedCopy.realmSet$releaseDate(realmSource.realmGet$releaseDate());

        return unmanagedObject;
    }

    static myapp.nigam.com.mymoviesapp.models.MovieDetails update(Realm realm, myapp.nigam.com.mymoviesapp.models.MovieDetails realmObject, myapp.nigam.com.mymoviesapp.models.MovieDetails newObject, Map<RealmModel, RealmObjectProxy> cache) {
        MovieDetailsRealmProxyInterface realmObjectTarget = (MovieDetailsRealmProxyInterface) realmObject;
        MovieDetailsRealmProxyInterface realmObjectSource = (MovieDetailsRealmProxyInterface) newObject;
        realmObjectTarget.realmSet$voteCount(realmObjectSource.realmGet$voteCount());
        realmObjectTarget.realmSet$video(realmObjectSource.realmGet$video());
        realmObjectTarget.realmSet$voteAverage(realmObjectSource.realmGet$voteAverage());
        realmObjectTarget.realmSet$title(realmObjectSource.realmGet$title());
        realmObjectTarget.realmSet$popularity(realmObjectSource.realmGet$popularity());
        realmObjectTarget.realmSet$posterPath(realmObjectSource.realmGet$posterPath());
        realmObjectTarget.realmSet$originalLanguage(realmObjectSource.realmGet$originalLanguage());
        realmObjectTarget.realmSet$originalTitle(realmObjectSource.realmGet$originalTitle());
        realmObjectTarget.realmSet$backdropPath(realmObjectSource.realmGet$backdropPath());
        realmObjectTarget.realmSet$adult(realmObjectSource.realmGet$adult());
        realmObjectTarget.realmSet$overview(realmObjectSource.realmGet$overview());
        realmObjectTarget.realmSet$releaseDate(realmObjectSource.realmGet$releaseDate());
        return realmObject;
    }

    @Override
    @SuppressWarnings("ArrayToString")
    public String toString() {
        if (!RealmObject.isValid(this)) {
            return "Invalid object";
        }
        StringBuilder stringBuilder = new StringBuilder("MovieDetails = proxy[");
        stringBuilder.append("{id:");
        stringBuilder.append(realmGet$id());
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{voteCount:");
        stringBuilder.append(realmGet$voteCount());
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{video:");
        stringBuilder.append(realmGet$video());
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{voteAverage:");
        stringBuilder.append(realmGet$voteAverage());
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{title:");
        stringBuilder.append(realmGet$title() != null ? realmGet$title() : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{popularity:");
        stringBuilder.append(realmGet$popularity());
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{posterPath:");
        stringBuilder.append(realmGet$posterPath() != null ? realmGet$posterPath() : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{originalLanguage:");
        stringBuilder.append(realmGet$originalLanguage() != null ? realmGet$originalLanguage() : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{originalTitle:");
        stringBuilder.append(realmGet$originalTitle() != null ? realmGet$originalTitle() : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{backdropPath:");
        stringBuilder.append(realmGet$backdropPath() != null ? realmGet$backdropPath() : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{adult:");
        stringBuilder.append(realmGet$adult());
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{overview:");
        stringBuilder.append(realmGet$overview() != null ? realmGet$overview() : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{releaseDate:");
        stringBuilder.append(realmGet$releaseDate() != null ? realmGet$releaseDate() : "null");
        stringBuilder.append("}");
        stringBuilder.append("]");
        return stringBuilder.toString();
    }

    @Override
    public ProxyState<?> realmGet$proxyState() {
        return proxyState;
    }

    @Override
    public int hashCode() {
        String realmName = proxyState.getRealm$realm().getPath();
        String tableName = proxyState.getRow$realm().getTable().getName();
        long rowIndex = proxyState.getRow$realm().getIndex();

        int result = 17;
        result = 31 * result + ((realmName != null) ? realmName.hashCode() : 0);
        result = 31 * result + ((tableName != null) ? tableName.hashCode() : 0);
        result = 31 * result + (int) (rowIndex ^ (rowIndex >>> 32));
        return result;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MovieDetailsRealmProxy aMovieDetails = (MovieDetailsRealmProxy)o;

        String path = proxyState.getRealm$realm().getPath();
        String otherPath = aMovieDetails.proxyState.getRealm$realm().getPath();
        if (path != null ? !path.equals(otherPath) : otherPath != null) return false;

        String tableName = proxyState.getRow$realm().getTable().getName();
        String otherTableName = aMovieDetails.proxyState.getRow$realm().getTable().getName();
        if (tableName != null ? !tableName.equals(otherTableName) : otherTableName != null) return false;

        if (proxyState.getRow$realm().getIndex() != aMovieDetails.proxyState.getRow$realm().getIndex()) return false;

        return true;
    }
}
