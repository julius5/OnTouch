package denkensfj.mx.ontouch;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageButton;
import android.widget.Switch;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    public int i=1,f=1;
    SoundManager sound;
    int sonido,gemido;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        final TextView show = (TextView) findViewById(R.id.txtPrincipal);
        final ImageButton touchh = (ImageButton) findViewById(R.id.imageButton);

        touchh.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                /*
                switch (event.getAction()){
                    case MotionEvent.ACTION_DOWN:
                        show.setTextSize(40);
                        show.setText("has precionado la pantalla");
                        return true;
                    case MotionEvent.ACTION_UP:
                        show.setTextSize(80);
                        show.setText("has soltado la pantalla");
                        return true;
                }
                */

                String names[];
                names = new String[] {"Hola","Sigue con este pedo","Porque quiero decirte","que despues de un rato",
                        "picandole a esta madre","va a ocurrir algo","muy interesante", "porque yo", "te voy a matar",
                        "no me crees?", "mira tras de ti..",""};

                // Creamos una instancia de SoundManager
                sound = new SoundManager(getApplicationContext());
                sonido = sound.load(R.raw.grito);
                gemido = sound.load(R.raw.gemido);

                Log.d("valor",names[i]);
                if (event.getAction()==MotionEvent.ACTION_DOWN) {
                    if(names[i]!=""){
                        show.setText(names[i]);
                        i++;
                    }else{
                        show.setText("se acabo");
                        //touchh.setBackgroundResource(R.drawable.ic_launcher_new);
                        sound.play(sonido);
                        touchh.setImageResource(R.drawable.ic_launcher_new);
                    }
                }

                return false;
            }
        });


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(f==1){
                    Snackbar.make(view, "No toques de nuevo este boton", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                    f++;
                }else if(f==2){
                    Snackbar.make(view, "Te lo advierto ultima oporunidad", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                    f++;
                }else{
                    Snackbar.make(view, "Te lo adverti", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                    sound.play(gemido);
                }

            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
