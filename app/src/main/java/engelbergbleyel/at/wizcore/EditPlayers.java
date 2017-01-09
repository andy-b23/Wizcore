package engelbergbleyel.at.wizcore;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.util.ArrayList;

public class EditPlayers extends AppCompatActivity{

    public final static String EXTRA_MESSAGE = "MESSAGE";
    private ListView obj;
    DBHelper mydb;
    Button addPlayerDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_players);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        /*FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/


        /*obj.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
                // TODO Auto-generated method stub
                int id_To_Search = array_ids.get(arg2);
                //Log.i("a","ITEM LV: "+arg0.getId()+" "+arg1.getId()+" "+arg2+" "+arg3);
                //Log.i("a","id: " +id_To_Search);

                Bundle dataBundle = new Bundle();
                dataBundle.putInt("id", id_To_Search);

                Intent intent = new Intent(getApplicationContext(), DisplayPlayer.class);

                intent.putExtras(dataBundle);
                startActivity(intent);
            }
        });

        addPlayerDB = (Button) findViewById(R.id.btn_addPlayerDB);
        addPlayerDB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), DisplayPlayer.class);
                Bundle dataBundle = new Bundle();
                dataBundle.putInt("id", 0);
                intent.putExtras(dataBundle);
                startActivity(intent);
            }
        });*/

        Button button = (Button)findViewById(R.id.btn_addPlayerDB);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), PlayerDetail.class);
                intent.putExtra("id",-1);
                startActivity(intent);
            }
        });
        buildTable();
    }

    public void buildTable() {

        TableLayout tableLayout = (TableLayout) findViewById(R.id.tbl_playerListEdit);
        DBHelper mydb = new DBHelper(this);

        Cursor rs = mydb.getAll();

        if (rs != null) {
            if (rs.moveToFirst()) {
                do {
                    final TableRow tableRow = new TableRow(this);
                    TextView all = new TextView(this);
                    TextView name = new TextView(this);
                    TextView score = new TextView(this);

                    String nam = rs.getString(rs.getColumnIndex(DBHelper.PLAYERS_COLUMN_NAME));
                    String high = rs.getString(rs.getColumnIndex(DBHelper.PLAYERS_COLUMN_HIGHSCORE));
                    String alltime = rs.getString(rs.getColumnIndex(DBHelper.PLAYERS_COLUMN_ALLTIMESCORE));

                    name.setText(nam);
                    name.setTextSize(20);

                    all.setText(alltime);
                    all.setTextSize(20);

                    score.setText(high);
                    score.setTextSize(20);

                    tableRow.addView(name);
                    tableRow.addView(all);
                    tableRow.addView(score);
                    tableRow.setId(rs.getInt(rs.getColumnIndex(DBHelper.PLAYERS_COLUMN_ID)));
                    tableRow.isClickable();
                    tableRow.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent intent = new Intent(getApplicationContext(), PlayerDetail.class);
                            intent.putExtra("id",tableRow.getId());
                            startActivity(intent);
                        }
                    });

                    TableLayout.LayoutParams tableRowParams= new TableLayout.LayoutParams(TableLayout.LayoutParams.MATCH_PARENT,TableLayout.LayoutParams.WRAP_CONTENT);
                    tableRowParams.setMargins(0, 0, 0, 10);
                    tableRow.setLayoutParams(tableRowParams);

                    tableLayout.addView(tableRow);

                } while (rs.moveToNext());
            }
        }
    }

    @Override
    public void onBackPressed() {
        NavUtils.navigateUpTo(this,getParentActivityIntent());
    }

}
