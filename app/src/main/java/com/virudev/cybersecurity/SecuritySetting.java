package com.virudev.cybersecurity;
public class SecuritySetting {
    private String key;
    private String title;
    private String description;
    private boolean enabled;
    public SecuritySetting(String key, String title, String description, boolean enabled) {
        this.key = key;
        this.title = title;
        this.description = description;
        this.enabled = enabled;
    }
    public String
getKey() { return key; }
    public String getTitle() { return title; }
    public String getDescription() { return description; }
    public boolean isEnabled() { return enabled; }
    public void setEnabled(boolean enabled) { this.enabled = enabled; }
}
