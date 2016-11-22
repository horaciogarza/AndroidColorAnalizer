package io.realm;


import android.annotation.TargetApi;
import android.os.Build;
import android.util.JsonReader;
import android.util.JsonToken;
import io.realm.RealmObjectSchema;
import io.realm.RealmSchema;
import io.realm.exceptions.RealmMigrationNeededException;
import io.realm.internal.ColumnInfo;
import io.realm.internal.LinkView;
import io.realm.internal.RealmObjectProxy;
import io.realm.internal.Row;
import io.realm.internal.SharedRealm;
import io.realm.internal.Table;
import io.realm.internal.TableOrView;
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

public class HistoryRealmRealmProxy extends com.example.hernanlopez.proyecto.HistoryRealm
    implements RealmObjectProxy, HistoryRealmRealmProxyInterface {

    static final class HistoryRealmColumnInfo extends ColumnInfo
        implements Cloneable {

        public long colorNameIndex;
        public long hexValueIndex;
        public long rgbValueIndex;
        public long isFavIndex;

        HistoryRealmColumnInfo(String path, Table table) {
            final Map<String, Long> indicesMap = new HashMap<String, Long>(4);
            this.colorNameIndex = getValidColumnIndex(path, table, "HistoryRealm", "colorName");
            indicesMap.put("colorName", this.colorNameIndex);
            this.hexValueIndex = getValidColumnIndex(path, table, "HistoryRealm", "hexValue");
            indicesMap.put("hexValue", this.hexValueIndex);
            this.rgbValueIndex = getValidColumnIndex(path, table, "HistoryRealm", "rgbValue");
            indicesMap.put("rgbValue", this.rgbValueIndex);
            this.isFavIndex = getValidColumnIndex(path, table, "HistoryRealm", "isFav");
            indicesMap.put("isFav", this.isFavIndex);

            setIndicesMap(indicesMap);
        }

        @Override
        public final void copyColumnInfoFrom(ColumnInfo other) {
            final HistoryRealmColumnInfo otherInfo = (HistoryRealmColumnInfo) other;
            this.colorNameIndex = otherInfo.colorNameIndex;
            this.hexValueIndex = otherInfo.hexValueIndex;
            this.rgbValueIndex = otherInfo.rgbValueIndex;
            this.isFavIndex = otherInfo.isFavIndex;

            setIndicesMap(otherInfo.getIndicesMap());
        }

        @Override
        public final HistoryRealmColumnInfo clone() {
            return (HistoryRealmColumnInfo) super.clone();
        }

    }
    private HistoryRealmColumnInfo columnInfo;
    private ProxyState proxyState;
    private static final List<String> FIELD_NAMES;
    static {
        List<String> fieldNames = new ArrayList<String>();
        fieldNames.add("colorName");
        fieldNames.add("hexValue");
        fieldNames.add("rgbValue");
        fieldNames.add("isFav");
        FIELD_NAMES = Collections.unmodifiableList(fieldNames);
    }

    HistoryRealmRealmProxy() {
        if (proxyState == null) {
            injectObjectContext();
        }
        proxyState.setConstructionFinished();
    }

    private void injectObjectContext() {
        final BaseRealm.RealmObjectContext context = BaseRealm.objectContext.get();
        this.columnInfo = (HistoryRealmColumnInfo) context.getColumnInfo();
        this.proxyState = new ProxyState(com.example.hernanlopez.proyecto.HistoryRealm.class, this);
        proxyState.setRealm$realm(context.getRealm());
        proxyState.setRow$realm(context.getRow());
        proxyState.setAcceptDefaultValue$realm(context.getAcceptDefaultValue());
        proxyState.setExcludeFields$realm(context.getExcludeFields());
    }

    @SuppressWarnings("cast")
    public String realmGet$colorName() {
        if (proxyState == null) {
            // Called from model's constructor. Inject context.
            injectObjectContext();
        }

        proxyState.getRealm$realm().checkIfValid();
        return (java.lang.String) proxyState.getRow$realm().getString(columnInfo.colorNameIndex);
    }

    public void realmSet$colorName(String value) {
        if (proxyState == null) {
            // Called from model's constructor. Inject context.
            injectObjectContext();
        }

        if (proxyState.isUnderConstruction()) {
            if (!proxyState.getAcceptDefaultValue$realm()) {
                return;
            }
            final Row row = proxyState.getRow$realm();
            if (value == null) {
                row.getTable().setNull(columnInfo.colorNameIndex, row.getIndex(), true);
                return;
            }
            row.getTable().setString(columnInfo.colorNameIndex, row.getIndex(), value, true);
            return;
        }

        proxyState.getRealm$realm().checkIfValid();
        if (value == null) {
            proxyState.getRow$realm().setNull(columnInfo.colorNameIndex);
            return;
        }
        proxyState.getRow$realm().setString(columnInfo.colorNameIndex, value);
    }

    @SuppressWarnings("cast")
    public String realmGet$hexValue() {
        if (proxyState == null) {
            // Called from model's constructor. Inject context.
            injectObjectContext();
        }

        proxyState.getRealm$realm().checkIfValid();
        return (java.lang.String) proxyState.getRow$realm().getString(columnInfo.hexValueIndex);
    }

    public void realmSet$hexValue(String value) {
        if (proxyState == null) {
            // Called from model's constructor. Inject context.
            injectObjectContext();
        }

        if (proxyState.isUnderConstruction()) {
            if (!proxyState.getAcceptDefaultValue$realm()) {
                return;
            }
            final Row row = proxyState.getRow$realm();
            if (value == null) {
                row.getTable().setNull(columnInfo.hexValueIndex, row.getIndex(), true);
                return;
            }
            row.getTable().setString(columnInfo.hexValueIndex, row.getIndex(), value, true);
            return;
        }

        proxyState.getRealm$realm().checkIfValid();
        if (value == null) {
            proxyState.getRow$realm().setNull(columnInfo.hexValueIndex);
            return;
        }
        proxyState.getRow$realm().setString(columnInfo.hexValueIndex, value);
    }

    @SuppressWarnings("cast")
    public String realmGet$rgbValue() {
        if (proxyState == null) {
            // Called from model's constructor. Inject context.
            injectObjectContext();
        }

        proxyState.getRealm$realm().checkIfValid();
        return (java.lang.String) proxyState.getRow$realm().getString(columnInfo.rgbValueIndex);
    }

    public void realmSet$rgbValue(String value) {
        if (proxyState == null) {
            // Called from model's constructor. Inject context.
            injectObjectContext();
        }

        if (proxyState.isUnderConstruction()) {
            if (!proxyState.getAcceptDefaultValue$realm()) {
                return;
            }
            final Row row = proxyState.getRow$realm();
            if (value == null) {
                row.getTable().setNull(columnInfo.rgbValueIndex, row.getIndex(), true);
                return;
            }
            row.getTable().setString(columnInfo.rgbValueIndex, row.getIndex(), value, true);
            return;
        }

        proxyState.getRealm$realm().checkIfValid();
        if (value == null) {
            proxyState.getRow$realm().setNull(columnInfo.rgbValueIndex);
            return;
        }
        proxyState.getRow$realm().setString(columnInfo.rgbValueIndex, value);
    }

    @SuppressWarnings("cast")
    public boolean realmGet$isFav() {
        if (proxyState == null) {
            // Called from model's constructor. Inject context.
            injectObjectContext();
        }

        proxyState.getRealm$realm().checkIfValid();
        return (boolean) proxyState.getRow$realm().getBoolean(columnInfo.isFavIndex);
    }

    public void realmSet$isFav(boolean value) {
        if (proxyState == null) {
            // Called from model's constructor. Inject context.
            injectObjectContext();
        }

        if (proxyState.isUnderConstruction()) {
            if (!proxyState.getAcceptDefaultValue$realm()) {
                return;
            }
            final Row row = proxyState.getRow$realm();
            row.getTable().setBoolean(columnInfo.isFavIndex, row.getIndex(), value, true);
            return;
        }

        proxyState.getRealm$realm().checkIfValid();
        proxyState.getRow$realm().setBoolean(columnInfo.isFavIndex, value);
    }

    public static RealmObjectSchema createRealmObjectSchema(RealmSchema realmSchema) {
        if (!realmSchema.contains("HistoryRealm")) {
            RealmObjectSchema realmObjectSchema = realmSchema.create("HistoryRealm");
            realmObjectSchema.add(new Property("colorName", RealmFieldType.STRING, !Property.PRIMARY_KEY, !Property.INDEXED, !Property.REQUIRED));
            realmObjectSchema.add(new Property("hexValue", RealmFieldType.STRING, !Property.PRIMARY_KEY, !Property.INDEXED, !Property.REQUIRED));
            realmObjectSchema.add(new Property("rgbValue", RealmFieldType.STRING, !Property.PRIMARY_KEY, !Property.INDEXED, !Property.REQUIRED));
            realmObjectSchema.add(new Property("isFav", RealmFieldType.BOOLEAN, !Property.PRIMARY_KEY, !Property.INDEXED, Property.REQUIRED));
            return realmObjectSchema;
        }
        return realmSchema.get("HistoryRealm");
    }

    public static Table initTable(SharedRealm sharedRealm) {
        if (!sharedRealm.hasTable("class_HistoryRealm")) {
            Table table = sharedRealm.getTable("class_HistoryRealm");
            table.addColumn(RealmFieldType.STRING, "colorName", Table.NULLABLE);
            table.addColumn(RealmFieldType.STRING, "hexValue", Table.NULLABLE);
            table.addColumn(RealmFieldType.STRING, "rgbValue", Table.NULLABLE);
            table.addColumn(RealmFieldType.BOOLEAN, "isFav", Table.NOT_NULLABLE);
            table.setPrimaryKey("");
            return table;
        }
        return sharedRealm.getTable("class_HistoryRealm");
    }

    public static HistoryRealmColumnInfo validateTable(SharedRealm sharedRealm, boolean allowExtraColumns) {
        if (sharedRealm.hasTable("class_HistoryRealm")) {
            Table table = sharedRealm.getTable("class_HistoryRealm");
            final long columnCount = table.getColumnCount();
            if (columnCount != 4) {
                if (columnCount < 4) {
                    throw new RealmMigrationNeededException(sharedRealm.getPath(), "Field count is less than expected - expected 4 but was " + columnCount);
                }
                if (allowExtraColumns) {
                    RealmLog.debug("Field count is more than expected - expected 4 but was %1$d", columnCount);
                } else {
                    throw new RealmMigrationNeededException(sharedRealm.getPath(), "Field count is more than expected - expected 4 but was " + columnCount);
                }
            }
            Map<String, RealmFieldType> columnTypes = new HashMap<String, RealmFieldType>();
            for (long i = 0; i < columnCount; i++) {
                columnTypes.put(table.getColumnName(i), table.getColumnType(i));
            }

            final HistoryRealmColumnInfo columnInfo = new HistoryRealmColumnInfo(sharedRealm.getPath(), table);

            if (!columnTypes.containsKey("colorName")) {
                throw new RealmMigrationNeededException(sharedRealm.getPath(), "Missing field 'colorName' in existing Realm file. Either remove field or migrate using io.realm.internal.Table.addColumn().");
            }
            if (columnTypes.get("colorName") != RealmFieldType.STRING) {
                throw new RealmMigrationNeededException(sharedRealm.getPath(), "Invalid type 'String' for field 'colorName' in existing Realm file.");
            }
            if (!table.isColumnNullable(columnInfo.colorNameIndex)) {
                throw new RealmMigrationNeededException(sharedRealm.getPath(), "Field 'colorName' is required. Either set @Required to field 'colorName' or migrate using RealmObjectSchema.setNullable().");
            }
            if (!columnTypes.containsKey("hexValue")) {
                throw new RealmMigrationNeededException(sharedRealm.getPath(), "Missing field 'hexValue' in existing Realm file. Either remove field or migrate using io.realm.internal.Table.addColumn().");
            }
            if (columnTypes.get("hexValue") != RealmFieldType.STRING) {
                throw new RealmMigrationNeededException(sharedRealm.getPath(), "Invalid type 'String' for field 'hexValue' in existing Realm file.");
            }
            if (!table.isColumnNullable(columnInfo.hexValueIndex)) {
                throw new RealmMigrationNeededException(sharedRealm.getPath(), "Field 'hexValue' is required. Either set @Required to field 'hexValue' or migrate using RealmObjectSchema.setNullable().");
            }
            if (!columnTypes.containsKey("rgbValue")) {
                throw new RealmMigrationNeededException(sharedRealm.getPath(), "Missing field 'rgbValue' in existing Realm file. Either remove field or migrate using io.realm.internal.Table.addColumn().");
            }
            if (columnTypes.get("rgbValue") != RealmFieldType.STRING) {
                throw new RealmMigrationNeededException(sharedRealm.getPath(), "Invalid type 'String' for field 'rgbValue' in existing Realm file.");
            }
            if (!table.isColumnNullable(columnInfo.rgbValueIndex)) {
                throw new RealmMigrationNeededException(sharedRealm.getPath(), "Field 'rgbValue' is required. Either set @Required to field 'rgbValue' or migrate using RealmObjectSchema.setNullable().");
            }
            if (!columnTypes.containsKey("isFav")) {
                throw new RealmMigrationNeededException(sharedRealm.getPath(), "Missing field 'isFav' in existing Realm file. Either remove field or migrate using io.realm.internal.Table.addColumn().");
            }
            if (columnTypes.get("isFav") != RealmFieldType.BOOLEAN) {
                throw new RealmMigrationNeededException(sharedRealm.getPath(), "Invalid type 'boolean' for field 'isFav' in existing Realm file.");
            }
            if (table.isColumnNullable(columnInfo.isFavIndex)) {
                throw new RealmMigrationNeededException(sharedRealm.getPath(), "Field 'isFav' does support null values in the existing Realm file. Use corresponding boxed type for field 'isFav' or migrate using RealmObjectSchema.setNullable().");
            }
            return columnInfo;
        } else {
            throw new RealmMigrationNeededException(sharedRealm.getPath(), "The 'HistoryRealm' class is missing from the schema for this Realm.");
        }
    }

    public static String getTableName() {
        return "class_HistoryRealm";
    }

    public static List<String> getFieldNames() {
        return FIELD_NAMES;
    }

    @SuppressWarnings("cast")
    public static com.example.hernanlopez.proyecto.HistoryRealm createOrUpdateUsingJsonObject(Realm realm, JSONObject json, boolean update)
        throws JSONException {
        final List<String> excludeFields = Collections.<String> emptyList();
        com.example.hernanlopez.proyecto.HistoryRealm obj = realm.createObjectInternal(com.example.hernanlopez.proyecto.HistoryRealm.class, true, excludeFields);
        if (json.has("colorName")) {
            if (json.isNull("colorName")) {
                ((HistoryRealmRealmProxyInterface) obj).realmSet$colorName(null);
            } else {
                ((HistoryRealmRealmProxyInterface) obj).realmSet$colorName((String) json.getString("colorName"));
            }
        }
        if (json.has("hexValue")) {
            if (json.isNull("hexValue")) {
                ((HistoryRealmRealmProxyInterface) obj).realmSet$hexValue(null);
            } else {
                ((HistoryRealmRealmProxyInterface) obj).realmSet$hexValue((String) json.getString("hexValue"));
            }
        }
        if (json.has("rgbValue")) {
            if (json.isNull("rgbValue")) {
                ((HistoryRealmRealmProxyInterface) obj).realmSet$rgbValue(null);
            } else {
                ((HistoryRealmRealmProxyInterface) obj).realmSet$rgbValue((String) json.getString("rgbValue"));
            }
        }
        if (json.has("isFav")) {
            if (json.isNull("isFav")) {
                throw new IllegalArgumentException("Trying to set non-nullable field 'isFav' to null.");
            } else {
                ((HistoryRealmRealmProxyInterface) obj).realmSet$isFav((boolean) json.getBoolean("isFav"));
            }
        }
        return obj;
    }

    @SuppressWarnings("cast")
    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    public static com.example.hernanlopez.proyecto.HistoryRealm createUsingJsonStream(Realm realm, JsonReader reader)
        throws IOException {
        com.example.hernanlopez.proyecto.HistoryRealm obj = new com.example.hernanlopez.proyecto.HistoryRealm();
        reader.beginObject();
        while (reader.hasNext()) {
            String name = reader.nextName();
            if (name.equals("colorName")) {
                if (reader.peek() == JsonToken.NULL) {
                    reader.skipValue();
                    ((HistoryRealmRealmProxyInterface) obj).realmSet$colorName(null);
                } else {
                    ((HistoryRealmRealmProxyInterface) obj).realmSet$colorName((String) reader.nextString());
                }
            } else if (name.equals("hexValue")) {
                if (reader.peek() == JsonToken.NULL) {
                    reader.skipValue();
                    ((HistoryRealmRealmProxyInterface) obj).realmSet$hexValue(null);
                } else {
                    ((HistoryRealmRealmProxyInterface) obj).realmSet$hexValue((String) reader.nextString());
                }
            } else if (name.equals("rgbValue")) {
                if (reader.peek() == JsonToken.NULL) {
                    reader.skipValue();
                    ((HistoryRealmRealmProxyInterface) obj).realmSet$rgbValue(null);
                } else {
                    ((HistoryRealmRealmProxyInterface) obj).realmSet$rgbValue((String) reader.nextString());
                }
            } else if (name.equals("isFav")) {
                if (reader.peek() == JsonToken.NULL) {
                    reader.skipValue();
                    throw new IllegalArgumentException("Trying to set non-nullable field 'isFav' to null.");
                } else {
                    ((HistoryRealmRealmProxyInterface) obj).realmSet$isFav((boolean) reader.nextBoolean());
                }
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        obj = realm.copyToRealm(obj);
        return obj;
    }

    public static com.example.hernanlopez.proyecto.HistoryRealm copyOrUpdate(Realm realm, com.example.hernanlopez.proyecto.HistoryRealm object, boolean update, Map<RealmModel,RealmObjectProxy> cache) {
        if (object instanceof RealmObjectProxy && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm().threadId != realm.threadId) {
            throw new IllegalArgumentException("Objects which belong to Realm instances in other threads cannot be copied into this Realm instance.");
        }
        if (object instanceof RealmObjectProxy && ((RealmObjectProxy)object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy)object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
            return object;
        }
        final BaseRealm.RealmObjectContext objectContext = BaseRealm.objectContext.get();
        RealmObjectProxy cachedRealmObject = cache.get(object);
        if (cachedRealmObject != null) {
            return (com.example.hernanlopez.proyecto.HistoryRealm) cachedRealmObject;
        } else {
            return copy(realm, object, update, cache);
        }
    }

    public static com.example.hernanlopez.proyecto.HistoryRealm copy(Realm realm, com.example.hernanlopez.proyecto.HistoryRealm newObject, boolean update, Map<RealmModel,RealmObjectProxy> cache) {
        RealmObjectProxy cachedRealmObject = cache.get(newObject);
        if (cachedRealmObject != null) {
            return (com.example.hernanlopez.proyecto.HistoryRealm) cachedRealmObject;
        } else {
            // rejecting default values to avoid creating unexpected objects from RealmModel/RealmList fields.
            com.example.hernanlopez.proyecto.HistoryRealm realmObject = realm.createObjectInternal(com.example.hernanlopez.proyecto.HistoryRealm.class, false, Collections.<String>emptyList());
            cache.put(newObject, (RealmObjectProxy) realmObject);
            ((HistoryRealmRealmProxyInterface) realmObject).realmSet$colorName(((HistoryRealmRealmProxyInterface) newObject).realmGet$colorName());
            ((HistoryRealmRealmProxyInterface) realmObject).realmSet$hexValue(((HistoryRealmRealmProxyInterface) newObject).realmGet$hexValue());
            ((HistoryRealmRealmProxyInterface) realmObject).realmSet$rgbValue(((HistoryRealmRealmProxyInterface) newObject).realmGet$rgbValue());
            ((HistoryRealmRealmProxyInterface) realmObject).realmSet$isFav(((HistoryRealmRealmProxyInterface) newObject).realmGet$isFav());
            return realmObject;
        }
    }

    public static long insert(Realm realm, com.example.hernanlopez.proyecto.HistoryRealm object, Map<RealmModel,Long> cache) {
        if (object instanceof RealmObjectProxy && ((RealmObjectProxy)object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy)object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
            return ((RealmObjectProxy)object).realmGet$proxyState().getRow$realm().getIndex();
        }
        Table table = realm.getTable(com.example.hernanlopez.proyecto.HistoryRealm.class);
        long tableNativePtr = table.getNativeTablePointer();
        HistoryRealmColumnInfo columnInfo = (HistoryRealmColumnInfo) realm.schema.getColumnInfo(com.example.hernanlopez.proyecto.HistoryRealm.class);
        long rowIndex = Table.nativeAddEmptyRow(tableNativePtr, 1);
        cache.put(object, rowIndex);
        String realmGet$colorName = ((HistoryRealmRealmProxyInterface)object).realmGet$colorName();
        if (realmGet$colorName != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.colorNameIndex, rowIndex, realmGet$colorName, false);
        }
        String realmGet$hexValue = ((HistoryRealmRealmProxyInterface)object).realmGet$hexValue();
        if (realmGet$hexValue != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.hexValueIndex, rowIndex, realmGet$hexValue, false);
        }
        String realmGet$rgbValue = ((HistoryRealmRealmProxyInterface)object).realmGet$rgbValue();
        if (realmGet$rgbValue != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.rgbValueIndex, rowIndex, realmGet$rgbValue, false);
        }
        Table.nativeSetBoolean(tableNativePtr, columnInfo.isFavIndex, rowIndex, ((HistoryRealmRealmProxyInterface)object).realmGet$isFav(), false);
        return rowIndex;
    }

    public static void insert(Realm realm, Iterator<? extends RealmModel> objects, Map<RealmModel,Long> cache) {
        Table table = realm.getTable(com.example.hernanlopez.proyecto.HistoryRealm.class);
        long tableNativePtr = table.getNativeTablePointer();
        HistoryRealmColumnInfo columnInfo = (HistoryRealmColumnInfo) realm.schema.getColumnInfo(com.example.hernanlopez.proyecto.HistoryRealm.class);
        com.example.hernanlopez.proyecto.HistoryRealm object = null;
        while (objects.hasNext()) {
            object = (com.example.hernanlopez.proyecto.HistoryRealm) objects.next();
            if(!cache.containsKey(object)) {
                if (object instanceof RealmObjectProxy && ((RealmObjectProxy)object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy)object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
                    cache.put(object, ((RealmObjectProxy)object).realmGet$proxyState().getRow$realm().getIndex());
                    continue;
                }
                long rowIndex = Table.nativeAddEmptyRow(tableNativePtr, 1);
                cache.put(object, rowIndex);
                String realmGet$colorName = ((HistoryRealmRealmProxyInterface)object).realmGet$colorName();
                if (realmGet$colorName != null) {
                    Table.nativeSetString(tableNativePtr, columnInfo.colorNameIndex, rowIndex, realmGet$colorName, false);
                }
                String realmGet$hexValue = ((HistoryRealmRealmProxyInterface)object).realmGet$hexValue();
                if (realmGet$hexValue != null) {
                    Table.nativeSetString(tableNativePtr, columnInfo.hexValueIndex, rowIndex, realmGet$hexValue, false);
                }
                String realmGet$rgbValue = ((HistoryRealmRealmProxyInterface)object).realmGet$rgbValue();
                if (realmGet$rgbValue != null) {
                    Table.nativeSetString(tableNativePtr, columnInfo.rgbValueIndex, rowIndex, realmGet$rgbValue, false);
                }
                Table.nativeSetBoolean(tableNativePtr, columnInfo.isFavIndex, rowIndex, ((HistoryRealmRealmProxyInterface)object).realmGet$isFav(), false);
            }
        }
    }

    public static long insertOrUpdate(Realm realm, com.example.hernanlopez.proyecto.HistoryRealm object, Map<RealmModel,Long> cache) {
        if (object instanceof RealmObjectProxy && ((RealmObjectProxy)object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy)object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
            return ((RealmObjectProxy)object).realmGet$proxyState().getRow$realm().getIndex();
        }
        Table table = realm.getTable(com.example.hernanlopez.proyecto.HistoryRealm.class);
        long tableNativePtr = table.getNativeTablePointer();
        HistoryRealmColumnInfo columnInfo = (HistoryRealmColumnInfo) realm.schema.getColumnInfo(com.example.hernanlopez.proyecto.HistoryRealm.class);
        long rowIndex = Table.nativeAddEmptyRow(tableNativePtr, 1);
        cache.put(object, rowIndex);
        String realmGet$colorName = ((HistoryRealmRealmProxyInterface)object).realmGet$colorName();
        if (realmGet$colorName != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.colorNameIndex, rowIndex, realmGet$colorName, false);
        } else {
            Table.nativeSetNull(tableNativePtr, columnInfo.colorNameIndex, rowIndex, false);
        }
        String realmGet$hexValue = ((HistoryRealmRealmProxyInterface)object).realmGet$hexValue();
        if (realmGet$hexValue != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.hexValueIndex, rowIndex, realmGet$hexValue, false);
        } else {
            Table.nativeSetNull(tableNativePtr, columnInfo.hexValueIndex, rowIndex, false);
        }
        String realmGet$rgbValue = ((HistoryRealmRealmProxyInterface)object).realmGet$rgbValue();
        if (realmGet$rgbValue != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.rgbValueIndex, rowIndex, realmGet$rgbValue, false);
        } else {
            Table.nativeSetNull(tableNativePtr, columnInfo.rgbValueIndex, rowIndex, false);
        }
        Table.nativeSetBoolean(tableNativePtr, columnInfo.isFavIndex, rowIndex, ((HistoryRealmRealmProxyInterface)object).realmGet$isFav(), false);
        return rowIndex;
    }

    public static void insertOrUpdate(Realm realm, Iterator<? extends RealmModel> objects, Map<RealmModel,Long> cache) {
        Table table = realm.getTable(com.example.hernanlopez.proyecto.HistoryRealm.class);
        long tableNativePtr = table.getNativeTablePointer();
        HistoryRealmColumnInfo columnInfo = (HistoryRealmColumnInfo) realm.schema.getColumnInfo(com.example.hernanlopez.proyecto.HistoryRealm.class);
        com.example.hernanlopez.proyecto.HistoryRealm object = null;
        while (objects.hasNext()) {
            object = (com.example.hernanlopez.proyecto.HistoryRealm) objects.next();
            if(!cache.containsKey(object)) {
                if (object instanceof RealmObjectProxy && ((RealmObjectProxy)object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy)object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
                    cache.put(object, ((RealmObjectProxy)object).realmGet$proxyState().getRow$realm().getIndex());
                    continue;
                }
                long rowIndex = Table.nativeAddEmptyRow(tableNativePtr, 1);
                cache.put(object, rowIndex);
                String realmGet$colorName = ((HistoryRealmRealmProxyInterface)object).realmGet$colorName();
                if (realmGet$colorName != null) {
                    Table.nativeSetString(tableNativePtr, columnInfo.colorNameIndex, rowIndex, realmGet$colorName, false);
                } else {
                    Table.nativeSetNull(tableNativePtr, columnInfo.colorNameIndex, rowIndex, false);
                }
                String realmGet$hexValue = ((HistoryRealmRealmProxyInterface)object).realmGet$hexValue();
                if (realmGet$hexValue != null) {
                    Table.nativeSetString(tableNativePtr, columnInfo.hexValueIndex, rowIndex, realmGet$hexValue, false);
                } else {
                    Table.nativeSetNull(tableNativePtr, columnInfo.hexValueIndex, rowIndex, false);
                }
                String realmGet$rgbValue = ((HistoryRealmRealmProxyInterface)object).realmGet$rgbValue();
                if (realmGet$rgbValue != null) {
                    Table.nativeSetString(tableNativePtr, columnInfo.rgbValueIndex, rowIndex, realmGet$rgbValue, false);
                } else {
                    Table.nativeSetNull(tableNativePtr, columnInfo.rgbValueIndex, rowIndex, false);
                }
                Table.nativeSetBoolean(tableNativePtr, columnInfo.isFavIndex, rowIndex, ((HistoryRealmRealmProxyInterface)object).realmGet$isFav(), false);
            }
        }
    }

    public static com.example.hernanlopez.proyecto.HistoryRealm createDetachedCopy(com.example.hernanlopez.proyecto.HistoryRealm realmObject, int currentDepth, int maxDepth, Map<RealmModel, CacheData<RealmModel>> cache) {
        if (currentDepth > maxDepth || realmObject == null) {
            return null;
        }
        CacheData<RealmModel> cachedObject = cache.get(realmObject);
        com.example.hernanlopez.proyecto.HistoryRealm unmanagedObject;
        if (cachedObject != null) {
            // Reuse cached object or recreate it because it was encountered at a lower depth.
            if (currentDepth >= cachedObject.minDepth) {
                return (com.example.hernanlopez.proyecto.HistoryRealm)cachedObject.object;
            } else {
                unmanagedObject = (com.example.hernanlopez.proyecto.HistoryRealm)cachedObject.object;
                cachedObject.minDepth = currentDepth;
            }
        } else {
            unmanagedObject = new com.example.hernanlopez.proyecto.HistoryRealm();
            cache.put(realmObject, new RealmObjectProxy.CacheData(currentDepth, unmanagedObject));
        }
        ((HistoryRealmRealmProxyInterface) unmanagedObject).realmSet$colorName(((HistoryRealmRealmProxyInterface) realmObject).realmGet$colorName());
        ((HistoryRealmRealmProxyInterface) unmanagedObject).realmSet$hexValue(((HistoryRealmRealmProxyInterface) realmObject).realmGet$hexValue());
        ((HistoryRealmRealmProxyInterface) unmanagedObject).realmSet$rgbValue(((HistoryRealmRealmProxyInterface) realmObject).realmGet$rgbValue());
        ((HistoryRealmRealmProxyInterface) unmanagedObject).realmSet$isFav(((HistoryRealmRealmProxyInterface) realmObject).realmGet$isFav());
        return unmanagedObject;
    }

    @Override
    public String toString() {
        if (!RealmObject.isValid(this)) {
            return "Invalid object";
        }
        StringBuilder stringBuilder = new StringBuilder("HistoryRealm = [");
        stringBuilder.append("{colorName:");
        stringBuilder.append(realmGet$colorName() != null ? realmGet$colorName() : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{hexValue:");
        stringBuilder.append(realmGet$hexValue() != null ? realmGet$hexValue() : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{rgbValue:");
        stringBuilder.append(realmGet$rgbValue() != null ? realmGet$rgbValue() : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{isFav:");
        stringBuilder.append(realmGet$isFav());
        stringBuilder.append("}");
        stringBuilder.append("]");
        return stringBuilder.toString();
    }

    @Override
    public ProxyState realmGet$proxyState() {
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
        HistoryRealmRealmProxy aHistoryRealm = (HistoryRealmRealmProxy)o;

        String path = proxyState.getRealm$realm().getPath();
        String otherPath = aHistoryRealm.proxyState.getRealm$realm().getPath();
        if (path != null ? !path.equals(otherPath) : otherPath != null) return false;

        String tableName = proxyState.getRow$realm().getTable().getName();
        String otherTableName = aHistoryRealm.proxyState.getRow$realm().getTable().getName();
        if (tableName != null ? !tableName.equals(otherTableName) : otherTableName != null) return false;

        if (proxyState.getRow$realm().getIndex() != aHistoryRealm.proxyState.getRow$realm().getIndex()) return false;

        return true;
    }

}
