package com.gravata.netconsul.fragments;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AutoCompleteTextView;
import android.widget.Toast;

import com.gravata.netconsul.R;
import com.gravata.netconsul.adapter.DataAdapter;
import com.gravata.netconsul.adapter.combos.ClienteComboAdapter;
import com.gravata.netconsul.adapter.combos.SimNaoComboAdapter;
import com.gravata.netconsul.adapter.planilha.MockDeConteudo;
import com.gravata.netconsul.helper.HomeHelper;
import com.gravata.netconsul.model.Cliente;
import com.gravata.netconsul.model.Temperatura;

import java.util.ArrayList;

import fr.ganfra.materialspinner.MaterialSpinner;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link TemperaturaHome.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link TemperaturaHome#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TemperaturaHome extends HomeHelper<Temperatura> {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    private Cliente cliente;

    private MaterialSpinner campoCliente, campoAcaoCorretiva, campoData;
    private AutoCompleteTextView campoEquipamento, campoTemperatura;

    private SimNaoComboAdapter simNaoComboAdapter;
    private ClienteComboAdapter clienteComboAdapter;
    private DataAdapter dataAdapter;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment TemperaturaHome.
     */
    // TODO: Rename and change types and number of parameters
    public static TemperaturaHome newInstance(String param1, String param2) {
        TemperaturaHome fragment = new TemperaturaHome();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public TemperaturaHome() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);

            cliente= (Cliente) getArguments().getSerializable("cliente");


        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root= inflater.inflate(R.layout.fragment_temperatura_home, container, false);

        campoCliente = (MaterialSpinner) root.findViewById(R.id.temperatura_cliente);
        campoData = (MaterialSpinner) root.findViewById(R.id.temperatura_data);
        campoAcaoCorretiva = (MaterialSpinner) root.findViewById(R.id.temperatura_acao_corretiva);
        campoTemperatura = (AutoCompleteTextView) root.findViewById(R.id.temperatura_temperatura);
        campoEquipamento = (AutoCompleteTextView) root.findViewById(R.id.temperatura_equipamento);

        simNaoComboAdapter=new SimNaoComboAdapter(getActivity());
        dataAdapter=new DataAdapter(getActivity(),R.layout.spinner_item,new ArrayList<String>(), campoData);
        clienteComboAdapter=new ClienteComboAdapter(getActivity(), MockDeConteudo.CLIENTES);

        campoCliente.setAdapter(clienteComboAdapter);
        campoAcaoCorretiva.setAdapter(simNaoComboAdapter);
        campoData.setAdapter(dataAdapter);
        campoData.onLoadFinished(dataAdapter.getOnClickListener());
        return root;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(String uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (OnFragmentInteractionListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
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
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        public void onFragmentInteraction(String uri);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        menu.clear();
        inflater.inflate(R.menu.menu_salvar,menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()){
            case R.id.item_salvar:
                Toast.makeText(getActivity(),"salvou",Toast.LENGTH_SHORT).show();
                isValid();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
