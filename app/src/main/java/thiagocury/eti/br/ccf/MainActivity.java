package thiagocury.eti.br.ccf;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.graphics.drawable.DrawerArrowDrawable;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

public class MainActivity extends AppCompatActivity {

    //treta di menu
    private DrawerArrowDrawable result = null;

    //Tretas do xml
    private Button btnCadastrar;
    private Button btnProcurar;
    private Button btnSobre;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnCadastrar = (Button)findViewById(R.id.btnCadastrar);
        btnProcurar = (Button)findViewById(R.id.btnProcurar);
        btnSobre = (Button)findViewById(R.id.btnSobre);

        //Propaganda
        AdView mAdView = (AdView) findViewById(R.id.adViewTelaInicial);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        btnCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(MainActivity.this, NewCadastroCliente.class);
                startActivity(it);
            }//fecha on click
        });//fecha onclicklistener

        btnProcurar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(MainActivity.this, NewBuscaCliente.class);
                startActivity(it);
            }//fecha onClick
        });//fecha setOnClicklistener

        btnSobre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(MainActivity.this, Sobre.class);
                startActivity(it);
            }//fecha onClick
        });//fecha setOnClicklistener
    }//fecha oncreate
}//fecha classe