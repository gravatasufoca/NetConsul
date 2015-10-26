package com.gravata.netconsul.fragments;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.ActionMode;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import com.gravata.netconsul.R;
import com.gravata.netconsul.adapter.TemperaturaListaAdapter;
import com.gravata.netconsul.fragments.dummy.DummyContent;
import com.gravata.netconsul.model.Cliente;
import com.gravata.netconsul.model.Temperatura;

/**
 * A fragment representing a list of Items.
 * <p/>
 * Large screen devices (such as tablets) are supported by replacing the ListView
 * with a GridView.
 * <p/>
 * Activities containing this fragment MUST implement the {@link OnFragmentInteractionListener}
 * interface.
 */
public class TemperaturaListaFragment extends Fragment implements AbsListView.OnItemLongClickListener,AbsListView.OnItemClickListener {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    private Cliente cliente;

    /**
     * The fragment's ListView/GridView.
     */
    private AbsListView mListView;

    /**
     * The Adapter which will be used to populate the ListView/GridView with
     * Views.
     */
    private TemperaturaListaAdapter mAdapter;

    private AutoCompleteTextView cCliente;
    private Button btnPesquisar;

    private ActionMode actionMode;

    private View selecionado;


    // TODO: Rename and change types of parameters
    public static TemperaturaListaFragment newInstance(String param1, String param2) {
        TemperaturaListaFragment fragment = new TemperaturaListaFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public TemperaturaListaFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);

        getActivity().setTitle(R.string.temperaturas);

        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);

            cliente= (Cliente) getArguments().getSerializable("cliente");
        }

        // TODO: Change Adapter to display your content
        mAdapter = new TemperaturaListaAdapter(this.getActivity(),cliente.getTemperaturas());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_temperatura, container, false);

        cCliente = (AutoCompleteTextView) view.findViewById(R.id.temperatura_pesquisa_cliente);
        btnPesquisar= (Button) view.findViewById(R.id.btnPesquiasr);

        if(cliente!=null){
            cCliente.setText(cliente.getNomeFantasia());
        }


        // Set the adapter
        mListView = (AbsListView) view.findViewById(android.R.id.list);
        ((AdapterView<ListAdapter>) mListView).setAdapter(mAdapter);

        // Set OnItemClickListener so we can be notified on item clicks
        mListView.setOnItemLongClickListener(this);
        mListView.setChoiceMode(AbsListView.CHOICE_MODE_SINGLE);

        return view;
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

    @Override
    public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
        selecionado=view;
        mListView.setItemChecked(position,true);
        mAdapter.setSelecionado(position);

        mListView.startActionMode(new ActionModeCallBack(this.getActivity(), mAdapter.getItem(position)));
        mAdapter.notifyDataSetChanged();
        return true;
    }

    /**
     * The default content for this Fragment has a TextView that is shown when
     * the list is empty. If you would like to change the text, call this method
     * to supply the text it should use.
     */
    public void setEmptyText(CharSequence emptyText) {
        View emptyView = mListView.getEmptyView();

        if (emptyView instanceof TextView) {
            ((TextView) emptyView).setText(emptyText);
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
        Temperatura temperatura=mAdapter.getItem(position);
        Bundle args=new Bundle();
        args.putSerializable("temperatura",temperatura);
        abrirHome(args);
    }

    private  void abrirHome( Bundle args){
        Fragment temperaturasFrag = new TemperaturaHome();
        args.putSerializable("cliente", cliente);
        temperaturasFrag.setArguments(args);
        getActivity().getFragmentManager().beginTransaction().replace(R.id.content_main, temperaturasFrag,"").commit();
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
        public void onFragmentInteraction(String id);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        menu.clear();
        inflater.inflate(R.menu.temperatura_lista_menu, menu);
       super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()){
            case R.id.item_menu_add_temperatura:
                abrirHome(new Bundle());
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    private class ActionModeCallBack implements ActionMode.Callback{
        private Temperatura temperatura;
        private  Activity activity;
        public ActionModeCallBack(Activity activity, Temperatura temperatura) {
            this.temperatura=temperatura;
            this.activity=activity;
        }

        @Override
        public boolean onCreateActionMode(ActionMode mode, Menu menu) {
            mode.getMenuInflater().inflate(R.menu.lista_cliente_menu, menu);
            return true;
        }

        @Override
        public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
            return false;
        }

        @Override
        public boolean onActionItemClicked(ActionMode mode, MenuItem item) {

            switch (item.getItemId()){
                case R.id.item_menu_add_planilha:
                    Toast.makeText(activity,R.string.planilhas,Toast.LENGTH_SHORT).show();
                    break;
                case R.id.item_menu_add_temperatura:
                   abrirHome(new Bundle());

                    break;
            }
            return true;
        }

        @Override
        public void onDestroyActionMode(ActionMode mode) {
            actionMode=null;
            mAdapter.setSelecionado(-1);
            mAdapter.notifyDataSetChanged();
        }
    }
}
