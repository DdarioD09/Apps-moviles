package google.example.location.locationexm;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button btnSitios, btnMapas, btnUbicacion;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnSitios = (Button) findViewById(R.id.btn_sitios);
        btnMapas = (Button) findViewById(R.id.btn_mapas);
        btnUbicacion = (Button) findViewById(R.id.btn_ubicacion);

        btnSitios.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),MapsActivity.class);
                startActivity(intent);
            }
        });
    }

    /*Forma alternativa de realizar la apertura de otra vista (mapa) en el btnMapas
    (configurado en el layout de activity_main buscando su respectivo boton)*/
    public void  MapaSitios(View view){
        Intent intent = new Intent(getApplicationContext(),MapsActivityTipos.class);
        startActivity(intent);
    }

    //Metodo de mi localizacion
    public void MyLocation(View view){
        Intent intent = new Intent(getApplicationContext(),MapsActivityMyLocation.class);
        startActivity(intent);
    }
}
