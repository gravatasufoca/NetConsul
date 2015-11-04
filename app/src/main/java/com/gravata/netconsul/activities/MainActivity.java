package com.gravata.netconsul.activities;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.gravata.netconsul.R;
import com.gravata.netconsul.authenticator.AutenticarUsuario;
import com.gravata.netconsul.dao.OrmLiteFragmentActivity;
import com.gravata.netconsul.fragments.ClienteListaFragment;
import com.gravata.netconsul.fragments.HomeFragment;
import com.gravata.netconsul.fragments.RespostaPlanilhaFragment;
import com.gravata.netconsul.fragments.TemperaturaHome;
import com.gravata.netconsul.fragments.TemperaturaListaFragment;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener,
        RespostaPlanilhaFragment.OnFragmentInteractionListener,
        HomeFragment.OnFragmentInteractionListener,
        ClienteListaFragment.OnFragmentInteractionListener,
        TemperaturaListaFragment.OnFragmentInteractionListener,
        TemperaturaHome.OnFragmentInteractionListener{


    public static int CLIENTE_LISTA_TEMPERATURA=1;
    public static int TEMPERATURA_ADD=2;
    public static int TEMPERATURA_DEL=3;

    public static String CLIENTE_LISTA="CLIENTE_LISTA";
    public static String TEMPERATURA_LISTA="TEMPERATURA_LISTA";
    public static String PLANINHA_LISTA="PLANINHA_LISTA";
    public static String TEMPERATURA_HOME="TEMPERATURA_HOME";
    public static String PLANILHA_HOME="PLANILHA_HOME";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        getFragmentManager().beginTransaction().replace(R.id.content_main, new HomeFragment(), "home").addToBackStack("home").commit();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        }if(getFragmentManager().getBackStackEntryCount()>0){
            getFragmentManager().popBackStack();
        }
        else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
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

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_clientes) {
            getFragmentManager().beginTransaction().replace(R.id.content_main,new ClienteListaFragment(),CLIENTE_LISTA).addToBackStack(CLIENTE_LISTA).commit();
        } else if (id == R.id.nav_gallery) {
            getFragmentManager().beginTransaction().replace(R.id.content_main, new RespostaPlanilhaFragment(), PLANINHA_LISTA).addToBackStack(PLANINHA_LISTA).commit();

        } else if (id == R.id.nav_slideshow) {
            getFragmentManager().beginTransaction().replace(R.id.content_main, new TemperaturaListaFragment(), TEMPERATURA_LISTA).addToBackStack(TEMPERATURA_LISTA).commit();


        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    @Override
    public void onFragmentInteraction(String id) {

    }
}
