package com.example.unbegrenzt.fisicab;

import android.content.Context;
import android.content.DialogInterface;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.RequiresApi;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import static com.example.unbegrenzt.fisicab.R.id.textView;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link momentum_1.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link momentum_1#newInstance} factory method to
 * create an instance of this fragment.
 */
public class momentum_1 extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private String[]datossp = {"-Seleccione-","momentum","masa","velocidad"};
    private EditText txt_masa;
    private EditText txt_veloc;
    private EditText txt_ang;
    private EditText txt_grav;
    private EditText txt_momentum;
    private FloatingActionButton save1;
    private FloatingActionButton load1;
    private EditText txt_int;
    private Button btn_cal;
    private Spinner sp_opc;


    private OnFragmentInteractionListener mListener;

    public momentum_1() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment momentum_1.
     */
    // TODO: Rename and change types and number of parameters
    public static momentum_1 newInstance(String param1, String param2) {
        momentum_1 fragment = new momentum_1();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        super.onCreateView(inflater, container, savedInstanceState);
        View v =  inflater.inflate(R.layout.fragment_momentum_1, container, false);
        if(v != null){
            txt_masa = (EditText) v.findViewById(R.id.txt_masa);
            txt_veloc = (EditText) v.findViewById(R.id.txt_veloc);
            txt_momentum = (EditText) v.findViewById(R.id.txt_momento);
            txt_ang = (EditText) v.findViewById(R.id.txt_ang);
            btn_cal = (Button) v.findViewById(R.id.btn_cal);
            sp_opc = (Spinner) v.findViewById(R.id.spinner);
            txt_grav = (EditText)v.findViewById(R.id.txt_grav);
            txt_int = (EditText) v.findViewById(R.id.txt_int);
            save1 = (FloatingActionButton)v.findViewById(R.id.save1);
            load1 = (FloatingActionButton)v.findViewById(R.id.load1);
            ArrayAdapter adaptor = new ArrayAdapter(getActivity().getApplicationContext(), R.layout.spiner_theme, datossp);
            sp_opc.setAdapter(adaptor);
        }
        return v;
    }

    @Override
    public void onViewCreated(final View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        load1.setOnClickListener(new View.OnClickListener()
        {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View v) {
                try
                {
                    AlertDialog.Builder builder = new AlertDialog.Builder(getContext());

                    builder.setTitle("Información");

                    builder.setMessage("Cargar los datos guardados?")
                            .setCancelable(false)
                            .setPositiveButton("Si", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    InputStream fin = getActivity().getResources().openRawResource(R.raw.momento);
                                    BufferedReader reader = new BufferedReader(new InputStreamReader(fin));
                                    String texto = "";
                                    String cadena = "";
                                    if(fin != null) {
                                        try {
                                            while ((cadena = reader.readLine()) != null) {
                                                texto = texto.concat(cadena + "!");
                                            }
                                            String[] cad = texto.split("!");
                                            txt_masa.setText(cad[0]);
                                            txt_veloc.setText(cad[1]);
                                            txt_momentum.setText(cad[2]);
                                            txt_ang.setText(cad[3]);
                                            txt_grav.setText(cad[4]);
                                            txt_int.setText(cad[5]);
                                            Log.i("valor",texto);
                                        }catch (Exception e){
                                            Log.i("error1",e.getMessage());
                                        }
                                    }
                                    try {
                                        fin.close();
                                    } catch (IOException e) {
                                        e.printStackTrace();
                                    }
                                }
                            })
                            .setNegativeButton("No", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    dialog.cancel();
                                }
                            }).show();

                }
                catch (Exception ex)
                {
                    Log.e("Ficheros", "Error al leer fichero desde la memoria interna");
                }

            }
        });

        save1.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v) {
                try {
                    AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                    builder.setTitle("Información");
                    builder.setMessage("Cargar los datos guardados?")
                            .setCancelable(false)
                            .setPositiveButton("Si", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {

                                }
                            })
                            .setNegativeButton("No", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    dialog.cancel();
                                }
                            }).show();
                }catch (Exception e){

                }
            }
        });

        btn_cal.setOnClickListener(new View.OnClickListener()
        {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View v) {
                Float m;
                Float veloc;
                Float moment;
                Float resul;
                switch (sp_opc.getSelectedItemPosition())
                {

                    case 0: {
                        Snackbar.make(view, "Seleccione una opción a calcular", Snackbar.LENGTH_LONG)
                                .setAction("Action", null).show();
                        break;
                    }

                    case 1: {

                        if (!(txt_masa.length() == 0) && !(txt_veloc.length() == 0)) {
                            m = Float.valueOf(String.valueOf(txt_masa.getText()));
                            veloc = Float.valueOf(String.valueOf(txt_veloc.getText()));
                            resul = m * veloc;
                            /*solucion.setText(
                                    "Datos recibidos:\n\n"
                                            + "masa = " + Float.toString(m) + " Kg\n"
                                            + "velocidad = " + Float.toString(veloc) + " m/s\n"
                                            + "momentum = ?\n\n"
                                            + "momentum = " + Float.toString(m) + " Kg * " + Float.toString(veloc) + " m/s\n"
                                            + "momentum = " + Float.toString(resul) + " Kg*m/s");*/
                            txt_momentum.setText(Float.toString(resul));
                        } else {
                            Snackbar.make(view, "Faltan datos, masa ó velocidad", Snackbar.LENGTH_LONG)
                                    .setAction("Action", null).show();
                        }
                        break;
                    }

                    case 2: {
                        if (!(txt_momentum.length() == 0) && !(txt_veloc.length() == 0)) {
                            veloc = Float.valueOf(String.valueOf(txt_veloc.getText()));
                            moment = Float.valueOf(String.valueOf(txt_momentum.getText()));
                            resul = moment / veloc;
                            /*solucion.setText(
                                    "Datos recibidos:\n\n"
                                            + "masa = ?\n"
                                            + "velocidad = " + Float.toString(veloc) + " m/s\n"
                                            + "momentum =" + Float.toString(moment) + " Kg*m/s\n\n"
                                            + "masa = " + Float.toString(moment) + " Kg*m/s / " + Float.toString(veloc) + " m/s\n"
                                            + "masa = " + Float.toString(resul) + " Kg");*/
                            txt_masa.setText(Float.toString(resul));
                        } else {
                            Snackbar.make(view, "Faltan datos, momentum ó velocidad", Snackbar.LENGTH_LONG)
                                    .setAction("Action", null).show();
                        }
                        break;
                    }

                    case 3: {
                        if (!(txt_momentum.length() == 0) && !(txt_masa.length() == 0)) {
                            moment = Float.valueOf(String.valueOf(txt_momentum.getText()));
                            m = Float.valueOf(String.valueOf(txt_masa.getText()));
                            resul = moment / m;
                            /*solucion.setText(
                                    "Datos recibidos:\n\n"
                                            + "masa = " + Float.toString(m) + " Kg\n"
                                            + "velocidad = ?\n"
                                            + "momentum = " + Float.toString(moment) + " Kg*m/s\n\n"
                                            + "velocidad = " + Float.toString(moment) + " Kg*m/s / " + Float.toString(m) + " Kg \n"
                                            + "velocidad = " + Float.toString(resul) + " m/s");*/
                            txt_veloc.setText(Float.toString(resul));
                        } else {
                            Snackbar.make(view, "Faltan datos, momentum o masa", Snackbar.LENGTH_LONG)
                                    .setAction("Action", null).show();
                        }
                        break;
                    }

                    default:{
                        Snackbar.make(view, "Error no conocido", Snackbar.LENGTH_LONG)
                                .setAction("Action", null).show();
                    }
                }
                if(!(txt_ang.length() == 0) && !(txt_grav.length() == 0) && !(txt_veloc.length() == 0)){
                    Snackbar.make(view, "Ver gráfica", Snackbar.LENGTH_LONG)
                            //.setActionTextColor(Color.CYAN)
                            .setActionTextColor(getResources().getColor(R.color.snackbar_action))
                            .setAction("Presioname", new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    Fragment estado = ejercicio_1.newInstance(Double.parseDouble(String.valueOf(txt_veloc.getText())),
                                            Double.parseDouble(String.valueOf(txt_ang.getText())),Double.parseDouble(String.valueOf(txt_grav.getText())),0,0,Double.parseDouble(String.valueOf(txt_int.getText())));
                                    getActivity().getSupportFragmentManager().beginTransaction()
                                            .replace(R.id.content_main, estado).addToBackStack(null)
                                                .commit();
                                }
                            })
                            .show();
                }
            }
        });
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
