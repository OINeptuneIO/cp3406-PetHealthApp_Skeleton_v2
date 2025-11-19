package com.example.pethealthtracker.data;

import androidx.annotation.NonNull;
import androidx.room.DatabaseConfiguration;
import androidx.room.InvalidationTracker;
import androidx.room.RoomDatabase;
import androidx.room.RoomOpenHelper;
import androidx.room.migration.AutoMigrationSpec;
import androidx.room.migration.Migration;
import androidx.room.util.DBUtil;
import androidx.room.util.TableInfo;
import androidx.sqlite.db.SupportSQLiteDatabase;
import androidx.sqlite.db.SupportSQLiteOpenHelper;
import com.example.pethealthtracker.data.dao.DietRecordDao;
import com.example.pethealthtracker.data.dao.DietRecordDao_Impl;
import com.example.pethealthtracker.data.dao.ExerciseRecordDao;
import com.example.pethealthtracker.data.dao.ExerciseRecordDao_Impl;
import com.example.pethealthtracker.data.dao.HealthRecordDao;
import com.example.pethealthtracker.data.dao.HealthRecordDao_Impl;
import com.example.pethealthtracker.data.dao.PetDao;
import com.example.pethealthtracker.data.dao.PetDao_Impl;
import com.example.pethealthtracker.data.dao.ReminderDao;
import com.example.pethealthtracker.data.dao.ReminderDao_Impl;
import java.lang.Class;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.annotation.processing.Generated;

@Generated("androidx.room.RoomProcessor")
@SuppressWarnings({"unchecked", "deprecation"})
public final class AppDatabase_Impl extends AppDatabase {
  private volatile PetDao _petDao;

  private volatile HealthRecordDao _healthRecordDao;

  private volatile DietRecordDao _dietRecordDao;

  private volatile ExerciseRecordDao _exerciseRecordDao;

  private volatile ReminderDao _reminderDao;

