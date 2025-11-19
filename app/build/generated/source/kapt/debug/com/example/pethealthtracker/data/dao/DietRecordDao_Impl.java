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
import com.example.pethealthtracker.data.entity.DietRecord;
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
public final class DietRecordDao_Impl implements DietRecordDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<DietRecord> __insertionAdapterOfDietRecord;

  private final EntityDeletionOrUpdateAdapter<DietRecord> __deletionAdapterOfDietRecord;

  private final EntityDeletionOrUpdateAdapter<DietRecord> __updateAdapterOfDietRecord;

  private final SharedSQLiteStatement __preparedStmtOfDeleteByPetId;

  public DietRecordDao_Impl(@NonNull final RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfDietRecord = new EntityInsertionAdapter<DietRecord>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR ABORT INTO `diet_records` (`id`,`petId`,`foodType`,`amount`,`calories`,`notes`,`recordDate`,`createdAt`) VALUES (?,?,?,?,?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @Nullable final DietRecord entity) {
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
        if (entity.getFoodType() == null) {
          statement.bindNull(3);
        } else {
          statement.bindString(3, entity.getFoodType());
        }
        statement.bindDouble(4, entity.getAmount());
        statement.bindDouble(5, entity.getCalories());
        if (entity.getNotes() == null) {
          statement.bindNull(6);
        } else {
          statement.bindString(6, entity.getNotes());
        }
        statement.bindLong(7, entity.getRecordDate());
        statement.bindLong(8, entity.getCreatedAt());
      }
    };
    this.__deletionAdapterOfDietRecord = new EntityDeletionOrUpdateAdapter<DietRecord>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "DELETE FROM `diet_records` WHERE `id` = ?";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @Nullable final DietRecord entity) {
        if (entity.getId() == null) {
          statement.bindNull(1);
        } else {
          statement.bindString(1, entity.getId());
        }
      }
    };
    this.__updateAdapterOfDietRecord = new EntityDeletionOrUpdateAdapter<DietRecord>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "UPDATE OR ABORT `diet_records` SET `id` = ?,`petId` = ?,`foodType` = ?,`amount` = ?,`calories` = ?,`notes` = ?,`recordDate` = ?,`createdAt` = ? WHERE `id` = ?";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @Nullable final DietRecord entity) {
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
        if (entity.getFoodType() == null) {
          statement.bindNull(3);
        } else {
          statement.bindString(3, entity.getFoodType());
        }
        statement.bindDouble(4, entity.getAmount());
        statement.bindDouble(5, entity.getCalories());
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
        final String _query = "DELETE FROM diet_records WHERE petId = ?";
        return _query;
      }
    };
  }

  @Override
  public Object insert(final DietRecord record, final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __insertionAdapterOfDietRecord.insert(record);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object delete(final DietRecord record, final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __deletionAdapterOfDietRecord.handle(record);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object update(final DietRecord record, final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __updateAdapterOfDietRecord.handle(record);
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
  public Object getRecordById(final String id, final Continuation<? super DietRecord> $completion) {
    final String _sql = "SELECT * FROM diet_records WHERE id = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    if (id == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, id);
    }
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<DietRecord>() {
      @Override
      @Nullable
      public DietRecord call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfPetId = CursorUtil.getColumnIndexOrThrow(_cursor, "petId");
          final int _cursorIndexOfFoodType = CursorUtil.getColumnIndexOrThrow(_cursor, "foodType");
          final int _cursorIndexOfAmount = CursorUtil.getColumnIndexOrThrow(_cursor, "amount");
          final int _cursorIndexOfCalories = CursorUtil.getColumnIndexOrThrow(_cursor, "calories");
          final int _cursorIndexOfNotes = CursorUtil.getColumnIndexOrThrow(_cursor, "notes");
          final int _cursorIndexOfRecordDate = CursorUtil.getColumnIndexOrThrow(_cursor, "recordDate");
          final int _cursorIndexOfCreatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "createdAt");
          final DietRecord _result;
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
            final String _tmpFoodType;
            if (_cursor.isNull(_cursorIndexOfFoodType)) {
              _tmpFoodType = null;
            } else {
              _tmpFoodType = _cursor.getString(_cursorIndexOfFoodType);
            }
            final double _tmpAmount;
            _tmpAmount = _cursor.getDouble(_cursorIndexOfAmount);
            final double _tmpCalories;
            _tmpCalories = _cursor.getDouble(_cursorIndexOfCalories);
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
            _result = new DietRecord(_tmpId,_tmpPetId,_tmpFoodType,_tmpAmount,_tmpCalories,_tmpNotes,_tmpRecordDate,_tmpCreatedAt);
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
  public Flow<List<DietRecord>> getRecordsByPetId(final String petId) {
    final String _sql = "SELECT * FROM diet_records WHERE petId = ? ORDER BY recordDate DESC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    if (petId == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, petId);
    }
    return CoroutinesRoom.createFlow(__db, false, new String[] {"diet_records"}, new Callable<List<DietRecord>>() {
      @Override
      @NonNull
      public List<DietRecord> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfPetId = CursorUtil.getColumnIndexOrThrow(_cursor, "petId");
          final int _cursorIndexOfFoodType = CursorUtil.getColumnIndexOrThrow(_cursor, "foodType");
          final int _cursorIndexOfAmount = CursorUtil.getColumnIndexOrThrow(_cursor, "amount");
          final int _cursorIndexOfCalories = CursorUtil.getColumnIndexOrThrow(_cursor, "calories");
          final int _cursorIndexOfNotes = CursorUtil.getColumnIndexOrThrow(_cursor, "notes");
          final int _cursorIndexOfRecordDate = CursorUtil.getColumnIndexOrThrow(_cursor, "recordDate");
          final int _cursorIndexOfCreatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "createdAt");
          final List<DietRecord> _result = new ArrayList<DietRecord>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final DietRecord _item;
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
            final String _tmpFoodType;
            if (_cursor.isNull(_cursorIndexOfFoodType)) {
              _tmpFoodType = null;
            } else {
              _tmpFoodType = _cursor.getString(_cursorIndexOfFoodType);
            }
            final double _tmpAmount;
            _tmpAmount = _cursor.getDouble(_cursorIndexOfAmount);
            final double _tmpCalories;
            _tmpCalories = _cursor.getDouble(_cursorIndexOfCalories);
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
            _item = new DietRecord(_tmpId,_tmpPetId,_tmpFoodType,_tmpAmount,_tmpCalories,_tmpNotes,_tmpRecordDate,_tmpCreatedAt);
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
