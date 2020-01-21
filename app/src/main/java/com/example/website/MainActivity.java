package com.example.website;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import android.Manifest;
import android.app.AlertDialog;
import android.content.ActivityNotFoundException;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity {

    private DrawerLayout drawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawerLayout = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        NavigationView navigationView = findViewById(R.id.navigation_view);
        navigationView.setNavigationItemSelectedListener(nav);

        BottomNavigationView bottomNavigationView = findViewById(R.id.Bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(navigationItemSelectedListener);
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new Fragment_home()).commit();
    }


    private BottomNavigationView.OnNavigationItemSelectedListener navigationItemSelectedListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                    Fragment selectedFragment = null;
                    switch (menuItem.getItemId()) {
                        case R.id.bottom_home:
                            selectedFragment = new Fragment_home();
                            break;
                        case R.id.bottom_groups:
                            selectedFragment = new Fragment_groups();
                            break;
                        case R.id.bottom_ques:
                            selectedFragment = new Fragment_Q_A();
                            break;
                        case R.id.bottom_write:
                            selectedFragment = new Fragment_Write();
                            break;

                    }
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, selectedFragment).commit();
                    return true;
                }
            };
    private NavigationView.OnNavigationItemSelectedListener nav = new NavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.about_us:
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new Fragment_about_us()).commit();
                    break;
                case R.id.contact_us:
                    contactUs();
                    //getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new Fragment_contact_us()).commit();
                    break;
                case R.id.join_us:
              Alertdialog();
                    break;
                case R.id.faq:
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new Fragment_faq()).commit();
                    break;
                case R.id.donate:
                    Toast.makeText(MainActivity.this, "donate", Toast.LENGTH_SHORT).show();
                    break;
                case R.id.share:
                    share();
                    break;
                case R.id.logout:
                    logout();
                    break;
            }
            drawerLayout.closeDrawer(GravityCompat.START);
            return true;
        }
    };

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }
    public void Alertdialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Apply For :");
        String[] items = {"1. Mentor at FreshlyBuilt","2. Internship at FreshlyBuilt"};
        builder.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch(which){
                    case 0:
                        Intent intent1 = new Intent();
                        intent1.setAction(Intent.ACTION_VIEW);
                        intent1.addCategory(Intent.CATEGORY_BROWSABLE);
                        intent1.setData(Uri.parse("https://freshlybuilt.com/how-to-become-mentor-at-freshlybuilt/"));
                        startActivity(intent1);
                        break;
                    case 1:
                        Intent intent2 = new Intent();
                        intent2.setAction(Intent.ACTION_VIEW);
                        intent2.addCategory(Intent.CATEGORY_BROWSABLE);
                        intent2.setData(Uri.parse("https://freshlybuilt.com/internship-at-freshlybuilt/"));
                        startActivity(intent2);
                        break;

                }
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    public void share(){
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_SEND);
        intent.setType("text/plain");
        String link = "https://freshlybuilt.com/";
        intent.putExtra(Intent.EXTRA_TEXT,link);
        startActivity(Intent.createChooser(intent,"Share using"));
    }
    public void logout(){
        startActivity(new Intent(getApplicationContext(),Login.class));
    }

    public void contactUs(){
        final Item[] items = {new Item("   Voice Call",R.drawable.ic_phone_call),new Item("   Email",R.drawable.ic_gmail),new Item("   Whats app",R.drawable.ic_whatsapp),new Item("   Instagram",R.drawable.ic_instagram)};

        ListAdapter adapter = new ArrayAdapter<Item>(this,android.R.layout.select_dialog_item,android.R.id.text1,items){
            public View getView(int position, View convertView, ViewGroup parent) {
                //Use super class to create the View
                View v = super.getView(position, convertView, parent);
                TextView tv = v.findViewById(android.R.id.text1);

                //Put the image on the TextView
                tv.setCompoundDrawablesRelativeWithIntrinsicBounds(items[position].icon, 0, 0, 0);
                return v;
            }
        };
        new AlertDialog.Builder(this)
                .setTitle("Make a choice :")
                .setAdapter(adapter, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int item) {
                        switch (item){
                            case 0:
                                String Phone = "+9191491 89644";
                                Intent i = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + Phone));
                                if (ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                                    ActivityCompat.requestPermissions(MainActivity.this,new String[]{
                                            Manifest.permission.CALL_PHONE
                                    },1000);}
                                    startActivity(i);
                                break;
                            case 1:
                                String Email="info@freshlybuilt.com";
                                Intent emailIntent = new Intent(Intent.ACTION_SENDTO, Uri.parse("mailto:" + Email));
                                startActivity(Intent.createChooser(emailIntent, "Chooser Title"));
                                break;
                            case 2:
                                String number = "91491 89644";
                                Uri uri = Uri.parse("smsto:" + number);
                                Intent sendIntent = new Intent(Intent.ACTION_SENDTO, uri);
                                sendIntent.setPackage("com.whatsapp");
                                if (sendIntent.resolveActivity(getPackageManager()) != null) {
                                    startActivity(Intent.createChooser(sendIntent,""));
                                }else {
                                    Toast.makeText(MainActivity.this, "Whatsapp not installed! please install whatsapp", Toast.LENGTH_SHORT).show();
                                }
                                break;
                            case 3:
                                Uri ur = Uri.parse("https://instagram.com/freshlybuiltofficial?igshid=1x2c8s4w905b6");
                                Intent insta = new Intent(Intent.ACTION_VIEW, ur);

                                insta.setPackage("com.instagram.android");

                                try {
                                    startActivity(insta);
                                } catch (ActivityNotFoundException e) {
                                    Toast.makeText(MainActivity.this, "Instagram not installed! please install instagram", Toast.LENGTH_SHORT).show();
                                }
                                break;


                        }
                }}).show();
        }
    }

