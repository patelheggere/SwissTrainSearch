package patelheggere.trainsearch.data;

import android.provider.BaseColumns;

/**
 * Created by Patel Heggere on 2/4/2018.
 */

public class SQLTableCreater  {
    SQLTableCreater(){}
    public static class TrainConnections implements BaseColumns {
        public static final String TABLE_NAME = "trainconnections";
        public static final String COLUMN_NAME_CONNECTIONS = "connections";
    }
}
