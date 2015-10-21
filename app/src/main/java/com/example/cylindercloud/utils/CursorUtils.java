package com.example.cylindercloud.utils;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.cylindercloud.annotation.DBField;


public class CursorUtils {
	public static <T> T getBeanFromCursor(Class<T> clazz, Cursor cursor) {
		if (cursor == null) {
			return null;
		}
		Field[] fields = getFilterField(clazz);
		T t = null;
		if (cursor.moveToNext()) {
			try {
				t = clazz.newInstance();
			} catch (Exception e1) {
				e1.printStackTrace();
				return null;
			}
			t = getBeanFromCurrentCursor(cursor, fields, t);
		}
		return t;
	}
	public static Field[] getFilterField(Class<?> clazz) {
		HashSet<Field> fieldSet = new HashSet<Field>();
		Field[] fields = ReflectUtils.getDeclaredFields(clazz);
		for (Field field : fields) {
//			DBField dbField = field.getAnnotation(DBField.class);
//			if (dbField != null) {
//				fieldSet.add(field);
//			}
			fieldSet.add(field);
		}
		return fieldSet.toArray(new Field[fieldSet.size()]);
	}

	public static <T> T getBeanFromCurrentCursor(Class<T> clazz, Cursor cursor) {
		if (cursor == null) {
			return null;
		}
		Field[] fields = getFilterField(clazz);
		T t = null;
		try {
			t = clazz.newInstance();
		} catch (Exception e1) {
			e1.printStackTrace();
			return null;
		}
		t = getBeanFromCurrentCursor(cursor, fields, t);
		return t;
	}

	public static <T> List<T> getBeanListFromCursor(Class<T> clazz, Cursor cursor) {
		List<T> result = new ArrayList<T>();
		if (cursor == null) {
			return result;
		}
		Field[] fields = getFilterField(clazz);
		while (cursor.moveToNext()) {
			T t = null;
			try {
				t = clazz.newInstance();
			} catch (Exception e1) {
				e1.printStackTrace();
				continue;
			}

			t = getBeanFromCurrentCursor(cursor, fields, t);
			result.add(t);
		}
		return result;
	}

	public static <T> T getBeanFromCurrentCursor(Cursor cursor, Field[] fields, T t) {
		Object value = null;
		String name = null;
		for (Field field : fields) {
			DBField dbField = field.getAnnotation(DBField.class);
			if (dbField == null) {
//				continue;
				name = field.getName();
			}else{
				name = dbField.value();
			}
			int columnIndex = cursor.getColumnIndex(name);
			if (columnIndex == -1) {
				continue;
			}
			Class<?> type = field.getType();
			if (type == String.class) {
				value = cursor.getString(columnIndex);
			} else if (type == Integer.class) {
				value = cursor.getInt(columnIndex);
			} else if (type == Long.class) {
				value = cursor.getLong(columnIndex);
			} else if (type == Float.class) {
				value = cursor.getFloat(columnIndex);
			}
			field.setAccessible(true);
			try {
				field.set(t, value);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return t;
	}

	/**
	 * 根据列名和值查询在表中有多少条记录
	 * 
	 * @param table
	 * @param colums
	 * @param args
	 * @return >=0表示查询结果,-1表示查询出错，
	 */
	public static long getRecordCount(SQLiteDatabase db, String table, String[] colums, String[] args) {
		long result = 0;
		if (table == null || colums == null || args == null || colums.length != args.length) {
			return -1;
		}
		Cursor cursor = null;
		try {

			StringBuilder sb = new StringBuilder();
			int len = colums.length;
			for (int i = 0; i < len; i++) {
				if (i > 0) {
					sb.append(" and ");
				}
				sb.append(colums[i]).append("=? ");
			}
			String cols = sb.toString();
			String sql = "select count(" + colums[0] + ") as count from " + table + " where " + cols;
			cursor = db.rawQuery(sql, args);
			if (cursor.moveToNext()) {
				result = cursor.getLong(0);
			}
		} catch (Exception e) {
			result = -1;
		} finally {
			IOUtils.closeSilently(cursor);
		}
		return result;
	}


	public static void closeDB(SQLiteDatabase db) {
		if (db != null && db.isOpen()) {
			db.close();
		}
		db = null;
	}

	public static void closeQuietly(Cursor cursor) {
		if (cursor != null) {
			try {
				cursor.close();
			} catch (Throwable e) {
			}
			cursor = null;
		}
	}
}
