package android.joelmena.imcapp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import org.w3c.dom.Text;

public class ImcFragment extends Fragment {
    public static final String KEY_IMC = "android.joelmena.imcapp.imc";
    private String Imc;
    private String aux;
    private EditText mCampoPeso;
    private EditText mCampoEstatura;
    private Button mBotonCalcular;
    private Button mBotonLimpiar;
    private TextView mEtiquetaImc;
    private TextView mSituacionNutricional;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_imc, container, false);

        //para recuperarlo del savedInstatnteState entonces
        if (savedInstanceState == null){
            Imc = " ";
            aux = " ";
            //no se que hacer aquí
        }
        else {
            Imc= savedInstanceState.getString(KEY_IMC);
            aux = savedInstanceState.getString(KEY_IMC);
            //tampoco aquí
        }

            mCampoPeso = v.findViewById(R.id.campo_peso);
            mCampoEstatura = v.findViewById(R.id.campo_estatura);
            mBotonCalcular = v.findViewById(R.id.boton_calcular);
            mBotonLimpiar = v.findViewById(R.id.boton_limpiar);
            mEtiquetaImc = v.findViewById(R.id.etiqueta_imc);
            mSituacionNutricional = v.findViewById(R.id.etiqueta_situacion);

            mBotonCalcular.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String s = mCampoPeso.getText().toString();
                    double peso = Double.parseDouble(s);
                    s = mCampoEstatura.getText().toString();
                    double estatura = Double.parseDouble(s);
                    double imc = peso / (estatura*estatura);
                    s = String.format("%2.2f", imc);
                    mEtiquetaImc.setText(s);

                    if (imc < 18.5){
                        mSituacionNutricional.setText(R.string.texto_situacion_peso_bajo);
                    }
                    else if (imc < 25.0){
                        mSituacionNutricional.setText(R.string.texto_situacion_peso_normal);
                    }
                    else if (imc < 30.0){
                        mSituacionNutricional.setText(R.string.texto_situacion_sobrepeso);
                    }
                    else if (imc < 40.0){
                        mSituacionNutricional.setText(R.string.texto_situacion_obesidad);
                    }
                    else{
                        mSituacionNutricional.setText(R.string.texto_situacion_obesidad_extrema);
                    }
                }
            });

            mBotonLimpiar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mCampoPeso.setText(" ");
                    mCampoEstatura.setText(" ");
                    mEtiquetaImc.setText("0.0");
                    mSituacionNutricional.setText(" ");
                }
            });

        return v;
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(KEY_IMC, Imc);
        outState.putString(KEY_IMC, aux);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
}
