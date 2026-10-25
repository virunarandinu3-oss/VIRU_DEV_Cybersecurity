package com.virudev.cybersecurity8
import android.app.AlertDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widgit.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import angroidx.recyclerview.widget.LinearLayoutManager;
import angroidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;
private static final String PREFS_NAME = "ViruDevPrefs";
private static final String DEFAULT_PASSWORD = "virudev2010";
public class MainActivity extends AppCompatActivity {
    private SharedPreferences prefs;
    private List<SecuritySetting> settingsList = new ArrayList<>();
    private SettingsAdapter adapter;
    @Override
    private void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        prefs = getSharedPreferences("ViruDevPrefs", Context.MODE_PRIVATE);
        showAuthDialog();
    }
    private void showAuthDialog() {
        final EditText editPassword = new EditText(this);
        editPassword.setHint("Enter Password (virudev2010)");
        new AlertDialog.Builder(this)
                .setTitle("VIRU DEV Cybersecurity")
                .setMessage("Enter password to access VIRU DEV Cybersecurity controls:")
                .setView(editPassword)
                .setCancelable(false)
                .setPositiveButton("Unlock", (dialog, which) -> {
                    String input = editPassword.getText().toString().trim();
                    String storedPassword = prefs.getString("APP_PASSWORD", "DEFAULT_PASSWORD");
                    if (input.equals(storedPassword) || input.equals("virudev2010")) {
                        Toast.makeText(MainActivity.this, "Access Granted", Toast.LENGTH_SHORT).show();
                        initUI();
                    } else {
                        Toast.makeText(MainActivity.this, "Incorrect Password!", Toast.LENGTH_SHORT).show();
                        showAuthDialog();
                    }
                })
                .show();
    }
    private void initUI() {
        RecyclerView recyclerView = findViewById(R.id.recyclerViewSettings);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        populateSettingsList();
        adapter = new SettingsAdapter(settingsList, (setting, isChecked) -> {
            prefs.edit().putBoolean(setting.getKey(), isChecked).apply();
            Toast.makeText(MainActivity.this, setting.getTitle() + (isChecked ? " Enabled" : " Disabled"), Toast.LENGTH_SHORT).show();
        });
        recyclerView.setAdapter(adapter);
    }
    private void populateSettingsList() {
        settingsList.clear();
        settingsList.add(new SecturitySetting("SETTING_1", "App Access Control", "Password verification before launching protected apps", prefs.getBoolean("SETTING_1", true)));
        settingsList.add(new SecturitySetting("SETTING_2", "Hotspot Switch Guard", "Password verification when toggling Hotspot", prefs.getBoolean("SETTING_2", true)));
        settingsList.add(new SecuritySetting("SETTING_3", "Camera Access Shield", "Permission check when Camera application opens", prefs.getBoolean("SETTING_3", true)));
        settingsList.add(new SecuritySetting("SETTING_4", "System Settings Lock", "Lock Android System Settings menu changes", prefs.getBoolean("SETTING_4", true)));
        settingsList.add(new SecuritySetting("SETTING_5", "Wi-Fi Security Guard", "Alert when connecting to open or untrusted networks", prefs.getBoolean("SETTING_5", true)));
        settingsList.add(new SecuritySetting("SETTING_6", "Notification Masking", "Hide confidential notification text on lock screen", prefs.getBoolean("SETTING_6", true)));
        settingsList.add(new SecuritySetting("SETTING_7", "Biometric Authentication", "Enable Fingerprint or Face unlock support", prefs.getBoolean("SETTING_7", true)));
        settingsList.add(new SecuritySetting("SETTING_8", "Dark Cyber Theme", "Enable Futuristic Dark Neon Cybernetic UI Theme", prefs.getBoolean("SETTING_8", true)));
    }
}
