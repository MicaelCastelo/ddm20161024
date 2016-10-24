package com.micaelcastelo.esgts_2016_10_24_nrtfav;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputFilter;
import android.text.InputType;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.HashSet;
import java.util.Set;

public class MainActivity extends AppCompatActivity {
    static final  int  Num_Digitos_Telemovel_PT = 9;
    Context mContext;
    Button mbtnEntrarTelefoneFav;
    EditText metEntrarTelefoneFav;
    View.OnClickListener mTratadorDeClicksNoButtonParaEntrarTelefoneFav;
    Set<Long> mSetTelefonesFav;

    private void personalizarEditTextParaEntradaDeInteiros(EditText et, int iMaxDigitos) {
        //et.setInputType(InputType.TYPE_TEXT_FLAG_NO_SUGGESTIONS);
    //    et.setInputType(InputType.TYPE_CLASS_PHONE);//aceita virgulas e eu n gosto
        et.setInputType(InputType.TYPE_NUMBER_FLAG_DECIMAL); //só aceita digitos  0~9
        InputFilter [] filtros = new InputFilter[1];
        filtros[0] = new InputFilter.LengthFilter(iMaxDigitos);
        et.setFilters(filtros);

    }
    void utilFb (String msg){

        Toast t = Toast.makeText(
                mContext,
                msg,
                Toast.LENGTH_LONG
        );
        t.show();
    }//utilFb

        boolean adicionarTelefoneFav(){
            //consultar a et que tem o telefone a adicionar
         String strTelCandidato =   metEntrarTelefoneFav.getText().toString();

            try{
                Long novoTelefone = Long.parseLong(strTelCandidato);
                return true;
            }
            catch (Exception e){
                String strCorreuMalPorque =e.getMessage().toString();
                utilFb (strCorreuMalPorque);
                return false;
            }//catch

        }//adicionarTelFav
    void init(){
        //Nao podemos fazer new set long porque set é uma class abstrata
        mSetTelefonesFav = new HashSet<Long>();
        mbtnEntrarTelefoneFav = (Button) findViewById(R.id.id_btnEntrarTelefoneFav);
        metEntrarTelefoneFav = (EditText) findViewById(R.id.id_etEntrarTelefoneFav);

        personalizarEditTextParaEntradaDeInteiros(metEntrarTelefoneFav, Num_Digitos_Telemovel_PT);

        mTratadorDeClicksNoButtonParaEntrarTelefoneFav = new OnClickListener(){

            @Override
            public void onClick(View v) {
                adicionarTelefoneFav();
            }//onClick
        };//instanciação
        mbtnEntrarTelefoneFav.setOnClickListener(mTratadorDeClicksNoButtonParaEntrarTelefoneFav);
    }//init



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }//onCreate
}//main activity
