package com.example.pethealthtracker.data.dao;

import android.database.Cursor;
import android.os.CancellationSignal;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.room.CoroutinesRoom;
import androidx.room.EntityDeletionOrUpdateAdapter;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.SharedSQLiteStatement;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import com.example.pethealthtracker.data.entity.ExerciseRecord;
import java.lang.Class;
import java.lang.Exception;
import java.lang.Object;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import javax.annotation.processing.Generated;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlinx.coroutines.flow.Flow;

@Generated("androidx.room.RoomProcessor")
@SuppressWarnings({"unchecked", "deprecation"})
public final class ExerciseRecordDao_Impl implements ExerciseRecordDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<ExerciseRecord> __insertionAdapterOfExerciseRecord;

  private final EntityDeletionOrUpdateAdapter<ExerciseRecord> __deletionAdapterOfExerciseRecord;

  private final EntityDeletionOrUpdateAdapter<ExerciseRecord> __updateAdapterOfExerciseRecord;

  private final SharedSQLiteStatement __preparedStmtOfDeleteByPetId;

  public ExerciseRecordDao_Impl(@NonNull final RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfExerciseRecord = new EntityInsertionAdapter<ExerciseRecord>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR ABORT INTO `exercise_records` (`id`,`petId`,`activityType`,`duration`,`caloriesBurned`,`notes`,`recordDate`,`createdAt`) VALUES (?,?,?,?,?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @Nullable final ExerciseRecord entity) {
        if (entity.getId() == null) {
          statement.bindNull(1);
        } else {
          statement.bindString(1, entity.getId());
        }
        if (entity.getPetId() == null) {
          statement.bindNull(2);
        } else {
          statement.bindString(2, entity.getPetId());
        }
        if (entity.getActivityType() == null) {
          statement.bindNull(3);
        } else {
          statement.bindString(3, entity.getActivityType());
        }
        statement.bindLong(4, entity.getDuration());
        statement.bindDouble(5, entity.getCaloriesBurned());
        if (entity.getNotes() == null) {
          statement.bindNull(6);
        } else {
          statement.bindString(6, entity.getNotes());
        }
        statement.bindLong(7, entity.getRecordDate());
        statement.bindLong(8, entity.getCreatedAt());
      }
    };
    this.__deletionAdapterOfExerciseRecord = new EntityDeletionOrUpdateAdapter<ExerciseRecord>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "DELETE FROM `exercise_records` WHERE `id` = ?";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @Nullable final ExerciseRecord entity) {
        if (entity.getId() == null) {
          statement.bindNull(1);
        } else {
          statement.bindString(1, entity.getId());
        }
      }
    };
    this.__updateAdapterOfExerciseRecord = new EntityDeletionOrUpdateAdapter<ExerciseRecord>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "UPDATE OR ABORT `exercise_records` SET `id` = ?,`petId` = ?,`activityType` = ?,`duration` = ?,`caloriesBurned` = ?,`notes` = ?,`recordDate` = ?,`createdAt` = ? WHERE `id` = ?";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @Nullable final ExerciseRecord entity) {
        if (entity.getId() == null) {
          statement.bindNull(1);
        } else {
          statement.bindString(1, entity.getId());
        }
        if (entity.getPetId() == null) {
          statement.bindNull(2);
        } else {
          statement.bindString(2, entity.getPetId());
        }
        if (entity.getActivityType() == null) {
          statement.bindNull(3);
        } else {
          statement.bindString(3, entity.getActivityType());
        }
        statement.bindLong(4, entity.getDuration());
        statement.bindDouble(5, entity.getCaloriesBurned());
        if (entity.getNotes() == null) {
          statement.bindNull(6);
        } else {
          statement.bindString(6, entity.getNotes());
        }
        statement.bindLong(7, entity.getRecordDate());
        statement.bindLong(8, entity.getCreatedAt());
        if (entity.getId() == null) {
          statement.bindNull(9);
        } else {
          statement.bindString(9, entity.getId());
        }
      }
    };
    this.__preparedStmtOfDeleteByPetId = new SharedSQLiteStatement(__db) {
      @Override
      @NonNull
      public String createQuery() {
        final String _query = "DELETE FROM exercise_records WHERE petId = ?";
        return _query;
      }
    };
  }

  @Override
  public Object insert(final ExerciseRecord record, final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __insertionAdapterOfExerciseRecord.insert(record);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object delete(final ExerciseRecord record, final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __deletionAdapterOfExerciseRecord.handle(record);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object update(final ExerciseRecord record, final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __updateAdapterOfExerciseRecord.handle(record);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object deleteByPetId(final String petId, final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        final SupportSQLiteStatement _stmt = __preparedStmtOfDeleteByPetId.acquire();
        int _argIndex = 1;
        if (petId == null) {
          _stmt.bindNull(_argIndex);
        } else {
          _stmt.bindString(_argIndex, petId);
        }
        try {
          __db.beginTransaction();
          try {
            _stmt.executeUpdateDelete();
            __db.setTransactionSuccessful();
            return Unit.INSTANCE;
          } finally {
            __db.endTransaction();
          }
        } finally {
          __preparedStmtOfDeleteByPetId.release(_stmt);
        }
      }
    }, $completion);
  }

  @Override
  public Object getRecordById(final String id,
      final Continuation<? super ExerciseRecord> $completion) {
    final String _sql = "SELECT * FROM exercise_records WHERE id = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    if (id == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, id);
    }
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<ExerciseRecord>() {
      @Override
      @Nullable
      public ExerciseRecord call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfPetId = CursorUtil.getColumnIndexOrThrow(_cursor, "petId");
          final int _cursorIndexOfActivityType = CursorUtil.getColumnIndexOrThrow(_cursor, "activityType");
          final int _cursorIndexOfDuration = CursorUtil.getColumnIndexOrThrow(_cursor, "duration");
          final int _cursorIndexOfCaloriesBurned = CursorUtil.getColumnIndexOrThrow(_cursor, "caloriesBurned");
          final int _cursorIndexOfNotes = CursorUtil.getColumnIndexOrThrow(_cursor, "notes");
          final int _cursorIndexOfRecordDate = CursorUtil.getColumnIndexOrThrow(_cursor, "recordDate");
          final int _cursorIndexOfCreatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "createdAt");
          final ExerciseRecord _result;
          if (_cursor.moveToFirst()) {
            final String _tmpId;
            if (_cursor.isNull(_cursorIndexOfId)) {
              _tmpId = null;
            } else {
              _tmpId = _cursor.getString(_cursorIndexOfId);
            }
            final String _tmpPetId;
            if (_cursor.isNull(_cursorIndexOfPetId)) {
              _tmpPetId = null;
            } else {
              _tmpPetId = _cursor.getString(_cursorIndexOfPetId);
            }
            final String _tmpActivityType;
            if (_cursor.isNull(_cursorIndexOfActivityType)) {
              _tmpActivityType = null;
            } else {
              _tmpActivityType = _cursor.getString(_cursorIndexOfActivityType);
            }
            final int _tmpDuration;
            _tmpDuration = _cursor.getInt(_cursorIndexOfDuration);
            final double _tmpCaloriesBurned;
            _tmpCaloriesBurned = _cursor.getDouble(_cursorIndexOfCaloriesBurned);
            final String _tmpNotes;
            if (_cursor.isNull(_cursorIndexOfNotes)) {
              _tmpNotes = null;
            } else {
              _tmpNotes = _cursor.getString(_cursorIndexOfNotes);
            }
            final long _tmpRecordDate;
            _tmpRecordDate = _cursor.getLong(_cursorIndexOfRecordDate);
            final long _tmpCreatedAt;
            _tmpCreatedAt = _cursor.getLong(_cursorIndexOfCreatedAt);
            _result = new ExerciseRecord(_tmpId,_tmpPetId,_tmpActivityType,_tmpDuration,_tmpCaloriesBurned,_tmpNotes,_tmpRecordDate,_tmpCreatedAt);
          } else {
            _result = null;
          }
          return _result;
        } finally {
          _cursor.close();
          _statement.release();
        }
      }
    }, $completion);
  }

  @Override
  public Flow<List<ExerciseRecord>> getRecordsByPetId(final String petId) {
    final String _sql = "SELECT * FROM exercise_records WHERE petId = ? ORDER BY recordDate DESC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    if (petId == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, petId);
    }
    return CoroutinesRoom.createFlow(__db, false, new String[] {"exercise_records"}, new Callable<List<ExerciseRecord>>() {
      @Override
      @NonNull
      public List<ExerciseRecord> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfPetId = CursorUtil.getColumnIndexOrThrow(_cursor, "petId");
          final int _cursorIndexOfActivityType = CursorUtil.getColumnIndexOrThrow(_cursor, "activityType");
          final int _cursorIndexOfDuration = CursorUtil.getColumnIndexOrThrow(_cursor, "duration");
          final int _cursorIndexOfCaloriesBurned = CursorUtil.getColumnIndexOrThrow(_cursor, "caloriesBurned");
          final int _cursorIndexOfNotes = CursorUtil.getColumnIndexOrThrow(_cursor, "notes");
          final int _cursorIndexOfRecordDate = CursorUtil.getColumnIndexOrThrow(_cursor, "recordDate");
          final int _cursorIndexOfCreatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "createdAt");
          final List<ExerciseRecord> _result = new ArrayList<ExerciseRecord>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final ExerciseRecord _item;
            final String _tmpId;
            if (_cursor.isNull(_cursorIndexOfId)) {
              _tmpId = null;
            } else {
              _tmpId = _cursor.getString(_cursorIndexOfId);
            }
            final String _tmpPetId;
            if (_cursor.isNull(_cursorIndexOfPetId)) {
              _tmpPetId = null;
            } else {
              _tmpPetId = _cursor.getString(_cursorIndexOfPetId);
            }
            final String _tmpActivityType;
            if (_cursor.isNull(_cursorIndexOfActivityType)) {
              _tmpActivityType = null;
            } else {
              _tmpActivityType = _cursor.getString(_cursorIndexOfActivityType);
            }
            final int _tmpDuration;
            _tmpDuration = _cursor.getInt(_cursorIndexOfDuration);
            final double _tmpCaloriesBurned;
            _tmpCaloriesBurned = _cursor.getDouble(_cursorIndexOfCaloriesBurned);
            final String _tmpNotes;
            if (_cursor.isNull(_cursorIndexOfNotes)) {
              _tmpNotes = null;
            } else {
              _tmpNotes = _cursor.getString(_cursorIndexOfNotes);
            }
            final long _tmpRecordDate;
            _tmpRecordDate = _cursor.getLong(_cursorIndexOfRecordDate);
            final long _tmpCreatedAt;
            _tmpCreatedAt = _cursor.getLong(_cursorIndexOfCreatedAt);
            _item = new ExerciseRecord(_tmpId,_tmpPetId,_tmpActivityType,_tmpDuration,_tmpCaloriesBurned,_tmpNotes,_tmpRecordDate,_tmpCreatedAt);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }

  @NonNull
  public static List<Class<?>> getRequiredConverters() {
    return Collections.emptyList();
  }
}
