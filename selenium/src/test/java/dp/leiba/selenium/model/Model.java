package dp.leiba.selenium.model;

import dp.leiba.selenium.tools.Array;
import dp.leiba.selenium.tools.Db;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.HashMap;

/**
 * Model.
 */
abstract public class Model extends Db
{

    /**
     * Sql.
     */
    public static final String S_SELECT = "select * from %s %s order by %s limit %s";
    public static final String S_SHORT  = "select * from %s %s ";
    public static final String S_INSERT = "insert into %s (%s) values (%s)";
    public static final String S_UPDATE = "update %s set %s %s";
    public static final String S_DELETE = "delete from %s %s";
    public static final String S_WHERE  = "where %s";
    public static final String S_EMPTY  = "";
    public static final String S_RANDOM = "random()";

    /**
     * Select.
     *
     * @param table    Table.
     * @param criteria Criteria.
     * @param order    Order.
     * @param limit    Limit.
     *
     * @return List.
     *
     * @throws Exception
     */
    public ArrayList<HashMap<String, String>> mSelect(
        String table,
        HashMap<String, String> criteria,
        String order,
        int limit
    ) throws Exception {
        return mSelect(new Formatter().format(
            S_SELECT,
            table,
            mWhere(criteria),
            order,
            limit
        ).toString());
    }

    public ArrayList<HashMap<String, String>> mSelect(
        String table,
        HashMap<String, String> criteria,
        HashMap<String, String[]> in,
        HashMap<String, String[]> not
    ) throws Exception {
        return mSelect(new Formatter().format(
            S_SHORT,
            table,
            mWhere(criteria, in, not)
        ).toString());
    }

    /**
     * Select.
     *
     * @param sql Sql.
     *
     * @return Result.
     */
    public ArrayList<HashMap<String, String>> mSelect(String sql)
        throws Exception
    {
        int i, count;
        ResultSetMetaData meta;
        HashMap row;

        ArrayList list = new ArrayList();
        ResultSet set  = Db.con().createStatement().executeQuery(sql);

        meta  = set.getMetaData();
        count = meta.getColumnCount();

        while (set.next()) {
            row = new HashMap();

            for(i = 1; i <= count; ++i){
                row.put(meta.getColumnName(i), set.getString(i));
            }

            list.add(row);
        }

        return list;
    }

    /**
     * Insert.
     *
     * @param table      Table.
     * @param attributes Attributes.
     *
     * @throws Exception
     */
    public static void mInsert(String table, HashMap<String, String> attributes)
        throws Exception
    {
        Formatter form  = new Formatter();

        Db.con().createStatement().executeUpdate(
            form.format(
                S_INSERT,
                table,
                Array.implode(",", "", attributes.keySet()),
                Array.implodeNull(",", "'", attributes.values())
            ).toString()
        );
    }

    /**
     * Update.
     *
     * @param table      Table.
     * @param attributes Attributes.
     * @param criteria   Criteria.
     */
    public void mUpdate(
        String table,
        HashMap<String, String> attributes,
        HashMap<String, String> criteria
    ) throws Exception {
        Formatter form  = new Formatter();

        Db.con().createStatement().executeUpdate(
            form.format(
                S_UPDATE,
                table,
                mSet(attributes),
                mWhere(criteria)
            ).toString()
        );
    }

    /**
     * Delete.
     *
     * @param table    Table.
     * @param criteria Criteria.
     */
    public void mDelete(
        String table,
        HashMap<String, String> criteria
    ) throws Exception {
        Formatter form = new Formatter();

        Db.con().createStatement().executeUpdate(
            form.format(
                S_DELETE,
                table,
                mWhere(criteria)
            ).toString()
        );
    }

    /**
     * Where.
     *
     * @param criteria Criteria.
     *
     * @return Where.
     */
    public String mWhere(HashMap<String, String> criteria) {
        return mWhere(
            criteria,
            new HashMap<String, String[]>(),
            new HashMap<String, String[]>()
        );
    }

    /**
     * Where.
     *
     * @param criteria Criteria.
     *
     * @return Where.
     */
    public String mWhere(
        HashMap<String, String> criteria,
        HashMap<String, String[]> in,
        HashMap<String, String[]> not
    ) {
        ArrayList<String> wheres = new ArrayList<String>();
        Formatter form  = new Formatter();

        for (String key : Array.getArray(criteria.keySet())) {
            wheres.add(key + "='" + criteria.get(key) + "'");
        }

        for (String key : Array.getArray(in.keySet())) {
            if (in.get(key).length == 0) {
                in.put(key, new String[] {"0"});
            }

            wheres.add(key + " in ('" + Array.implode("','", in.get(key)) + "')");
        }

        for (String key : Array.getArray(not.keySet())) {
            if (not.get(key).length == 0) {
                not.put(key, new String[] {"0"});
            }

            wheres.add(key + " not in ('" + Array.implode("','", not.get(key)) + "')");
        }


        return wheres.size() > 0 ?
            form.format(S_WHERE, Array.implode(" and ", wheres.toArray(new String[wheres.size()]))).toString() :
            S_EMPTY;
    }


    /**
     * Set.
     *
     * @param attributes Attributes.
     *
     * @return Set.
     */
    public String mSet(HashMap<String, String> attributes)
    {
        int i;
        String[] sets = Array.getArray(attributes.keySet());

        if (attributes.size() > 0) {
            for (i = 0; i < sets.length; i++) {
                sets[i] = sets[i] + "='" + attributes.get(sets[i]) + "'";
            }
        }

        return Array.implode(",", sets);
    }

    /**
     * As models.
     *
     * @param list List.
     * @param type Class type.
     *
     * @return As models.
     */
    public <T> T[] mModels(ArrayList<HashMap<String, String>> list, Class<T> type)
        throws Exception
    {
        int i = 0, modifier;
        T model;
        T[] models = (T[]) java.lang.reflect.Array.newInstance(type, list.size());

        for (HashMap<String, String> map : list) {
            model = type.newInstance();

            for (Field field : model.getClass().getFields()) {
                modifier = field.getModifiers();

                if (!Modifier.isStatic(modifier) && Modifier.isPublic(modifier)) {
                    field.set(model, map.get(field.getName()));
                }
            }

            models[i++] = model;
        }

        return models;
    }
}
