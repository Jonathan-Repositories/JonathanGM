package com.jonathan.jonathan;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class Segundos extends AppCompatActivity {

    ArrayList<Empleado> lista;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_segundos);
        LinearLayout vista = (LinearLayout) findViewById(R.id.vista);
        Conexion conn = new Conexion(this, "bdd_empleados", null, 1);

        SQLiteDatabase db=conn.getWritableDatabase();
        db.execSQL("DELETE FROM empleados");
        db.execSQL("VACUUM");

        ContentValues val1=new ContentValues();
        val1.put("id", "1");
        val1.put("nombre", "Miguel Cervantes");
        val1.put("fecha", "08-Dic-1990");
        val1.put("puesto","Desarrollador");
        db.insert("empleados","id",val1);

        ContentValues val2=new ContentValues();
        val2.put("id", "2");
        val2.put("nombre", "Juan Morales");
        val2.put("fecha", "03-Jul-1990");
        val2.put("puesto","Desarrollador");
        db.insert("empleados","id",val2);

        ContentValues val3=new ContentValues();
        val3.put("id", "3");
        val3.put("nombre", "Roberto Méndez");
        val3.put("fecha", "14-Oct-1990");
        val3.put("puesto","Desarrollador");
        db.insert("empleados","id",val3);

        ContentValues val4=new ContentValues();
        val4.put("id", "4");
        val4.put("nombre", "Miguel Cuevas");
        val4.put("fecha", "0-Dic-1990");
        val4.put("puesto","Desarrollador");
        db.insert("empleados","id",val4);

        db.close();

        SQLiteDatabase sq = conn.getReadableDatabase();
        Empleado emp=null;
        try {
            Cursor cursor = sq.rawQuery("SELECT id,nombre,fecha,puesto FROM empleados", null);
            //Toast.makeText(getApplicationContext(), ""+cursor.getCount(), Toast.LENGTH_LONG).show();
            cursor.moveToFirst();

            do{
                TextView txt = new TextView(this);
                txt.setText(cursor.getString(0)+ " | " +cursor.getString(1)+ " | " +cursor.getString(2)+ " | " +cursor.getString(3));
                vista.addView(txt);
            }while (cursor.moveToNext());

        }catch (Exception e){
            Toast.makeText(getApplicationContext(),"No hay registro",Toast.LENGTH_LONG).show();
        }


        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT );

        Button button = new Button(this);
        button.setLayoutParams(params);
        button.setText("Crear Hilo");
        button.setOnClickListener(new Pulsado());
        vista.addView(button);
    }

    class Pulsado implements View.OnClickListener
    {
        @Override
        public void onClick(View v)
        {
            Dos hilo = new Dos();
            hilo.start();
        }

    };
    class Dos extends Thread {
        @Override
        public void run(){
            try {
                Thread.sleep(10000);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Hilo h = new Hilo();
                        Toast.makeText(getApplicationContext(), h.Mensaje(), Toast.LENGTH_SHORT).show();
                    }
                });
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
