package br.senai.sp.informatica.jurossimples;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class JurosActivity extends AppCompatActivity {

    private EditText etCapitalInicial;
    private EditText etTaxaJurosMensal;
    private EditText etNumMeses;
    private EditText etCapitalResultante;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_juros);

        etCapitalInicial = (EditText)findViewById(R.id.etCapitalInicial);
        etTaxaJurosMensal = (EditText)findViewById(R.id.etTaxaJurosMensal);
        etNumMeses = (EditText)findViewById(R.id.etNumMeses);
        etCapitalResultante = (EditText)findViewById(R.id.etCapitalResultante);
    }

    public void OnClickCalc(View view) {

        try {
            double cap = Double.parseDouble(etCapitalInicial.getText().toString());
            double taxa = Double.parseDouble(etTaxaJurosMensal.getText().toString());
            double meses = Double.parseDouble(etNumMeses.getText().toString());

            double result = cap * taxa / 100 * meses + cap;

            etCapitalResultante.setText(String.valueOf(result));
            Toast.makeText(getBaseContext(),"Calculado " + result, Toast.LENGTH_LONG).show();
        } catch (Exception ex) {
            Toast.makeText(getBaseContext(),"Número(s) inválidos", Toast.LENGTH_LONG).show();
        }



    }

    public void onClickLimpar(View view){

        etCapitalInicial.setText("");
        etTaxaJurosMensal.setText("");
        etNumMeses.setText("");
        etCapitalResultante.setText("");

        Toast.makeText(getBaseContext(),"Limpo", Toast.LENGTH_LONG).show();

    }
}
