package io.codefault.githubsummer;

import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import io.codefault.githubsummer.Fragments.Home;
import io.codefault.githubsummer.Fragments.Issue;
import io.codefault.githubsummer.Fragments.Organization;
import io.codefault.githubsummer.Fragments.PublicRepo;
import io.codefault.githubsummer.Fragments.PullRequest;
import io.codefault.githubsummer.Fragments.Repo;

public class Index extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private Menu menuBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_index);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        setUpDrawer(toolbar);
    }

    private void setUpDrawer(Toolbar toolbar) {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        InflateClick(R.id.nav_home);
        navigationView.getMenu().findItem(R.id.nav_home).setChecked(true);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.index, menu);
        menuBar = menu;
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

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        InflateClick(id);

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @SuppressWarnings("StatementWithEmptyBody")
    private void InflateClick(int id) {
        Fragment fragment = null;
        String title = "";

        if (id == R.id.nav_home) {
            fragment = new Home();
            title = "Home";
        } else if (id == R.id.nav_issues) {
            fragment = new Issue();
            title = "Issue";
        } else if (id == R.id.user_repo) {
            fragment = new Repo();
            title = "Repository";
        } else if (id == R.id.user_org) {
            fragment = new Organization();
            title = "Organisation";
        } else if (id == R.id.nav_pulls) {
            fragment = new PullRequest();
            title = "Pull request";
        }
        else if (id == R.id.public_repo) {
            fragment = new PublicRepo();
            title = "Public Repo";
        }

        if (fragment != null && !title.equals("")) {
            fragmentTransaction(fragment);
            getSupportActionBar().setTitle(title);
        }
    }

    private void fragmentTransaction(Fragment fragment) {
        FragmentManager manager = getSupportFragmentManager();

        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.content_frame, fragment);
        transaction.commit();
    }
}
