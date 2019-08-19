package com.toolbar.demo;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class MainActivity extends AppCompatActivity implements Toolbar.OnMenuItemClickListener, MenuItem.OnMenuItemClickListener {

    private static final int MENU_ITEM_ID_ACTION_ADD = 1;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = findViewById(R.id.toolbar);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                loadMenu();
            }
        }, 200);
    }

    private void loadMenu() {
        toolbar.getMenu()
                .add(Menu.NONE, MENU_ITEM_ID_ACTION_ADD, Menu.NONE, "Add")
                .setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM);
        toolbar.setTitle(R.string.app_name);
        toolbar.inflateMenu(R.menu.main);
        toolbar.setOnMenuItemClickListener(this);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {
        int itemId = item.getItemId();
        switch (itemId) {
            case R.id.action_copy:
                copy();
                return true;
            case R.id.action_help:
                help();
                return true;
            case MENU_ITEM_ID_ACTION_ADD:
                manual();
                return true;
            default:
                return false;
        }
    }

    private void manual() {
        Toast.makeText(MainActivity.this, "Add action clicked", Toast.LENGTH_SHORT).show();
    }

    private void help() {

        Toast.makeText(MainActivity.this, "Help request sent", Toast.LENGTH_SHORT).show();

    }

    private void copy() {
        ClipboardManager clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
        if (clipboard != null) {
            ClipData clip = ClipData.newPlainText("simple text", ((TextView) findViewById(R.id.tv_info)).getText());
            clipboard.setPrimaryClip(clip);
            Toast.makeText(MainActivity.this, "Text copied", Toast.LENGTH_SHORT).show();

        }
    }
}