  @Override
  @NonNull
  protected SupportSQLiteOpenHelper createOpenHelper(@NonNull final DatabaseConfiguration config) {
    final SupportSQLiteOpenHelper.Callback _openCallback = new RoomOpenHelper(config, new RoomOpenHelper.Delegate(1) {
      @Override
      public void createAllTables(@NonNull final SupportSQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS `pets` (`id` TEXT NOT NULL, `name` TEXT NOT NULL, `type` TEXT NOT NULL, `breed` TEXT NOT NULL, `age` INTEGER NOT NULL, `weight` REAL NOT NULL, `createdAt` INTEGER NOT NULL, `updatedAt` INTEGER NOT NULL, PRIMARY KEY(`id`))");
        db.execSQL("CREATE TABLE IF NOT EXISTS `health_records` (`id` TEXT NOT NULL, `petId` TEXT NOT NULL, `weight` REAL NOT NULL, `energyExpenditure` REAL NOT NULL, `notes` TEXT NOT NULL, `recordDate` INTEGER NOT NULL, `createdAt` INTEGER NOT NULL, PRIMARY KEY(`id`), FOREIGN KEY(`petId`) REFERENCES `pets`(`id`) ON UPDATE NO ACTION ON DELETE CASCADE )");
        db.execSQL("CREATE INDEX IF NOT EXISTS `index_health_records_petId` ON `health_records` (`petId`)");
        db.execSQL("CREATE TABLE IF NOT EXISTS `diet_records` (`id` TEXT NOT NULL, `petId` TEXT NOT NULL, `foodType` TEXT NOT NULL, `amount` REAL NOT NULL, `calories` REAL NOT NULL, `notes` TEXT NOT NULL, `recordDate` INTEGER NOT NULL, `createdAt` INTEGER NOT NULL, PRIMARY KEY(`id`), FOREIGN KEY(`petId`) REFERENCES `pets`(`id`) ON UPDATE NO ACTION ON DELETE CASCADE )");
        db.execSQL("CREATE INDEX IF NOT EXISTS `index_diet_records_petId` ON `diet_records` (`petId`)");
        db.execSQL("CREATE TABLE IF NOT EXISTS `exercise_records` (`id` TEXT NOT NULL, `petId` TEXT NOT NULL, `activityType` TEXT NOT NULL, `duration` INTEGER NOT NULL, `caloriesBurned` REAL NOT NULL, `notes` TEXT NOT NULL, `recordDate` INTEGER NOT NULL, `createdAt` INTEGER NOT NULL, PRIMARY KEY(`id`), FOREIGN KEY(`petId`) REFERENCES `pets`(`id`) ON UPDATE NO ACTION ON DELETE CASCADE )");
        db.execSQL("CREATE INDEX IF NOT EXISTS `index_exercise_records_petId` ON `exercise_records` (`petId`)");
        db.execSQL("CREATE TABLE IF NOT EXISTS `reminders` (`id` TEXT NOT NULL, `petId` TEXT NOT NULL, `title` TEXT NOT NULL, `category` TEXT NOT NULL, `reminderDate` INTEGER NOT NULL, `reminderType` TEXT NOT NULL, `isEnabled` INTEGER NOT NULL, `notes` TEXT NOT NULL, `createdAt` INTEGER NOT NULL, `updatedAt` INTEGER NOT NULL, PRIMARY KEY(`id`), FOREIGN KEY(`petId`) REFERENCES `pets`(`id`) ON UPDATE NO ACTION ON DELETE CASCADE )");
        db.execSQL("CREATE INDEX IF NOT EXISTS `index_reminders_petId` ON `reminders` (`petId`)");
        db.execSQL("CREATE TABLE IF NOT EXISTS room_master_table (id INTEGER PRIMARY KEY,identity_hash TEXT)");
        db.execSQL("INSERT OR REPLACE INTO room_master_table (id,identity_hash) VALUES(42, '5917b38e6e23afe2373655fc5b83bf53')");
      }

      @Override
      public void dropAllTables(@NonNull final SupportSQLiteDatabase db) {
        db.execSQL("DROP TABLE IF EXISTS `pets`");
        db.execSQL("DROP TABLE IF EXISTS `health_records`");
        db.execSQL("DROP TABLE IF EXISTS `diet_records`");
        db.execSQL("DROP TABLE IF EXISTS `exercise_records`");
        db.execSQL("DROP TABLE IF EXISTS `reminders`");
        final List<? extends RoomDatabase.Callback> _callbacks = mCallbacks;
        if (_callbacks != null) {
          for (RoomDatabase.Callback _callback : _callbacks) {
            _callback.onDestructiveMigration(db);
          }
        }
      }

      @Override
      public void onCreate(@NonNull final SupportSQLiteDatabase db) {
        final List<? extends RoomDatabase.Callback> _callbacks = mCallbacks;
        if (_callbacks != null) {
          for (RoomDatabase.Callback _callback : _callbacks) {
            _callback.onCreate(db);
          }
        }
      }

      @Override
      public void onOpen(@NonNull final SupportSQLiteDatabase db) {
        mDatabase = db;
        db.execSQL("PRAGMA foreign_keys = ON");
        internalInitInvalidationTracker(db);
        final List<? extends RoomDatabase.Callback> _callbacks = mCallbacks;
        if (_callbacks != null) {
          for (RoomDatabase.Callback _callback : _callbacks) {
            _callback.onOpen(db);
          }
        }
      }

      @Override
      public void onPreMigrate(@NonNull final SupportSQLiteDatabase db) {
        DBUtil.dropFtsSyncTriggers(db);
      }

      @Override
      public void onPostMigrate(@NonNull final SupportSQLiteDatabase db) {
      }

      @Override
      @NonNull
      public RoomOpenHelper.ValidationResult onValidateSchema(
          @NonNull final SupportSQLiteDatabase db) {
        final HashMap<String, TableInfo.Column> _columnsPets = new HashMap<String, TableInfo.Column>(8);
        _columnsPets.put("id", new TableInfo.Column("id", "TEXT", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsPets.put("name", new TableInfo.Column("name", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsPets.put("type", new TableInfo.Column("type", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsPets.put("breed", new TableInfo.Column("breed", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsPets.put("age", new TableInfo.Column("age", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsPets.put("weight", new TableInfo.Column("weight", "REAL", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsPets.put("createdAt", new TableInfo.Column("createdAt", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsPets.put("updatedAt", new TableInfo.Column("updatedAt", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysPets = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesPets = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoPets = new TableInfo("pets", _columnsPets, _foreignKeysPets, _indicesPets);
        final TableInfo _existingPets = TableInfo.read(db, "pets");
        if (!_infoPets.equals(_existingPets)) {
          return new RoomOpenHelper.ValidationResult(false, "pets(com.example.pethealthtracker.data.entity.Pet).\n"
                  + " Expected:\n" + _infoPets + "\n"
                  + " Found:\n" + _existingPets);
        }
        final HashMap<String, TableInfo.Column> _columnsHealthRecords = new HashMap<String, TableInfo.Column>(7);
        _columnsHealthRecords.put("id", new TableInfo.Column("id", "TEXT", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsHealthRecords.put("petId", new TableInfo.Column("petId", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsHealthRecords.put("weight", new TableInfo.Column("weight", "REAL", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsHealthRecords.put("energyExpenditure", new TableInfo.Column("energyExpenditure", "REAL", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsHealthRecords.put("notes", new TableInfo.Column("notes", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsHealthRecords.put("recordDate", new TableInfo.Column("recordDate", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsHealthRecords.put("createdAt", new TableInfo.Column("createdAt", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysHealthRecords = new HashSet<TableInfo.ForeignKey>(1);
        _foreignKeysHealthRecords.add(new TableInfo.ForeignKey("pets", "CASCADE", "NO ACTION", Arrays.asList("petId"), Arrays.asList("id")));
        final HashSet<TableInfo.Index> _indicesHealthRecords = new HashSet<TableInfo.Index>(1);
        _indicesHealthRecords.add(new TableInfo.Index("index_health_records_petId", false, Arrays.asList("petId"), Arrays.asList("ASC")));
        final TableInfo _infoHealthRecords = new TableInfo("health_records", _columnsHealthRecords, _foreignKeysHealthRecords, _indicesHealthRecords);
        final TableInfo _existingHealthRecords = TableInfo.read(db, "health_records");
        if (!_infoHealthRecords.equals(_existingHealthRecords)) {
          return new RoomOpenHelper.ValidationResult(false, "health_records(com.example.pethealthtracker.data.entity.HealthRecord).\n"
                  + " Expected:\n" + _infoHealthRecords + "\n"
                  + " Found:\n" + _existingHealthRecords);
        }
        final HashMap<String, TableInfo.Column> _columnsDietRecords = new HashMap<String, TableInfo.Column>(8);
        _columnsDietRecords.put("id", new TableInfo.Column("id", "TEXT", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsDietRecords.put("petId", new TableInfo.Column("petId", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsDietRecords.put("foodType", new TableInfo.Column("foodType", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsDietRecords.put("amount", new TableInfo.Column("amount", "REAL", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsDietRecords.put("calories", new TableInfo.Column("calories", "REAL", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsDietRecords.put("notes", new TableInfo.Column("notes", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsDietRecords.put("recordDate", new TableInfo.Column("recordDate", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsDietRecords.put("createdAt", new TableInfo.Column("createdAt", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysDietRecords = new HashSet<TableInfo.ForeignKey>(1);
        _foreignKeysDietRecords.add(new TableInfo.ForeignKey("pets", "CASCADE", "NO ACTION", Arrays.asList("petId"), Arrays.asList("id")));
        final HashSet<TableInfo.Index> _indicesDietRecords = new HashSet<TableInfo.Index>(1);
        _indicesDietRecords.add(new TableInfo.Index("index_diet_records_petId", false, Arrays.asList("petId"), Arrays.asList("ASC")));
        final TableInfo _infoDietRecords = new TableInfo("diet_records", _columnsDietRecords, _foreignKeysDietRecords, _indicesDietRecords);
        final TableInfo _existingDietRecords = TableInfo.read(db, "diet_records");
        if (!_infoDietRecords.equals(_existingDietRecords)) {
          return new RoomOpenHelper.ValidationResult(false, "diet_records(com.example.pethealthtracker.data.entity.DietRecord).\n"
                  + " Expected:\n" + _infoDietRecords + "\n"
                  + " Found:\n" + _existingDietRecords);
        }
        final HashMap<String, TableInfo.Column> _columnsExerciseRecords = new HashMap<String, TableInfo.Column>(8);
        _columnsExerciseRecords.put("id", new TableInfo.Column("id", "TEXT", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsExerciseRecords.put("petId", new TableInfo.Column("petId", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsExerciseRecords.put("activityType", new TableInfo.Column("activityType", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsExerciseRecords.put("duration", new TableInfo.Column("duration", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsExerciseRecords.put("caloriesBurned", new TableInfo.Column("caloriesBurned", "REAL", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsExerciseRecords.put("notes", new TableInfo.Column("notes", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsExerciseRecords.put("recordDate", new TableInfo.Column("recordDate", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsExerciseRecords.put("createdAt", new TableInfo.Column("createdAt", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysExerciseRecords = new HashSet<TableInfo.ForeignKey>(1);
        _foreignKeysExerciseRecords.add(new TableInfo.ForeignKey("pets", "CASCADE", "NO ACTION", Arrays.asList("petId"), Arrays.asList("id")));
        final HashSet<TableInfo.Index> _indicesExerciseRecords = new HashSet<TableInfo.Index>(1);
        _indicesExerciseRecords.add(new TableInfo.Index("index_exercise_records_petId", false, Arrays.asList("petId"), Arrays.asList("ASC")));
        final TableInfo _infoExerciseRecords = new TableInfo("exercise_records", _columnsExerciseRecords, _foreignKeysExerciseRecords, _indicesExerciseRecords);
        final TableInfo _existingExerciseRecords = TableInfo.read(db, "exercise_records");
        if (!_infoExerciseRecords.equals(_existingExerciseRecords)) {
          return new RoomOpenHelper.ValidationResult(false, "exercise_records(com.example.pethealthtracker.data.entity.ExerciseRecord).\n"
                  + " Expected:\n" + _infoExerciseRecords + "\n"
                  + " Found:\n" + _existingExerciseRecords);
        }
        final HashMap<String, TableInfo.Column> _columnsReminders = new HashMap<String, TableInfo.Column>(10);
        _columnsReminders.put("id", new TableInfo.Column("id", "TEXT", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsReminders.put("petId", new TableInfo.Column("petId", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsReminders.put("title", new TableInfo.Column("title", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsReminders.put("category", new TableInfo.Column("category", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsReminders.put("reminderDate", new TableInfo.Column("reminderDate", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsReminders.put("reminderType", new TableInfo.Column("reminderType", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsReminders.put("isEnabled", new TableInfo.Column("isEnabled", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsReminders.put("notes", new TableInfo.Column("notes", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsReminders.put("createdAt", new TableInfo.Column("createdAt", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsReminders.put("updatedAt", new TableInfo.Column("updatedAt", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysReminders = new HashSet<TableInfo.ForeignKey>(1);
        _foreignKeysReminders.add(new TableInfo.ForeignKey("pets", "CASCADE", "NO ACTION", Arrays.asList("petId"), Arrays.asList("id")));
        final HashSet<TableInfo.Index> _indicesReminders = new HashSet<TableInfo.Index>(1);
        _indicesReminders.add(new TableInfo.Index("index_reminders_petId", false, Arrays.asList("petId"), Arrays.asList("ASC")));
        final TableInfo _infoReminders = new TableInfo("reminders", _columnsReminders, _foreignKeysReminders, _indicesReminders);
        final TableInfo _existingReminders = TableInfo.read(db, "reminders");
        if (!_infoReminders.equals(_existingReminders)) {
          return new RoomOpenHelper.ValidationResult(false, "reminders(com.example.pethealthtracker.data.entity.Reminder).\n"
                  + " Expected:\n" + _infoReminders + "\n"
                  + " Found:\n" + _existingReminders);
        }
        return new RoomOpenHelper.ValidationResult(true, null);
      }
    }, "5917b38e6e23afe2373655fc5b83bf53", "4fbc607b3f51ed9f9061a6ffde360971");
    final SupportSQLiteOpenHelper.Configuration _sqliteConfig = SupportSQLiteOpenHelper.Configuration.builder(config.context).name(config.name).callback(_openCallback).build();
    final SupportSQLiteOpenHelper _helper = config.sqliteOpenHelperFactory.create(_sqliteConfig);
    return _helper;
  }

  @Override
  @NonNull
  protected InvalidationTracker createInvalidationTracker() {
    final HashMap<String, String> _shadowTablesMap = new HashMap<String, String>(0);
    final HashMap<String, Set<String>> _viewTables = new HashMap<String, Set<String>>(0);
    return new InvalidationTracker(this, _shadowTablesMap, _viewTables, "pets","health_records","diet_records","exercise_records","reminders");
  }

  @Override
  public void clearAllTables() {
    super.assertNotMainThread();
    final SupportSQLiteDatabase _db = super.getOpenHelper().getWritableDatabase();
    final boolean _supportsDeferForeignKeys = android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP;
    try {
      if (!_supportsDeferForeignKeys) {
        _db.execSQL("PRAGMA foreign_keys = FALSE");
      }
      super.beginTransaction();
      if (_supportsDeferForeignKeys) {
        _db.execSQL("PRAGMA defer_foreign_keys = TRUE");
      }
      _db.execSQL("DELETE FROM `pets`");
      _db.execSQL("DELETE FROM `health_records`");
      _db.execSQL("DELETE FROM `diet_records`");
      _db.execSQL("DELETE FROM `exercise_records`");
      _db.execSQL("DELETE FROM `reminders`");
      super.setTransactionSuccessful();
    } finally {
      super.endTransaction();
      if (!_supportsDeferForeignKeys) {
        _db.execSQL("PRAGMA foreign_keys = TRUE");
      }
      _db.query("PRAGMA wal_checkpoint(FULL)").close();
      if (!_db.inTransaction()) {
        _db.execSQL("VACUUM");
      }
    }
  }

  @Override
  @NonNull
  protected Map<Class<?>, List<Class<?>>> getRequiredTypeConverters() {
    final HashMap<Class<?>, List<Class<?>>> _typeConvertersMap = new HashMap<Class<?>, List<Class<?>>>();
    _typeConvertersMap.put(PetDao.class, PetDao_Impl.getRequiredConverters());
    _typeConvertersMap.put(HealthRecordDao.class, HealthRecordDao_Impl.getRequiredConverters());
    _typeConvertersMap.put(DietRecordDao.class, DietRecordDao_Impl.getRequiredConverters());
    _typeConvertersMap.put(ExerciseRecordDao.class, ExerciseRecordDao_Impl.getRequiredConverters());
    _typeConvertersMap.put(ReminderDao.class, ReminderDao_Impl.getRequiredConverters());
    return _typeConvertersMap;
  }

  @Override
  @NonNull
  public Set<Class<? extends AutoMigrationSpec>> getRequiredAutoMigrationSpecs() {
    final HashSet<Class<? extends AutoMigrationSpec>> _autoMigrationSpecsSet = new HashSet<Class<? extends AutoMigrationSpec>>();
    return _autoMigrationSpecsSet;
  }

  @Override
  @NonNull
  public List<Migration> getAutoMigrations(
      @NonNull final Map<Class<? extends AutoMigrationSpec>, AutoMigrationSpec> autoMigrationSpecs) {
    final List<Migration> _autoMigrations = new ArrayList<Migration>();
    return _autoMigrations;
  }

  @Override
  public PetDao petDao() {
    if (_petDao != null) {
      return _petDao;
    } else {
      synchronized(this) {
        if(_petDao == null) {
          _petDao = new PetDao_Impl(this);
        }
        return _petDao;
      }
    }
  }

  @Override
  public HealthRecordDao healthRecordDao() {
    if (_healthRecordDao != null) {
      return _healthRecordDao;
    } else {
      synchronized(this) {
        if(_healthRecordDao == null) {
          _healthRecordDao = new HealthRecordDao_Impl(this);
        }
        return _healthRecordDao;
      }
    }
  }

  @Override
  public DietRecordDao dietRecordDao() {
    if (_dietRecordDao != null) {
      return _dietRecordDao;
    } else {
      synchronized(this) {
        if(_dietRecordDao == null) {
          _dietRecordDao = new DietRecordDao_Impl(this);
        }
        return _dietRecordDao;
      }
    }
  }

  @Override
  public ExerciseRecordDao exerciseRecordDao() {
    if (_exerciseRecordDao != null) {
      return _exerciseRecordDao;
    } else {
      synchronized(this) {
        if(_exerciseRecordDao == null) {
          _exerciseRecordDao = new ExerciseRecordDao_Impl(this);
        }
        return _exerciseRecordDao;
      }
    }
  }

  @Override
  public ReminderDao reminderDao() {
    if (_reminderDao != null) {
      return _reminderDao;
    } else {
      synchronized(this) {
        if(_reminderDao == null) {
          _reminderDao = new ReminderDao_Impl(this);
        }
        return _reminderDao;
      }
    }
  }
}
