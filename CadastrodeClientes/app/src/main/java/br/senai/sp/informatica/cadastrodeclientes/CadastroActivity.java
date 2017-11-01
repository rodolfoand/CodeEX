package br.senai.sp.informatica.cadastrodeclientes;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class CadastroActivity extends AppCompatActivity implements View.OnClickListener{

    Button btTela3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_cadastro);

        btTela3 = (Button)this.findViewById(R.id.btTela3);
        btTela3.setOnClickListener(this);

    }


    public void onClickVai(View view) {
        Intent intent = new Intent(this, TelaSecundaria.class);
        startActivity(intent);

        //String msg = "Toast";

        String msg = getResources().getString(R.string.msg, "Tela 2");

        Toast.makeText(getBaseContext(),msg, Toast.LENGTH_LONG).show();

    }

    public void onClick(View view){
        Intent intent = new Intent(this, TerceiraTela.class);
        startActivity(intent);

        String msg = getResources().getString(R.string.msg, "Tela 3");

        Toast.makeText(getBaseContext(),msg, Toast.LENGTH_LONG).show();
    }
}
